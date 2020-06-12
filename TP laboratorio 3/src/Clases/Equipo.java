package Clases;

public class Equipo {
	private String nombreEquipo;
	private String nombreLiga;
	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getNombreLiga() {
		return nombreLiga;
	}

	public void setNombreLiga(String nombreLiga) {
		this.nombreLiga = nombreLiga;
	}

	private Plantilla plantillaEquipo;
	private DirectorTecnico DTEquipo;
	
	public Equipo (String nombreEquipo, String nombreLiga) {
		this.nombreEquipo = nombreEquipo;
		this.nombreLiga = nombreLiga;
		this.plantillaEquipo = new Plantilla();
		this.DTEquipo = null;
	}
	
	public void agregarJugador (int IDJugador) {
		plantillaEquipo.agregarJugador(IDJugador);
	}

	public boolean agregarDT(DirectorTecnico nuevoDT) {
		if (DTEquipo == null) {
			DTEquipo = nuevoDT;
			return true;
		}
		return false;
	}
	
}
