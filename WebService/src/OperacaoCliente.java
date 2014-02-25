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
import org.json.JSONObject;

public class OperacaoCliente {

	static StringBuilder builder = new StringBuilder();
	static String url = "https://webservicephp-knkshiki.rhcloud.com/operacao.php";

	public static void main(String[] args) throws ClientProtocolException, IOException {

		// Metodo para adicionar cliente no servidor
		addCliente();

		// Metodo para atualizar cliente no servidor
		updateCliente();

		// Metodo para deletar cliente no servidor
		deleteCliente();
	}

	private static void addCliente() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		Usuario usuario = new Usuario("emailteste",12341234,"12345678");

		JSONObject json = new JSONObject();
		json.put("operacao", "add");
		json.put("email", usuario.getEmail());
		json.put("telefone", usuario.getTelefone());
		json.put("senha", usuario.getSenha());
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

	private static void updateCliente() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		Usuario usuario = new Usuario("emailteste2",12341234,"12345678");
		usuario.setId(9);

		JSONObject json = new JSONObject();
		json.put("operacao", "update");
		json.put("_id", usuario.getId());
		// Só permite atualizar email e senha
		json.put("email", usuario.getEmail());
		json.put("senha", usuario.getSenha());
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

	private static void deleteCliente() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		Usuario usuario = new Usuario();
		usuario.setId(9);

		JSONObject json = new JSONObject();
		json.put("operacao", "delete");
		json.put("_id", usuario.getId());
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