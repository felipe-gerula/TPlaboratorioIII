package Clases;

import interfaces.IMenu;

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
	
	public static boolean comparacionPasswordCreacionAdmin() {
		String password = "123";
		int intentos = 3;
		String passwordLeida;
		Simulador.getScanner().nextLine();
		System.out.println("Ingrese la contrase�a de creaci�n de Administrador (3 intentos restantes): ");
		passwordLeida = Simulador.getScanner().nextLine();
		if (passwordLeida.equals(password)) {
			return true;
		} else {
			while (intentos>1) {
				intentos--;
				System.out.println("Contrase�a incorrecta. Ingres�la nuevamente ("+ intentos + " intentos restantes): ");
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
		System.out.println("Bienvenido al men� de modificaci�n de Jugador.");
		System.out.println("  ID del Jugador a modificar: ");
		int idBuscado = Simulador.ingresoOpcion(0, Jugador.getCantidadJugadores()-1);
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
		System.out.println("    5. Modificar Calificaci�n, Tipo y Precio.");
		System.out.println("    6. Modificar Pie H�bil.");
		System.out.println("    7. Modificar Movimientos H�biles.");
		System.out.println("    8. Modificar Posici�n.");
		System.out.println("    9. Regresar al Men� de Administrador.");
		System.out.println("");
		ingresarAOpcionModificacionJugador(jugadorAModificar);
	}
	
	private void ingresarAOpcionModificacionJugador(Jugador jugadorAModificar) {
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
				System.out.println("  Ingrese la nueva nacionalidad: ");
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

	public void bajaJugadorMercado() {
		System.out.println("Bienvenido al men� de baja de de Jugador.");
		System.out.println("  Ingreso del ID del Jugador a dar de baja: ");
		int idBuscado = Simulador.ingresoOpcion(0, Jugador.getCantidadJugadores()-1);
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		if (jugadorAModificar.getEstado()) {
			System.out.println(jugadorAModificar.toString());
			System.out.println("�Est� seguro de que desea darlo de baja? (s para confirmar): ");
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
	
	public void altaJugadorMercado() {
		System.out.println("Bienvenido al men� de alta de Jugador.");
		System.out.println("  Ingreso del ID del Jugador a dar de alta: ");
		int idBuscado = Simulador.ingresoOpcion(0, Jugador.getCantidadJugadores()-1);
		Jugador jugadorAModificar = Simulador.getMercado().getListadoJugadores().buscar(idBuscado);
		if (!jugadorAModificar.getEstado()) {
			System.out.println(jugadorAModificar.toString());
			System.out.println("�Est� seguro de que desea darlo de alta? (s para confirmar): ");
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
		System.out.println("Bienvenido al men� de modificaci�n de Director T�cnico.");
		System.out.println("  Ingrese el ID del Director T�cnico que desea modificar: ");
		int idBuscado = Simulador.ingresoOpcion(0, DirectorTecnico.getCantidadDTs()-1);
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		listadoOpcionesModificacionDT(dtAModificar);
	}
	
	public void listadoOpcionesModificacionDT(DirectorTecnico dtAModificar) {
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
	
	private void ingresarAOpcionModificacionDT(DirectorTecnico dtAModificar) {
		int opcion = Simulador.ingresoOpcion(1, 7);
		switch (opcion) {
			case 1: //nombre apellido
				System.out.println("  Ingrese el nuevo nombre y apellido: ");
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
				System.out.println("  Ingrese la nueva nacionalidad: ");
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
	
	public void bajaDTMercado() {
		System.out.println("Bienvenido al men� de baja de de Director T�cnico.");
		System.out.println("  Ingrese el ID del DT que desea dar de baja: ");
		int idBuscado = Simulador.ingresoOpcion(0, DirectorTecnico.getCantidadDTs()-1);
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		System.out.println(dtAModificar.toString());
		if (dtAModificar.getEstado()) {
			System.out.println("�Est� seguro de que desea darlo de baja? (s para confirmar): ");
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
	
	public void altaDTMercado() {
		System.out.println("Bienvenido al men� de alta de de Director T�cnico.");
		System.out.println("  Ingrese el ID del DT que desea dar de alta: ");
		int idBuscado = Simulador.ingresoOpcion(0, DirectorTecnico.getCantidadDTs()-1);
		DirectorTecnico dtAModificar = Simulador.getMercado().getListadoDTs().buscar(idBuscado);
		System.out.println(dtAModificar.toString());
		if (!dtAModificar.getEstado()) {
			System.out.println("�Est� seguro de que desea darlo de alta? (s para confirmar): ");
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
	
	@Override
	public PersonaSistema crearPersona() {
		GestionAdministrador nuevoAdministrador;
		System.out.println("Bienvenido al men� de creaci�n de Administrador.");
		System.out.println("  Ingrese el nombre de Administrador:");
		Simulador.getScanner().nextLine();
		String nombreAdministrador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la contrase�a del Administrador " + nombreAdministrador + ": ");
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

	@Override
	public void regresar() {
		System.out.println("Regresando al Men� Principal del Simulador.");
	}

}
