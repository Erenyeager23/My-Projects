from django.urls import path
from .views import *

urlpatterns=[
    path('', login_view, name='login'),
    path('balance/', balance, name='balance'),
    path('deposit/', deposit, name='deposit'),
    path('withdraw/', withdraw, name='withdraw'),
    path('logout/', logout_view, name='logout'),
]