package co.edu.unbosque.model;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.dto.PistaMusicalDTO;
import co.edu.unbosque.dto.ProgramacionDiariaDTO;

@ManagedBean
@RequestScoped
public class ProgramacionDiariaBean {

	private String fechaProg;
	private List<String> listaCanciones;
	private ProgramacionDiariaTestJSON tj;
	private ArrayList<PistaMusicalDTO> lista;
	private ArrayList<ProgramacionDiariaDTO> progDiari;
	private LocalDate date2;

	public ProgramacionDiariaBean() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getDate2() {
		return date2;
	}

	public void setDate2(LocalDate date2) {
		this.date2 = date2;
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

	public ProgramacionDiariaTestJSON getTj() {
		return tj;
	}

	public void setTj(ProgramacionDiariaTestJSON tj) {
		this.tj = tj;
	}

	public ArrayList<PistaMusicalDTO> getLista() {
		return lista;
	}

	public void setLista(ArrayList<PistaMusicalDTO> lista) {
		this.lista = lista;
	}

	public ArrayList<ProgramacionDiariaDTO> getProgDiari() {
		return progDiari;
	}

	public void setProgDiari(ArrayList<ProgramacionDiariaDTO> progDiari) {
		this.progDiari = progDiari;
	}

	public String cargarParrilla() {
		try {
			progDiari = ProgramacionDiariaTestJSON.getJSONProgramacion();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return "reproductorMusical.xhtml";
	}

	public String guardarProgramacionDiaria() {

		ProgramacionDiariaDTO prog = new ProgramacionDiariaDTO();
		String fechaComoString = date2.toString();
		prog.setFechaProg(fechaComoString);
		prog.setListaCanciones(this.listaCanciones);
		System.out.println("Lista llenada");

		int respuesta = 0;
		try {
			respuesta = ProgramacionDiariaTestJSON.postJSONProgramacion(prog);
			System.out.println(respuesta);
			if (respuesta == 200) {
				addMessage("Confirmado", "¡Registro Agregado!");
				return "Exito";
			} else {
				return "Error";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@PostConstruct
	public void init() {
		listaCanciones = new ArrayList<>();
		try {
			lista = PistaMusicalTestJSON.getJSONPista();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			progDiari = ProgramacionDiariaTestJSON.getJSONProgramacion();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

}
