package Clases;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Iterator;

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
	
	public boolean estaVacio () {
		return listadoPersonaFutbol.isEmpty();
	}
	
	public boolean agregar (V nuevoElemento) { //TODO hace un throws de ID erróneo (suponemos que no debería pasar)
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
	 * @return Este método devuelve un ArrayList con los elementos cargados en el listado. Desde afuera se decide cómo ordenarlos para la muestra, y si mostrar todos o los que no están dados de baja
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

	public String opcionesListado() {
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Ver todos.");
		System.out.println("    2. Opcion 2.");
		System.out.println("    3. Opcion 3.");
		System.out.println("    4. Opcion 4.");
		// TODO hacer funciones
		return ingresarAOpcionesListado();
	}
	
	public String ingresarAOpcionesListado() {
		System.out.println("  Ingrese el número de opción deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>4) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1: //Ver Todos
				return this.toString();
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
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
