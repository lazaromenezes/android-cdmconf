from django.conf.urls import patterns, include, url
from django.contrib import admin
from web_cdm.despesas.api import urls as urlsAPIDespesas
from web_cdm.views import ListaDespesa
from django.conf import settings

# Uncomment the next two lines to enable the admin:
admin.autodiscover()


urlpatterns = patterns('',
    url(r'^admin/', include(admin.site.urls)),
    url(r'^$', ListaDespesa.as_view()),
    
) + urlsAPIDespesas

if settings.DEBUG:
    urlpatterns += patterns('',
        (r'^media/(?P<path>.*)$', 'django.views.static.serve', {'document_root' : settings.MEDIA_ROOT}),
    )
