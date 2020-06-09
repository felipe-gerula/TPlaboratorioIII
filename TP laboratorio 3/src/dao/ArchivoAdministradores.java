package dao;

import Clases.ContenedorPersonaSistema;
import Clases.GestionAdministrador;

public class ArchivoAdministradores extends ArchivoAbstracto<GestionAdministrador>{
	public ArchivoAdministradores () {
		super ("Administradores.dat");
	}
	
	public boolean guardar(GestionAdministrador administradorAGuardar) {
		return super.guardar(administradorAGuardar);
	}
	
	public boolean modificar (GestionAdministrador administradorAModificar) {
		return super.modificar(administradorAModificar);
	}
	
	public void cargarListadoAdministradores (ContenedorPersonaSistema<GestionAdministrador> listadoAdministradoresRecibido) {
		String archivo = super.getNombreArchivo();
		// While !EOF cargar nombres y contraseñas al listado
	}
}
