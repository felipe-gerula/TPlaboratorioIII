package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo ClubUsuario
 *  nos permite armar nuestro equipo, ademas de jugar partidos con el mismo
 *  @author 
 */
import Interfaces.IMenu;

public class ClubUsuario implements IMenu{
	private String nombreClub;
	private Plantilla plantillaClub;
	private Double fondos;
	private DirectorTecnico dtClub;
	private Estadio estadio; 
	private String camiseta;
	
	public boolean comprarJugador (int idJugador) {
		return true; //TODO programar método
	}
	
	public boolean venderJugador (int idJugador) {
		return true; //TODO programar método
	}
	
	public Double jugarPartido () {
		return (Double)0.00; //TODO programar método
	}
	
	@Override
	public String listadoOpciones() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void ingresarAOpcion() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void regresar() {
		// TODO Auto-generated method stub
		
	}
	
}
