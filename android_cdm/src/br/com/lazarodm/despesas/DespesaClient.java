package br.com.lazarodm.despesas;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Client para o serviço 'Despesas'
 */
public class DespesaClient {

    private final String URI = "/api/despesas/";
    private final String host;

    /**
     * @param host
     */
    public DespesaClient(String host) {
        this.host = host;
    }

    /**
     * Busca uma lista de despesas no servidor
     * 
     * @return JSONArray com o resultado obtido ou null
     */
    private JSONArray buscarDespesas() {
        
    	try {
    	
	    	final HttpClient clienteHttp= new DefaultHttpClient();
	        final HttpGet get = new HttpGet(host + URI);
	        final HttpResponse resposta = clienteHttp.execute(get);
	        
	        final int status = resposta.getStatusLine().getStatusCode();
	        
	        if (status == HttpStatus.SC_OK) {
	        	final HttpEntity entityResposta = resposta.getEntity();
	        	if(entityResposta != null){
	        		JSONArray jsonArray = new JSONArray(EntityUtils.toString(entityResposta));
	        		return jsonArray;
	        	}
	        }
        
    	} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        return null;
    }

    /**
     * Cria uma despesa no servidor
     * 
     * @param despesa
     * @return
     */
    public int criarDespesa(Despesa despesa) {
        
    	try {
	    	JSONObject jsonobject = despesa.toJsonObject();
			
	        final DefaultHttpClient clienteHttp = new DefaultHttpClient();
	        
	        final StringEntity stringentity = new StringEntity(jsonobject.toString(), "UTF-8");
	        stringentity.setContentType("application/json;charset=utf-8");
	        
	        final HttpPost post = new HttpPost(host + URI);
	        post.setHeader("Content-Type", "application/json;charset=utf-8");
	        post.setEntity(stringentity);
	        
	        final HttpResponse resposta = clienteHttp.execute(post);
	        
	        final int status = resposta.getStatusLine().getStatusCode();
	        
	        if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {
	        	
	        	final HttpEntity entityResposta = resposta.getEntity();
	        	
	        	if(entityResposta != null){
	        		
	        		final String conteudoResposta = EntityUtils.toString(entityResposta);
	        		final JSONObject respostaObject = new JSONObject(conteudoResposta); 
	        		return respostaObject.getInt("despesa");
	        	}
	        }
    	} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return -1;
    	
    }

    /**
     * Obtém uma lista de despesas do servidor;
     * @return ArrayList com as Despesas obtidas
     */
    public ArrayList<Despesa> obterDespesas() {
        final JSONArray jsonArray = buscarDespesas();
        if (jsonArray != null && jsonArray.length() > 0) {
            ArrayList<Despesa> despesas = new ArrayList<Despesa>(jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++){
            	try {
            		final Despesa despesa = Despesa.criarDeJSONObject(jsonArray.getJSONObject(i)); 
					despesas.add(despesa);
				} catch (JSONException e) {
					e.printStackTrace();
				}
            }
            return despesas;
        }
        return null;
    }
}
