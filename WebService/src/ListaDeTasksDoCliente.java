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

public class ListaDeTasksDoCliente {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClientProtocolException, IOException {

		// Instantiate an HttpClient
		@SuppressWarnings("resource")
		HttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
		String url = "https://webservicephp-knkshiki.rhcloud.com/tasks/?idUser=1";
		HttpPost httppost = new HttpPost(url);

		// Instantiate a GET HTTP method
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = httpclient.execute(httppost,responseHandler);

		// Parse
		JSONObject json = new JSONObject(responseBody);
		JSONArray jArray = json.getJSONArray("tasks");
		List<Task> mylist = new ArrayList<Task>();

		for (int i = 0; i < jArray.length(); i++) {
			JSONObject e = jArray.getJSONObject(i);
			JSONObject jObject = e.getJSONObject("tasks");

			Task task = new Task();
			popularTaskComJSON(jObject, task);

			mylist.add(task);
		}

		System.out.println(mylist.toString());
	}

	public static void popularTaskComJSON(JSONObject jObject, Task task) {
		task.setId(jObject.getLong("_id"));
		task.setIdUsuario(jObject.getLong("_idUsuario"));
		task.setNome(jObject.getString("nome"));
		task.setDescricao(jObject.getString("descricao"));
		task.setData(jObject.getString("data"));
		task.setNotificacao(jObject.getInt("notificacao"));
		if(jObject.getInt("status") == 1 ){
			task.setStatus(true);
		}else{
			task.setStatus(false);
		}
	}
}
