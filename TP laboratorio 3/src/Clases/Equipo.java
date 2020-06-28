package Clases;

import java.util.ArrayList;

/** 
 *  Esta clase nos permite crear objetos del tipo Equipo, la cual cuenta con los atributos y m�todos 
 * necesarios para su gesti�n.
 */

public class Equipo {
	private String nombreEquipo;
	private String nombreLiga;
	private Plantilla plantillaEquipo;
	private int DTEquipo;
	
	/**
	 * Constructor de un nuevo equipo
	 * @param nombreEquipo nombre del nuevo equipo
	 * @param nombreLiga nombre de la liga del nuevo equipo
	 */
	public Equipo (String nombreEquipo, String nombreLiga) {
		this.nombreEquipo = nombreEquipo;
		this.nombreLiga = nombreLiga;
		this.plantillaEquipo = new Plantilla();
		this.DTEquipo = -1;
	}
	
	/**
	 * Constructor que se usa para crear un equipo en base a un club
	 * @param nombreEquipo nombre del club que ser� copiado por referencia
	 * @param plantillaClub plantilla del club que ser� copiada por referencia
	 * @param dtClub ID del DT del club que ser� copiado
	 */
	public Equipo (String nombreEquipo, Plantilla plantillaClub, int dtClub) {
		this.nombreEquipo = nombreEquipo;
		this.plantillaEquipo = plantillaClub;
		this.DTEquipo = dtClub;
		this.nombreLiga = "";
	}
	
	/**
	 * @return nombre del equipo
	 */
	public String getNombreEquipo() {
		return nombreEquipo;
	}

	/**
	 * @param nombreEquipo nuevo nombre del equipo
	 */
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	/**
	 * @return nombre de la liga del equipo
	 */
	public String getNombreLiga() {
		return nombreLiga;
	}

	/**
	 * @param nombreLiga nuevo nombre de la liga del equipo
	 */
	public void setNombreLiga(String nombreLiga) {
		this.nombreLiga = nombreLiga;
	}	
	
	/**
	 * @return plantilla del equipo
	 */
	public Plantilla getPlantillaEquipo () {
		return plantillaEquipo;
	}
	
	/**
	 * M�todo que devuelve el ID del DT del Equipo
	 * @return DT del equipo. Se busca por su ID en el mercado
	 */
	public DirectorTecnico getDTEquipo () {
		if (DTEquipo == -1) {
			return new DirectorTecnico();
		} else {
			return Simulador.getMercado().getListadoDTs().buscar(DTEquipo);
		}
	}
	
	/**
	 * M�todo que agrega un nuevo ID de jugador a la plantilla
	 * @param IDJugador ID a agregar
	 */
	public void agregarJugador (int IDJugador) {
		plantillaEquipo.agregarJugador(IDJugador);
	}

	/**
	 * M�todo que elimina un ID de jugador de la plantilla
	 * @param idJugador ID a eliminar
	 */
	public void eliminarJugador(int idJugador) {
		plantillaEquipo.eliminarJugador(idJugador);
	}
	
	/**
	 * M�todo que elimina el DT del equipo, seteando su ID a -1
	 */
	public void eliminarDT() {
		this.DTEquipo = -1;
	}
	
	/**
	 * M�todo que determina si hay espacio en la plantilla
	 * @param ingresoAdmin determina si se ingresa al m�todo como Administrador
	 * @return true si hay menos de 11 jugadores en la plantilla
	 */
	public boolean hayEspacioEnPlantilla (boolean ingresoAdmin) {
		return plantillaEquipo.cantidadJugadores(ingresoAdmin)<11;
	}

	/**
	 * M�todo que determina si hay o no un DT en el equipo
	 * @return true si el ID del DT en el equipo es igual a -1 o si su estado es false. False si su estado es true
	 */
	public boolean hayEspacioParaDT() {
		if (DTEquipo == -1) {
			return true;
		} else {
			return !Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getEstado();
		}
	}
	
	/**
	 * M�todo que determina si hay un DT cargado en el equipo
	 * @return true si el ID del DT en el equipo es igual a -1. False si no
	 */
	public boolean hayEspacioParaDTAdmin() {
		if (DTEquipo == -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * M�todo que determina si un jugador ya estaba cargado en la plantilla, por su nombre
	 * @param nombreApellidoRecibido nombre del jugador a comprobar
	 * @return true si el jugador ya estaba cargado, false si no
	 */
	public boolean jugadorYaCargado (String nombreApellidoRecibido) {
		return plantillaEquipo.jugadorYaCargado(nombreApellidoRecibido);
	}

	/**
	 * M�todo para agregar un DT al equipo
	 * @param nuevoDT ID del nuevo DT
	 * @return true si se pudo agregar, false si no (si ya hay un DT)
	 */
	public boolean agregarDT(int nuevoDT) {
		if (DTEquipo == -1) {
			DTEquipo = nuevoDT;
			return true;
		}
		return false;
	}

	/**
	 * M�todo que actualiza los valores de la cantidad de jugadores por posici�n en la Plantilla del equipo
	 * @param posicionAnterior posici�n anterior del jugador modificado
	 * @param nuevaPosicionJugador nueva posici�n del jugador modificado
	 */
	public void modificacionPosiciones(String posicionAnterior, String nuevaPosicionJugador) {
		this.plantillaEquipo.modificacionPosiciones(posicionAnterior, nuevaPosicionJugador);
	}
	
	/**
	 * M�todo que determina si las posiciones pertenecen o no al mismo sector de juego
	 * @param viejaPosicion vieja posici�n del jugador modificado
	 * @param nuevaPosicionJugador nueva posici�n del jugador modificado
	 * @return true si las posiciones pertenecen al mismo sector de juego, false si no
	 */
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

	/**
	 * @return promedio de calidad del equipo
	 */
	public double promedioJugadores() {
		return plantillaEquipo.promedioJugadores(Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getTipo());
	}
	
	/**
	 * @return ArrayList con los jugadores del equipo
	 */
	public ArrayList<Jugador> listado(){
		return plantillaEquipo.listado();
	}
	
	/**
	 * Muestra del equipo
	 * @param ingresoAdmin determina si se ingresa o no como administrador
	 * @return listado de informaci�n
	 */
	public String toString(boolean ingresoAdmin) {
		StringBuilder retorno = new StringBuilder();
		if (DTEquipo != -1) {
			if (ingresoAdmin) {
				retorno.append("\n\nInformaci�n del Director T�cnico: \n" + Simulador.getMercado().getListadoDTs().buscar(DTEquipo).toString() + "\n Estado: " + Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getEstado());
			} else {
				if (Simulador.getMercado().getListadoDTs().buscar(DTEquipo).getEstado()) {
					retorno.append("\n\nInformaci�n del Director T�cnico: \n" + Simulador.getMercado().getListadoDTs().buscar(DTEquipo).toString());
				}
			}
		} else {
			retorno.append("\n\nNo hay un Director T�cnico en el Equipo.");
		}
		if (!plantillaEquipo.plantillaVacia()) {
			if (ingresoAdmin) {
				retorno.append("\n\nInformaci�n de Plantilla: \n" + plantillaEquipo.listadoJugadores(ingresoAdmin));
			} else {
				if (plantillaEquipo.cantidadJugadoresValidos()>0) {
					retorno.append("\n\nInformaci�n de Plantilla: \n" + plantillaEquipo.listadoJugadores(ingresoAdmin));
				}
			}
		} else {
			retorno.append("\n\nNo hay Jugadores en el Equipo.");
		}
		return retorno.toString();
	}
	
}
