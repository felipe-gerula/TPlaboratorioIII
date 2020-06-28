package excepciones;

/**
 *  Clase que extiende de Exception. Utilizada en los casos en los que se intente agregar un jugador
 *  a una plantilla que ya tiene ocupados todos los espacios para la misma o sus aledañas. Se maneja por
 *  zonas del campo de juego
 */
public class SinEspacioEnPosicionException extends Exception{
	
	private static final long serialVersionUID = -8940924120787242438L;

	/**
	 * Constructor de la clase-excepción
	 * @param mensajeError mensaje a mostrar como parte de la excepción
	 */
	public SinEspacioEnPosicionException(String mensajeError) {
		super(mensajeError);
	}
	
	/**
	 * Método que devuelve un mensaje de excepción
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
