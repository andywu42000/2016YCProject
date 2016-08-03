from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(request):
    return render(request, "index.html", {
            })
            
def member(request):
    return render(request, "member.html", {
            })
            
def vendor(request):
	return render(request, "vendor.html", {
			})
			
def all_mark(request):
	return render(request, "allMark.html", {
			})
            
def coupon_manage(request):
    return render(request, "couponManage.html", {
            })
            
def lottery(request):
    return render(request, "lottery.html", {
            })
            