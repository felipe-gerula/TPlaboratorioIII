package Clases;

import java.util.HashSet;

import dao.ArchivoJugadores;

public class ContenedorPersonaFutbol<V> {
	HashSet<V> listadoPersonaFutbol;
	
	public ContenedorPersonaFutbol (){
		listadoPersonaFutbol = new HashSet<V>();
	}
	
	public boolean estaVacio () {
		return listadoPersonaFutbol.isEmpty();
	}
	
	public void agregar (V nuevoID) { //TODO hace un throws de ID erróneo (suponemos que no debería pasar)
		listadoPersonaFutbol.add(nuevoID);
	}
	
	public boolean buscarJugador (V idBuscado) {
		return listadoPersonaFutbol.contains((Integer)idBuscado);
	}

	public String listado () {
		StringBuilder retorno = new StringBuilder();
		for (int i =0; i<Jugador.getCantidadJugadores(); i++) {
			System.out.println(Simulador.getArchivoJugadores().consultarID((Integer)i).toString());
			//retorno.append(Simulador.getArchivoJugadores().consultarID((Integer)i).toString());
		}
		return retorno.toString();
	}
	
	@Override
	public String toString() {
		//TODO programar, que devuelva solamente los que no están dados de baja
		return "";
	}
	
}
