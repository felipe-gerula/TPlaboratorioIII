package Clases;

import interfaces.IMenu;

/** 
 * Esta clase nos permite crear objetos del tipo Administrador, la cual cuenta con los atributos y m�todos 
 * necesarios para su gesti�n. Permite la modificaci�n, alta y baja de jugadores y DTs en el mercado.
 * Hereda de PersonaSistema e implementa la interfaz IMenu
 */
public class GestionAdministrador extends PersonaSistema implements IMenu{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor con nombre y contrase�a
	 * @param nombreAdministrador nombre del nuevo administrador
	 * @param passAdministrador contrase�a del nuevo administrador
	 */
	public GestionAdministrador(String nombreAdministrador, String passAdministrador) {
		super(nombreAdministrador, passAdministrador);
	}
	
	/**
	 * Constructor vac�o que llama al constructor vac�o de la clase padre, PersonaSistema
	 */
	public GestionAdministrador() {
		super();
	}
	
	/**
	 * Constructor con nombre solamente. Se usa para m�todos como el de b�squeda, en el que la contrase�a no interesa
	 * @param nombreAdministrador nombre del nuevo administrador
	 */
	public GestionAdministrador(String nombreAdministrador) {
		super(nombreAdministrador);
	}
	
	/**
	 * M�todo que compara la contrase�a de creaci�n de administrador con los ingresos del usuario.
	 * Es una contrase�a que restringe la creaci�n de administradores solamenta a aquellas personas que la conozcan.
	 * @return true si se ingresa la contrase�a correctamente. False si no
	 */
	public static boolean comparacionPasswordCreacionAdmin() {
		String password = "123";
		int intentos = 3;
		String passwordLeida;
		Simulador.getScanner().nextLine();
		System.out.print("\nIngrese la contrase�a de creaci�n de Administrador (3 intentos restantes): ");
		passwordLeida = Simulador.getScanner().nextLine();
		if (passwordLeida.equals(password)) {
			return true;
		} else {
			while (intentos>1) {
				intentos--;
				System.out.print("\nContrase�a incorrecta. Ingres�la nuevamente ("+ intentos + " intentos restantes): ");
				passwordLeida = Simulador.getScanner().nextLine();
				if (passwordLeida.equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * M�todo para agregar un jugador al mercado
	 */
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
	
	/**
	 * M�todo para modificar un jugador del mercado
	 */
	public void modificarJugadorMercado() {
		System.out.println("Bienvenido al men� de modificaci�n de Jugador.");
		System.out.println("  ID del Jugador a modificar: ");
		int idBuscado = Simulador.ingresoOpcion(0, Jugador.getCantidadJugadores()-1);
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		listadoOpcionesModificacionJugador(jugadorAModificar);
	}
	
	/**
	 * Listado de opciones disponibles para la modificaci�n de un jugador espec�fico
	 * @param jugadorAModificar jugador a ser modificado
	 */
	public void listadoOpcionesModificacionJugador(Jugador jugadorAModificar) {
		Simulador.esperar();
		System.out.println(jugadorAModificar.toString());
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Modificar Nombre y Apellido.");
		System.out.println("    2. Modificar Club y Liga.");
		System.out.println("    3. Modificar Nacionalidad.");
		System.out.println("    4. Modificar Edad.");
		System.out.println("    5. Modificar Calificaci�n, Tipo y Precio.");
		System.out.println("    6. Modificar Pie H�bil.");
		System.out.println("    7. Modificar Movimientos H�biles.");
		System.out.println("    8. Modificar Posici�n.");
		System.out.println("    9. Regresar al Men� de Administrador.");
		System.out.println("");
		ingresarAOpcionModificacionJugador(jugadorAModificar);
	}
	
	/**
	 * Acceso a la opci�n de modificaci�n de jugador deseada
	 * @param jugadorAModificar jugador a ser modificado
	 */
	private void ingresarAOpcionModificacionJugador(Jugador jugadorAModificar) {
		Simulador.esperar();
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		int opcion = Simulador.ingresoOpcion(1, 9);
		switch (opcion) {
			case 1: //nombre apellido
				System.out.println("  Ingrese el nuevo nombre y apellido: ");
				Simulador.getScanner().nextLine();
				jugadorAModificar.setNombreApellido(Simulador.getScanner().nextLine().toUpperCase());
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 2: //club y liga
				System.out.println("  A continuaci�n se mostrar�n las ligas y equipos disponibles. Puede elegir o agregar nuevos.");
				Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
				if (equipoSeleccionado.hayEspacioEnPlantilla(true)) {
					if (equipoSeleccionado.getPlantillaEquipo().hayEspacioEnPosicion(jugadorAModificar.getPosicion())) {
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
						System.out.println("  No hay espacio para la posici�n del jugador en el equipo seleccionado.");
					}
				} else {
					System.out.println("  No hay espacio en el equipo seleccionado. Intente modificar uno de los jugadores ya existentes.");
				}
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 3: //nacionalidad
				System.out.print("  Ingrese la nueva nacionalidad: ");
				Simulador.getScanner().nextLine();
				jugadorAModificar.setNacionalidad(Simulador.getScanner().nextLine().toUpperCase());
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 4: //edad
				System.out.println("  Cambio de edad: ");
				int edadJugador = Simulador.ingresoOpcion(15, 40);
				jugadorAModificar.setEdad(edadJugador);
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 5: //calificaci�n, tipo y precio
				System.out.println("  Cambio de calificaci�n:");
				int calificacionJugador = Simulador.ingresoOpcion(49, 99);
				jugadorAModificar.setTipo(jugadorAModificar.seleccionDeCalidad(calificacionJugador));
				jugadorAModificar.setPrecio(jugadorAModificar.seleccionDePrecio(calificacionJugador, jugadorAModificar.getTipo()));
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 6: //pie h�bil
				jugadorAModificar.setPieHabil(); //Asigna autom�ticamente el otro pie al pie h�bil
				System.out.println("  Nuevo pie h�bil de " + jugadorAModificar.getNombre() + ": " + jugadorAModificar.getPieHabil() + ".");
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 7: //movimientos h�biles
				System.out.println("  Cambio de movimientos h�biles del Jugador:");
				int movHabilesJugador = Simulador.ingresoOpcion(1, 5);
				jugadorAModificar.setMovHabiles(movHabilesJugador);
				Simulador.guardarArchivoJugadores();
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			case 8: //posici�n
				System.out.println("  Ingrese la nueva posici�n del Jugador:");
				String viejaPosicion = jugadorAModificar.getPosicion();
				String nuevaPosicionJugador = jugadorAModificar.seleccionDePosicion();
				Equipo equipoJugador = Simulador.getListadoLigasEquipos().getEquipo(jugadorAModificar.getLiga(), jugadorAModificar.getClub());
				if (equipoJugador.posicionesEquivalentes(viejaPosicion, nuevaPosicionJugador) || equipoJugador.getPlantillaEquipo().hayEspacioEnPosicion(nuevaPosicionJugador)) {
					String posicionAnterior = jugadorAModificar.getPosicion();
					jugadorAModificar.setPosicion(nuevaPosicionJugador);
					equipoJugador.modificacionPosiciones(posicionAnterior, nuevaPosicionJugador);
					Simulador.guardarArchivoJugadores();
				} else {
					System.out.println("  No hay m�s espacios para la posici�n de " + nuevaPosicionJugador + " en el equipo seleccionado.");
				}
				this.listadoOpcionesModificacionJugador(jugadorAModificar);
				break;
			default:
				System.out.println("Regresando al Men� de Administrador.");
		}
	}

	/**
	 * M�todo que permite la baja de un jugador, cambiando su estado a false
	 */
	public void bajaJugadorMercado() {
		Simulador.esperar();
		System.out.println("Bienvenido al men� de baja de de Jugador.");
		System.out.println("  Ingreso del ID del Jugador a dar de baja: ");
		int idBuscado = Simulador.ingresoOpcion(0, Jugador.getCantidadJugadores()-1);
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		if (jugadorAModificar.getEstado()) {
			System.out.println(jugadorAModificar.toString());
			System.out.print("�Est� seguro de que desea darlo de baja? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				jugadorAModificar.setEstado(false);
				System.out.println("\n\n  Jugador " + jugadorAModificar.getNombre() + " dado de baja con �xito.");
				Simulador.guardarArchivoJugadores();
			}
		} else {
			System.out.println("\n\n  El jugador " + jugadorAModificar.getNombre() + " ya se encontraba dado de baja.");
		}
	}
	
	/**
	 * M�todo que permite el alta de un jugador, cambiando su estado a true
	 */
	public void altaJugadorMercado() {
		Simulador.esperar();
		System.out.println("Bienvenido al men� de alta de Jugador.");
		System.out.println("  Ingreso del ID del Jugador a dar de alta: ");
		int idBuscado = Simulador.ingresoOpcion(0, Jugador.getCantidadJugadores()-1);
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		if (!jugadorAModificar.getEstado()) {
			System.out.println(jugadorAModificar.toString());
			System.out.print("�Est� seguro de que desea darlo de alta? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				jugadorAModificar.setEstado(true);
				System.out.println("\n\n  Jugador " + jugadorAModificar.getNombre() + " dado de alta con �xito.");
				Simulador.guardarArchivoJugadores();
			}
		} else {
			System.out.println("\n\n  El jugador " + jugadorAModificar.getNombre() + " ya se encontraba dado de alta.");
		}
	}
	
	
	/**
	 * M�todo que permite agregar un DT al mercado
	 */
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
	
	/**
	 * M�todo que permite modificar un DT del mercado
	 */
	public void modificarDTMercado() {
		Simulador.esperar();
		System.out.println("Bienvenido al men� de modificaci�n de Director T�cnico.");
		System.out.println("  Ingrese el ID del Director T�cnico que desea modificar: ");
		int idBuscado = Simulador.ingresoOpcion(0, DirectorTecnico.getCantidadDTs()-1);
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		listadoOpcionesModificacionDT(dtAModificar);
	}
	
	/**
	 * Listado de opciones disponibles para la modificaci�n de un DT espec�fico
	 * @param dtAModificar DT a ser modificado
	 */
	public void listadoOpcionesModificacionDT(DirectorTecnico dtAModificar) {
		Simulador.esperar();
		System.out.println(dtAModificar.toString());
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Modificar Nombre y Apellido.");
		System.out.println("    2. Modificar Club y Liga.");
		System.out.println("    3. Modificar Nacionalidad.");
		System.out.println("    4. Modificar Edad.");
		System.out.println("    5. Modificar Calidad y Precio.");
		System.out.println("    6. Modificar Vestimenta.");
		System.out.println("    7. Regresar al Men� de Administrador.");
		System.out.println("");
		ingresarAOpcionModificacionDT(dtAModificar);
	}
	
	/**
	 * Acceso a la opci�n de modificaci�n de DT deseada
	 * @param dtAModificar DT a ser modificado
	 */
	private void ingresarAOpcionModificacionDT(DirectorTecnico dtAModificar) {
		Simulador.esperar();
		int opcion = Simulador.ingresoOpcion(1, 7);
		switch (opcion) {
			case 1: //nombre apellido
				System.out.print("  Ingrese el nuevo nombre y apellido: ");
				Simulador.getScanner().nextLine();
				dtAModificar.setNombreApellido(Simulador.getScanner().nextLine().toUpperCase());
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
				System.out.print("  Ingrese la nueva nacionalidad: ");
				Simulador.getScanner().nextLine();
				dtAModificar.setNacionalidad(Simulador.getScanner().nextLine().toUpperCase());
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 4: //edad
				System.out.println("  Cambio de edad: ");
				int edadDT = Simulador.ingresoOpcion(35, 75);
				dtAModificar.setEdad(edadDT);
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 5: //calidad y precio
				dtAModificar.setTipo(dtAModificar.seleccionDeCalidad());
				dtAModificar.setPrecio(dtAModificar.seleccionDePrecio(dtAModificar.getTipo()));
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			case 6: //vestimenta
				dtAModificar.cambiarVestimenta();
				Simulador.guardarArchivoDTs();
				this.listadoOpcionesModificacionDT(dtAModificar);
				break;
			default:
				System.out.println("Regresando al Men� de Administrador.");
		}
	}
	
	/**
	 * M�todo que permite la baja de un DT, cambiando su estado a false
	 */
	public void bajaDTMercado() {
		Simulador.esperar();
		System.out.println("Bienvenido al men� de baja de de Director T�cnico.");
		System.out.println("  Ingrese el ID del DT que desea dar de baja: ");
		int idBuscado = Simulador.ingresoOpcion(0, DirectorTecnico.getCantidadDTs()-1);
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		System.out.println(dtAModificar.toString());
		if (dtAModificar.getEstado()) {
			System.out.print("�Est� seguro de que desea darlo de baja? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				dtAModificar.setEstado(false);
				System.out.println("\n\n  Director t�cnico " + dtAModificar.getNombre() + " dado de baja con �xito.");
				Simulador.guardarArchivoDTs();
			}
		} else {
			System.out.println("\n\n  El director t�cnico " + dtAModificar.getNombre() + " ya se encontraba dado de baja.");
		}
	}
	
	/**
	 * M�todo que permite el alta de un DT, cambiando su estado a true
	 */
	public void altaDTMercado() {
		Simulador.esperar();
		System.out.println("Bienvenido al men� de alta de de Director T�cnico.");
		System.out.println("  Ingrese el ID del DT que desea dar de alta: ");
		int idBuscado = Simulador.ingresoOpcion(0, DirectorTecnico.getCantidadDTs()-1);
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		System.out.println(dtAModificar.toString());
		if (!dtAModificar.getEstado()) {
			System.out.print("�Est� seguro de que desea darlo de alta? (s para confirmar): ");
			Simulador.getScanner().nextLine();
			char opcion = Simulador.getScanner().nextLine().charAt(0);
			if (opcion == 's' || opcion == 'S') {
				dtAModificar.setEstado(true);
				System.out.println("\n\n  Director t�cnico " + dtAModificar.getNombre() + " dado de alta con �xito.");
				Simulador.guardarArchivoDTs();
			}
		} else {
			System.out.println("\n\n  El director t�cnico " + dtAModificar.getNombre() + " ya se encontraba dado de alta.");
		}
	}
	
	/**
	 * M�todo para el ingreso de datos y la creaci�n de un nuevo Administrador
	 * @return el nuevo administrador
	 */
	@Override
	public PersonaSistema crearPersona() {
		GestionAdministrador nuevoAdministrador;
		System.out.println("\nBienvenido al men� de creaci�n de Administrador.");
		System.out.print("  Ingrese el nombre de Administrador:");
		String nombreAdministrador = Simulador.getScanner().nextLine();
		System.out.print("  Ingrese la contrase�a del Administrador " + nombreAdministrador + ": ");
		String passAdministrador = Simulador.getScanner().nextLine();
		nuevoAdministrador = new GestionAdministrador(nombreAdministrador, passAdministrador);
		return nuevoAdministrador;
	}

	/**
	 * M�todo para la creaci�n de un administrador s�lo con su nombre
	 * @return la instancia del administrador con su nombre
	 */
	@Override
	public PersonaSistema acceder() {
		GestionAdministrador ingresoAdministrador;
		System.out.println("\nBienvenido al men� de ingreso a Administrador.");
		System.out.print("  Ingrese el nombre de Administrador:");
		Simulador.getScanner().nextLine();
		String nombreAdministrador = Simulador.getScanner().nextLine();
		ingresoAdministrador = new GestionAdministrador(nombreAdministrador);
		return ingresoAdministrador;
	}

	/**
	 * Listado de opciones de administrador
	 */
	@Override
	public void listadoOpciones() {
		Simulador.esperar();
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

	/**
	 * Acceso a la opci�n de administrador deseada.
	 */
	@Override
	public void ingresarAOpcion() {
		Simulador.esperar();
		int opcion;
		opcion = Simulador.ingresoOpcion(1, 10);
		switch (opcion) {
			case 1: //Agregar Jugador
				this.agregarJugadorMercado();
				this.listadoOpciones();
				break;
			case 2: //Modificar Jugador
				if (Jugador.getCantidadJugadores()>0) {
					this.modificarJugadorMercado();
				} else {
					System.out.println("No hay Jugadores cargados.");
				}
				this.listadoOpciones();
				break;
			case 3: //Baja Jugador
				this.bajaJugadorMercado();
				this.listadoOpciones();
				break;
			case 4: //Alta Jugador
				this.altaJugadorMercado();
				this.listadoOpciones();
				break;
			case 5: //Agregar DT
				this.agregarDTMercado();
				this.listadoOpciones();
				break;
			case 6: //Modificar DT
				if (DirectorTecnico.getCantidadDTs()>0) {
					this.modificarDTMercado();
				} else {
					System.out.println("No hay Directores T�cnicos cargados.");
				}
				this.listadoOpciones();
				break;
			case 7: //Baja DT
				this.bajaDTMercado();
				this.listadoOpciones();
				break;
			case 8: //Alta DT
				this.altaDTMercado();
				this.listadoOpciones();
				break;
			case 9: //Ver Mercado
				System.out.println(Simulador.getMercado().verMercado(true));
				this.listadoOpciones();
				break;
			default:
				regresar();
				
		}
	}

	/**
	 * Regreso al men� principal del Simulador
	 */
	@Override
	public void regresar() {
		System.out.println("Regresando al Men� Principal del Simulador.");
	}

}
