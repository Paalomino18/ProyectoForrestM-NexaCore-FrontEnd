package co.edu.unbosque.model;

import java.io.IOException;  

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean
@RequestScoped
public class EmisoraBean {

	private String nombre;
	private String modo_transmision;
	private String tipo_musica;
	private EmisoraTestJSON tj;

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
	

	public EmisoraTestJSON getTj() {
		return tj;
	}

	public void setTj(EmisoraTestJSON tj) {
		this.tj = tj;
	}

	public void agregarEmisora() {
		EmisoraDTO emisora = new EmisoraDTO();

		emisora.setNombre(this.nombre);
		emisora.setModo_transmision(this.modo_transmision);
		emisora.setTipo_musica(this.tipo_musica);

		int respuesta = 0;
		try {
			respuesta = EmisoraTestJSON.postJSON(emisora);
			if (respuesta == 200) {
				System.out.println("Registro Agregado!");
				addMessage("Confirmado", "¡Registro Agregado!");
				
			} else {
				System.out.println("Error!" + respuesta);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


	public List<EmisoraDTO> getlistarEmisoras() {
		try {
			ArrayList<EmisoraDTO> lista = EmisoraTestJSON.getJSON();
			
		return lista;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String listar() {
		getlistarEmisoras();

		return "listar.xhtml";
	}
	

}

