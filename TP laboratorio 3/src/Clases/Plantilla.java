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
import excepciones.SinEspacioEnPosicionException;

/** 
 *  Esta clase nos permite crear objetos del tipo Plantilla, la cual cuenta con los métodos y atributos
 * necesarios para su gestión. Trabaja con los IDs de los jugadores
 */
public class Plantilla implements Serializable{
	private static final long serialVersionUID = 3139014270633585868L;
	private HashSet<Integer> listadoJugadores;
	private int cantidadPorteros;
	private int cantidadDefensores;
	private int cantidadMediocampistas;
	private int cantidadDelanteros;	
	
	/**
	 * Constructor vacío que inicia los atributos de una nueva plantilla
	 */
	public Plantilla() {
		listadoJugadores = new HashSet<>();
		cantidadPorteros = 0;
		cantidadDefensores = 0;
		cantidadMediocampistas = 0;
		cantidadDelanteros = 0;
	}	
	
	/**
	 * @return cantidad de porteros de la plantilla
	 */
	public int getCantidadPorteros() {
		return cantidadPorteros;
	}

	/**
	 * @return cantidad de defensores de la plantilla
	 */
	public int getCantidadDefensores() {
		return cantidadDefensores;
	}

	/**
	 * @return cantidad de mediocampistas de la plantilla
	 */
	public int getCantidadMediocampistas() {
		return cantidadMediocampistas;
	}

	/**
	 * @return cantidad de delanteros de la plantilla
	 */
	public int getCantidadDelanteros() {
		return cantidadDelanteros;
	}
	
	/**
	 * @return iterator del listado de jugadores
	 */
	public Iterator<Integer> getIterator() {
		return listadoJugadores.iterator();
	}

	/**
	 * Método que recibe un índice y devuelve el jugador ubicado en esa posición (ordenando la plantilla
	 * por IDs)
	 * @param jugX índice al que se accederá
	 * @return jugador que ocupa el lugar del índice en la plantilla ordenada por IDs
	 */
	public Jugador getJugador(int jugX) {
		ArrayList<Jugador> aux = listado();
		Collections.sort(aux, new ComparacionID());
		return aux.get(jugX);
	}
	
	/**
	 * Método que consulta si la plantilla está vacía
	 * @return true si la plantilla está vacía, false si no
	 */
	public boolean plantillaVacia() {
		return listadoJugadores.isEmpty();
	}
	
	/**
	 * Método que se usa para volver a contar las posiciones en la plantilla al entrar al Club.
	 * De esta forma, se evita tener valores negativos en las posiciones, y evitar que queden 
	 * espacios ocupados en posiciones vacías.
	 * Al hacerse consulta por ID, podía pasar que un delantero nunca descontara su posición 
	 * al ser cambiado por el Administrador.
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
	
	/**
	 * Método que determina si en la plantilla ya hay un jugador que tenga el mismo nombre que el
	 * nombre recibido
	 * @param nombreApellidoRecibido nombre y apellido a encontrar
	 * @return true si el nombre ya es parte de la plantilla, false si no
	 */
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
	
	/**
	 * Método que agrega un nuevo ID a la plantilla e incrementa en uno la posición del jugador en la plantilla
	 * @param idJugador ID del nuevo jugador a ser agregado a la plantilla
	 */
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
	
	/**
	 * Método que elimina un nuevo ID a la plantilla y disminuye en uno la posición del jugador en la plantilla
	 * @param idJugador ID del jugador a ser eliminado de la plantilla
	 */
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
	
	/**
	 * Método que devuelve la cantidad de jugadores, contando o no los válidos según quién ingrese al mismo
	 * @param ingresoAdmin boolean que determina si se accede desde un administrador o no
	 * @return cantidad de jugadores de la plantilla
	 */
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
	
	/**
	 * Método que devuelve la cantidad de jugadores válidos (estado == true) cargados en la plantilla
	 * @return cantidad de jugadores válidos en la plantilla
	 */
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
	 * Método que calcula un promedio de calidad de un equipo en base a las siguientes pautas sumadas:
	 *    • Promedio de calificaciones;
	 *    • 0.5 * cantidad Jugadores Especiales;
	 *    • 0.3 si el DT es de PLATA || 0.5 si es de ORO || 1 si es ESPECIAL.
	 * @param calidadDT: Tipo del DT del equipo
	 * @return valoración del equipo
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
	 * método devuelve una ArrayList con los elementos cargados en el listado. 
	 * Desde afuera se decide cómo ordenarlos para la muestra, y si mostrar todos, 
	 * o solamente aquellos que no estén dados de baja.
	 * @return una ArrayList con los elementos cargados en el listado.
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

	/**
	 * Método que devuelve el listado de los jugadores que forman parte de la plantilla.
	 * Según quién ingrese al método, muestra o no los que están dados de baja y el estado
	 * @param ingresoAdmin determina si se accede al método como administrador
	 * @return mensaje con la información de los jugadores que forman parte de la plantilla
	 */
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

	/**
	 * Método que muestra las posiciones disponibles para la compra de jugadores
	 * @param posicionJugador posición llena del jugador que se quiso cargar
	 * @return mensaje con las posiciones disponibles
	 */
	public String muestraEspaciosDisponibles (String posicionJugador) {
		StringBuilder retorno = new StringBuilder();
		retorno.append("Sin espacio en la posición de " + posicionJugador + ". Espacios disponibles:\n");
		if (cantidadPorteros==0) {
			retorno.append("  Portero: 1.");
		}
		if (cantidadDefensores<4) {
			retorno.append("  Defensores: " + (4 - cantidadDefensores) + ".\n");
		}
		if (cantidadMediocampistas<3) {
			retorno.append("  Mediocampistas: " + (3 - cantidadMediocampistas) + ".\n");
		}
		if (cantidadDelanteros<3) {
			retorno.append("  Delanteros: " + (3 - cantidadDelanteros) + ".");
		}
		return retorno.toString();
	}
	
	/**
	 * Método que comprueba que la posición del jugador pueda ser ocupada, teniendo en cuenta la zona del campo
	 * @param posicionJugador posición del jugador
	 * @throws SinEspacioEnPosicionException excepción lanzada en el caso de que se genere un colapso en la posición
	 */
	public void comprobarPosicion (String posicionJugador) throws SinEspacioEnPosicionException{
		if (posicionJugador.equals("PO") && !hayEspacioPortero(getCantidadPorteros())) {
			throw new SinEspacioEnPosicionException("No hay espacio en la posición de Portero.");
		} else {
			if ((posicionJugador.equals("DFC") || posicionJugador.equals("LI") || posicionJugador.equals("LD")) && !hayEspacioDefensor(getCantidadDefensores())) {
				throw new SinEspacioEnPosicionException("No hay espacio en la posición de Defensor.");
			} else {
				if ((posicionJugador.equals("MC") || posicionJugador.equals("MI") || posicionJugador.equals("MD") || posicionJugador.equals("MCO")) && !hayEspacioMediocampista(getCantidadMediocampistas())) {
					throw new SinEspacioEnPosicionException("No hay espacio en la posición de Mediocampista.");
				} else {
					if ((posicionJugador.equals("DC") || posicionJugador.equals("EI") || posicionJugador.equals("ED")) && !hayEspacioDelantero(getCantidadDelanteros())) {
						throw new SinEspacioEnPosicionException("No hay espacio en la posición de Delantero.");
					}
				}
			}
		}
	}

	/**
	 * Método que determina si hay espacio en la posición de delantero
	 * @param cantidadDelanteros cantidad de delanteros actual (hasta 3)
	 * @return true si hay espacio, false si no
	 */
	private boolean hayEspacioDelantero(int cantidadDelanteros) {
		if (cantidadDelanteros <3) {
			return true;
		}
		return false;
	}

	/**
	 * Método que determina si hay espacio en la posición de mediocampista
	 * @param cantidadMediocampistas cantidad de mediocampistas actual (hasta 3)
	 * @return true si hay espacio, false si no
	 */
	private boolean hayEspacioMediocampista(int cantidadMediocampistas) {
		if (cantidadMediocampistas <3) {
			return true;
		}
		return false;
	}

	/**
	 * Método que determina si hay espacio en la posición de defensor
	 * @param cantidadDefensores cantidad de defensores actual (hasta 4)
	 * @return true si hay espacio, false si no
	 */
	private boolean hayEspacioDefensor(int cantidadDefensores) {
		if (cantidadDefensores <4) {
			return true;
		}
		return false;
	}

	/**
	 * Método que determina si hay espacio en la posición de portero
	 * @param cantidadPorteros cantidad de portero actual (1 o 0)
	 * @return true si hay espacio, false si no
	 */
	private boolean hayEspacioPortero(int cantidadPorteros) {
		if (cantidadPorteros == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Método que determina si se puede agregar un jugador con la posición recibida
	 * @param posicionJugador posición del jugador a agregar
	 * @return true si se puede, false si no
	 */
	public boolean hayEspacioEnPosicion(String posicionJugador){
		try {
			comprobarPosicion(posicionJugador);
		} catch (SinEspacioEnPosicionException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
}
