package excepciones;

/**
 * Clase que extiende de Exception. Utilizada en los casos en los que un usuario intente comprar un jugador
 * cuyo precio sea superior a los fondos del club. De esta forma evitamos que el club tenga un valor de fondos
 * negativo, y que el usuario pueda comprar jugadores que no deber�a.
 */
public class FondosInsuficientesException extends Exception{
	
	private static final long serialVersionUID = 8174757789769433068L;
	private double fondosNecesarios;
	
	/**
	 * Constructor de la clase-excepci�n
	 * @param mensajeError mensaje a mostrar como parte de la excepci�n
	 * @param fondosNecesarios fondos que le faltan al usuario para comprar el jugador
	 */
	public FondosInsuficientesException(String mensajeError, double fondosNecesarios) {
		super(mensajeError);
		this.fondosNecesarios = fondosNecesarios;
	}
	
	/**
	 * M�todo que devuelve un mensaje de excepci�n
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + " Monedas necesarias: $" + fondosNecesarios;
	}
	
}
