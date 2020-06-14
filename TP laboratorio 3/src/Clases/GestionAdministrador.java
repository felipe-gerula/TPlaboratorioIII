package Clases;


import java.util.Scanner;

import Interfaces.IMenu;

/** 
 *  Esta clase nos permite crear objetos de tipo GestionAdministracionAdministrador
 *  permite la modificacion,alta y baja de jugadores  Y DT´s  en el mercado 
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
	
	public static boolean comparacionPasswordCreacionAdmin() {
		String password = "123";
		int intentos = 3;
		String passwordLeida;
		Simulador.getScanner().nextLine();
		System.out.println("Ingrese la contraseña de creación de Administrador (3 intentos restantes): ");
		passwordLeida = Simulador.getScanner().nextLine();
		if (passwordLeida.equals(password)) {
			return true;
		} else {
			while (intentos>1) {
				intentos--;
				System.out.println("Contraseña incorrecta. Ingreséla nuevamente ("+ intentos + " intentos restantes): ");
				passwordLeida = Simulador.getScanner().nextLine();
				if (passwordLeida.equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	public void agregarJugadorMercado() {
		Jugador nuevoJugador = new Jugador();
		nuevoJugador = nuevoJugador.crearJugador();
		if (nuevoJugador != null) {
			System.out.println(nuevoJugador.toString());
			Simulador.getMercado().agregarJugador(nuevoJugador);
			Simulador.getListadoLigasEquipos().agregarJugador(nuevoJugador);
			Simulador.guardarArchivoJugadores();
		}
	}
	
	public void modificarJugadorMercado() {
		System.out.println("Bienvenido al menú de modificación de Jugador.");
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
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Modificar Nombre y Apellido.");
		System.out.println("    2. Modificar Club y Liga.");
		System.out.println("    3. Modificar Nacionalidad.");
		System.out.println("    4. Modificar Edad.");
		System.out.println("    5. Modificar Calificación.");
		System.out.println("    6. Modificar Pie Hábil.");
		System.out.println("    7. Modificar Movimientos Hábiles.");
		System.out.println("    8. Modificar Posición.");
		System.out.println("    9. Modificar Precio.");
		System.out.println("    10. Regresar al Menú de Administrador.");
		System.out.println("");
		ingresarAOpcionModificacionJugador(jugadorAModificar);
	}
	
	private void ingresarAOpcionModificacionJugador(Jugador jugadorAModificar) {
		int opcion;
		System.out.println("  Ingrese el número de opción deseada: ");
		Scanner scanner = Simulador.getScanner(); //Lo pasamos a una variable local porque tiraba error de leaking resource
		opcion = scanner.nextInt();
		while (opcion<1 || opcion>10) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = scanner.nextInt();
		}
		switch (opcion) {
			case 1: //nombre apellido
				System.out.println("  Ingrese el nuevo nombre y apellido: ");
				scanner.nextLine();
				jugadorAModificar.setNombreApellido(scanner.nextLine());
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 2: //club y liga
				System.out.println("  A continuación se mostrarán las ligas y equipos disponibles. Puede elegir o agregar nuevos.");
				Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
				if (equipoSeleccionado.hayEspacioEnPlantilla()) {
					if (!equipoSeleccionado.jugadorYaCargado(jugadorAModificar.getNombre())) {
						Simulador.getListadoLigasEquipos().eliminarJugador(jugadorAModificar);
						jugadorAModificar.setClub(equipoSeleccionado.getNombreEquipo());
						jugadorAModificar.setLiga(equipoSeleccionado.getNombreLiga());
						Simulador.getListadoLigasEquipos().agregarJugador(jugadorAModificar);
						Simulador.guardarArchivoJugadores();
					} else {
						System.out.println("  Ya hay un jugador con el nombre " + jugadorAModificar.getNombre() + " cargado en el equipo.");
					}
				} else {
					System.out.println("  No hay espacio en el equipo seleccionado. Intente modificar uno de los jugadores ya existentes.");
				}
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 3: //nacionalidad
				System.out.println("  Ingrese la nueva nacionalidad: ");
				scanner.nextLine();
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
			case 5: //calificación
				System.out.println("  Ingrese la nueva calificación: ");
				jugadorAModificar.setCalificacion(scanner.nextInt());
				//TODO controlar que no sea negativa y menor a 99
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 6: //pie hábil
				jugadorAModificar.setPieHabil();
				System.out.println("  Nuevo pie hábil de " + jugadorAModificar.getNombre() + ": " + jugadorAModificar.getPieHabil() + ".");
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 7: //movimientos hábiles
				System.out.println("  Ingrese la nueva calificación de movimientos hábiles: ");
				jugadorAModificar.setMovHabiles(scanner.nextInt());
				//TODO controlar que esté entre 1 y 5
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 8: //posición //TODO hacer listado
				System.out.println("  Ingrese la nueva posición: ");
				scanner.nextLine();
				jugadorAModificar.setPosicion(scanner.nextLine());
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 9: //precio
				System.out.println("  Ingrese el nuevo precio: ");
				jugadorAModificar.setPrecio(scanner.nextDouble());
				//TODO controlar que sea positivo y dentro del rango de la calificación
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			default:
				System.out.println("Regresando al Menú de Administrador.");
		}
	}

	public void bajaJugadorMercado() {
		System.out.println("Bienvenido al menú de baja de de Jugador.");
		System.out.println("  Ingrese el ID del Jugador que desea dar de baja: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(Jugador.getCantidadJugadores()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (Jugador.getCantidadJugadores()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		if (jugadorAModificar.getEstado()) {
			System.out.println(jugadorAModificar.toString());
			System.out.println("¿Está seguro de que desea darlo de baja? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				jugadorAModificar.setEstado(false);
				System.out.println("\n\n  Jugador " + jugadorAModificar.getNombre() + " dado de baja con éxito.");
				Simulador.guardarArchivoJugadores();
			}
		} else {
			System.out.println("\n\n  El jugador " + jugadorAModificar.getNombre() + " ya se encontraba dado de baja.");
		}
	}
	
	public void altaJugadorMercado() {
		System.out.println("Bienvenido al menú de alta de Jugador.");
		System.out.println("  Ingrese el ID del Jugador que desea dar de alta: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(Jugador.getCantidadJugadores()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (Jugador.getCantidadJugadores()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		if (!jugadorAModificar.getEstado()) {
			System.out.println(jugadorAModificar.toString());
			System.out.println("¿Está seguro de que desea darlo de alta? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				jugadorAModificar.setEstado(true);
				System.out.println("\n\n  Jugador " + jugadorAModificar.getNombre() + " dado de alta con éxito.");
				Simulador.guardarArchivoJugadores();
			}
		} else {
			System.out.println("\n\n  El jugador " + jugadorAModificar.getNombre() + " ya se encontraba dado de alta.");
		}
	}
	
	public void agregarDTMercado() {
		DirectorTecnico nuevoDT = new DirectorTecnico();
		nuevoDT = nuevoDT.crearDirectorTecnico();
		if (nuevoDT != null) {
			System.out.println(nuevoDT.toString());
			Simulador.getMercado().agregarDirectorTecnico(nuevoDT);
			Simulador.getListadoLigasEquipos().agregarDT(nuevoDT);
			Simulador.guardarArchivoDTs();
		}
	}
	
	public void modificarDTMercado() {
		System.out.println("Bienvenido al menú de modificación de Director Técnico.");
		System.out.println("  Ingrese el ID del Director Técnico que desea modificar: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(DirectorTecnico.getCantidadDTs()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (DirectorTecnico.getCantidadDTs()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		listadoOpcionesModificacionDT(dtAModificar);
	}
	
	public void listadoOpcionesModificacionDT(DirectorTecnico dtAModificar) {
		System.out.println(dtAModificar.toString());
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Modificar Nombre y Apellido.");
		System.out.println("    2. Modificar Club y Liga.");
		System.out.println("    3. Modificar Nacionalidad.");
		System.out.println("    4. Modificar Edad.");
		System.out.println("    5. Modificar Precio.");
		System.out.println("    6. Modificar Vestimenta.");
		System.out.println("    7. Regresar al Menú de Administrador.");
		System.out.println("");
		ingresarAOpcionModificacionDT(dtAModificar);
	}
	
	private void ingresarAOpcionModificacionDT(DirectorTecnico dtAModificar) {
		int opcion;
		System.out.println("  Ingrese el número de opción deseada: ");
		Scanner scanner = Simulador.getScanner(); //Lo pasamos a una variable local porque tiraba error de leaking resource
		opcion = scanner.nextInt();
		while (opcion<1 || opcion>7) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = scanner.nextInt();
		}
		switch (opcion) {
			case 1: //nombre apellido
				System.out.println("  Ingrese el nuevo nombre y apellido: ");
				scanner.nextLine();
				dtAModificar.setNombreApellido(scanner.nextLine());
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 2: //club y liga
				Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
				if (equipoSeleccionado.hayEspacioParaDT()) {
					Simulador.getListadoLigasEquipos().eliminarDT(dtAModificar);
					dtAModificar.setClub(equipoSeleccionado.getNombreEquipo());
					dtAModificar.setLiga(equipoSeleccionado.getNombreLiga());
					Simulador.getListadoLigasEquipos().agregarDT(dtAModificar);
					Simulador.guardarArchivoDTs();
				} else {
					System.out.println("  Ya hay un DT cargado en el equipo seleccionado. Intente modificar sus datos.");
				}		
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 3: //nacionalidad
				System.out.println("  Ingrese la nueva nacionalidad: ");
				scanner.nextLine();
				dtAModificar.setNacionalidad(scanner.nextLine());
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 4: //edad
				System.out.println("  Ingrese la nueva edad: ");
				dtAModificar.setEdad(scanner.nextInt());
				//TODO controlar que no sea negativo
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 5: //precio
				System.out.println("  Ingrese el nuevo precio: ");
				dtAModificar.setPrecio(scanner.nextDouble());
				//TODO controlar que sea positivo y dentro del rango de la calificación
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 6: //vestimenta
				dtAModificar.cambiarVestimenta();
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			default:
				System.out.println("Regresando al Menú de Administrador.");
		}
	}
	
	public void bajaDTMercado() {
		System.out.println("Bienvenido al menú de baja de de Director Técnico.");
		System.out.println("  Ingrese el ID del DT que desea dar de baja: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(DirectorTecnico.getCantidadDTs()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (DirectorTecnico.getCantidadDTs()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		System.out.println(dtAModificar.toString());
		if (dtAModificar.getEstado()) {
			System.out.println("¿Está seguro de que desea darlo de baja? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				dtAModificar.setEstado(false);
				System.out.println("\n\n  Director técnico " + dtAModificar.getNombre() + " dado de baja con éxito.");
				Simulador.guardarArchivoDTs();
			}
		} else {
			System.out.println("\n\n  El director técnico " + dtAModificar.getNombre() + " ya se encontraba dado de baja.");
		}
	}
	
	public void altaDTMercado() {
		System.out.println("Bienvenido al menú de alta de de Director Técnico.");
		System.out.println("  Ingrese el ID del DT que desea dar de alta: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(DirectorTecnico.getCantidadDTs()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (DirectorTecnico.getCantidadDTs()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		System.out.println(dtAModificar.toString());
		if (!dtAModificar.getEstado()) {
			System.out.println("¿Está seguro de que desea darlo de alta? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				dtAModificar.setEstado(true);
				System.out.println("\n\n  Director técnico " + dtAModificar.getNombre() + " dado de alta con éxito.");
				Simulador.guardarArchivoDTs();
			}
		} else {
			System.out.println("\n\n  El director técnico " + dtAModificar.getNombre() + " ya se encontraba dado de alta.");
		}
	}
	
	@Override
	public PersonaSistema crearPersona() {
		GestionAdministrador nuevoAdministrador;
		System.out.println("Bienvenido al menú de creación de Administrador.");
		System.out.println("  Ingrese el nombre de Administrador:");
		Simulador.getScanner().nextLine();
		String nombreAdministrador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la contraseña del Administrador " + nombreAdministrador + ": ");
		//Simulador.getScanner().nextLine();
		String passAdministrador = Simulador.getScanner().nextLine();
		nuevoAdministrador = new GestionAdministrador(nombreAdministrador, passAdministrador);
		return nuevoAdministrador;
	}

	@Override
	public PersonaSistema acceder() {
		GestionAdministrador ingresoAdministrador;
		System.out.println("Bienvenido al menú de ingreso a Administrador.");
		System.out.println("  Ingrese el nombre de Administrador:");
		Simulador.getScanner().nextLine();
		String nombreAdministrador = Simulador.getScanner().nextLine();
		ingresoAdministrador = new GestionAdministrador(nombreAdministrador);
		return ingresoAdministrador;
	}


	@Override
	public void listadoOpciones() {
		System.out.println("\n\nBienvenido al Menú de Administrador, " /*+ nombreAdmin*/);
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Agregar jugador al Mercado.");
		System.out.println("    2. Modificar jugador en el Mercado.");
		System.out.println("    3. Baja jugador del Mercado.");
		System.out.println("    4. Alta jugador del Mercado.");
		System.out.println("    5. Agregar DT al Mercado.");
		System.out.println("    6. Modificar DT en el Mercado.");
		System.out.println("    7. Baja DT del Mercado.");
		System.out.println("    8. Alta DT del Mercado.");
		System.out.println("    9. Ver Mercado.");
		System.out.println("    10. Regresar al Menú Principal.");
		System.out.println("");
		ingresarAOpcion();
	}

	@Override
	public void ingresarAOpcion() {
		int opcion;
		System.out.println("  Ingrese el número de opción deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>10) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				this.agregarJugadorMercado();
				this.listadoOpciones();
				break;
			case 2:
				if (Jugador.getCantidadJugadores()>0) {
					this.modificarJugadorMercado();
				} else {
					System.out.println("No hay Jugadores cargados.");
				}
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
				if (DirectorTecnico.getCantidadDTs()>0) {
					this.modificarDTMercado();
				} else {
					System.out.println("No hay Directores Técnicos cargados.");
				}
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
		System.out.println("Regresando al Menú Principal del Simulador.");
	}

}
