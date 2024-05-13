package co.edu.unbosque.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.dto.EmisoraDTO;

public class EmisoraTestJSON {

	private static URL url;
	private static String sitio = "http://localhost:8090/";

	public static ArrayList<EmisoraDTO> getJSON() throws IOException, ParseException {
		url = new URL(sitio + "emisoras/listar");
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		ArrayList<EmisoraDTO> lista = new ArrayList<EmisoraDTO>();
		lista = parsingEmisoras(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<EmisoraDTO> parsingEmisoras(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<EmisoraDTO> lista = new ArrayList<EmisoraDTO>();
		JSONArray usuarios = (JSONArray) jsonParser.parse(json);
		Iterator i = usuarios.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			EmisoraDTO usuario = new EmisoraDTO();
			usuario.setNombre(innerObj.get("nombre").toString());
			usuario.setModo_transmision(innerObj.get("modo_transmision").toString());
			usuario.setTipo_musica(innerObj.get("tipo_musica").toString());
			lista.add(usuario);
		}
		return lista;
	}
	
	public static int postJSON(EmisoraDTO usuario) throws IOException {
		url = new URL(sitio + "emisoras/guardar");

		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{" + "\"nombre\":\"" + usuario.getNombre() + "\",\"modo_transmision\": \""
				+ usuario.getModo_transmision() + "\",\"tipo_musica\": \"" + usuario.getTipo_musica() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	
}
