package dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import Clases.ContenedorPersonaFutbol;
import Clases.Jugador;

/**
 * Clase utilizada para la gestión del archivo de jugadores. Cuenta con los métodos necesarios
 * para tal fin.
 * Hereda de la clase ArchivoAbstracto
 */
public class ArchivoJugadores extends ArchivoAbstracto<ContenedorPersonaFutbol<Jugador>>{
	
	/**
	 * Constructor vacío que llama al constructor de la clase padre con el nombre del archivo a gestionar
	 */
	public ArchivoJugadores () {
		super ("Jugadores.dat");
	}
	
	/**
	 * Método que llama a la función de carga de datos
	 * @param listadoJugadoresRecibido listado a cargar de datos
	 */
	public void cargarListadoJugadores (ContenedorPersonaFutbol<Jugador> listadoJugadoresRecibido) {
		leer(listadoJugadoresRecibido);
		Jugador.setCantidadJugadores(listadoJugadoresRecibido.cantidad());
	}

	/**
	 * Método que recibe un listado de jugadores y guarda sus elementos en el archivo
	 */
	@Override
	public void guardar(ContenedorPersonaFutbol<Jugador> objetoAGuardar) {
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(super.getNombreArchivo()));
			Iterator<Jugador> it = objetoAGuardar.getIterator();
			while (it.hasNext()) {
				archivo.writeObject(it.next());
			}
			archivo.close();
		}
		catch (EOFException e){
			System.out.println("EOF exception.");
		}
		catch (FileNotFoundException e) {
			System.out.println("Archivo '" + super.getNombreArchivo() + "' no encontrado.");
		}
		catch (IOException e) {
			System.out.println("IOException.");
		}
	}

	/**
	 * Método que carga los datos desde el archivo al contenedor de jugadores recibido
	 * @param contenedorRecibido contenedor a cargar de datos
	 */
	@Override
	public void leer(ContenedorPersonaFutbol<Jugador> contenedorRecibido)  {
		try {
			Jugador aux;
			FileInputStream fileInputStream = new FileInputStream(super.getNombreArchivo());
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			while ((aux = (Jugador) inputStream.readObject()) != null) {
				contenedorRecibido.agregar(aux);
			}
			inputStream.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Archivo '" + super.getNombreArchivo() + "' no encontrado.");
		}
		catch (EOFException e) {
			System.out.println("Fin de carga desde el archivo '" + super.getNombreArchivo() + "'.");
		}
		catch (IOException e) {
			System.out.println("IOException..");
		}
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException.");
		}
	}
}
