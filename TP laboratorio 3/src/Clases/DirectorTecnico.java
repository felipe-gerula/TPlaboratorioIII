package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo DirectorTecnico
 *  @author 
 */
public class DirectorTecnico extends PersonaFutbol {
	private static int cantidadTecnicos;
	private int idTecnico;
	private Vestimenta vestimentaEquipada;
	
	public DirectorTecnico() {
		super();
		idTecnico = 0;
		vestimentaEquipada = new Vestimenta();
	}
	
	public void cambiarVestimenta() {
		//TODO programar método
	}
}
