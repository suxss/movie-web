# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class MovieinfospiderItem(scrapy.Item):
    # define the fields for your item here like:
    title = scrapy.Field()
    id = scrapy.Field()
    IMDb = scrapy.Field()
    rate = scrapy.Field()
    rate_num = scrapy.Field()
    content = scrapy.Field()
    actors = scrapy.Field()
    director = scrapy.Field()
    type = scrapy.Field()
    one_star = scrapy.Field()
    two_star = scrapy.Field()
    three_star = scrapy.Field()
    four_star = scrapy.Field()
    five_star = scrapy.Field()
    # pass
