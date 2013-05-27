'''
Created on 24/02/2013

@author: sysnetwork
'''
from django.views.generic.list import ListView
from web_cdm.despesas.models import Despesa

class ListaDespesa(ListView):
    
    model = Despesa
    template_name = "index.html"
    context_object_name = "despesas"