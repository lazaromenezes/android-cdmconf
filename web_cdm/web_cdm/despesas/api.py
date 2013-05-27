#coding:utf-8

from django.conf.urls.defaults import patterns, url
from piston.handler import BaseHandler
from piston.resource import Resource
from piston.utils import rc
from web_cdm.despesas.models import Despesa
from datetime import date

class DespesaHandler(BaseHandler):
    
    allowed_methods = ("GET", "POST")
    
    fields = ("id", "categoria", "descricao", "data", "valor")
    
    def read(self, request):
        """
        Método para atender os requests via GET
        """
        try:
            return Despesa.objects.filter(data = date.today())
        except:
            import sys
            print sys.exc_info()
            return rc.BAD_REQUEST
        
    def create(self, request):
        """
        Método para atender os requests via POST
        """
        
        try:
            dados = request.data
            
            data = date.today()
            
            despesa = Despesa.objects.create(data = data, **dados)
            
            return {"despesa":despesa.id}
            
        except:
            import sys
            print sys.exc_info()
            return rc.BAD_REQUEST
        
despesaResource = Resource(DespesaHandler)

urls = patterns('',
    url(r'^api/despesas/$', despesaResource, {'emitter_format' : 'json'}),
)