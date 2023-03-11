import random
import time

import scrapy
import pymysql
import movieInfoSpider.settings
from movieInfoSpider.items import MovieinfospiderItem


class InfospiderSpider(scrapy.Spider):
    name = 'InfoSpider'
    # allowed_domains = ['example.cn']
    # start_urls = ['http://example.cn/']
    last = 9000
    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36",
    }

    def start_requests(self):
        self.db = pymysql.connect(host='127.0.0.1', port=3306, db='综合项目', user='root', passwd='20140820xss', charset='utf8')
        self.cursor = self.db.cursor()
        start = 0
        N = 20
        for i in range(start, N):
            results = self.getId(i)
            for rows in results:
                time.sleep(random.randint(3, 10))
                yield scrapy.Request(url=f"https://movie.douban.com/subject/{rows[0]}/", headers=self.headers, callback=self.parse, meta={"id": rows[0]})


    def parse(self, response):
        XPath = ['/html/head/title//text()', '//*[@id="info"]/text()[last()-1]', '//*[@id="interest_sectl"]/div[1]/div[2]/strong//text()', '//*[@id="interest_sectl"]/div[1]/div[2]/div/div[2]/a/span//text()',
                 '//*[@id="link-report-intra"]/span//text()', '//*[@id="info"]/span[3]/span[2]//text()', '//*[@id="info"]/span[1]/span[2]/a//text()', '//*[@id="info"]/span[5]//text()', '//*[@id="interest_sectl"]/div[1]/div[3]/div[5]/span[2]//text()',
                 '//*[@id="interest_sectl"]/div[1]/div[3]/div[4]/span[2]//text()', '//*[@id="interest_sectl"]/div[1]/div[3]/div[3]/span[2]//text()',
                 '//*[@id="interest_sectl"]/div[1]/div[3]/div[2]/span[2]//text()', '//*[@id="interest_sectl"]/div[1]/div[3]/div[1]/span[2]//text()']
        keys = ["title", "IMDb", "rate", "rate_num", "content", "actors", "director", "type", "one_star", "two_star",
                "three_star", "four_star", "five_star"]
        item = MovieinfospiderItem()
        item['id'] = response.meta['id']
        for i in range(13):
            try:
                item[keys[i]] = response.xpath(XPath[i]).extract_first().strip()
            except Exception as e:
                print(i, ">>>>>>", i)
        if response.xpath('//*[@id="link-report-intra"]/span[2]//text()').extract() and "".join([x.strip() for x in response.xpath('//*[@id="link-report-intra"]/span[2]//text()').extract()]).replace("\n", "") != "©豆瓣":
            item["content"] = "".join([x.strip() for x in response.xpath('//*[@id="link-report-intra"]/span[2]//text()').extract()]).replace("\n", "")[:700]
        else:
            item["content"] = "".join([x.strip() for x in response.xpath(XPath[4]).extract()]).replace("\n", "")[:700]
        try:
            item['IMDb'] = response.xpath('//*[@id="info"]/text()').re(r"tt\d*")[0]
        except IndexError:
            pass
        for x in ["one_star", "two_star",
                "three_star", "four_star", "five_star"]:
            item[x] = item[x][:-1]
        item['title'] = item['title'].strip()[:-5]
        yield item


    def getId(self, page):
        sql = f"select distinct * from doubanid limit 50 offset {page*50+self.last}"
        try:
            self.cursor.execute(sql)
            results = self.cursor.fetchall()
            return [row for row in results]
        except Exception as e:
            print("error:", e)
            return None
