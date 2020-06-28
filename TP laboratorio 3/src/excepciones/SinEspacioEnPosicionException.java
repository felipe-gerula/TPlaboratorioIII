package excepciones;

/**
 *  Clase que extiende de Exception. Utilizada en los casos en los que se intente agregar un jugador
 *  a una plantilla que ya tiene ocupados todos los espacios para la misma o sus aleda�as. Se maneja por
 *  zonas del campo de juego
 */
public class SinEspacioEnPosicionException extends Exception{
	
	private static final long serialVersionUID = -8940924120787242438L;

	/**
	 * Constructor de la clase-excepci�n
	 * @param mensajeError mensaje a mostrar como parte de la excepci�n
	 */
	public SinEspacioEnPosicionException(String mensajeError) {
		super(mensajeError);
	}
	
	/**
	 * M�todo que devuelve un mensaje de excepci�n
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
