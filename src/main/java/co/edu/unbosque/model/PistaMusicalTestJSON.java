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

import co.edu.unbosque.dto.PistaMusicalDTO;

public class PistaMusicalTestJSON {
	private static URL url;
	private static String sitio = "http://localhost:8090/";
	
	
	public static ArrayList<PistaMusicalDTO> getJSONPista() throws IOException, ParseException {
		url = new URL(sitio + "pistas/listar");
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		ArrayList<PistaMusicalDTO> lista = new ArrayList<PistaMusicalDTO>();
		lista = parsingPista(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<PistaMusicalDTO> parsingPista(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<PistaMusicalDTO> lista = new ArrayList<PistaMusicalDTO>();
		JSONArray usuarios = (JSONArray) jsonParser.parse(json);
		Iterator i = usuarios.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			PistaMusicalDTO usuario = new PistaMusicalDTO();
			usuario.setGenero(innerObj.get("genero").toString());
			usuario.setNombre(innerObj.get("nombre").toString());
			usuario.setNombre_artista(innerObj.get("nombre_artista").toString());
			usuario.setNombre_archivo(innerObj.get("nombre_archivo").toString());
			lista.add(usuario);
		}
		return lista;
	}
	
	public static int postJSONPista(PistaMusicalDTO usuario) throws IOException {
		url = new URL(sitio + "pistas/guardar");

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
		String data = "{" + "\"genero\":\"" + usuario.getGenero() + "\",\"nombre\": \"" + usuario.getNombre()
				+ "\",\"nombre_artista\": \"" + usuario.getNombre_artista() + "\",\"nombre_archivo\": \""
				+ usuario.getNombre_archivo() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	 private static String convertPistaToJson(PistaMusicalDTO pista) {
	        StringBuilder json = new StringBuilder();
	        json.append("{");
	        json.append("\"nombre\":\"").append(pista.getNombre()).append("\",");
	        json.append("\"genero\":\"").append(pista.getGenero()).append("\",");
	        json.append("\"nombre_artista\":\"").append(pista.getNombre_artista()).append("\",");
	        json.append("\"nombre_archivo\":\"").append(pista.getNombre_archivo()).append("\"");
	        json.append("}");
	        return json.toString();
	 }

}
