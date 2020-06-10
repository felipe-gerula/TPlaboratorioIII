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
import Clases.GestionAdministrador;

public class ArchivoAdministradores extends ArchivoAbstracto<ContenedorPersonaSistema<GestionAdministrador>>{
	public ArchivoAdministradores () {
		super ("Administradores.dat");
	}

	public void cargarListadoAdministradores (ContenedorPersonaSistema<GestionAdministrador> listadoAdministradoresRecibido) {
		leer(listadoAdministradoresRecibido);
	}
	
	@Override
	public boolean guardar(ContenedorPersonaSistema<GestionAdministrador> objetoAGuardar) {
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(super.getNombreArchivo()));
			Iterator<GestionAdministrador> it = objetoAGuardar.getIterator();
			while (it.hasNext()) {
				archivo.writeObject(it.next());
			}
			archivo.close();
		}
		catch (Exception e){
			
		}
		return true;
	}

	@Override
	public boolean leer(ContenedorPersonaSistema<GestionAdministrador> contenedorRecibido) {
		try {
			GestionAdministrador aux;
			FileInputStream fileInputStream = new FileInputStream(super.getNombreArchivo());
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			while ((aux = (GestionAdministrador) inputStream.readObject()) != null) {
				contenedorRecibido.agregarElemento(aux);
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
			e.printStackTrace();
		}
		return true;
	}
	
}
