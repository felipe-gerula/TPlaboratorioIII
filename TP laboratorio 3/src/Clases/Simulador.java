package Clases;
/**
 * Esta clase nos permite crear objetos de tipo Simulador
 * simula el funcionamiento del juego FIFA 20
 * @add "Simulador FIFA 20" <a href=https://www.ea.com/es-es/games/fifa/fifa-20>SimuladorFIFA 20</a> 
 */
import java.util.Scanner;

import Interfaces.IMenu;
import dao.ArchivoAdministradores;
import dao.ArchivoDTs;
import dao.ArchivoJugadores;
import dao.ArchivoUsuarios;

public class Simulador implements IMenu{

	private ContenedorPersonaSistema<GestionUsuario> listadoUsuarios;
	private ContenedorPersonaSistema<GestionAdministrador> listadoAdministradores;
	private static ArchivoUsuarios archivoUsuarios;
	private static ArchivoAdministradores archivoAdministradores;
	private static ArchivoJugadores archivoJugadores;
	private static ArchivoDTs archivoDTs;
	private static Mercado mercadoDePases; //TODO hacer get y modificar usuario
	private static Scanner scan;
	///TODO scan se crea en constructor y se destruye al salir
	
	/// * * * CONSTRUCTORES * * * ///
	public Simulador() {
		listadoUsuarios = new ContenedorPersonaSistema<>();
		listadoAdministradores = new ContenedorPersonaSistema<>();
		mercadoDePases = new Mercado();
		scan = new Scanner(System.in);
		Jugador.setCantidadJugadores(0); //TODO sacar cuando esté el archivo hecho
		archivoJugadores = new ArchivoJugadores();
	}
	/// * * * FIN CONSTRUCTORES * * * ///
	
	/// * * * GETTERS * * * ///
	public static Mercado getMercado() {
		return mercadoDePases;
	}
	
	public static ArchivoAdministradores getArchivoAdministradores() {
		return archivoAdministradores;
	}
	
	public static ArchivoUsuarios getArchivoUsuarios() {
		return archivoUsuarios;
	}
	
	public static ArchivoJugadores getArchivoJugadores() {
		return archivoJugadores;
	}
	
	public static ArchivoDTs getArchivoDTs() {
		return archivoDTs;
	}
	
	public static Scanner getScanner() {
		return scan;
	}
	/// * * * FIN GETTERS * * * ///
	
	
	@Override
	public void listadoOpciones() {
		System.out.println("Bienvenido al Simulador de Mercado de FIFA 20.");
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Menú Usuario.");
		System.out.println("    2. Menú Administrador.");
		System.out.println("    3. Salir.");
		System.out.println("");
		ingresarAOpcion();
	}

	@Override
	public void ingresarAOpcion() {
		int opcion; //TODO sacar inicialización
		System.out.println("  Ingrese el número de opción deseada: ");
		opcion = scan.nextInt();
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = scan.nextInt();
		}
		switch (opcion) {
			case 1:
				listadoOpcionesUsuario();
				listadoOpciones();
				break;
			case 2:
				listadoOpcionesAdministrador();
				listadoOpciones();
				break;
			default:
				regresar();
				break;
		}
	}

	@Override
	public void regresar() {
		System.out.println("Gracias por usar el Simulador. Esperamos que vuelva pronto.");
		scan.close();
	}
	
	public void listadoOpcionesUsuario() {
		System.out.println("\n\nBienvenido al Menú de Usuario.");
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Crear Usuario.");
		System.out.println("    2. Ingresar a Usuario ya existente.");
		System.out.println("    3. Salir.");
		System.out.println("");
		ingresarAOpcionUsuario();
		
	}
	
	public void ingresarAOpcionUsuario() {
		int opcion = 0; //TODO sacar inicialización
		System.out.println("  Ingrese el número de opción deseada: ");
		opcion = scan.nextInt();
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = scan.nextInt();
		}
		switch (opcion) {
			case 1:
				GestionUsuario nuevoUsuario = new GestionUsuario();
				nuevoUsuario = (GestionUsuario)nuevoUsuario.crearPersona();
				if(listadoUsuarios.agregarElemento(nuevoUsuario)) {
					System.out.println("    Usuario agregado con éxito.");
				} else {
					System.out.println("    El nombre de usuario " + nuevoUsuario.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
					char opcionIntentar = Simulador.scan.next().charAt(0);
					while (opcionIntentar == 's' || opcionIntentar == 'S') {
						nuevoUsuario = new GestionUsuario(); ///Reseteamos los valores 
						nuevoUsuario = (GestionUsuario)nuevoUsuario.crearPersona();
						if(listadoUsuarios.agregarElemento(nuevoUsuario)) {
							System.out.println("    Usuario agregado con éxito.");
							opcionIntentar = 'n';
						} else {
							System.out.println("    El nombre de usuario " + nuevoUsuario.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
							opcionIntentar = Simulador.scan.next().charAt(0);
						}
					}
				}
				listadoOpcionesUsuario();
				break;
			case 2:
				GestionUsuario usuarioLeido = new GestionUsuario();
				usuarioLeido = (GestionUsuario)usuarioLeido.acceder();
				GestionUsuario usuarioRecibido = listadoUsuarios.buscarElemento(usuarioLeido);
				if (usuarioRecibido != null){ ///TODO analizar uso de excepciones
					if (usuarioRecibido.comparacionPassword()) {
						if (usuarioRecibido.getClubUsuario() == null) {
							usuarioRecibido.setClubUsuario(usuarioRecibido.crearClub());
						}
						usuarioRecibido.getClubUsuario().listadoOpciones();
					} else {
						System.out.println("   Se alcanzó el límite de intentos de ingreso de contraseña.");
					}
				} else {
					System.out.println("   El Usuario ingresado no existe");
				}
				listadoOpcionesUsuario();
				break;
		}
	}
	
	public void listadoOpcionesAdministrador() {
		System.out.println("\n\nBienvenido al Menú de Administrador.");
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Crear Administrador.");
		System.out.println("    2. Ingresar a Administrador ya existente.");
		System.out.println("    3. Salir.");
		System.out.println("");
		ingresarAOpcionAdministrador();
	}
	
	public void ingresarAOpcionAdministrador() {
		int opcion = 0; //TODO sacar inicialización
		System.out.println("  Ingrese el número de opción deseada: ");
		opcion = scan.nextInt();
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = scan.nextInt();
		}
		switch (opcion) {
			case 1: //TODO pedir contraseña de creación para evitar que cualquiera cree admins
				GestionAdministrador nuevoAdministrador =  new GestionAdministrador();
				nuevoAdministrador = (GestionAdministrador)nuevoAdministrador.crearPersona();
				if(listadoAdministradores.agregarElemento(nuevoAdministrador)) {
					System.out.println("    Administrador agregado con éxito.");
				} else {
					System.out.println("    El nombre de administrador " + nuevoAdministrador.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
					char opcionIntentar = Simulador.scan.next().charAt(0);
					while (opcionIntentar == 's' || opcionIntentar == 'S') {
						nuevoAdministrador = new GestionAdministrador(); ///Reseteamos los valores 
						nuevoAdministrador = (GestionAdministrador)nuevoAdministrador.crearPersona();
						if(listadoAdministradores.agregarElemento(nuevoAdministrador)) {
							System.out.println("    Administrador agregado con éxito.");
							opcionIntentar = 'n';
						} else {
							System.out.println("    El nombre de administrador " + nuevoAdministrador.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
							opcionIntentar = Simulador.scan.next().charAt(0);
						}
					}
				}
				listadoOpcionesAdministrador();
				break;
			case 2:
				GestionAdministrador administradorLeido = new GestionAdministrador();
				administradorLeido = (GestionAdministrador)administradorLeido.acceder();
				GestionAdministrador administradorRecibido = listadoAdministradores.buscarElemento(administradorLeido);
				if (administradorRecibido != null){
					if (administradorRecibido.comparacionPassword()) {
						administradorRecibido.listadoOpciones();
					} else {
						System.out.println("   Se alcanzó el límite de intentos de ingreso de contraseña.");
					}
				} else {
					System.out.println("   El Administrador ingresado no existe");
				}
				
				break;
				
		}
	}
	

}
