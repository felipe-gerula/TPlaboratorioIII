package Clases;


import java.util.Scanner;

import Interfaces.IMenu;

/** 
 *  Esta clase nos permite crear objetos de tipo GestionAdministracionAdministrador
 *  permite la modificacion,alta y baja de jugadores  Y DT�s  en el mercado 
 *  @author 
 */
public class GestionAdministrador extends PersonaSistema implements IMenu{
	
	private static final long serialVersionUID = 1L;

	public GestionAdministrador(String nombreAdministrador, String passAdministrador) {
		super(nombreAdministrador, passAdministrador);
	}
	
	public GestionAdministrador() {
		super();
	}
	
	public GestionAdministrador(String nombreAdministrador) {
		super(nombreAdministrador);
	}

	public void agregarJugadorMercado() {
		Jugador nuevoJugador = new Jugador();
		nuevoJugador = nuevoJugador.crearJugador();
		if (nuevoJugador != null) {
			System.out.println(nuevoJugador.toString());
			Simulador.getMercado().agregarJugador(nuevoJugador);
			Simulador.guardarArchivoJugadores();
		}
	}
	
	public void modificarJugadorMercado() {
		System.out.println("Bienvenido al men� de modificaci�n de Jugador.");
		System.out.println("  Ingrese el ID del Jugador que desea modificar: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(Jugador.getCantidadJugadores()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (Jugador.getCantidadJugadores()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		listadoOpcionesModificacionJugador(jugadorAModificar);
	}
	
	public void listadoOpcionesModificacionJugador(Jugador jugadorAModificar) {
		System.out.println(jugadorAModificar.toString());
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Modificar Nombre y Apellido.");
		System.out.println("    2. Modificar Club y Liga.");
		System.out.println("    3. Modificar Nacionalidad.");
		System.out.println("    4. Modificar Edad.");
		System.out.println("    5. Modificar Calificaci�n.");
		System.out.println("    6. Modificar Pie H�bil.");
		System.out.println("    7. Modificar Movimientos H�biles.");
		System.out.println("    8. Modificar Posici�n.");
		System.out.println("    9. Modificar Precio.");
		System.out.println("    10. Regresar al Men� Principal.");
		System.out.println("");
		ingresarAOpcionModificacionJugador(jugadorAModificar);
	}
	
	private void ingresarAOpcionModificacionJugador(Jugador jugadorAModificar) {
		int opcion;
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		Scanner scanner = Simulador.getScanner(); //Lo pasamos a una variable local porque tiraba error de leaking resource
		opcion = scanner.nextInt();
		while (opcion<1 || opcion>10) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = scanner.nextInt();
		}
		switch (opcion) {
			case 1: //nombre apellido
				System.out.println("  Ingrese el nuevo nombre y apellido: ");
				jugadorAModificar.setNombreApellido(scanner.nextLine());
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 2: //club y liga
				//TODO hacer un listado con las ligas ya existentes (opciones con n�mero).
				//El admin elige y se muestran los clubes dentro de la liga
				//Vuelve a elegir y se modifica el jugador
				//Si quiere crear una liga y/o club se le da la opci�n
				/*System.out.println("  Ingrese el nuevo club: ");
				jugadorAModificar.setClub(Simulador.getScanner().nextLine());
				System.out.println("  Ingrese la nueva liga: ");
				jugadorAModificar.setClub(Simulador.getScanner().nextLine());
				Simulador.guardarArchivoJugadores();*/
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 3: //nacionalidad
				System.out.println("  Ingrese la nueva nacionalidad: ");
				jugadorAModificar.setNacionalidad(scanner.nextLine());
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 4: //edad
				System.out.println("  Ingrese la nueva edad: ");
				jugadorAModificar.setEdad(scanner.nextInt());
				//TODO controlar que no sea negativo
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 5: //calificaci�n
				System.out.println("  Ingrese la nueva calificaci�n: ");
				jugadorAModificar.setCalificacion(scanner.nextInt());
				//TODO controlar que no sea negativa y menor a 99
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 6: //pie h�bil
				jugadorAModificar.setPieHabil();
				System.out.println("  Nuevo pie h�bil de " + jugadorAModificar.getNombre() + ": " + jugadorAModificar.getPieHabil() + ".");
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 7: //movimientos h�biles
				System.out.println("  Ingrese la nueva calificaci�n de movimientos h�biles: ");
				jugadorAModificar.setMovHabiles(scanner.nextInt());
				//TODO controlar que est� entre 1 y 5
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 8: //posici�n //TODO hacer listado
				System.out.println("  Ingrese la nueva posici�n: ");
				jugadorAModificar.setPosicion(scanner.nextLine());
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 9: //precio
				System.out.println("  Ingrese el nuevo precio: ");
				jugadorAModificar.setPrecio(scanner.nextDouble());
				//TODO controlar que sea positivo y dentro del rango de la calificaci�n
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			default:
				regresar();
		}
	}

	public void bajaJugadorMercado() {
		
	}
	
	public void altaJugadorMercado() {
		
	}
	
	public void agregarDTMercado() {
		DirectorTecnico nuevoDT = new DirectorTecnico();
		nuevoDT = nuevoDT.crearDirectorTecnico();
		if (nuevoDT != null) {
			System.out.println(nuevoDT.toString());
			Simulador.getMercado().agregarDirectorTecnico(nuevoDT);
			Simulador.guardarArchivoDTs();
		}
	}
	
	public void modificarDTMercado() {
		
	}
	
	public void bajaDTMercado() {
		
	}
	
	public void altaDTMercado() {
		
	}
	
	@Override
	public PersonaSistema crearPersona() {
		GestionAdministrador nuevoAdministrador;
		System.out.println("Bienvenido al men� de creaci�n de Administrador.");
		System.out.println("  Ingrese el nombre de Administrador:");
		Simulador.getScanner().nextLine();
		String nombreAdministrador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la contrase�a del Administrador " + nombreAdministrador + ": ");
		//Simulador.getScanner().nextLine();
		String passAdministrador = Simulador.getScanner().nextLine();
		nuevoAdministrador = new GestionAdministrador(nombreAdministrador, passAdministrador);
		return nuevoAdministrador;
	}

	@Override
	public PersonaSistema acceder() {
		GestionAdministrador ingresoAdministrador;
		System.out.println("Bienvenido al men� de ingreso a Administrador.");
		System.out.println("  Ingrese el nombre de Administrador:");
		Simulador.getScanner().nextLine();
		String nombreAdministrador = Simulador.getScanner().nextLine();
		ingresoAdministrador = new GestionAdministrador(nombreAdministrador);
		return ingresoAdministrador;
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
		int opcion;
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
				this.listadoOpciones();
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