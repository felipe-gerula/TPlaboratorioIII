package Clases;
import java.io.Serializable;
/** 
 *  Esta clase nos permite crear objetos de tipo Plantilla
 *  Es un contenedor con todos los jugadores del equipo creado
 *  Utiliza un HashSet con los ID´s de los jugadores que conforman la plantilla
 *  @ass "Plantilla FIFA 20" <a href=https://www.futbin.com/squad-builder>PlantillaFIFA 20</a>
 *  @author 
 */
import java.util.HashSet;
import java.util.Iterator;

public class Plantilla implements Serializable{
	private static final long serialVersionUID = 3139014270633585868L;
	private HashSet<Integer> listadoJugadores; ///TODO capaz que conviene que sea arreglo o arreglo JSON
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
			cantidadPorteros--;
		} else {
			if (posicionJugador.equals("DFC") || posicionJugador.equals("LI") || posicionJugador.equals("LD")) {
				cantidadDefensores--;
			} else {
				if (posicionJugador.equals("MC") || posicionJugador.equals("MI") || posicionJugador.equals("MD") || posicionJugador.equals("MCO")) {
					cantidadMediocampistas--;
				} else {
					cantidadDelanteros--;
				}
			}
		}
	}
	
	public int cantidadJugadores() {
		return listadoJugadores.size(); //TODO programar método
	}
	
	public double promedioJugadores() {
		return 0.00; //TODO programar método
	}
	
	public boolean jugadorEncontrado(int idBuscado) {
		return listadoJugadores.contains((Integer)idBuscado);
	}

	private String listadoJugadores() {
		StringBuilder retorno = new StringBuilder();
		Iterator<Integer> it = listadoJugadores.iterator();
		while (it.hasNext()) {
			retorno.append(Simulador.getMercado().getListadoJugadores().buscar(it.next()) + "\n\n");
		}
		return retorno.toString();
	}

	public Iterator<Integer> getIterator() {
		return listadoJugadores.iterator();
	}
	
	@Override
	public String toString() {
		return listadoJugadores();
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
	
}
