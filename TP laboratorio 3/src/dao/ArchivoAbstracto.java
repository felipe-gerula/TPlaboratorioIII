package dao;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public abstract class ArchivoAbstracto <V>{
	private String nombreArchivo;
	
	public String getNombreArchivo () {
		return this.nombreArchivo;
	}
	
	public ArchivoAbstracto(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public boolean guardar(V objetoAGuardar) {
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
			archivo.writeObject(objetoAGuardar);
			archivo.close();
		}
		catch (Exception e){
			
		}
		return true;
	}
	
	public boolean modificar(V objetoAModificar) {
		//TODO programar m�todo
		//Usamos el equals, tanto para DTs y Jugadores (equals de Integer por los ID) como para Usuarios y Admins (equals por nombre)
		//Una vez encontrado, se sobreescribe el objeto
		return true;
	}
	
}
