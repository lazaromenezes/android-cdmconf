package br.com.lazarodm.despesas;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Activity principal
 */
public class Principal extends Activity {

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_principal);
        
        final Resources recursos = getResources();
        final String[] categorias = recursos.getStringArray(R.array.categorias);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerCategoria);
        spinner.setAdapter(adapter);
    }

    /**
     * @param view
     */
    public void salvarDespesa(View view) {
        
    	final EditText editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);
        final EditText editTextValor = (EditText) findViewById(R.id.editTextValor);
        final Spinner spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        
        final String descricao = editTextDescricao.getText().toString();
        final String valorString = editTextValor.getText().toString();
        final double valor = valorString.equals("") ? 0D : Double.valueOf(valorString);
        final String categoria = spinnerCategoria.getSelectedItem().toString();
        
        if (descricao.equals("") || valor == 0D) {
            Toast.makeText(this, getString(R.string.mensagem_validacao), Toast.LENGTH_LONG).show();
            return;
        } else {
            final Despesa despesa = new Despesa();
            despesa.setCategoria(categoria);
            despesa.setDescricao(descricao);
            despesa.setValor(valor);
            
            new EnviarDespesaAsyncTask(this).execute(despesa);
            
            editTextDescricao.setText("");
            editTextValor.setText("");
            spinnerCategoria.setSelection(0);
            
            return;
        }
    }

    /**
     * @param view
     */
    public void visualizarDespesas(View view) {
        startActivity(new Intent(this, ListaDespesa.class));
    }
}
