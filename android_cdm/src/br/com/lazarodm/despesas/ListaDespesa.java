package br.com.lazarodm.despesas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author sysnetwork
 *
 */
public class ListaDespesa extends Activity implements OnItemClickListener {

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    protected void onCreate(Bundle bundle) {
        
    	super.onCreate(bundle);
        setContentView(R.layout.activity_listar_despesas);
        
        final ListView listViewDespesas = (ListView) findViewById(R.id.listViewDespesas); 
        listViewDespesas.setOnItemClickListener(this);
        
        new BuscarDespesasAsyncTask(this).execute();
    }

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Despesa despesa = (Despesa) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, VerDespesa.class);
        intent.putExtra("despesa", despesa);
        startActivity(intent);
	}

}
