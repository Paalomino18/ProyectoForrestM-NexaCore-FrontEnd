package co.edu.unbosque.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.dto.ProgramacionDiariaDTO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProgramacionDiariaTestJSON {
	private static URL url;
	private static String sitio = "http://localhost:8090/";
	
	public static ArrayList<ProgramacionDiariaDTO> getJSONProgramacion() throws IOException, ParseException {
		url = new URL(sitio + "programacion/listar");
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		ArrayList<ProgramacionDiariaDTO> lista = new ArrayList<ProgramacionDiariaDTO>();
		lista = parsingProgramacion(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<ProgramacionDiariaDTO> parsingProgramacion(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<ProgramacionDiariaDTO> lista = new ArrayList<ProgramacionDiariaDTO>();
		JSONArray programaciones = (JSONArray) jsonParser.parse(json);
		Iterator i = programaciones.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			ProgramacionDiariaDTO programacion = new ProgramacionDiariaDTO();
			programacion.setFechaProg(innerObj.get("fechaProg").toString());
			programacion.setListaCanciones((List<String>) innerObj.get("listaCanciones"));
			lista.add(programacion);
		}
		return lista;
	}
	
	public static int postJSONProgramacion(ProgramacionDiariaDTO programacion) throws IOException {
	    url = new URL(sitio + "programacion/guardar");
	    HttpURLConnection http = (HttpURLConnection) url.openConnection();
	    http.setRequestMethod("POST");
	    http.setDoOutput(true);
	    http.setRequestProperty("Accept", "application/json");
	    http.setRequestProperty("Content-Type", "application/json");

	    try (OutputStream outputStream = http.getOutputStream()) {
	        JSONObject json = new JSONObject();
	        json.put("fechaProg", programacion.getFechaProg());

	        JSONArray cancionesArray = new JSONArray();
	        for (String cancion : programacion.getListaCanciones()) {
	            cancionesArray.add(cancion);
	        }
	        json.put("listaCanciones", cancionesArray);

	        // Convertir el JSON a una cadena y enviarlo como bytes
	        String jsonString = json.toJSONString(); // Convertir JSONObject a String
	        byte[] postData = jsonString.getBytes(StandardCharsets.UTF_8);

	        outputStream.write(postData);
	        outputStream.flush();

	        return http.getResponseCode();
	    } finally {
	        http.disconnect();
	    }
	}

}

