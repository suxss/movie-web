# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class MoviespiderItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    name = scrapy.Field()
    id = scrapy.Field()
    release_date = scrapy.Field()
    overview = scrapy.Field()
    poster_path = scrapy.Field()
    popularity = scrapy.Field()
    vote_average = scrapy.Field()
    vote_count = scrapy.Field()
    # pass

class DouBanIdItem(scrapy.Item):
    id = scrapy.Field()
    name = scrapy.Field()
    cover = scrapy.Field()
    rate = scrapy.Field()
