package excepciones;

/**
 * Clase que extiende de Exception. Utilizada en los casos en los que un usuario intente enfrentar a un usuario
 * que no pueda ser encontrado en el sistema.
 */
public class RivalNoEncontradoException extends Exception{

	private String nombreRival;
	private static final long serialVersionUID = -2987277430178022836L;

	/**
	 * Constructor de la clase-excepción
	 * @param mensaje mensaje a mostrar como parte de la excepción
	 * @param nombreUsuario nombre del usuario a enfrentar que no pudo ser encontrado
	 */
	public RivalNoEncontradoException (String mensaje, String nombreUsuario) {
		super (mensaje);
		this.nombreRival = nombreUsuario;
	}
	
	/**
	 * Método que devuelve un mensaje de excepción
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + " El usuario " + nombreRival + " no fue encontrado.";
	}
	
}
