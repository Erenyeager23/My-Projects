from django.shortcuts import render, redirect
from .models import Account




def login_view(request):
    account = Account.objects.first()

    if account is None:
        Account.objects.create(
            username="saikiran",
            password="12345678",
            balance=0
        )
    message = ""

    if request.method == "POST":

        username = request.POST["username"]
        password = request.POST["password"]

        try:

            account = Account.objects.get(
                username=username,
                password=password
            )

            request.session["account_id"] = account.id

            return redirect("balance")

        except Account.DoesNotExist:

            message = "Invalid Login"

    return render(
        request,
        "home.html",
        {"message": message}
    )

def balance(request):

    account_id = request.session.get("account_id")

    if not account_id:
        return redirect("login")

    account = Account.objects.get(id=account_id)

    return render(
        request,
        "balance.html",
        {"balance": account.balance}
    )

def deposit(request):

    account_id = request.session.get("account_id")

    if not account_id:
        return redirect("login")

    account = Account.objects.get(id=account_id)

    message = ""

    if request.method == "POST":

        amount = float(request.POST["amount"])

        account.balance += amount
        account.save()

        message = "Money Deposited"

    return render(
        request,
        "deposit.html",
        {"message": message}
    )

def withdraw(request):

    account_id = request.session.get("account_id")

    if not account_id:
        return redirect("login")

    account = Account.objects.get(id=account_id)

    message = ""

    if request.method == "POST":

        amount = float(request.POST["amount"])

        if account.balance >= amount:

            account.balance -= amount

            account.save()

            message = "Money Withdrawn"

        else:

            message = "Insufficient Balance"

    return render(
        request,
        "withdraw.html",
        {"message": message}
    )

def logout_view(request):
    request.session.flush()
    return redirect('login')