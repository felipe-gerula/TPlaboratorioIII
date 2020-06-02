package Clases;

import Interfaces.IMenu;

public class Simulador implements IMenu{

	private ContenedorPersonaSistema<Usuario> listadoUsuarios;
	private ContenedorPersonaSistema<Administrador> listadoAdministradores;
	public static Mercado mercadoDePases; //TODO ver y preguntar si conviene que sea static
	
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
		int opcion = 0; //TODO sacar inicializaci�n
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		//scanner (opcion);
		opcion = 3;
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			//scanner (opcion);
		}
		switch (opcion) {
			case 1:
				listadoOpcionesUsuario();
				break;
			case 2:
				listadoOpcionesAdministrador();
				break;
			default:
				System.out.println("Gracias por usar el Simulador. Esperamos que vuelva pronto.");
		}
	}

	@Override
	public void regresar() {
		// TODO Auto-generated method stub
		
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
		//scanner (opcion);
		opcion = 3;
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			//scanner (opcion);
		}
		switch (opcion) {
			case 1:
				Usuario nuevoUsuario = new Usuario(); //TODO crear usuario con los m�todos correspondientes
				listadoUsuarios.agregarElemento(nuevoUsuario); //TODO usar boolean recibido para mostrar mensaje de �xito
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
			default:
				listadoOpcionesUsuario();
				
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
		//scanner (opcion);
		opcion = 3;
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			//scanner (opcion);
		}
		switch (opcion) {
			case 1:
				Administrador nuevoAdministrador = new Administrador(); //TODO crear administrador con los m�todos correspondientes
				listadoAdministradores.agregarElemento(nuevoAdministrador); //TODO usar boolean recibido para mostrar mensaje de �xito
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
				break;
			default:
				listadoOpcionesAdministrador();
				
		}
	}
	
	public Simulador() {
		
	}

}
