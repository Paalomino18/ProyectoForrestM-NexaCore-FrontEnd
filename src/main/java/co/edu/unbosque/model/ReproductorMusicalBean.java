package co.edu.unbosque.model;

import javazoom.jl.player.Player;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.dto.ProgramacionDiariaDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@ManagedBean
@SessionScoped
public class ReproductorMusicalBean {
	private List<String> audiosMP3;
	private int indexCancionActual = 0;
	private Player player;
	private boolean isPlaying = false;
	private List<ProgramacionDiariaDTO> parrilla;
	private String fechaProgramada;

	@PostConstruct
	public void init() {
		cargarparrilla();
	}

	public String redireccion() {
		cargarparrilla();
		return "reproductorMusical.xhtml";
	}

	public void cargarparrilla() {
		try {
			this.parrilla = ProgramacionDiariaTestJSON.getJSONProgramacion();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public void reproducirProgramacion(String fecha) {
		for (ProgramacionDiariaDTO programacion : parrilla) {
			if (programacion.getFechaProg().equals(fecha)) {
				this.audiosMP3 = programacion.getListaCanciones();
				this.indexCancionActual = 0;
				play();
				break;
			}
		}
	}

	public void next() {
		stop(); // Detener la reproducción actual
		indexCancionActual++; // Ir a la siguiente canción
		if (indexCancionActual >= 0 && indexCancionActual < audiosMP3.size()) {
			play(); // Reproducir la nueva canción
		}
	}

	public void previous() {
		stop(); // Detener la reproducción actual
		indexCancionActual--; // Ir a la canción anterior
		if (indexCancionActual >= 0 && indexCancionActual < audiosMP3.size()) {
			play(); // Reproducir la nueva canción
		}
	}

	public void play() {
		if (audiosMP3 != null && indexCancionActual >= 0 && indexCancionActual < audiosMP3.size()) {
			String currentAudioFile = audiosMP3.get(indexCancionActual);
			try {
				if (!isPlaying) { // Si no se está reproduciendo, comenzar la reproducción
					FileInputStream fis = new FileInputStream(currentAudioFile);
					player = new Player(fis);
					new Thread(() -> {
						try {
							player.play();
						} catch (Exception ex) {
							System.err.println(ex.getMessage());
						}
					}).start();
					isPlaying = true; // Actualizar el estado de reproducción
				} else { // Si se está reproduciendo, pausar la canción
					player.close();
					isPlaying = false; // Actualizar el estado de reproducción
				}
			} catch (IOException ex) {
				System.err.println("Error al abrir el archivo de audio: " + ex.getMessage());
			} catch (Exception ex) {
				System.err.println("Error al reproducir la canción: " + ex.getMessage());
			}
		}
	}

	public void stop() {
		if (isPlaying && player != null) {
			player.close();
			isPlaying = false; // Actualizar el estado de reproducción
		}
	}

	public int getindexCancionActual() {
		return indexCancionActual;
	}

	public void setindexCancionActual(int indexCancionActual) {
		this.indexCancionActual = indexCancionActual;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean getIsPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public List<String> getaudiosMP3() {
		return audiosMP3;
	}

	public void setaudiosMP3(List<String> audiosMP3) {
		this.audiosMP3 = audiosMP3;
	}

	public List<ProgramacionDiariaDTO> getparrilla() {
		return parrilla;
	}

	public void setparrilla(List<ProgramacionDiariaDTO> parrilla) {
		this.parrilla = parrilla;
	}

	public String getfechaProgramada() {
		return fechaProgramada;
	}

	public void setfechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}
}