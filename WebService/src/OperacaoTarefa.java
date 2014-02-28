import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class OperacaoTarefa {

	static StringBuilder builder = new StringBuilder();
	static String url = "https://webservicephp-knkshiki.rhcloud.com/operacao.php";

	public static void main(String[] args) throws ClientProtocolException, IOException {

		// Metodo para adicionar Tarefa no servidor
//		addTarefa();

		// Metodo para atualizar Tarefa no servidor
//		updateTarefa();

		// Metodo para deletar Tarefa no servidor
//		deleteTarefa();
	}

	private static void addTarefa() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		Task task = new Task();
		task.setIdUsuario(2);
		task.setNome("aniversario!");
		task.setDescricao("aniversario de hugo");
		task.setData("21/04/2014");
		task.setNotificacao(1);

		JSONObject json = new JSONObject();
		json.put("objeto", "task");
		json.put("operacao", "add");
		//Parametros importantes para a operacao
		json.put("_idUsuario", task.getIdUsuario());
		json.put("nome", task.getNome());
		json.put("descricao", task.getDescricao());
		json.put("data", task.getData());
		json.put("notificacao", task.getNotificacao());
		json.put("status", 1);
		HttpClient client = new DefaultHttpClient(new BasicHttpParams());

		// Bota o JSON na url
		HttpPost request = new HttpPost(url);
		request.setEntity(new ByteArrayEntity(json.toString().getBytes("UTF8")));
		request.setHeader("json", json.toString());

		// Executa e da a resposta
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				content));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder.toString());
	}

	private static void updateTarefa() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		Task task = new Task();
		task.setId(5);
		task.setIdUsuario(2);
		task.setNome("aniversario2!");
		task.setDescricao("aniversario de hugo");
		task.setData("21/04/2014");
		task.setNotificacao(1);

		JSONObject json = new JSONObject();
		json.put("objeto", "user");
		json.put("operacao", "update");
		//Parametros importantes para a operacao
		json.put("_id", task.getId());
		// Só permite atualizar email e senha
		json.put("nome", task.getNome());
		json.put("descricao", task.getDescricao());
		json.put("data", task.getData());
		json.put("notificacao", task.getNotificacao());
		json.put("status", 1);
		HttpClient client = new DefaultHttpClient(new BasicHttpParams());

		// Bota o JSON na url
		HttpPost request = new HttpPost(url);
		request.setEntity(new ByteArrayEntity(json.toString().getBytes("UTF8")));
		request.setHeader("json", json.toString());

		// Executa e da a resposta
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				content));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder.toString());
	}

	private static void deleteTarefa() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		Task task = new Task();
		task.setId(4);

		JSONObject json = new JSONObject();
		json.put("objeto", "task");
		json.put("operacao", "delete");
		//Parametros importantes para a operacao
		json.put("_id", task.getId());
		HttpClient client = new DefaultHttpClient(new BasicHttpParams());

		// Bota o JSON na url
		HttpPost request = new HttpPost(url);
		request.setEntity(new ByteArrayEntity(json.toString().getBytes("UTF8")));
		request.setHeader("json", json.toString());

		// Executa e da a resposta
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				content));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder.toString());
	}
}