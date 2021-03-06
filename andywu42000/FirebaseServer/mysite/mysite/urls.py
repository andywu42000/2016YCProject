"""mysite URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.8/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import include, url
from django.contrib import admin

from server.views import *;

urlpatterns = [
    url(r'^admin/', include(admin.site.urls)),
    url(r'^$', index),
    url(r'^member/$', member),
	url(r'^vendor/$', vendor),
    url(r'^coupon/$', coupon),
    url(r'^lottery/$', lottery),
    url(r'^lovecode/$', lovecode),
    url(r'^activity/$', activity),
    url(r'^accounts/login/$', login),
    url(r'^accounts/logout/$', logout),
    url(r'memberVendor/$', memberVendor),
    #url(r'^accounts/register/$',register),
]
