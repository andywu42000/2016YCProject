# -*- coding: utf-8 -*-

from scrapy.spiders import Spider  
from scrapy.selector import Selector  
from ycproject.items import WebItem
from bs4 import BeautifulSoup
import codecs, json

class WebSpider(Spider):  
    name = "web"
    start_urls = [  
        "http://harvest365.org/posts/732"  
    ]

    def parse(self, response):
        sel = Selector(response)  
        items = []
        sites = sel.xpath('//div[@id="jq-no-mr"]/div') 
        for site in sites:  
            item = WebItem()
            title = site.xpath('//strong/text()').extract()
            item['title'] = title
            items.append(item) 
        return items