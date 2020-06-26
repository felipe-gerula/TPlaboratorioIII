package excepciones;

public class RivalNoEncontradoException extends Exception{

	private String nombreRival;
	private static final long serialVersionUID = -2987277430178022836L;

	public RivalNoEncontradoException (String mensaje, String nombreUsuario) {
		super (mensaje);
		this.nombreRival = nombreUsuario;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + " El usuario " + nombreRival + " no fue encontrado.";
	}
	
}
