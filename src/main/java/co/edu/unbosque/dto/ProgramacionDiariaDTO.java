package co.edu.unbosque.dto;

import java.util.List;



public class ProgramacionDiariaDTO {

	
	private String fechaProg;

	private List<String> listaCanciones;

	public ProgramacionDiariaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ProgramacionDiariaDTO(String fechaProg, List<String> listaCanciones) {
		super();
	
		this.fechaProg = fechaProg;
		this.listaCanciones = listaCanciones;
	}



	public String getFechaProg() {
		return fechaProg;
	}

	public void setFechaProg(String fechaProg) {
		this.fechaProg = fechaProg;
	}

	public List<String> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(List<String> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
}
