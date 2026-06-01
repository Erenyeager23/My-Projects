from django.http import HttpResponse
from django.shortcuts import render
from .models import Account

def home(request):
    account=Account.objects.first()
    if account is None:
        account=Account.objects.create(balance=0)
    balance=""
    message=""
    if request.method=='POST':
        if 'deposit' in request.POST:
            amount=float(request.POST['amount'])
            account.balance+=amount
            account.save()
            message='money deposited'
        elif 'withdraw' in request.POST:
            amount=float(request.POST['amount'])
            if account.balance >= amount:
                account.balance -= amount
                account.save()
                message = "Money Withdrawn"
            else:
                message = "Insufficient Balance"
        elif 'show' in request.POST:
            balance=account.balance
    return render(request,"home.html",{'message':message,'balance':balance})

