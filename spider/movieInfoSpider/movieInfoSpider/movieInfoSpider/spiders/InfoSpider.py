import scrapy


class InfospiderSpider(scrapy.Spider):
    name = 'InfoSpider'
    allowed_domains = ['example.cn']
    start_urls = ['http://example.cn/']

    def parse(self, response):
        pass
