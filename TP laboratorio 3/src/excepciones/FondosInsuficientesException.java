package excepciones;

public class FondosInsuficientesException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8174757789769433068L;

	public FondosInsuficientesException(String mensajeError) {
		super(mensajeError);
	}
	
	public static boolean comprobarFondos (double precioJugador, double fondosClub) throws FondosInsuficientesException{
		if (fondosClub >= precioJugador) {
			return true;
		} else {
			throw new FondosInsuficientesException("Fondos insuficientes. Faltan $" + (precioJugador - fondosClub));
		}
	}
}
