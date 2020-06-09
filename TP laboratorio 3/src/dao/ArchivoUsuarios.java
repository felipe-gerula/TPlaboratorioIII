package dao;

import Clases.ContenedorPersonaSistema;
import Clases.GestionUsuario;

public class ArchivoUsuarios extends ArchivoAbstracto<GestionUsuario>{
	
	public ArchivoUsuarios () {
		super ("Usuarios.dat");
	}
	
	public boolean guardar(GestionUsuario usuarioAGuardar) {
		return super.guardar(usuarioAGuardar);
	}
	
	public boolean modificar (GestionUsuario usuarioAModificar) {
		return super.modificar(usuarioAModificar);
	}
	
	public void cargarListadoUsuarios (ContenedorPersonaSistema<GestionUsuario> listadoUsuariosRecibido) {
		String archivo = super.getNombreArchivo();
		// While !EOF cargar nombres y contraseñas al listado
	}
}
