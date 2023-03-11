import json
import time

import scrapy
from movieSpider.items import MoviespiderItem


class IdspiderSpider(scrapy.Spider):
    name = 'idSpider'
    # start_urls = ['http://front-gateway.mtime.com/mtime-search/search/unionSearch2/']
    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1623.0 Safari/537.36",
    }

    def start_requests(self):
        # print(json.dumps(data))
        url = "https://api.themoviedb.org/3/discover/movie?api_key=0ab4e579088c429e96ac33c44957458f&language=zh-CN&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate"
        yield scrapy.Request(url=url, dont_filter=True, headers=self.headers, callback=self.parse, meta={'try': 1})

    def parse(self, response):
        try_times = response.meta['try']
        html = response.text
        j = json.loads(html)
        # print(j)
        page = j['page']
        url = f"https://api.themoviedb.org/3/discover/movie?api_key=0ab4e579088c429e96ac33c44957458f&language=zh-CN&sort_by=popularity.desc&include_adult=false&include_video=false&page={page+1}&with_watch_monetization_types=flatrate"
        item = MoviespiderItem()
        for x in j['results']:
            try:
                item['name'] = x['title']
                item['id'] = x['id']
                item['release_date'] = x.get("release_date", "")
                item['overview'] = x['overview']
                item['poster_path'] = x['poster_path']
                item['popularity'] = x['popularity']
                item['vote_average'] = x['vote_average']
                item['vote_count'] = x['vote_count']
                yield item
            except KeyError:
                print(x)
                time.sleep(20)
                url1 = f"https://api.themoviedb.org/3/discover/movie?api_key=0ab4e579088c429e96ac33c44957458f&language=zh-CN&sort_by=popularity.desc&include_adult=false&include_video=false&page={page}&with_watch_monetization_types=flatrate"
                if try_times < 3:
                    yield scrapy.Request(url=url1, dont_filter=True, headers=self.headers, callback=self.parse, meta={'try': try_times+1})
        if page < j['total_pages']:
            yield scrapy.Request(url=url, dont_filter=True, headers=self.headers, callback=self.parse, meta={'try': 1})
