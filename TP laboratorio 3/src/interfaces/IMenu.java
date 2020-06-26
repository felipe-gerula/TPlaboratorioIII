package interfaces;
/** 
 *  Interfaz de métodos para las clases que tengan un menú
 *  @author 
 */
public interface IMenu {
	
	/**
	 * Listado de opciones de menú
	 */
	public void listadoOpciones(); ///Optamos por mostrar el listado dentro del método
	
	/**
	 * Método de acceso a la opción deseada
	 */
	public void ingresarAOpcion();
	
	/**
	 * Método utilizado para salir del menú
	 */
	public void regresar();
}
