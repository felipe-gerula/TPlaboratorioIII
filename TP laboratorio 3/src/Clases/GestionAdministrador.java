package Clases;


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
		System.out.println(Simulador.getMercado().getListadoJugadores().buscar(Simulador.getScanner().nextInt()).toString());
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
		System.out.println("Regresando al Menú Principal del Simulador.");
		
	}

}
