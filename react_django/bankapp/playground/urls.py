from django.urls import path
from .views import *

urlpatterns=[
    path('', login_view, name='login'),
    path('balance/', balance, name='balances'),
    path('deposit/', deposit, name='deposits'),
    path('withdraw/', withdraw, name='withdraw'),
    path('logout/', logout_view, name='logout'),
]