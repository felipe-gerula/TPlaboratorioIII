package Clases;
import java.util.InputMismatchException;
/**
 * Esta clase nos permite crear objetos de tipo Simulador
 * simula el funcionamiento del juego FIFA 20
 * @add "Simulador FIFA 20" <a href=https://www.ea.com/es-es/games/fifa/fifa-20>SimuladorFIFA 20</a> 
 */
import java.util.Scanner;

import dao.ArchivoAdministradores;
import dao.ArchivoDTs;
import dao.ArchivoJugadores;
import dao.ArchivoUsuarios;
import excepciones.MismoRivalUsuarioException;
import excepciones.RivalNoEncontradoException;
import interfaces.IMenu;

/** 
 *  Esta clase es la raíz del programa, nos permite crear un objeto del tipo Simulador. 
 * La misma cuenta con los métodos y atributos necesarios para el manejo del programa.
 * Se encarga de la inicialización de datos y cuenta con algunos atributos estáticos a 
 * los que se puede acceder desde cualquier parte del programa.
 */
public class Simulador implements IMenu{

	private static ContenedorPersonaSistema<GestionUsuario> listadoUsuarios;
	private static ContenedorPersonaSistema<GestionAdministrador> listadoAdministradores;
	private static ArchivoUsuarios archivoUsuarios;
	private static ArchivoAdministradores archivoAdministradores;
	private static ArchivoJugadores archivoJugadores;
	private static ArchivoDTs archivoDTs;
	private static Mercado mercadoDePases;
	private static ContenedorLigasEquipos listadoLigasEquipos;
	private static Scanner scan;
	private static String usuarioLogueado;
	
	/// * * * CONSTRUCTORES * * * ///
	
	/**
	 * Constructor vacío que inicializa todos los datos a partir de los archivos,
	 * e inicializa los demás atributos.
	 */
	public Simulador() {
		listadoUsuarios = new ContenedorPersonaSistema<>();
		listadoAdministradores = new ContenedorPersonaSistema<>();
		archivoJugadores = new ArchivoJugadores();
		archivoDTs = new ArchivoDTs();
		archivoAdministradores = new ArchivoAdministradores();
		archivoUsuarios = new ArchivoUsuarios();
		archivoUsuarios.cargarListadoUsuarios(listadoUsuarios);
		archivoAdministradores.cargarListadoAdministradores(listadoAdministradores);
		mercadoDePases = new Mercado();
		listadoLigasEquipos = new ContenedorLigasEquipos();
		mercadoDePases.cargarLigasEquipos(listadoLigasEquipos);
		scan = new Scanner(System.in);
	}
	/// * * * FIN CONSTRUCTORES * * * ///
	
	/// * * * GETTERS * * * ///
	
	/**
	 * @return listado de ligas y equipos del simulador
	 */
	public static ContenedorLigasEquipos getListadoLigasEquipos() {
		return listadoLigasEquipos;
	}
	
	/**
	 * @return mercado del simulador
	 */
	public static Mercado getMercado() {
		return mercadoDePases;
	}
	
	/**
	 * @return archivo de administradores
	 */
	public static ArchivoAdministradores getArchivoAdministradores() {
		return archivoAdministradores;
	}
	
	/**
	 * @return archivo de usuarios
	 */
	public static ArchivoUsuarios getArchivoUsuarios() {
		return archivoUsuarios;
	}
	
	/**
	 * @return archivo de jugadores
	 */
	public static ArchivoJugadores getArchivoJugadores() {
		return archivoJugadores;
	}
	
	/**
	 * @return archivo de DTs
	 */
	public static ArchivoDTs getArchivoDTs() {
		return archivoDTs;
	}
	
	/**
	 * @return scanner estático que se puede utilizar desde cualquier parte del programa
	 */
	public static Scanner getScanner() {
		return scan;
	}
	
	/// * * * FIN GETTERS * * * ///
	
	/**
	 * Método estático utilizado para actualizar los datos en el archivo de administradores
	 */
	public static void guardarArchivoAdministradores() {
		archivoAdministradores.guardar(listadoAdministradores);
	}
	
	/**
	 * Método estático utilizado para actualizar los datos en el archivo de usuarios
	 */
	public static void guardarArchivoUsuarios() {
		archivoUsuarios.guardar(listadoUsuarios);
	}
	
	/**
	 * Método estático utilizado para actualizar los datos en el archivo de jugadores
	 */
	public static void guardarArchivoJugadores() {
		archivoJugadores.guardar(mercadoDePases.getListadoJugadores());
	}
	
	/**
	 * Método estático utilizado para actualizar los datos en el archivo de DTs
	 */
	public static void guardarArchivoDTs() {
		archivoDTs.guardar(mercadoDePases.getListadoDTs());
	}
	
	/**
	 * Menú utilizado para la lectura correcta de un entero
	 * @param i límite inferior de lectura de datos
	 * @param j límite superior de lectura de datos
	 * @return dato leído
	 */
	public static int ingresoOpcion (int i, int j) {
		int opcion;
		System.out.print("  Ingrese un valor entre " + i + " y " + j + ": ");
		try {
			opcion = scan.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("  Ingresó un tipo de dato erróneo.");
			opcion = i-1;
		}
		while (opcion<i || opcion>j) {
			scan.nextLine();
			System.out.print("  Por favor ingrese un valor correcto (entre " + i + " y " + j + "): ");
			try {
				opcion = scan.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("  Ingresó un tipo de dato erróneo.");
				opcion = i-1;
			}
		}
		return opcion;
	}
	
	/**
	 * Método de listado de las opciones del simulador
	 */
	@Override
	public void listadoOpciones() {
		esperar();
		System.out.println("Bienvenido al Simulador de Mercado de FIFA 20.");
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Menú Usuario.");
		System.out.println("    2. Menú Administrador.");
		System.out.println("    3. Salir.\n");
		ingresarAOpcion();
	}

	/**
	 * Método de acceso a las opciones del simulador
	 */
	@Override
	public void ingresarAOpcion() {
		int opcion = ingresoOpcion(1, 3);
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
	
	/**
	 * Método que genera una espera en la consola. Se usa en menúes
	 */
	public static void esperar () {
		System.out.println();
		for (int i=0; i<7; i++){
	        System.out.print(" . ");
	        try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
	    }
		System.out.println();
	}

	/**
	 * Método de salida del simulador
	 */
	@Override
	public void regresar() {
		System.out.println("Gracias por usar el Simulador. Esperamos que vuelva pronto.");
		esperar();
		scan.close();
	}
	
	/**
	 * Método que lista las opciones de usuario
	 */
	public void listadoOpcionesUsuario() {
		esperar();
		System.out.println("\n\nBienvenido al Menú de Usuario.");
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Crear Usuario.");
		System.out.println("    2. Ingresar a Usuario ya existente.");
		System.out.println("    3. Salir.\n");
		ingresarAOpcionUsuario();
	}
	
	/**
	 * Método de acceso a las opciones de usuario
	 */
	public void ingresarAOpcionUsuario() {
		int opcion = ingresoOpcion(1, 3);
		switch (opcion) {
			case 1:
				GestionUsuario nuevoUsuario = new GestionUsuario();
				nuevoUsuario = (GestionUsuario)nuevoUsuario.crearPersona();
				if(listadoUsuarios.agregarElemento(nuevoUsuario)) {
					guardarArchivoUsuarios();
					System.out.println("    Usuario agregado y guardado con éxito.");
				} else {
					System.out.print("    El nombre de usuario " + nuevoUsuario.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
					char opcionIntentar = Simulador.scan.next().charAt(0);
					while (opcionIntentar == 's' || opcionIntentar == 'S') {
						nuevoUsuario = new GestionUsuario(); ///Reseteamos los valores 
						nuevoUsuario = (GestionUsuario)nuevoUsuario.crearPersona();
						if(listadoUsuarios.agregarElemento(nuevoUsuario)) {
							System.out.println("    Usuario agregado con éxito.");
							opcionIntentar = 'n';
						} else {
							System.out.print("    El nombre de usuario " + nuevoUsuario.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
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
						usuarioLogueado = usuarioRecibido.getNombre();
						if (usuarioRecibido.getClubUsuario() == null) {
							usuarioRecibido.setClubUsuario(usuarioRecibido.crearClub());
							guardarArchivoUsuarios();
						}
						System.out.println("  A continuación están las opciones:");
						System.out.println("    1. Ingresar a club.");
						System.out.println("    2. Cambiar nombre de usuario.");
						System.out.println("    3. Cambiar contraseña de usuario.");
						opcion = ingresoOpcion(1, 3);
						switch (opcion) {
							case 1:
								usuarioRecibido.getClubUsuario().listadoOpciones();
								break;
							case 2:
								if (usuarioRecibido.cambiarNombre(listadoUsuarios)) {
									guardarArchivoUsuarios();
								}
								break;
							case 3:
								usuarioRecibido.cambiarPassword();
								guardarArchivoUsuarios();
								break;
						}
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
	
	/**
	 * Método que lista las opciones de administrador
	 */
	public void listadoOpcionesAdministrador() {
		esperar();
		System.out.println("\n\nBienvenido al Menú de Administrador.");
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Crear Administrador.");
		System.out.println("    2. Ingresar a Administrador ya existente.");
		System.out.println("    3. Salir.\n");
		ingresarAOpcionAdministrador();
	}
	
	/**
	 * Método de acceso a las opciones de administrador
	 */
	public void ingresarAOpcionAdministrador() {
		int opcion = ingresoOpcion (1, 3);
		switch (opcion) {
			case 1:
				if (GestionAdministrador.comparacionPasswordCreacionAdmin()) {
					GestionAdministrador nuevoAdministrador =  new GestionAdministrador();
					nuevoAdministrador = (GestionAdministrador)nuevoAdministrador.crearPersona();
					if(listadoAdministradores.agregarElemento(nuevoAdministrador)) {
						archivoAdministradores.guardar(listadoAdministradores);
						System.out.println("    Administrador agregado con éxito.");
					} else {
						System.out.print("    El nombre de administrador " + nuevoAdministrador.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
						char opcionIntentar = Simulador.scan.next().charAt(0);
						while (opcionIntentar == 's' || opcionIntentar == 'S') {
							nuevoAdministrador = new GestionAdministrador(); ///Reseteamos los valores 
							nuevoAdministrador = (GestionAdministrador)nuevoAdministrador.crearPersona();
							if(listadoAdministradores.agregarElemento(nuevoAdministrador)) {
								System.out.println("    Administrador agregado con éxito.");
								opcionIntentar = 'n';
							} else {
								System.out.print("    El nombre de administrador " + nuevoAdministrador.getNombre() + " ya existe ¿Desea intentar nuevamente? (s/n): ");
								opcionIntentar = Simulador.scan.next().charAt(0);
							}
						}
					}
				} else {
					System.out.println("    La contraseña de Creación fue incorrecta demasiadas veces.");
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
	
	/**
	 * Método que determina si el usuario a enfrentar en un partido no es el logueado
	 * @param usuario nombre del usuario logueado
	 * @param rival nombre del usuario rival a enfrentar
	 * @throws MismoRivalUsuarioException excepción lanzada en el caso de que los nombres coincidan
	 */
	public static void mismoUsuarioRival (String usuario, String rival) throws MismoRivalUsuarioException{
		if (usuario.equals(rival)) {
			throw new MismoRivalUsuarioException("Error de selección de rival.");
		}
	}
	
	/**
	 * Método que determina si el usuario a enfrentar en un partido existe
	 * @param recibido usuario a enfrentar
	 * @param nombre nombre del usuario a enfrentar. Lo pasamos como String porque puede que el usuario == null
	 * @throws RivalNoEncontradoException excepción lanzada en el caso de que el rival a enfrentar no exista
	 */
	public static void rivalNulo (GestionUsuario recibido, String nombre) throws RivalNoEncontradoException{
		if (recibido == null) {
			throw new RivalNoEncontradoException("Error de selección de rival.", nombre);
		}
	}
	
	/**
	 * Método para la selección de un usuario para enfrentar en un partido
	 * @return club del rival convertido en equipo
	 */
	public static Equipo seleccionUsuarioRival(){
		System.out.println(listadoUsuarios.listar());
		System.out.print("\nIngrese el nombre del Usuario que quiere enfrentar: ");
		scan.nextLine();
		String nombreUsuarioAEnfrentar = scan.nextLine();
		GestionUsuario usuarioAEnfrentar = listadoUsuarios.buscarElemento(new GestionUsuario(nombreUsuarioAEnfrentar));
		try {
			rivalNulo(usuarioAEnfrentar, nombreUsuarioAEnfrentar);
			mismoUsuarioRival(usuarioLogueado, nombreUsuarioAEnfrentar);
			//Usamos uno de los constructores de Equipo para instanciar el Club a enfrentar como un Equipo y devolverlo
			return new Equipo(usuarioAEnfrentar.getClubUsuario().getNombre(), usuarioAEnfrentar.getClubUsuario().getPlantillaClub(), usuarioAEnfrentar.getClubUsuario().getDTClub().getID());
		}
		catch (RivalNoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		catch (MismoRivalUsuarioException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
