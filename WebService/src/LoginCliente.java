import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;


public class LoginCliente {

	public static void main(String[] args) throws ClientProtocolException, IOException {

        // Instantiate an HttpClient
        @SuppressWarnings({ "resource", "deprecation" })
		HttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        String url = "https://webservicephp-knkshiki.rhcloud.com/login.php?userLogin=88064566&userSenha=123456";
        HttpPost httppost = new HttpPost(url);
        
        // Instantiate a GET HTTP method
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = httpclient.execute(httppost,responseHandler);
		
		// Parse
		JSONObject json = new JSONObject(responseBody);
		JSONArray jArray = json.getJSONArray("users");
		List<Usuario> mylist = new ArrayList<Usuario>();

		if(jArray.length() == 1){
		    JSONObject e = jArray.getJSONObject(0);
		    JSONObject jObject = e.getJSONObject("users");
		    
		    Usuario usuario = new Usuario();
		    usuario.setId(jObject.getLong("_id"));
		    usuario.setEmail(jObject.getString("email"));
		    usuario.setTelefone(jObject.getInt("telefone"));
		    usuario.setSenha(jObject.getString("senha"));

		    mylist.add(usuario);
		    System.out.println(usuario.getTelefone());
		}else{
			System.out.println("Dados errados ou usuário não existe");
		}
	}
}