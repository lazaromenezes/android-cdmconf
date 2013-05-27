package br.com.lazarodm.despesas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * AsyncTask para o envio de uma Despesa para o servidor 
 * 
 */
public class EnviarDespesaAsyncTask extends AsyncTask<Despesa, Integer, Integer> {

    private final Context context;

    public EnviarDespesaAsyncTask(Context context) {
        this.context = context;
    }

    /* 
	 * Executará os comandos em background
     */
    public Integer doInBackground(Despesa ... despesas) {
        DespesaClient despesaclient = new DespesaClient(context.getString(R.string.host));
        if (despesas.length >= 1) {
            return Integer.valueOf(despesaclient.criarDespesa(despesas[0]));
        } else {
            return Integer.valueOf(0);
        }
    }

    /* 
     * Executado ao finalizar o método 'doInBackground'
     */
    public void onPostExecute(Integer numeroDespesa) {
        final String mensagem = String.format(context.getString(R.string.despesa_criada), numeroDespesa); 
    	Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

}