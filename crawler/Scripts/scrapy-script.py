#!d:\andy\documents\github\2016ycproject\crawler\scripts\python.exe
# EASY-INSTALL-ENTRY-SCRIPT: 'scrapy==1.1.2','console_scripts','scrapy'
__requires__ = 'scrapy==1.1.2'
import sys
from pkg_resources import load_entry_point

if __name__ == '__main__':
    sys.exit(
        load_entry_point('scrapy==1.1.2', 'console_scripts', 'scrapy')()
    )
