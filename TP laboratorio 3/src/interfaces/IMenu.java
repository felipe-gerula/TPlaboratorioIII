package interfaces;
/** 
 *  Interfaz de m�todos para las clases que tengan un men�
 *  @author 
 */
public interface IMenu {
	
	/**
	 * Listado de opciones de men�
	 */
	public void listadoOpciones(); ///Optamos por mostrar el listado dentro del m�todo
	
	/**
	 * M�todo de acceso a la opci�n deseada
	 */
	public void ingresarAOpcion();
	
	/**
	 * M�todo utilizado para salir del men�
	 */
	public void regresar();
}
