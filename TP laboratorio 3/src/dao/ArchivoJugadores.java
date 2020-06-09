package dao;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

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
		System.out.println("id: "+(int)idRecibido);
		Jugador auxiliar = new Jugador();
		try {
			ObjectInputStream archivo = new ObjectInputStream(new FileInputStream(super.getNombreArchivo()));
			System.out.println("id(try): "+(int)idRecibido);
			if (idRecibido==0) {
				auxiliar = (Jugador) archivo.readObject();
			} else {
				for (int i = 0; i<(int)idRecibido+1; i++) {
					System.out.println("id(for): "+(int)idRecibido);
					auxiliar = (Jugador) archivo.readObject();
					System.out.println(auxiliar.toString());
				}
			}
			archivo.close();
		}
		catch (Exception e) {
			
		}
		return auxiliar; //TODO programar método que devuelve el jugador correspondiente al ID
	}
}
