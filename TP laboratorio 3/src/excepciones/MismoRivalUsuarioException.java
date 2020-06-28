package excepciones;

/**
 * Clase que extiende de Exception. Utilizada en los casos en los que un usuario intente enfrentarse a s�
 * mismo, lo que generar�a un partido no deseado por el sistema.
 */
public class MismoRivalUsuarioException extends Exception{

	private static final long serialVersionUID = 439115354130915140L;

	/**
	 * Constructor de la clase-excepci�n
	 * @param mensajeError mensaje a mostrar como parte de la excepci�n
	 */
	public MismoRivalUsuarioException(String mensajeError) {
		super(mensajeError);
	}
	
	/**
	 * M�todo que devuelve un mensaje de excepci�n
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + " Un usuario no puede enfrentarse a s� mismo.";
	}

}
