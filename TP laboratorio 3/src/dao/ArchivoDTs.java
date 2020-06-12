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

public class ArchivoDTs extends ArchivoAbstracto<ContenedorPersonaFutbol<DirectorTecnico>>{
	
	public ArchivoDTs () {
		super ("DirectoresTecnicos.dat");
	}

	public void cargarListadoDTs(ContenedorPersonaFutbol<DirectorTecnico> listadoDTsRecibido) {
		leer(listadoDTsRecibido);
		DirectorTecnico.setCantidadDTs(listadoDTsRecibido.cantidad());
	}
	
	@Override
	public boolean guardar(ContenedorPersonaFutbol<DirectorTecnico> objetoAGuardar) {
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
		return true;
	}

	@Override
	public boolean leer(ContenedorPersonaFutbol<DirectorTecnico> contenedorRecibido)  {
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
		return true;
	}
	
}
