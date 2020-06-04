package Clases;

import java.util.Scanner;

import Interfaces.IMenu;

/** 
 *  Esta clase nos permite crear objetos de tipo Usuario
 *  @author 
 */
public class Usuario extends PersonaSistema implements IMenu{

	private ClubUsuario clubUsuario;
	private Scanner scanLocal;
	
	public Usuario(String nombreUsuario, String passUsuario) {
		super(nombreUsuario, passUsuario);
	}
	
	public Usuario() {
		super();
		scanLocal = new Scanner(System.in);
	}

	@Override
	public PersonaSistema crearPersona() {
		Usuario nuevoUsuario;
		System.out.println("Bienvenido al men� de creaci�n de Usuario.");
		System.out.println("  Ingrese el nombre de Usuario:");
		String nombreUsuario = scanLocal.nextLine();
		System.out.println("  Ingrese la contrase�a del Usuario " + nombreUsuario + ": ");
		//Simulador.getScanner().nextLine();
		String passUsuario = scanLocal.nextLine();
		nuevoUsuario = new Usuario(nombreUsuario, passUsuario);
		return nuevoUsuario;
	}

	@Override
	public boolean acceder() {
		// TODO Auto-generated method stub
		return false;
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
		System.out.println("    6. Regresar al Men� Principal.");
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
				Simulador.getMercado().listadoOpcionesMercado(clubUsuario);
				listadoOpciones();
				break;
			case 2:
				//jugar partido;
				listadoOpciones();
				break;
			case 3:
				//modificar info club;
				listadoOpciones();
			case 4:
				//listado info club;
				listadoOpciones();
			case 5:
				//ver plantilla;
				listadoOpciones();
			default:
				regresar();
		}
	}

	@Override
	public void regresar() {
		System.out.println("Regresando al Men� Principal del Simulador.");
	}
	
}
