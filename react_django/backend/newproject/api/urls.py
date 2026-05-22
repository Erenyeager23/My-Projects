from django.urls import path
from .views import get_anime,post_anime
urlpatterns=[
    path('anime/',get_anime,name='get_anime'),
    path('anime/create/',post_anime,name='post_anime')
]