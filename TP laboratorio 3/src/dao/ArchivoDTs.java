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
import Clases.DirectorTecnico;

/**
 * Clase utilizada para la gestión del archivo de DTs. Cuenta con los métodos necesarios para tal fin.
 * Hereda de la clase ArchivoAbstracto
 */
public class ArchivoDTs extends ArchivoAbstracto<ContenedorPersonaFutbol<DirectorTecnico>>{
	
	/**
	 * Constructor vacío que llama al constructor de la clase padre con el nombre del archivo a gestionar
	 */
	public ArchivoDTs () {
		super ("DirectoresTecnicos.dat");
	}

	/**
	 * Método que llama a la función de carga de datos y determina la cantidad de DTs totales
	 * @param listadoDTsRecibido listado a cargar de datos
	 */
	public void cargarListadoDTs(ContenedorPersonaFutbol<DirectorTecnico> listadoDTsRecibido) {
		leer(listadoDTsRecibido);
		DirectorTecnico.setCantidadDTs(listadoDTsRecibido.cantidad());
	}
	
	/**
	 * Método que recibe un listado de DTs y guarda sus elementos en el archivo
	 */
	@Override
	public void guardar(ContenedorPersonaFutbol<DirectorTecnico> objetoAGuardar) {
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(super.getNombreArchivo()));
			Iterator<DirectorTecnico> it = objetoAGuardar.getIterator();
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
	 * Método que carga los datos desde el archivo al contenedor de DTs recibido
	 * @param contenedorRecibido contenedor a cargar de datos
	 */
	@Override
	public void leer(ContenedorPersonaFutbol<DirectorTecnico> contenedorRecibido)  {
		try {
			DirectorTecnico aux;
			FileInputStream fileInputStream = new FileInputStream(super.getNombreArchivo());
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			while ((aux = (DirectorTecnico) inputStream.readObject()) != null) {
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
			System.out.println("IOException.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException.");
		}
	}
	
}
