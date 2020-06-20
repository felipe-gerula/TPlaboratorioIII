package excepciones;

import Clases.GestionUsuario;

public class RivalNoEncontradoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2987277430178022836L;

	public RivalNoEncontradoException (String mensaje) {
		super (mensaje);
	}
	
	public static boolean rivalNulo (GestionUsuario recibido) throws RivalNoEncontradoException{
		if (recibido == null) {
			throw new RivalNoEncontradoException("El usuario logueado intentó jugar un partido contra un usuario no existente.");
		}
		return true;
	}
	
}
