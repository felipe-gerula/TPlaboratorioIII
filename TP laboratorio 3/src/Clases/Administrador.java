package Clases;

import java.util.Scanner;

import Interfaces.IMenu;

/** 
 *  Esta clase nos permite crear objetos de tipo Administrador
 *  permite la modificacion de jugadores en el mercado 
 *  @author 
 */
public class Administrador extends PersonaSistema implements IMenu{

	private Scanner scanLocal;
	
	public Administrador(String nombreAdministrador, String passAdministrador) {
		super(nombreAdministrador, passAdministrador);
	}
	
	public Administrador() {
		super();
		scanLocal = new Scanner(System.in);
	}
	
	public void agregarJugadorMercado() {
		
	}
	
	public void modificarJugadorMercado() {
		
	}
	
	public void bajaJugadorMercado() {
		
	}
	
	public void altaJugadorMercado() {
		
	}
	
	public void agregarDTMercado() {
		
	}
	
	public void modificarDTMercado() {
		
	}
	
	public void bajaDTMercado() {
		
	}
	
	public void altaDTMercado() {
		
	}
	
	@Override
	public PersonaSistema crearPersona() {
		Administrador nuevoAdministrador;
		System.out.println("Bienvenido al men� de creaci�n de Administrador.");
		System.out.println("  Ingrese el nombre de Administrador:");
		String nombreAdministrador = scanLocal.nextLine();
		System.out.println("  Ingrese la contrase�a del Administrador " + nombreAdministrador + ": ");
		//Simulador.getScanner().nextLine();
		String passAdministrador = scanLocal.nextLine();
		nuevoAdministrador = new Administrador(nombreAdministrador, passAdministrador);
		return nuevoAdministrador;
	}

	@Override
	public boolean acceder() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void listadoOpciones() {
		System.out.println("\n\nBienvenido al Men� de Administrador, " /*+ nombreAdmin*/);
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Agregar jugador al Mercado.");
		System.out.println("    2. Modificar jugador en el Mercado.");
		System.out.println("    3. Baja jugador del Mercado.");
		System.out.println("    4. Alta jugador del Mercado.");
		System.out.println("    5. Agregar DT al Mercado.");
		System.out.println("    6. Modificar DT en el Mercado.");
		System.out.println("    7. Baja DT del Mercado.");
		System.out.println("    8. Alta DT del Mercado.");
		System.out.println("    9. Ver Mercado.");
		System.out.println("    10. Regresar al Men� Principal.");
		System.out.println("");
		ingresarAOpcion();
	}

	@Override
	public void ingresarAOpcion() {
		int opcion = 0; //TODO sacar inicializaci�n
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>10) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				this.agregarJugadorMercado();
				this.listadoOpciones();
				break;
			case 2:
				this.modificarJugadorMercado();
				this.listadoOpciones();
				break;
			case 3:
				this.bajaJugadorMercado();
				this.listadoOpciones();
				break;
			case 4:
				this.altaJugadorMercado();
				this.listadoOpciones();
				break;
			case 5:
				this.agregarDTMercado();
				this.listadoOpciones();
				break;
			case 6:
				this.modificarDTMercado();
				this.listadoOpciones();
				break;
			case 7:
				this.bajaDTMercado();
				this.listadoOpciones();
				break;
			case 8:
				this.altaDTMercado();
				this.listadoOpciones();
				break;
			case 9:
				System.out.println(Simulador.getMercado().verMercado());
				break;
			default:
				regresar();
				
		}
	}

	@Override
	public void regresar() {
		System.out.println("Regresando al Men� Principal del Simulador.");
		
	}

}
