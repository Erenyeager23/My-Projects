from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import anime
from .serializer import animeSerializer
from rest_framework import status

@api_view(['GET'])
def get_anime(request):
    a=anime.objects.all()
    serialdata=animeSerializer(a,many=True).data
    return Response(serialdata)

@api_view(['POST'])
def post_anime(request):
    data=request.data
    serialdata=animeSerializer(data=data)
    if serialdata.is_valid():
        serialdata.save()
        return Response(serialdata.data,status=status.HTTP_201_CREATED)
    return Response(serialdata.errors,status=status.HTTP_400_BAD_REQUEST)
