package excepciones;

public class MismoRivalUsuarioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 439115354130915140L;

	public MismoRivalUsuarioException(String mensajeError) {
		super(mensajeError);
	}
	
	public static boolean mismoUsuarioRival (String usuario, String rival) throws MismoRivalUsuarioException{
		if (usuario.equals(rival)) {
			throw new MismoRivalUsuarioException("El usuario logueado está intentando jugar un partido contra sí mismo.");
		}
		return true;
	}

}
