package dao;

/**
 * Clase abstracta genérica utilizada para el manejo de archivos de objetos. Cuenta con un atributo y 
 * con los métodos necesarios para cumplir su función
 *
 * @param <V> Tipo almacenado en el archivo
 */
public abstract class ArchivoAbstracto <V>{
	private String nombreArchivo;

	/**
	 * Constructor que recibe el nombre del archivo a manejar
	 * @param nombreArchivo nombre del archivo a manejar
	 */
	public ArchivoAbstracto(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	/**
	 * @return nombre del archivo con el que se instanció la clase
	 */
	public String getNombreArchivo () {
		return this.nombreArchivo;
	}
	
	/**
	 * Método para almacenar un nuevo objeto
	 * @param objetoAGuardar objeto a ser almacenado
	 */
	public abstract void guardar(V objetoAGuardar);

	/**
	 * Método para almacenar datos en un objeto
	 * @param objetoALeer objeto en el que serán almacenados los datos
	 */
	public abstract void leer(V objetoALeer);
	
}
