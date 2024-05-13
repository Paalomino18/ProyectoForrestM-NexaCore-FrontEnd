package co.edu.unbosque.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.file.UploadedFile;

import co.edu.unbosque.dto.PistaMusicalDTO;

@ManagedBean
@RequestScoped
public class PistaMusicalBean {

	private String genero;
	private String nombre;
	private String nombre_artista;
	private String nombre_archivo;
	private ArrayList<PistaMusicalDTO> lista;
	private PistaMusicalTestJSON tj;

	private UploadedFile file;
	private String url = "";
	File fichero;

	

	public String upload() {
		if (file != null) {
			
			File archivo = buscarArchivo(new File("C:\\"), file.getFileName());
	        if (archivo != null) {
	            System.out.println("Ruta completa del archivo: " + archivo.getAbsolutePath());
	           url = archivo.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\");
	           return url;
	        } else {
	            System.out.println("El archivo no se encontró en el sistema de archivos.");
	        }
	    
			FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			
			return url;
		}
		return url;
	}
	
	public static File buscarArchivo(File directorio, String nombreArchivo) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    File resultado = buscarArchivo(archivo, nombreArchivo);
                    if (resultado != null) {
                        return resultado;
                    }
                } else if (archivo.getName().equals(nombreArchivo)) {
                    return archivo;
                }
            }
        }
        return null;
    }

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public PistaMusicalTestJSON getTj() {
		return tj;
	}

	public void setTj(PistaMusicalTestJSON tj) {
		this.tj = tj;
	}

	public ArrayList<PistaMusicalDTO> getLista() {
		return lista;
	}

	public void setLista(ArrayList<PistaMusicalDTO> lista) {
		this.lista = lista;
	}

	public void agregarPista() {
		PistaMusicalDTO pista = new PistaMusicalDTO();
		

		pista.setGenero(this.genero);
		pista.setNombre(this.nombre);
		pista.setNombre_artista(this.nombre_artista);
		pista.setNombre_archivo(upload());

		int respuesta = 0;
		try {
			respuesta = PistaMusicalTestJSON.postJSONPista(pista);
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

	public List<PistaMusicalDTO> getlistarPistas() {
		try {
			ArrayList<PistaMusicalDTO> lista = PistaMusicalTestJSON.getJSONPista();

			return lista;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}