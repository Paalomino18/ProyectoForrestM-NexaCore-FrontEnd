package co.edu.unbosque.dto;

import java.util.ArrayList; 
import java.util.List;

public class PistaMusicalDTO {

	private String genero;
	private String nombre;
	private String nombre_artista;
	private String nombre_archivo;
	private ArrayList<PistaMusicalDTO> lista;


	
	
	public PistaMusicalDTO() {
		// TODO Auto-generated constructor stub
	}

	
	
	public PistaMusicalDTO(String genero, String nombre, String nombre_artista, String nombre_archivo,
			ArrayList<PistaMusicalDTO> lista) {
		super();
		this.genero = genero;
		this.nombre = nombre;
		this.nombre_artista = nombre_artista;
		this.nombre_archivo = nombre_archivo;
		this.lista = lista;
	
	}



	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre_artista() {
		return nombre_artista;
	}

	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}

	public String getNombre_archivo() {
		return nombre_archivo;
	}

	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}



	public ArrayList<PistaMusicalDTO> getLista() {
		return lista;
	}



	public void setLista(ArrayList<PistaMusicalDTO> lista) {
		this.lista = lista;
	}



	
}
