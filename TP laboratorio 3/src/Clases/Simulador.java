package Clases;

import java.util.Scanner;

import Interfaces.IMenu;

public class Simulador implements IMenu{

	private ContenedorPersonaSistema<Usuario> listadoUsuarios;
	private ContenedorPersonaSistema<Administrador> listadoAdministradores;
	private static Mercado mercadoDePases; //TODO hacer get y modificar usuario
	private static Scanner scan;
	///TODO scan se crea en constructor y se destruye al salir
	
	/// * * * CONSTRUCTORES * * * ///
	public Simulador() {
		listadoUsuarios = new ContenedorPersonaSistema<>();
		listadoAdministradores = new ContenedorPersonaSistema<>();
		scan = new Scanner(System.in);
	}
	/// * * * FIN CONSTRUCTORES * * * ///
	
	/// * * * GETTERS * * * ///
	public static Mercado getMercado() {
		return mercadoDePases;
	}
	
	public static Scanner getScanner() {
		return scan;
	}
	/// * * * FIN GETTERS * * * ///
	
	
	@Override
	public void listadoOpciones() {
		System.out.println("Bienvenido al Simulador de Mercado de FIFA 20.");
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Men� Usuario.");
		System.out.println("    2. Men� Administrador.");
		System.out.println("    3. Salir.");
		System.out.println("");
		ingresarAOpcion();
	}

	@Override
	public void ingresarAOpcion() {
		int opcion; //TODO sacar inicializaci�n
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		opcion = scan.nextInt();
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
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
		}
	}

	@Override
	public void regresar() {
		System.out.println("Gracias por usar el Simulador. Esperamos que vuelva pronto.");
		scan.close();
	}
	
	public void listadoOpcionesUsuario() {
		System.out.println("\n\nBienvenido al Men� de Usuario.");
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Crear Usuario.");
		System.out.println("    2. Ingresar a Usuario ya existente.");
		System.out.println("    3. Salir.");
		System.out.println("");
		ingresarAOpcionUsuario();
		
	}
	
	public void ingresarAOpcionUsuario() {
		int opcion = 0; //TODO sacar inicializaci�n
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		opcion = scan.nextInt();
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = scan.nextInt();
		}
		switch (opcion) {
			case 1:
				Usuario nuevoUsuario =  new Usuario();
				nuevoUsuario = (Usuario)nuevoUsuario.crearPersona();
				if(listadoUsuarios.agregarElemento(nuevoUsuario)) {
					System.out.println("    Usuario agregado con �xito.");
				} else {
					System.out.println("    El nombre de usuario " + nuevoUsuario.getNombre() + " ya existe �Desea intentar nuevamente? (s/n): ");
					char opcionIntentar = Simulador.scan.next().charAt(0);
					while (opcionIntentar == 's' || opcionIntentar == 'S') {
						nuevoUsuario = new Usuario(); ///Reseteamos los valores 
						nuevoUsuario = (Usuario)nuevoUsuario.crearPersona();
						if(listadoUsuarios.agregarElemento(nuevoUsuario)) {
							System.out.println("    Usuario agregado con �xito.");
							opcionIntentar = 'n';
						} else {
							System.out.println("    El nombre de usuario " + nuevoUsuario.getNombre() + " ya existe �Desea intentar nuevamente? (s/n): ");
							opcionIntentar = Simulador.scan.next().charAt(0);
						}
					}
				}
				listadoOpcionesUsuario();
				break;
			case 2:
				/**
				 * leer nombre del usuario;
				 * usuarioLocal = crar usuario local con el nombre le�do;
				 * usuarioRecibido = listadoUsuarios.buscarElemento(usuarioLocal);
				 * if (usuarioRecibido != null){ ///TODO analizar uso de excepciones
				 * 		leer contrase�a x veces (intentos)
				 * 		if contrase�aLocal.equals(usuarioRecibido.getPassword){
				 * 			usuarioRecibido.listadoOpcionesClub();
				 * 		}
				 * }
				 **/
				break;
				
		}
	}
	
	public void listadoOpcionesAdministrador() {
		System.out.println("\n\nBienvenido al Men� de Administrador.");
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Crear Administrador.");
		System.out.println("    2. Ingresar a Administrador ya existente.");
		System.out.println("    3. Salir.");
		System.out.println("");
		ingresarAOpcionAdministrador();
	}
	
	public void ingresarAOpcionAdministrador() {
		int opcion = 0; //TODO sacar inicializaci�n
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		opcion = scan.nextInt();
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = scan.nextInt();
		}
		switch (opcion) {
			case 1: //TODO pedir contrase�a de creaci�n para evitar que cualquiera cree admins
				Administrador nuevoAdministrador =  new Administrador();
				nuevoAdministrador = (Administrador)nuevoAdministrador.crearPersona();
				if(listadoAdministradores.agregarElemento(nuevoAdministrador)) {
					System.out.println("    Administrador agregado con �xito.");
				} else {
					System.out.println("    El nombre de administrador " + nuevoAdministrador.getNombre() + " ya existe �Desea intentar nuevamente? (s/n): ");
					char opcionIntentar = Simulador.scan.next().charAt(0);
					while (opcionIntentar == 's' || opcionIntentar == 'S') {
						nuevoAdministrador = new Administrador(); ///Reseteamos los valores 
						nuevoAdministrador = (Administrador)nuevoAdministrador.crearPersona();
						if(listadoAdministradores.agregarElemento(nuevoAdministrador)) {
							System.out.println("    Administrador agregado con �xito.");
							opcionIntentar = 'n';
						} else {
							System.out.println("    El nombre de administrador " + nuevoAdministrador.getNombre() + " ya existe �Desea intentar nuevamente? (s/n): ");
							opcionIntentar = Simulador.scan.next().charAt(0);
						}
					}
				}
				listadoOpcionesAdministrador();
				break;
			case 2:
				/**
				 * leer nombre del administrador;
				 * administradorLocal = crar administrador local con el nombre le�do;
				 * administradorRecibido = listadoAdministradores.buscarElemento(administradorLocal);
				 * if (administradorRecibido != null){ ///TODO analizar uso de excepciones
				 * 		leer contrase�a x veces (intentos)
				 * 		if contrase�aLocal.equals(administradorRecibido.getPassword){
				 * 			administradorRecibido.listadoOpcionesAdministrador
				 * 		}
				 * }
				 **/
				listadoOpcionesAdministrador();
				break;
				
		}
	}
	

}
