package br.com.lazarodm.despesas;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe que representa uma Despesa
 * 
 * @author sysnetwork
 */
public class Despesa implements Serializable {

    private static final long serialVersionUID = 1L;
    private String categoria;
    private String data;
    private String descricao;
    private double valor;

    public static Despesa criarDeJSONObject(JSONObject jsonobject) throws JSONException {
        Despesa despesa = new Despesa();
        despesa.setCategoria(jsonobject.getString("categoria"));
        despesa.setDescricao(jsonobject.getString("descricao"));
        despesa.setData(jsonobject.getString("data"));
        despesa.setValor(jsonobject.getDouble("valor"));
        return despesa;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }

    public void setValor(double valor) {
    	this.valor = valor;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categoria", categoria);
        jsonObject.put("descricao", descricao);
        jsonObject.put("valor", valor);
        jsonObject.put("data", data);
        return jsonObject;
    }
}
