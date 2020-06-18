package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
/** 
 *  Esta clase nos permite crear objetos de tipo Plantilla
 *  Es un contenedor con todos los jugadores del equipo creado
 *  Utiliza un HashSet con los ID´s de los jugadores que conforman la plantilla
 *  @ass "Plantilla FIFA 20" <a href=https://www.futbin.com/squad-builder>PlantillaFIFA 20</a>
 *  @author 
 */
import java.util.HashSet;
import java.util.Iterator;

import comparaciones.ComparacionID;
import comparaciones.ComparacionPosicion;

public class Plantilla implements Serializable{
	private static final long serialVersionUID = 3139014270633585868L;
	private HashSet<Integer> listadoJugadores;
	private int cantidadPorteros;
	private int cantidadDefensores;
	private int cantidadMediocampistas;
	private int cantidadDelanteros;	
	
	public Plantilla() {
		listadoJugadores = new HashSet<>();
		cantidadPorteros = 0;
		cantidadDefensores = 0;
		cantidadMediocampistas = 0;
		cantidadDelanteros = 0;
	}
	
	/**
	 * consulta si la plantilla esta vacia
	 * @return true si la plantilla esta vacia
	 */
	public boolean plantillaVacia() {
		return listadoJugadores.isEmpty();
	}
	
	/**
	 * Método que se usa para volver a contar las posiciones en la plantilla al entrar al Club.
	 * De esta forma, se evita tener valores negativos en las posiciones, y evitar que queden espacios ocupados en posiciones vacías.
	 * Al hacerse consulta por ID, podía pasar que un delantero nunca descontara su posición al ser cambiado por el Admin.
	 * 
	 */
	public void sincronizarCantidadPosiciones (){
		cantidadPorteros = 0;
		cantidadDefensores = 0;
		cantidadMediocampistas = 0;
		cantidadDelanteros = 0;
		Iterator<Integer> it = listadoJugadores.iterator();
		String posActual;
		while (it.hasNext()) {
			posActual = Simulador.getMercado().getListadoJugadores().buscar((int)it.next()).getPosicion();
			if (posActual.equals("PO")) {
				cantidadPorteros++;
			} else {
				if (posActual.equals("DFC") || posActual.equals("LI") || posActual.equals("LD")) {
					cantidadDefensores++;
				} else {
					if (posActual.equals("MC") || posActual.equals("MI") || posActual.equals("MD") || posActual.equals("MCO")) {
						cantidadMediocampistas++;
					} else {
						cantidadDelanteros++;
					}
				}
			}
		}
	}
	
	public boolean jugadorYaCargado (String nombreApellidoRecibido) {
		if (listadoJugadores.isEmpty()) {
			return false;
		} else {
			Iterator<Integer> it = listadoJugadores.iterator();
			while (it.hasNext()) {
				if (Simulador.getMercado().getListadoJugadores().buscar((int)it.next()).getNombre().equals(nombreApellidoRecibido)) {
					return true;
				}
			}
			return false;
		}
	}
	
	
	/**
	 * Método que actualiza los valores de posiciones en la plantilla al cambiar un jugador de posición.
	 * 
	 * @param posicionAnterior posición que tenía el jugador. Se resta uno en esta posición.
	 * @param posicionNueva posición que tendrá el jugador. Se suma uno en esta posición.
	 */
	public void modificacionPosiciones (String posicionAnterior, String posicionNueva) {
		if (posicionAnterior.equals("PO")) {
			cantidadPorteros--;
		} else {
			if (posicionAnterior.equals("DFC") || posicionAnterior.equals("LI") || posicionAnterior.equals("LD")) {
				cantidadDefensores--;
			} else {
				if (posicionAnterior.equals("MC") || posicionAnterior.equals("MI") || posicionAnterior.equals("MD") || posicionAnterior.equals("MCO")) {
					cantidadMediocampistas--;
				} else {
					cantidadDelanteros--;
				}
			}
		}
		if (posicionNueva.equals("PO")) {
			cantidadPorteros++;
		} else {
			if (posicionNueva.equals("DFC") || posicionNueva.equals("LI") || posicionNueva.equals("LD")) {
				cantidadDefensores++;
			} else {
				if (posicionNueva.equals("MC") || posicionNueva.equals("MI") || posicionNueva.equals("MD") || posicionNueva.equals("MCO")) {
					cantidadMediocampistas++;
				} else {
					cantidadDelanteros++;
				}
			}
		}
	}
	
	public void agregarJugador (int idJugador) {
		listadoJugadores.add((Integer)idJugador);
		String posicionJugador = Simulador.getMercado().getListadoJugadores().buscar(idJugador).getPosicion();
		if (posicionJugador.equals("PO")) {
			cantidadPorteros++;
		} else {
			if (posicionJugador.equals("DFC") || posicionJugador.equals("LI") || posicionJugador.equals("LD")) {
				cantidadDefensores++;
			} else {
				if (posicionJugador.equals("MC") || posicionJugador.equals("MI") || posicionJugador.equals("MD") || posicionJugador.equals("MCO")) {
					cantidadMediocampistas++;
				} else {
					cantidadDelanteros++;
				}
			}
		}
	}
	
	public void eliminarJugador (int idJugador) {
		listadoJugadores.remove((Integer)idJugador);
		String posicionJugador = Simulador.getMercado().getListadoJugadores().buscar(idJugador).getPosicion();
		if (posicionJugador.equals("PO")) {
			if (cantidadPorteros > 0 ) //Puede ser que un club termine con cantidades negativas si se producen muchos cambios de posición desde el administrador. De esta forma limitamos el piso a 0
				cantidadPorteros--;
		} else {
			if (posicionJugador.equals("DFC") || posicionJugador.equals("LI") || posicionJugador.equals("LD")) {
				if (cantidadDefensores > 0)
					cantidadDefensores--;
			} else {
				if (posicionJugador.equals("MC") || posicionJugador.equals("MI") || posicionJugador.equals("MD") || posicionJugador.equals("MCO")) {
					if (cantidadMediocampistas > 0)
						cantidadMediocampistas--;
				} else {
					if (cantidadDelanteros > 0)
						cantidadDelanteros--;
				}
			}
		}
	}
	
	public int cantidadJugadores(boolean ingresoAdmin) {
		if (ingresoAdmin) {
			return listadoJugadores.size();
		} else {
			int i=0;
			Iterator<Integer> it = listadoJugadores.iterator();
			while (it.hasNext()) {
				if (Simulador.getMercado().getListadoJugadores().buscar((int)it.next()).getEstado()) {
					i++;
				}
			}
			return i;
		}
	}
	
	public int cantidadJugadoresValidos() {
		int retorno = 0;
		Iterator<Integer> it = listadoJugadores.iterator();
		while (it.hasNext()) {
			if (Simulador.getMercado().getListadoJugadores().buscar(it.next()).getEstado()) {
				retorno++;
			}
		}
		return retorno;
	}
	
	/**
	 * @param calidadDT: Tipo del DT del equipo o club
	 * @return Promedio de calificaciones + 0.5 * cantidad Jugadores Especiales + 0.3 si el DT es de PLATA || 0.5 si es de ORO
	 */
	public double promedioJugadores(String calidadDT) {
		double suma = 0;
		Iterator<Integer> it = listadoJugadores.iterator();
		Jugador aux;
		while (it.hasNext()) {
			aux = Simulador.getMercado().getListadoJugadores().buscar(it.next());
			suma += (aux.getCalificacion());
			if (aux.getTipo().equals("ESPECIAL")) {
				suma += 0.5;
			}
		}
		if (calidadDT.equals("PLATA")) {
			suma += 0.3;
		} else {
			if (calidadDT.equals("ORO")) {
				suma += 0.5;
			} else {
				if (calidadDT.equals("ESPECIAL")) {
					suma += 1;
				}
			}
		}
		return (suma/listadoJugadores.size());
	}
	
	public boolean jugadorEncontrado(int idBuscado) {
		return listadoJugadores.contains((Integer)idBuscado);
	}
	
	/**
	 * 
	 * @return Este método devuelve un ArrayList con los elementos cargados en el listado. Desde afuera se decide cómo ordenarlos para la muestra, y si mostrar todos o los que no están dados de baja
	 */
	
	public ArrayList<Jugador> listado () {
		ArrayList<Jugador> retorno = new ArrayList<>();
		Iterator<Integer> it = listadoJugadores.iterator();
		Jugador auxiliar = null;
		while (it.hasNext()) {
			auxiliar = Simulador.getMercado().getListadoJugadores().buscar((int)it.next());
			retorno.add(auxiliar);
		}
		return retorno;
	}

	public String listadoJugadores(boolean ingresoAdmin) {
		ArrayList<Jugador> lista = listado();
		Collections.sort(lista, new ComparacionPosicion());
		StringBuilder retorno = new StringBuilder();
		for (int i=0; i<lista.size(); i++) {
			if (ingresoAdmin) {
				retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
			} else {
				if (lista.get(i).getEstado()) {
					retorno.append(lista.get(i).toString() + "\n\n");
				}
			}
		}
		return retorno.toString();
	}

	public Iterator<Integer> getIterator() {
		return listadoJugadores.iterator();
	}


	public boolean hayEspacioEnPosicion(String posicionJugador) {
		if (posicionJugador.equals("PO")) {
			return hayEspacioPortero();
		} else {
			if (posicionJugador.equals("DFC") || posicionJugador.equals("LI") || posicionJugador.equals("LD")) {
				return hayEspacioDefensor();
			} else {
				if (posicionJugador.equals("MC") || posicionJugador.equals("MI") || posicionJugador.equals("MD") || posicionJugador.equals("MCO")) {
					return hayEspacioMediocampista();
				} else {
					return hayEspacioDelantero();
				}
			}
		}
	}

	private boolean hayEspacioDelantero() {
		if (cantidadDelanteros <3) {
			return true;
		}
		return false;
	}

	private boolean hayEspacioMediocampista() {
		if (cantidadMediocampistas <3) {
			return true;
		}
		return false;
	}

	private boolean hayEspacioDefensor() {
		if (cantidadDefensores <4) {
			return true;
		}
		return false;
	}

	private boolean hayEspacioPortero() {
		if (cantidadPorteros == 0) {
			return true;
		}
		return false;
	}

	public Jugador getJugador(int jugX) {
		ArrayList<Jugador> aux = listado();
		Collections.sort(aux, new ComparacionID());
		return aux.get(jugX);
	}
}
