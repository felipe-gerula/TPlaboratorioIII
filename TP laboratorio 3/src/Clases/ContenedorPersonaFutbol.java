package Clases;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashSet;

import java.util.Iterator;

import comparaciones.ComparacionCalificacion;
import comparaciones.ComparacionEdad;
import comparaciones.ComparacionID;
import comparaciones.ComparacionMovimientosHabiles;
import comparaciones.ComparacionNombre;
import comparaciones.ComparacionPosicion;
import comparaciones.ComparacionPrecio;
import comparaciones.ComparacionTipo;

/**
 * Clase que permite la creación de Contenedores de Jugadores o DTs, la cual cuenta con los atributos y métodos 
 * necesarios para su gestión.
 *
 */

public class ContenedorPersonaFutbol<V extends PersonaFutbol> {
	HashSet<V> listadoPersonaFutbol;
	
	/**
	 * Constructor vacío que inicia el hashSet
	 */
	public ContenedorPersonaFutbol (){
		listadoPersonaFutbol = new HashSet<V>();
	}
	
	/**
	 * Método que devuelve el Iterator del hashSet
	 * @return el Iterator del hashSet
	 */
	public Iterator<V> getIterator () {
		Iterator<V> it = listadoPersonaFutbol.iterator();
		return it;
	}
	
	/**
	 * Método que permite saber la cantidad de elementos cargados en el contenedor
	 * @return tamaño del contenedor
	 */
	public int cantidad() {
		return listadoPersonaFutbol.size();
	}
	
	/**
	 * Método que permite saber si el contenedor tiene elementos
	 * @return true si el listado está vacío, false si contiene al menos un válido
	 * @param ingresoAdmin determina si se accede al método como Administrador, en cuyo caso no se tienen en cuenta los estados
	 */
	public boolean estaVacio (boolean ingresoAdmin) {
		if (listadoPersonaFutbol.isEmpty()) {
			return true; //Si el listado está vacío
		} else {
			if (ingresoAdmin) { //Si el listado no está vacío, y se ingresó desde el Admin. No se tienen en cuenta los estados
				return false;
			}
			Iterator<V> it = listadoPersonaFutbol.iterator();
			while (it.hasNext()) {
				if (it.next().getEstado() == true) {
					return false; //Si hay alguno válido
				}
			}
		}
		return true; //Si no hay ningún válido
	}
	
	/**
	 * Método que permite agregar un elemento al contenedor
	 * @param nuevoElemento elemento a agregar
	 * @return true si se agrega correctamente, false si no
	 */
	public boolean agregar (V nuevoElemento) {
		return listadoPersonaFutbol.add(nuevoElemento);
	}
	
	
	/**
	 * Método que busca un elemento por su ID
	 * @param idBuscado ID del elemento a buscar
	 * @return elemento buscado por ID. Null si no se encuentra
	 */
	public V buscar (int idBuscado) {
		Iterator<V> it = listadoPersonaFutbol.iterator();
		boolean encontrado = false;
		V auxiliar = null;
		while (it.hasNext() && !encontrado) {
			auxiliar = it.next();
			if (auxiliar.getID() == idBuscado) {
				encontrado = true;
			}
		}
		return auxiliar;
	}
	
	/**
	 * Método que devuelve un ArrayList con los elementos cargados en el listado. Desde afuera se decide cómo 
	 * ordenarlos para la muestra, y si mostrar todos o los que no están dados de baja
	 * @return ArrayList con los elementos cargados en el listado.
	 */
	public ArrayList<V> listado () {
		ArrayList<V> retorno = new ArrayList<>();
		Iterator<V> it = listadoPersonaFutbol.iterator();
		V auxiliar = null;
		while (it.hasNext()) {
			auxiliar = it.next();
			retorno.add(auxiliar);
		}
		return retorno;
	}
	
	/**
	 * Método que lista las opciones de muestra de los elementos del tipo Jugador.
	 * Si bien la clase es genérica, sabemos que hay o Jugadores o DTs
	 * @param ingresoAdmin determina si se ingresa al método desde un Administrador
	 * @return muestra de elementos
	 */
	public String opcionesListadoJugador(boolean ingresoAdmin) {
		Simulador.esperar();
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Ordenar por ID (menor a mayor).");
		System.out.println("    2. Ordenar por Calificación (mayor a menor).");
		System.out.println("    3. Ordenar por Tipo (bronce a especial).");
		System.out.println("    4. Ordenar por Posición (arqueros, defensores, mediocampistas, delanteros).");
		System.out.println("    5. Ordenar por Precio (menor a mayor).");
		System.out.println("    6. Ordenar por Movimientos Hábiles (menor a mayor).");
		System.out.println("    7. Ordenar por Edad (menor a mayor).");
		System.out.println("    8. Ordenar por Nombre y Apellido.\n");
		return ingresarAOpcionesListadoJugador(ingresoAdmin);
	}
	
	/**
	 * Acceso a las opciones de muestra de los elementos del tipo Jugador
	 * @param ingresoAdmin determina si se ingresa al método desde un Administrador
	 * @return muestra de elementos
	 */
	public String ingresarAOpcionesListadoJugador(boolean ingresoAdmin) {
		System.out.println();
		int opcion = Simulador.ingresoOpcion(1, 8);
		ArrayList<V> lista = listado();
		StringBuilder retorno = new StringBuilder();
		switch (opcion) {
			case 1: //Ordenar por ID
				Collections.sort(lista, new ComparacionID());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 2: //Ordenar por calificación
				Collections.sort(lista, new ComparacionCalificacion());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 3: //Ordenar por tipo
				Collections.sort(lista, new ComparacionTipo());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 4:
				Collections.sort(lista, new ComparacionPosicion());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 5: //Ordenar por precio
				Collections.sort(lista, new ComparacionPrecio());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 6: //Ordenar por movimientos hábiles
				Collections.sort(lista, new ComparacionMovimientosHabiles());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 7: //Ordenar por edad
				Collections.sort(lista, new ComparacionEdad());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 8: //Ordenar por nombre y apellido
				Collections.sort(lista, new ComparacionNombre());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			default:
				System.out.println("Gracias por ver el Mercado.");
				break;
		}
		return "";
	}
	
	/**
	 * Método que da las opciones de listado de DTs
	 * @param ingresoAdmin determina si se accede al método desde un Administrador
	 * @return muestra del listado
	 */
	public String opcionesListadoDT(boolean ingresoAdmin) {
		Simulador.esperar();
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Ordenar por ID (menor a mayor).");
		System.out.println("    2. Ordenar por Tipo.");
		System.out.println("    3. Ordenar por Nombre y Apellido.");
		System.out.println("    4. Ordenar por Edad (menor a mayor).");
		System.out.println("    5. Ordenar por Precio (menor a mayor).\n");
		return ingresarAOpcionesListadoDT(ingresoAdmin);
	}
	
	/**
	 * Acceso a la opción de muestra deseada
	 * @param ingresoAdmin determina si se accede al método desde un Administrador
	 * @return muestra del listado
	 */
	public String ingresarAOpcionesListadoDT(boolean ingresoAdmin) {
		int opcion = Simulador.ingresoOpcion(1, 5);
		System.out.println();
		ArrayList<V> lista = listado();
		StringBuilder retorno = new StringBuilder();
		switch (opcion) {
			case 1: //Ordenar por ID
				Collections.sort(lista, new ComparacionID());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 2: //Ordenar por calificación
				Collections.sort(lista, new ComparacionTipo());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 3: //Ordenar por nombre y apellido
				Collections.sort(lista, new ComparacionNombre());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 4: //Ordenar por edad
				Collections.sort(lista, new ComparacionEdad());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			case 5: //Ordenar por precio
				Collections.sort(lista, new ComparacionPrecio());
				for (int i = 0; i < lista.size(); i++) {
					if (ingresoAdmin) {
						retorno.append(lista.get(i).toString() + "\n Estado: " + lista.get(i).getEstado() + "\n\n");
					} else {
						if (lista.get(i).getEstado()) {
							retorno.append(lista.get(i).toString() + "\n\n");
						}
					}
				}
				return retorno.toString();
			default:
				System.out.println("Gracias por ver el Mercado.");
				break;
		}
		return "";
	}
	
	/**
	 * Muestra del contenedor
	 */
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		Iterator<V> it = getIterator();
		while (it.hasNext()) {
			retorno.append(it.next().toString() + "\n\n");
		}
		return retorno.toString();
	}
	
}
