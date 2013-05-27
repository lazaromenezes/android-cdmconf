#coding:utf-8
from django.db import models

class Despesa(models.Model):
    """
    Classe que representa uma despesa
    """
    
    categoria = models.CharField(max_length=50)
    descricao = models.CharField(max_length=100)
    valor = models.DecimalField(max_digits=13, decimal_places=2)
    data = models.DateField()
    
    def __unicode__(self):
        
        return self.descricao
    
