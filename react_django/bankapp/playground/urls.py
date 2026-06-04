from django.urls import path
from .views import *

urlpatterns=[
    path('', login_view, name='login'),
    path('balances344/', balance, name='balance'),
    path('deposit/', deposit, name='deposits'),
    path('withdraw/', withdraw, name='withdraw'),
    path('logout/', logout_view, name='logout'),
]