from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(request):
    return render(request, "index.html", {
            })
            
def member(request):
    return render(request, "member.html", {
            })
            
def add_mark(request):
	return render(request, "addMark.html", {
			})
			
def all_mark(request):
	return render(request, "allMark.html", {
			})
            
def coupon_manage(request):
    return render(request, "couponManage.html", {
            })
            