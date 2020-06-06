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
	private double fondos;
	private DirectorTecnico dtClub;
	private Estadio estadio; 
	private String camiseta;
	
	public ClubUsuario(String nombreClub, String nuevaCamiseta, Estadio nuevoEstadio) {
		this.nombreClub = nombreClub;
		camiseta = nuevaCamiseta;
		estadio = nuevoEstadio;
		fondos = 100000;
		plantillaClub = new Plantilla();
		dtClub = new DirectorTecnico();
	}
	
	public DirectorTecnico getDTClub () {
		return dtClub;
	}
	
	public Plantilla getPlantillaClub () {
		return plantillaClub;
	}
	
	public String getNombre () {
		return nombreClub;
	}

	public boolean comprarJugador (int idJugador) {
		return true; //TODO programar m�todo
	}
	
	public boolean venderJugador (int idJugador) {
		return true; //TODO programar m�todo
	}
	
	public Double jugarPartido () {
		return (Double)0.00; //TODO programar m�todo
	}
	
	@Override
	public void listadoOpciones() {
		System.out.println("\n\nBienvenido al Club " /*+ nombreClub*/);
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Acceder al Mercado.");
		System.out.println("    2. Jugar Partido.");
		System.out.println("    3. Modificar informaci�n de Club.");
		System.out.println("    4. Listado de informaci�n de Club.");
		System.out.println("    5. Ver Plantilla.");
		System.out.println("    6. Regresar al Men� de Usuario.");
		System.out.println("");
		ingresarAOpcion();
	}
	@Override
	public void ingresarAOpcion() {
		int opcion = 0; //TODO sacar inicializaci�n
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>6) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				Simulador.getMercado().listadoOpcionesMercado(this);
				listadoOpciones();
				break;
			case 2:
				//jugar partido;
				listadoOpciones();
				break;
			case 3:
				//modificar info club;
				listadoOpciones();
				break;
			case 4:
				//listado info club;
				listadoOpciones();
				break;
			case 5:
				//ver plantilla;
				listadoOpciones();
				break;
			default:
				regresar();
				break;
		}
		
	}
	@Override
	public void regresar() {
		System.out.println("Regresando al Men� de Usuario.");
	}
	
}
