package dao;

/**
 * Clase abstracta gen�rica utilizada para el manejo de archivos de objetos. Cuenta con un atributo y 
 * con los m�todos necesarios para cumplir su funci�n
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
	 * @return nombre del archivo con el que se instanci� la clase
	 */
	public String getNombreArchivo () {
		return this.nombreArchivo;
	}
	
	/**
	 * M�todo para almacenar un nuevo objeto
	 * @param objetoAGuardar objeto a ser almacenado
	 */
	public abstract void guardar(V objetoAGuardar);

	/**
	 * M�todo para almacenar datos en un objeto
	 * @param objetoALeer objeto en el que ser�n almacenados los datos
	 */
	public abstract void leer(V objetoALeer);
	
}
