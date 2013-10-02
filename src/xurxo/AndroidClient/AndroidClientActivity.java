package xurxo.AndroidClient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Activity principal de la aplicación
 * @author Eljo
 *
 */
public class AndroidClientActivity extends ListActivity {
	ListView _lv;
	//Getter a leer de cada objeto a rellenar en el ListView
	final String[] FROM = { "GetCodCliente", "GetRazonSocial","GetDireccion","GetTelefono" }; //
	
	//Ids de los controles del ItemListView.xml que hay que rellenar
	final int[] TO = { R.id.CodCliente, R.id.RazonSocial, R.id.Direccion,R.id.Telefono };
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        _lv = this.getListView();
      
        //invocamos la tarea asincrona de llamar al web service y rellenar el LitView
        new HttpClientCustomer().execute(this);
    }
    
    /**
     * Tarea asincrona que inicia la invocación del web service, convierte el JSONArray en un ArrayList y asigna 
     * el Adapter al ListView
     * @author Eljo
     *
     */
    public class HttpClientCustomer extends AsyncTask<Object, Object, Object>{
    	/**
    	 * Se invoca cuando la tarea asincrona es executada
    	 */
    	@Override
    	protected Object doInBackground(Object... arg0) {

    			try
    			{
    				//inicio de la invocación del web service
	    			JSONArray Clientes = HttpClientHelper.GET("getclientes");
	    			
	    			//Conversión en ArrayList
	    			List<Object> CustomerList= new ArrayList<Object> ();
	    			
	    			for (int i = 0; i < Clientes.length(); ++i) 
	    			{      
	    			  
	    			  CustomerList.add(new Customer(
	    					  Clientes.getJSONObject(i).getString("CodCliente")=="null" ? "" : Clientes.getJSONObject(i).getString("CodCliente") ,
	    					  Clientes.getJSONObject(i).getString("RazonSocial")=="null" ? "" : Clientes.getJSONObject(i).getString("RazonSocial"),
	    					  Clientes.getJSONObject(i).getString("Direccion")=="null" ? "" : Clientes.getJSONObject(i).getString("Direccion"),
	    					  Clientes.getJSONObject(i).getString("Telefono")=="null" ? "" : Clientes.getJSONObject(i).getString("Telefono")));
	    			}     
	    			
	    			//Devolvemos el ArrayList
	    			return CustomerList; 
    			}
    			catch (Exception ex)
    			{
    				//Como desde este metodo no se puede lanzar una excepción, lo que devolvemos es la propia
    				//Excepcion
    				return ex;
    			}
    			
    		}
    	
    		/**
    		 * Se invoca cuando la tarea asincrona ha terminado
    		 */
    	 	protected void onPostExecute(Object result) 
    	 	{    
    	 	     //Si el resultado es un ArrayList... no hay error
    	 		 if (result instanceof ArrayList) 
    	 		 {
    	 			//asignamos el Adapter 
					List<Object> L = (List<Object>) result;
    	 			_lv.setAdapter(new ObjectAdapter(_lv.getContext(),R.layout.itemlistview, L,FROM,TO));
    	             // ... process result ...
    	         } 
    	 		 else 
    	 		 {
    	 			 //Si el resultado es una Excepción..hay error
    	 			 if (result instanceof Exception) 
    	 			 {
    	 				 //mostramos el error en pantalla
	    	             Exception ex = (Exception) result;
	    	             Toast.makeText(_lv.getContext(), "Error al crear la actividad. Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
    	             // ... process error ...
    	 			 } 
    	         };     
    	 	}
    	

    }
}
