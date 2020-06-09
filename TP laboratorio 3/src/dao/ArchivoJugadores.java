package dao;

import Clases.ContenedorPersonaFutbol;
import Clases.Jugador;

public class ArchivoJugadores extends ArchivoAbstracto<Jugador>{
	public ArchivoJugadores () {
		super ("Jugadores.dat");
	}
	
	public boolean guardar(Jugador jugadorAGuardar) {
		return super.guardar(jugadorAGuardar);
	}
	
	public boolean modificar (Jugador jugadorAModificar) {
		return super.modificar(jugadorAModificar);
	}
	
	public void cargarListadoJugadores (ContenedorPersonaFutbol<Integer> listadoJugadoresRecibido) {
		String archivo = super.getNombreArchivo();
		// While !EOF cargar IDs al listado
	}
	
	public Jugador consultarID (Integer idRecibido) {
		return null; //TODO programar método que devuelve el jugador correspondiente al ID
	}
}
