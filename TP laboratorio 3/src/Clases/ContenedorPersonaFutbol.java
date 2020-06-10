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
	
	@Override
	public String toString() {
		//TODO programar, que devuelva solamente los que no están dados de baja
		return "";
	}

	public String opcionesListado() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
