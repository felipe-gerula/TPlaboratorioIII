package excepciones;

/**
 * Clase que extiende de Exception. Utilizada en los casos en los que un usuario intente enfrentarse a sí
 * mismo, lo que generaría un partido no deseado por el sistema.
 */
public class MismoRivalUsuarioException extends Exception{

	private static final long serialVersionUID = 439115354130915140L;

	/**
	 * Constructor de la clase-excepción
	 * @param mensajeError mensaje a mostrar como parte de la excepción
	 */
	public MismoRivalUsuarioException(String mensajeError) {
		super(mensajeError);
	}
	
	/**
	 * Método que devuelve un mensaje de excepción
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + " Un usuario no puede enfrentarse a sí mismo.";
	}

}
