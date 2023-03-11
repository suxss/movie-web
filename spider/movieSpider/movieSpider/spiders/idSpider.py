import json

import scrapy


class IdspiderSpider(scrapy.Spider):
    name = 'idSpider'
    allowed_domains = ['http://front-gateway.mtime.com/mtime-search/search/unionSearch2']
    start_urls = ['http://http://front-gateway.mtime.com/mtime-search/search/unionSearch2/']
    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1623.0 Safari/537.36",
        'Referer': "http://film.mtime.com/"
    }

    def start_requests(self):
        data = {
            "keyword": '',
            "pageIndex": 1,
            "pageSize": 20,
            "searchType": 0,
            "locationId": 290,
            "genreTypes": '',
            "area": '',
            "year": '',
        }
        for url in self.start_urls:
            yield scrapy.Request(url=url, dont_filter=True, headers=self.headers, callback=self.parse, meta={'page': 1}, method="POST", body=json.dumps(data))

    def parse(self, response):
        page = response.meta['page']
        html = response.text
        j = json.loads(html)
        item = MoviespiderItem()
        for x in j['data']['movies']:
            item['name'] = x['name']
            item['id'] = x['id']
            yield item

        data = {
            "keyword": '',
            "pageIndex": page+1,
            "pageSize": 20,
            "searchType": 0,
            "locationId": 290,
            "genreTypes": '',
            "area": '',
            "year": '',
        }

        yield scrapy.Request(url=url, dont_filter=True, headers=self.headers, callback=self.parse, meta={'page': page+1}, method="POST", body=json.dumps(data))
