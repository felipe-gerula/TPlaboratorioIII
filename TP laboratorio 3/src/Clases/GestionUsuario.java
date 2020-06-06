package Clases;


import Interfaces.IMenu;

/** 
 *  Esta clase nos permite crear objetos de tipo Usuario
 *  @author 
 */
public class GestionUsuario extends PersonaSistema{ //TODO cambiar a GestionUsuario

	private ClubUsuario clubUsuario;
	
	public GestionUsuario(String nombreUsuario, String passUsuario) {
		super(nombreUsuario, passUsuario);
		clubUsuario = null; //Cuando se ingresa al Usuario, se controla si el club existe o no
	}
	
	public GestionUsuario() {
		super();
	}

	public ClubUsuario getClubUsuario() {
		return clubUsuario;
	}
	
	public void setClubUsuario(ClubUsuario clubRecibido) {
		this.clubUsuario = clubRecibido;
	}
	
	public ClubUsuario crearClub () {
		System.out.println("Bienvenido al menú de creación de Club.");
		System.out.println("  Ingrese el nombre del Club:");
		String nombreClub = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre del equipo de la Camiseta:");
		String nuevaCamiseta = Simulador.getScanner().nextLine();
		Estadio nuevoEstadio = new Estadio();
		nuevoEstadio = nuevoEstadio.crearEstadio();
		ClubUsuario nuevoClub = new ClubUsuario(nombreClub, nuevaCamiseta, nuevoEstadio);
		return nuevoClub;
	}
	
	public GestionUsuario(String nombreUsuario) {
		super (nombreUsuario);
	}

	@Override
	public PersonaSistema crearPersona() {
		GestionUsuario nuevoUsuario;
		System.out.println("Bienvenido al menú de creación de Usuario.");
		System.out.println("  Ingrese el nombre de Usuario:");
		Simulador.getScanner().nextLine();
		String nombreUsuario = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la contraseña del Usuario " + nombreUsuario + ": ");
		//Simulador.getScanner().nextLine();
		String passUsuario = Simulador.getScanner().nextLine();
		nuevoUsuario = new GestionUsuario(nombreUsuario, passUsuario);
		return nuevoUsuario;
	}

	@Override
	public PersonaSistema acceder() {
		GestionUsuario ingresoUsuario;
		System.out.println("Bienvenido al menú de ingreso a Usuario.");
		System.out.println("  Ingrese el nombre de Usuario:");
		Simulador.getScanner().nextLine();
		String nombreUsuario = Simulador.getScanner().nextLine();
		ingresoUsuario = new GestionUsuario(nombreUsuario);
		return ingresoUsuario;
	}


}