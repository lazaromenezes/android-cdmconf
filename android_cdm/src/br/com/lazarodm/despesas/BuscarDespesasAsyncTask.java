package br.com.lazarodm.despesas;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * AsyncTask para buscar uma lista de despesas no servidor
 */
public class BuscarDespesasAsyncTask extends AsyncTask<Void, Integer, ArrayList<Despesa>> {
	
    private class ListaDespesaAdapter extends ArrayAdapter<Despesa> {

        private Activity context;

        public View getView(int position, View convertView, ViewGroup parent) {
            
        	View view = convertView;
            
        	if (view == null) {
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService("layout_inflater"); 
        		view = inflater.inflate(R.layout.despesa_list_item, null);
            }
            TextView textViewDescricao = (TextView) view.findViewById(R.id.textViewDescricao);
            TextView textViewValor = (TextView) view.findViewById(R.id.textViewValor);
            Despesa despesa = (Despesa) getItem(position);
            textViewDescricao.setText(despesa.getDescricao());
            textViewValor.setText(String.valueOf(despesa.getValor()));
            return view;
        }

        public ListaDespesaAdapter(Activity activity, ArrayList<Despesa> despesas) {
            super(activity, R.layout.despesa_list_item, despesas);
            context = activity;
        }
    }

    private final Activity activity;

    /**
     * @param activity
     */
    public BuscarDespesasAsyncTask(Activity activity) {
        this.activity = activity;
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    protected ArrayList<Despesa> doInBackground(Void ...voids) {
        return new DespesaClient(activity.getString(R.string.host)).obterDespesas();
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    protected void onPostExecute(ArrayList<Despesa> despesas) {
        if (despesas != null) {
            ListaDespesaAdapter listaDespesaAdapter = new ListaDespesaAdapter(activity, despesas);
            final ListView listViewDespesas = (ListView) activity.findViewById(R.id.listViewDespesas);
            listViewDespesas.setAdapter(listaDespesaAdapter);
        }
    }
}
