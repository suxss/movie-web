import json
import time
import random

import requests
import scrapy
from movieSpider.items import DouBanIdItem


class IdspiderSpider(scrapy.Spider):
    name = 'idSpider'
    # start_urls = ['http://front-gateway.mtime.com/mtime-search/search/unionSearch2/']
    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36",
    }
    N = 5
    total_page = 10000
    proxy = None
    appKey = 884005022424518656
    appSecret = "APSrTY8O"
    start = 9400

    def start_requests(self):
        # print(json.dumps(data))
        url = f"https://movie.douban.com/j/new_search_subjects?sort=T&range=0,20&tags=%E7%94%B5%E5%BD%B1&start={self.start}"
        yield scrapy.Request(url=url, dont_filter=True, headers=self.headers, callback=self.parse, meta={'n': 1, "page": self.start//20 + 1, "try": 1})

    def parse(self, response):
        n = response.meta['n']
        t = response.meta["try"]
        page = response.meta['page']
        html = response.text
        j = json.loads(html)
        # print(j)
        item = DouBanIdItem()
        if "data" not in j:
            print(j)
            print("getIP\ngetIP >>>>>>")
            time.sleep(3)
            self.proxy = fetch_proxy_ip()
            url = f"https://movie.douban.com/j/new_search_subjects?sort=T&range=0,20&tags=%E7%94%B5%E5%BD%B1&start={page * 20 - 19}"
            meta = {'n': n + 1, "page": page + 1, "try": t+1}
            if self.proxy:
                # meta["Proxy"] = self.proxy
                print("Use Proxy >>>>> " + self.proxy)
            if t < 5:
                yield scrapy.Request(url=url, dont_filter=True, headers={"User-Agent": self.getUserAgent()},
                                 callback=self.parse, meta=meta)
        else:

            for x in j['data']:
                # try:
                item['name'] = x['title']
                item['id'] = x['id']
                item['cover'] = x.get("cover", "")
                item['rate'] = x['rate']
                yield item

        n += 1

        if n % self.N == 0:
            print("getIP\ngetIP >>>>>>")
            # self.proxy = fetch_proxy_ip()

        if (page-1)*20+1 < self.total_page:
            meta = {'n': n+1, "page": page+1, "try": 1}
            url = f"https://movie.douban.com/j/new_search_subjects?sort=T&range=0,20&tags=%E7%94%B5%E5%BD%B1&start={page*20+1}"
            if self.proxy:
                # meta["Proxy"] = self.proxy
                print("Use Proxy >>>>> "+self.proxy)
            time.sleep(random.randint(5, 20))
            yield scrapy.Request(url=url, dont_filter=True, headers={"User-Agent": self.getUserAgent()}, callback=self.parse, meta=meta)

    def setIP(self, response):
        print("setIP\nsetIP >>>>>>")
        html = response.text
        j = json.loads(html)
        # if j['code'] == 200:
        #     self.proxy = ":".join(["http", "//"+j["data"][0]["ip"], str(j["data"][0]["port"])])


    def getUserAgent(self):
        l = ["Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36",
             "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
             'Opera/8.0 (Windows NT 5.1; U; en)',
             'Mozilla/5.0 (Windows NT 5.1; U; en; rv:1.8.1) Gecko/20061208 Firefox/2.0.0 Opera 9.50',
             'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; en) Opera 9.50',
             'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0',
             'Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10',
             'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2 ',
             'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36',
             'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11',
             'Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.133 Safari/534.16',
             'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36',
             'Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko',
             'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11',
             'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER',
             'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)',
             'Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0',
             'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0) ',
             ]
        user_agent = random.choice(l)  # 随机抽取对象
        return user_agent


def fetch_proxy_ip():
    return None
    url = "https://api.xiaoxiangdaili.com/ip/get?appKey=884248504586096640&appSecret=dxgaIcU1&cnt=&wt=json"
    req = requests.get(url)
    j = req.json()
    if j["code"] == 200:
        if len(j.get("data", [])) > 0:
            return "http://" + j["data"][0]["ip"] + ":" + str(j["data"][0]["port"])