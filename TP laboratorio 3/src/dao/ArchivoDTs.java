package dao;

import Clases.ContenedorPersonaFutbol;
import Clases.DirectorTecnico;

public class ArchivoDTs extends ArchivoAbstracto<DirectorTecnico>{
	
	public ArchivoDTs () {
		super ("DirectoresTecnicos.dat");
	}
	
	public boolean guardar(DirectorTecnico dtAGuardar) {
		return super.guardar(dtAGuardar);
	}
	
	public boolean modificar (DirectorTecnico dtAModificar) {
		return super.modificar(dtAModificar);
	}
	
	public void cargarListadoDTs (ContenedorPersonaFutbol<Integer> listadoDTsRecibido) {
		String archivo = super.getNombreArchivo();
		// While !EOF cargar IDs al listado
	}
	
	public DirectorTecnico consultarID (Integer idRecibido) {
		return null; //TODO programar método que devuelve el DT correspondiente al ID
	}
}
