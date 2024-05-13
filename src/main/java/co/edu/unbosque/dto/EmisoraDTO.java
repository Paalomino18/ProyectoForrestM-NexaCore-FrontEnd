package co.edu.unbosque.dto;

public class EmisoraDTO {

	
	private String nombre;
	private String modo_transmision;
	private String tipo_musica;
	
	
	public EmisoraDTO(String nombre, String modo_transmision, String tipo_musica) {
	        this.nombre = nombre;
	        this.modo_transmision = modo_transmision;
	        this.tipo_musica = tipo_musica;
	    }
	
	public EmisoraDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getModo_transmision() {
		return modo_transmision;
	}
	public void setModo_transmision(String modo_transmision) {
		this.modo_transmision = modo_transmision;
	}
	public String getTipo_musica() {
		return tipo_musica;
	}
	public void setTipo_musica(String tipo_musica) {
		this.tipo_musica = tipo_musica;
	}
	
	
}

