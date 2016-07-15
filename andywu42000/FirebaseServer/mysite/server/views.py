from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def add_mark(request):
	return render(request, "addMark.html", {
			})
			
def all_mark(request):
	return render(request, "allMark.html", {
			})