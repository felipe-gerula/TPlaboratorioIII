package excepciones;


public class SinEspacioEnPosicionException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8940924120787242438L;

	public SinEspacioEnPosicionException(String mensajeError) {
		super(mensajeError);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
