package xurxo.AndroidClient;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.util.Log;

/**
 * Clase encargada de comunicarse con el servicio web y devolver eel resultado en JSONArray
 * @author Eljo
 *
 */
public class HttpClientHelper {
	
	//Tag para identificar en el Log
	private static final String TAG = "HttpClientHelper";
	
	//Función que realiza una acción de tipo GET contra el servicio web	
	public static JSONArray GET(String OperationName) throws Exception
	{
		BufferedReader reader =null;
		StringBuilder sb =null;
		try 
		{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://88.12.9.163:21000/WCFServiceJSON/Service.svc/" + OperationName);                  
		HttpResponse response = httpClient.execute(request);          
		HttpEntity responseEntity = response.getEntity();          
		
		InputStream stream = responseEntity.getContent();    
		
		reader = new BufferedReader(new InputStreamReader(stream));
		sb = new StringBuilder();
		
			String line = reader.readLine();
			
			while (line != null) 
			{
				sb.append(line);
				line = reader.readLine();
			}
		}
		catch (Exception ex) 
		{
			//Procesamos el error
			Log.e(TAG, "Error al crear la actividad. Error: " + ex.getMessage() );
			throw ex;
		} 	
		finally 
		{
			reader.close();
		}
		return new JSONArray(sb.toString());		
			
	}
}
