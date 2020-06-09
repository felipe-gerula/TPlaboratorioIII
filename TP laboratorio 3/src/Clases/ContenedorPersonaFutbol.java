package Clases;

import java.util.HashSet;

public class ContenedorPersonaFutbol<V> {
	HashSet<V> listadoPersonaFutbol;
	
	public ContenedorPersonaFutbol (){
		listadoPersonaFutbol = new HashSet<V>();
	}
	
	public boolean estaVacio () {
		return listadoPersonaFutbol.isEmpty();
	}
	
	public void agregar (V nuevoID) { //TODO hace un throws de ID err�neo (suponemos que no deber�a pasar)
		listadoPersonaFutbol.add(nuevoID);
	}
	
	public boolean buscarJugador (V idBuscado) {
		return listadoPersonaFutbol.contains((Integer)idBuscado);
	}

	public String listado () {
		return this.toString();
	}
	
	@Override
	public String toString() {
		//TODO programar, que devuelva solamente los que no est�n dados de baja
		return "";
	}
	
}
