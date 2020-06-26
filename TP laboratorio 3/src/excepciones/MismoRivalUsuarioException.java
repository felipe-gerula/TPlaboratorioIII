package excepciones;

public class MismoRivalUsuarioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 439115354130915140L;

	public MismoRivalUsuarioException(String mensajeError) {
		super(mensajeError);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + " Un usuario no puede enfrentarse a sí mismo.";
	}

}
