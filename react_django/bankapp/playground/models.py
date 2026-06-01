from django.db import models

class Account(models.Model):
    balance = models.FloatField(default=0)

    def __str__(self):
        return f"Balance: {self.balance}"