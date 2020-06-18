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

public class ContenedorPersonaFutbol<V extends PersonaFutbol> {
	HashSet<V> listadoPersonaFutbol;
	
	public ContenedorPersonaFutbol (){
		listadoPersonaFutbol = new HashSet<V>();
	}
	
	public Iterator<V> getIterator () {
		Iterator<V> it = listadoPersonaFutbol.iterator();
		return it;
	}
	
	public int cantidad() {
		return listadoPersonaFutbol.size();
	}
	
	/**
	 * 
	 * @return true si el listado est� vac�o, false si contiene al menos un v�lido
	 * 
	 */
	public boolean estaVacio (boolean ingresoAdmin) {
		if (listadoPersonaFutbol.isEmpty()) {
			return true; //Si el listado est� vac�o
		} else {
			if (ingresoAdmin) { //Si el listado no est� vac�o, y se ingres� desde el Admin. No se tienen en cuenta los estados
				return false;
			}
			Iterator<V> it = listadoPersonaFutbol.iterator();
			while (it.hasNext()) {
				if (it.next().getEstado() == true) {
					return false; //Si hay alguno v�lido
				}
			}
		}
		return true; //Si no hay ning�n v�lido
	}
	
	public boolean agregar (V nuevoElemento) { //TODO hace un throws de ID err�neo (suponemos que no deber�a pasar)
		return listadoPersonaFutbol.add(nuevoElemento);
	}
	
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
	 * 
	 * @return Este m�todo devuelve un ArrayList con los elementos cargados en el listado. Desde afuera se decide c�mo ordenarlos para la muestra, y si mostrar todos o los que no est�n dados de baja
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
	
	/*public String listarLista(ArrayList<V>) {
		
	}*/

	public String opcionesListadoJugador(boolean ingresoAdmin) {
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Ordenar por ID (menor a mayor).");
		System.out.println("    2. Ordenar por Calificaci�n (mayor a menor).");
		System.out.println("    3. Ordenar por Tipo (bronce a especial).");
		System.out.println("    4. Ordenar por Posici�n (arqueros, defensores, mediocampistas, delanteros).");
		System.out.println("    5. Ordenar por Precio (menor a mayor).");
		System.out.println("    6. Ordenar por Movimientos H�biles (menor a mayor).");
		System.out.println("    7. Ordenar por Edad (menor a mayor).");
		System.out.println("    8. Ordenar por Nombre y Apellido.");
		return ingresarAOpcionesListadoJugador(ingresoAdmin);
	}
	
	public String ingresarAOpcionesListadoJugador(boolean ingresoAdmin) {
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
			case 2: //Ordenar por calificaci�n
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
			case 6: //Ordenar por movimientos h�biles
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
	
	public String opcionesListadoDT(boolean ingresoAdmin) {
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Ordenar por ID (menor a mayor).");
		System.out.println("    2. Ordenar por Tipo.");
		System.out.println("    3. Ordenar por Nombre y Apellido.");
		System.out.println("    4. Ordenar por Edad (menor a mayor).");
		System.out.println("    5. Ordenar por Precio (menor a mayor).");
		return ingresarAOpcionesListadoDT(ingresoAdmin);
	}
	
	public String ingresarAOpcionesListadoDT(boolean ingresoAdmin) {
		int opcion = Simulador.ingresoOpcion(1, 5);
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
			case 2: //Ordenar por calificaci�n
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
