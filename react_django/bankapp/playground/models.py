from django.db import models

class Account(models.Model):
    username = models.CharField(max_length=50, unique=True,null=False)
    password = models.CharField(max_length=50,null=False)
    balance = models.FloatField(default=0)

    