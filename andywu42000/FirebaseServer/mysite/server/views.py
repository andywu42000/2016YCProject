from django.shortcuts import render, render_to_response
from django.http import HttpResponse, HttpResponseRedirect
from django.contrib import auth
from django.contrib.auth.decorators import login_required
from django.contrib.auth.forms import UserCreationForm

# Create your views here.
@login_required  
def index(request):
    return render_to_response('index.html',locals())
            
@login_required       
def member(request):
    return render(request, "member.html", {
            })

@login_required                 
def vendor(request):
	return render(request, "vendor.html", {
			})

@login_required                  
def coupon(request):
    return render(request, "coupon.html", {
            })
            
@login_required                
def lottery(request):
    return render(request, "lottery.html", {
            })
            
@login_required                  
def lovecode(request):
    return render(request, "lovecode.html", {
            })    

@login_required                  
def activity(request):
    return render(request, "activity.html", {
            })               
            
def login(request):
    if request.user.is_authenticated(): 
        return HttpResponseRedirect('/')

    username = request.POST.get('username', '')
    password = request.POST.get('password', '')
    
    user = auth.authenticate(username=username, password=password)

    if user is not None and user.is_active:
        auth.login(request, user)
        return HttpResponseRedirect('/')
    else:
        return render_to_response('login.html') 

@login_required        
def logout(request):
    auth.logout(request)
    return HttpResponseRedirect('/')
            
#def register(request):
 #   if request.method == 'POST':
  #      form = UserCreationForm(request.POST)
   #     if form.is_valid():
    #        user = form.save()
     #       return HttpResponseRedirect('/accounts/login/')
    #else:
     #   form = UserCreationForm()
    #return render_to_response('register.html',locals())