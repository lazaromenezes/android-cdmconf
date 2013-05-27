package br.com.lazarodm.despesas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author sysnetwork
 *
 */
public class VerDespesa extends Activity {

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    protected void onCreate(Bundle bundle) {
        
    	super.onCreate(bundle);
        setContentView(R.layout.activity_despesa);
        
        TextView textViewDescricao = (TextView) findViewById(R.id.textViewDescricao);
        TextView textViewData = (TextView) findViewById(R.id.textViewData);
        TextView textViewCategoria = (TextView) findViewById(R.id.textViewCategoria);
        TextView textViewValor = (TextView) findViewById(R.id.textViewValor);
        
        final Despesa despesa = (Despesa)getIntent().getSerializableExtra("despesa");
        
        textViewDescricao.setText(despesa.getDescricao());
        textViewData.setText(despesa.getData());
        textViewCategoria.setText(despesa.getCategoria());
        textViewValor.setText(String.valueOf(despesa.getValor()));
    }
}
