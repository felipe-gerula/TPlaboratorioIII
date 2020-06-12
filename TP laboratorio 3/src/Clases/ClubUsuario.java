package Clases;
import java.io.Serializable;

/** 
 *  Esta clase nos permite crear objetos de tipo ClubUsuario
 *  nos permite armar nuestro equipo, ademas de jugar partidos con el mismo
 *  @author 
 */
import Interfaces.IMenu;

public class ClubUsuario implements IMenu, Serializable{
	private static final long serialVersionUID = 8888764086344279621L;
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
	
	public double getFondos() {
		return fondos;
	}
	public void setFondos(double fondos) {
		this.fondos = fondos;
	}

	public Estadio getEstadio() {
		return estadio;
	}

	public String getCamiseta() {
		return camiseta;
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

	public void agregarJugadorPlantilla (int nuevoID) {
		this.plantillaClub.agregarJugador(nuevoID);
	}

	public void eliminarJugadorPlantilla (int idAEliminar) {
		this.plantillaClub.eliminarJugador(idAEliminar);
	}
	
	public boolean jugadorExistentePlantilla (int idBuscado) {
		return plantillaClub.jugadorEncontrado(idBuscado);
	}
	
	public double jugarPartido () {
		return (Double)0.00; //TODO programar m�todo
	}
	
	@Override
	public void listadoOpciones() {
		//TODO controlar que los jugadores de la plantilla est�n dados de alta. Si no, dar las monedas y elminar el id
		//TODO sincronizar la informaci�n del DT: cambiar todo menos la vestimenta, y ver que est� dado de alta.
		System.out.println("\n\nBienvenido al Club " /*+ nombreClub*/);
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Acceder al Mercado.");
		System.out.println("    2. Jugar Partido.");
		System.out.println("    3. Modificar informaci�n de Club.");
		System.out.println("    4. Listado de informaci�n de Club.");
		System.out.println("    5. Ver Plantilla y Director T�cnico.");
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
				System.out.println(this.toString());
				listadoOpciones();
				break;
			case 5:
				verPlantilla();
				listadoOpciones();
				break;
			default:
				regresar();
				break;
		}
		
	}
	
	private void verPlantilla() {
		if (this.dtClub.getEstado()) {
			System.out.println("\n\nInformaci�n del Director T�cnico: ");
			System.out.println(this.dtClub.toString());
		} else {
			System.out.println("\n\nNo hay un Director T�cnico en el Club.");
		}
		System.out.println("\n\nInformaci�n de Plantilla: ");
		System.out.println(plantillaClub.toString());
	}

	@Override
	public void regresar() {
		System.out.println("Regresando al Men� de Usuario.");
	}
	
	@Override
	public String toString() {
		return ("Informaci�n del Club " + getNombre() + ": \n  Fondos: $" + getFondos() +".\n  Informaci�n del Estadio: " + estadio.toString() + ".\n  Camiseta: " + camiseta + ".");
	}

	public void setDT(DirectorTecnico nuevoDT) {
		dtClub = nuevoDT;
	}
	
}
