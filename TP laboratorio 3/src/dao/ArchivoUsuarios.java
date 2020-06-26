package dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import Clases.ContenedorPersonaSistema;
import Clases.GestionUsuario;

/**
 * Clase utilizada para la gestión del archivo de usuarios. Cuenta con los métodos necesarios
 * para tal fin.
 * Hereda de la clase ArchivoAbstracto
 */
public class ArchivoUsuarios extends ArchivoAbstracto<ContenedorPersonaSistema<GestionUsuario>>{
	
	/**
	 * Constructor vacío que llama al constructor de la clase padre con el nombre del archivo a gestionar
	 */
	public ArchivoUsuarios () {
		super ("Usuarios.dat");
	}

	/**
	 * Método que llama a la función de carga de datos
	 * @param listadoUsuariosRecibido listado a cargar de datos
	 */
	public void cargarListadoUsuarios (ContenedorPersonaSistema<GestionUsuario> listadoUsuariosRecibido) {
		leer(listadoUsuariosRecibido);
	}
	
	/**
	 * Método que recibe un listado de usuarios y guarda sus elementos en el archivo
	 */
	@Override
	public void guardar(ContenedorPersonaSistema<GestionUsuario> objetoAGuardar) {
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(super.getNombreArchivo()));
			Iterator<GestionUsuario> it = objetoAGuardar.getIterator();
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
	 * Método que carga los datos desde el archivo al contenedor de usuarios recibido
	 * @param contenedorRecibido contenedor a cargar de datos
	 */
	@Override
	public void leer(ContenedorPersonaSistema<GestionUsuario> contenedorRecibido) {
		try {
			GestionUsuario aux;
			FileInputStream fileInputStream = new FileInputStream(super.getNombreArchivo());
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			while ((aux = (GestionUsuario) inputStream.readObject()) != null) {
				contenedorRecibido.agregarElemento(aux);
			}
			inputStream.close();
		}
		catch (EOFException e) {
			System.out.println("Fin de carga desde el archivo '" + super.getNombreArchivo() + "'.");
		}
		catch (FileNotFoundException e) {
			System.out.println("Archivo '" + super.getNombreArchivo() + "' no encontrado.");
		}
		catch (IOException e) {
			System.out.println("IOException.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException.");
		}
	}
	
	
}
