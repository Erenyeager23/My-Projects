from rest_framework import serializers
from .models import anime

class animeSerializer(serializers.ModelSerializer):
    class Meta():
        model=anime
        fields='__all__'
