package Clases;

import java.util.ArrayList;

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
	
	public Equipo (String nombreEquipo, Plantilla plantillaClub, int dtClub) {
		this.nombreEquipo = nombreEquipo;
		this.plantillaEquipo = plantillaClub;
		this.DTEquipo = dtClub;
		this.nombreLiga = "";
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
	
	public Plantilla getPlantillaEquipo () {
		return plantillaEquipo;
	}
	
	public DirectorTecnico getDTEquipo () {
		if (DTEquipo == -1) {
			return new DirectorTecnico();
		} else {
			return Simulador.getMercado().getListadoDTs().buscar(DTEquipo);
		}
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
	
	public boolean hayEspacioEnPlantilla (boolean ingresoAdmin) {
		return plantillaEquipo.cantidadJugadores(ingresoAdmin)<11;
	}

	public boolean hayEspacioParaDT() {
		if (DTEquipo == -1) {
			return true;
		} else {
			return !Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getEstado();
		}
	}
	
	public boolean hayEspacioParaDTAdmin() {
		if (DTEquipo == -1) {
			return true;
		}
		return false;
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

	public void modificacionPosiciones(String posicionAnterior, String nuevaPosicionJugador) {
		this.plantillaEquipo.modificacionPosiciones(posicionAnterior, nuevaPosicionJugador);
	}

	public boolean posicionesEquivalentes(String viejaPosicion, String nuevaPosicionJugador) {
		if (viejaPosicion.equals(nuevaPosicionJugador)) {
			return true;
		} else {
			if (viejaPosicion.equals("DFC") || viejaPosicion.equals("LI") || viejaPosicion.equals("LD") && nuevaPosicionJugador.equals("DFC") || nuevaPosicionJugador.equals("LI") || nuevaPosicionJugador.equals("LD")) {
				return true;
			} else {
				if (viejaPosicion.equals("MC") || viejaPosicion.equals("MI") || viejaPosicion.equals("MD") || viejaPosicion.equals("MCO") && nuevaPosicionJugador.equals("MC") || nuevaPosicionJugador.equals("MI") || nuevaPosicionJugador.equals("MD") || nuevaPosicionJugador.equals("MCO") ) {
					return true;
				} else {
					if (viejaPosicion.equals("DC") || viejaPosicion.equals("EI") || viejaPosicion.equals("ED") && viejaPosicion.equals("DC") || viejaPosicion.equals("EI") || viejaPosicion.equals("ED")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public double promedioJugadores() {
		return plantillaEquipo.promedioJugadores(Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getTipo());
	}
	
	public ArrayList<Jugador> listado(){
		return plantillaEquipo.listado();
	}
	
	public String toString(boolean ingresoAdmin) {
		StringBuilder retorno = new StringBuilder();
		if (DTEquipo != -1) {
			if (ingresoAdmin) {
				retorno.append("\n\nInformación del Director Técnico: \n" + Simulador.getMercado().getListadoDTs().buscar(DTEquipo).toString() + "\n Estado: " + Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getEstado());
			} else {
				if (Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getEstado()) {
					retorno.append("\n\nInformación del Director Técnico: \n" + Simulador.getMercado().getListadoDTs().buscar(DTEquipo).toString());
				}
			}
		} else {
			retorno.append("\n\nNo hay un Director Técnico en el Equipo.");
		}
		if (!plantillaEquipo.plantillaVacia()) {
			if (ingresoAdmin) {
				retorno.append("\n\nInformación de Plantilla: \n" + plantillaEquipo.listadoJugadores(ingresoAdmin));
			} else {
				if (plantillaEquipo.cantidadJugadoresValidos()>0) {
					retorno.append("\n\nInformación de Plantilla: \n" + plantillaEquipo.listadoJugadores(ingresoAdmin));
				}
			}
		} else {
			retorno.append("\n\nNo hay Jugadores en el Equipo.");
		}
		return retorno.toString();
	}


	
}
