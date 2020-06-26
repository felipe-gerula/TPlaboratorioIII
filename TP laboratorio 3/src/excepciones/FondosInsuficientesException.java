package excepciones;

public class FondosInsuficientesException extends Exception{
	
	private static final long serialVersionUID = 8174757789769433068L;
	private double fondosNecesarios;
	
	public FondosInsuficientesException(String mensajeError, double fondosNecesarios) {
		super(mensajeError);
		this.fondosNecesarios = fondosNecesarios;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + " Monedas necesarias: $" + fondosNecesarios;
	}
	
}
