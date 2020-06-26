package Clases;

/** 
 * Esta clase nos permite crear objetos del tipo Usuario, la cual cuenta con los atributos y m�todos 
 * necesarios para su gesti�n. Permite crear y controlar las opciones del club de cada Usuario.
 * Hereda de PersonaSistema e implementa la interfaz IMenu
 */
public class GestionUsuario extends PersonaSistema{

	private static final long serialVersionUID = 1L;
	private ClubUsuario clubUsuario;
	
	/**
	 * Constructor con nombre y contrase�a
	 * @param nombreUsuario nombre del nuevo usuario
	 * @param passUsuario contrase�a del nuevo usuario
	 */
	public GestionUsuario(String nombreUsuario, String passUsuario) {
		super(nombreUsuario, passUsuario);
		clubUsuario = null; //Cuando se ingresa al Usuario, se controla si el club existe o no
	}	
	
	/**
	 * Constructor con nombre solamente. Se usa para m�todos como el de b�squeda, en el que la contrase�a no interesa
	 * @param nombreUsuario nombre del nuevo usuario
	 */
	public GestionUsuario(String nombreUsuario) {
		super (nombreUsuario);
	}
	
	/**
	 * Constructor vac�o que llama al constructor vac�o de la clase padre, PersonaSistema
	 */
	public GestionUsuario() {
		super();
	}

	/**
	 * @return club de la instancia de usuario
	 */
	public ClubUsuario getClubUsuario() {
		return clubUsuario;
	}
	
	/**
	 * @param clubRecibido nuevo club de la instancia de usuario
	 */
	public void setClubUsuario(ClubUsuario clubRecibido) {
		this.clubUsuario = clubRecibido;
	}
	
	/**
	 * Permite al usuario reci�n ingresado al sistema crear un club
	 * @return el club creado por el usuario
	 */
	public ClubUsuario crearClub () {
		System.out.println("Bienvenido al men� de creaci�n de Club.");
		System.out.println("  Ingrese el nombre del Club:");
		String nombreClub = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre del equipo de la Camiseta:");
		String nuevaCamiseta = Simulador.getScanner().nextLine();
		Estadio nuevoEstadio = new Estadio();
		nuevoEstadio = nuevoEstadio.crearEstadio();
		ClubUsuario nuevoClub = new ClubUsuario(nombreClub, nuevaCamiseta, nuevoEstadio);
		return nuevoClub;
	}
	
	/**
     * M�todo de cambio de nombre del usuario. Controla que el nuevo nombre no exista
     * @return true si el nombre es cambiado con �xito. False si no pudo ser cambiado
     */
	public boolean cambiarNombre(ContenedorPersonaSistema<GestionUsuario> listadoRecibido) {
		Simulador.getScanner().nextLine();
		char opcion = 's';
		while (opcion == 's' || opcion == 'S') {
			System.out.println("  Ingrese el nuevo nombre deseado: ");
			String nuevoNombre = Simulador.getScanner().nextLine();
			GestionUsuario buscado = new GestionUsuario(nuevoNombre);
			if (listadoRecibido.buscarElemento(buscado) == null) {
				this.setNombre(nuevoNombre);
				System.out.println("  Nombre de usuario cambiado a " + nuevoNombre + " con �xito.");
				return true;
			} else {
				System.out.println("  El nombre de usuario " + nuevoNombre + " ya est� siendo utilizado.");
				System.out.println("  �Desea intentar con otro nombre? (s para confirmar): ");
				opcion = Simulador.getScanner().nextLine().charAt(0);
			}
		}
		return false;
	}

	/**
	 * M�todo para el ingreso de datos y la creaci�n de un nuevo Usuario
	 * @return el nuevo usuario
	 */
	@Override
	public PersonaSistema crearPersona() {
		GestionUsuario nuevoUsuario;
		System.out.println("Bienvenido al men� de creaci�n de Usuario.");
		System.out.println("  Ingrese el nombre de Usuario:");
		Simulador.getScanner().nextLine();
		String nombreUsuario = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la contrase�a del Usuario " + nombreUsuario + ": ");
		//Simulador.getScanner().nextLine();
		String passUsuario = Simulador.getScanner().nextLine();
		nuevoUsuario = new GestionUsuario(nombreUsuario, passUsuario);
		return nuevoUsuario;
	}

	/**
	 * M�todo para la creaci�n de un usuario s�lo con su nombre
	 * @return la instancia del usuario con su nombre
	 */
	@Override
	public PersonaSistema acceder() {
		GestionUsuario ingresoUsuario;
		System.out.println("Bienvenido al men� de ingreso a Usuario.");
		System.out.println("  Ingrese el nombre de Usuario:");
		Simulador.getScanner().nextLine();
		String nombreUsuario = Simulador.getScanner().nextLine();
		ingresoUsuario = new GestionUsuario(nombreUsuario);
		return ingresoUsuario;
	}


}