package xurxo.AndroidClient;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapter estándar para rellenar un listview
 * @author Eljo
 *
 */
public class ObjectAdapter extends ArrayAdapter<Object>
{
    private  List<Object> _items;
    private  String[] _from;
    private  int[] _to;
    private static final String TAG = "ListObjectAdapter";
    private int _textViewResourceId;

    /**
     * Contructor del Adapter
     * @param context contexto donde se aloja el ListView, por ejemplo un Activity
     * @param textViewResourceId Id del itemListview
     * @param items ArraList con los objetos a rellenar en el ListView
     * @param from Array de String con los nombres de los getter que hay que leer de cada objeto del ArraList
     * @param to Array de int con los Ids de los controles que hay que rellenar de cada item del ListView
     */
    public ObjectAdapter(Context context, int textViewResourceId, List<Object> items, String[] from, int[] to) 
    {
            super(context, textViewResourceId,items);
            
            //Almacenamos los parametros
            _textViewResourceId = textViewResourceId;
            _items = items;
            _from = from;
            _to = to;
    }


	/**
     * Es invocado para cada elemento a crear en el ListView. Inyecta el diseño del xml y rellena los controles
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
            View v = convertView;
            if (v == null) 
            {
            	//creamos el inflater para inyectar xml
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                
                //inyectamos
                v = vi.inflate(_textViewResourceId, null);
            }
            
            //recuperamos del ArrayList el objeto a rellenar segun la posición
            Object o = _items.get(position);
            
            if (o != null) 
            {   
            	
            	try       	
            	{
            		//recorremos el array que contiene los getter a leer y para cada uno
            		//leemos y asignamos su valor en su homologo en el array de ids de controles a rellenar
            		for (int i = 0; i < _from.length; ++i) 
            		{               		  
	            		TextView txt =(TextView) v.findViewById (_to[i]);
	            		txt.setText((String) o.getClass().getMethod(_from[i]).invoke(o, (Object[]) null));
            		}
            	}
            	catch (Exception ex)
            	{
            		Log.e(TAG, "Error al leer la informacion " + ex.getMessage() );
            	}
    		}
            
            return v;
    }
}