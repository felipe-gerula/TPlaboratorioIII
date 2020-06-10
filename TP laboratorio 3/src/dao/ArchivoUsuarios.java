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

public class ArchivoUsuarios extends ArchivoAbstracto<ContenedorPersonaSistema<GestionUsuario>>{
	
	public ArchivoUsuarios () {
		super ("Usuarios.dat");
	}

	public void cargarListadoUsuarios (ContenedorPersonaSistema<GestionUsuario> listadoUsuariosRecibido) {
		leer(listadoUsuariosRecibido);
	}
	
	@Override
	public boolean guardar(ContenedorPersonaSistema<GestionUsuario> objetoAGuardar) {
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(super.getNombreArchivo()));
			Iterator<GestionUsuario> it = objetoAGuardar.getIterator();
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
	public boolean leer(ContenedorPersonaSistema<GestionUsuario> contenedorRecibido) {
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
			System.out.println("IOException..");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
}
