package Interfaces;
/** 
 *  Esta interfaz con permite conectar las clases relacionadas con el Menu
 *  @author 
 */
public interface IMenu {
	/**
	 * lista las opciones del menu
	 */
	public void listadoOpciones(); ///Optamos por mostrar el listado dentro del método
	/**
	 * permite al usuario ingresar la opcion deseada
	 */
	public void ingresarAOpcion();
	/**
	 * regresa al menu anterior
	 */
	public void regresar();
}
