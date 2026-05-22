from django.db import models

class anime(models.Model):
    name=models.CharField(max_length=50)
    year=models.IntegerField()
    def __str__(self):
        return self.name
