package Clases;

public class Equipo {
	private String nombreEquipo;
	private String nombreLiga;
	private Plantilla plantillaEquipo;
	private int DTEquipo;
	
	public Equipo (String nombreEquipo, String nombreLiga) {
		this.nombreEquipo = nombreEquipo;
		this.nombreLiga = nombreLiga;
		this.plantillaEquipo = new Plantilla();
		this.DTEquipo = -1;
	}
	
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
	
	public void agregarJugador (int IDJugador) {
		plantillaEquipo.agregarJugador(IDJugador);
	}

	public void eliminarJugador(int idJugador) {
		plantillaEquipo.eliminarJugador(idJugador);
	}
	
	public void elimiarDT() {
		this.DTEquipo = -1;
	}
	
	public boolean hayEspacioEnPlantilla () {
		return plantillaEquipo.cantidadJugadores()<11;
	}

	public boolean hayEspacioParaDT() {
		return this.DTEquipo == -1;
	}
	
	public boolean jugadorYaCargado (String nombreApellidoRecibido) {
		return plantillaEquipo.jugadorYaCargado(nombreApellidoRecibido);
	}
	
	public boolean hayEspacioEnPosicion(String posicionJugador) {
		return plantillaEquipo.hayEspacioEnPosicion(posicionJugador);
	}

	public boolean agregarDT(int nuevoDT) {
		if (DTEquipo == -1) {
			DTEquipo = nuevoDT;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		if (DTEquipo != -1) {
			retorno.append("\n\nInformación del Director Técnico: \n" + Simulador.getMercado().getListadoDTs().buscar(DTEquipo).toString());
		} else {
			retorno.append("\n\nNo hay un Director Técnico en el Equipo.");
		}
		if (!plantillaEquipo.plantillaVacia()) {
			retorno.append("\n\nInformación de Plantilla: " + plantillaEquipo.toString());
		} else {
			retorno.append("\n\nNo hay Jugadores en el Equipo.");
		}
		return retorno.toString();
	}

	
}
