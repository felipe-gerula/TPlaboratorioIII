package dao;

public abstract class ArchivoAbstracto <V>{
	private String nombreArchivo;

	public ArchivoAbstracto(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public String getNombreArchivo () {
		return this.nombreArchivo;
	}
	
	public abstract boolean guardar(V objetoAGuardar);

	public abstract boolean leer(V objetoALeer);
	
}
