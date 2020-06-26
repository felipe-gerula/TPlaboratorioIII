package Clases;

/** 
 *  Esta clase nos permite crear objetos del tipo Jugador, la cual cuenta con los atributos y m�todos 
 *  necesarios para su gesti�n. Hereda de PersonaFutbol
 *  @see "Jugadores FIFA 20"<a href=https://www.fifaindex.com/es/players/fifa20/> JugadorFIFA 20</a>
 */

public class Jugador extends PersonaFutbol /*implements Comparable*/{
	
	private static final long serialVersionUID = -1017025389533618303L;
	
	private static int cantidadJugadores;
	private int calificacion;
	private char pieHabil;
	private int movHabiles;
	private String posicion;
	
	/**
	 * Constructor de Jugador con los par�metros a asignar al nuevo objeto
	 */
	public Jugador(String nombreApellido, String club, String liga, String nacionalidad, int edad, String tipo, double precio, int calificacion, char pieHabil, int movHabiles, String posicion) {
		super(nombreApellido, club, liga, nacionalidad, edad, tipo, precio, cantidadJugadores);
		cantidadJugadores++;
		this.calificacion = calificacion;
		this.pieHabil = pieHabil;
		this.movHabiles = movHabiles;
		this.posicion = posicion;
	}

	/**
	 * Constructor vac�o que llama al constructor vac�o de PersonaFutbol
	 */
	public Jugador() {
		super();
	}

	/**
	 * @return cantidad de jugadores existentes
	 */
	public static int getCantidadJugadores() {
		return cantidadJugadores;
	}
	
	/**
	 * @return ID de la instancia de Jugador
	 */
	public int getIDJugador() {
		return super.getID();
	}
	
	/**
	 * @return calificaci�n de la instancia de Jugador
	 */
	public int getCalificacion() {
		return calificacion;
	}
	
	/**
	 * M�todo para cambiar la cantidad total de Jugadores
	 * @param cantidad nueva cantidad total
	 */
	public static void setCantidadJugadores(int cantidad) {
		cantidadJugadores = cantidad;
	}
	
	/**
	 * @param calificacion nueva calificaci�n del jugador
	 */
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	/**
	 * @return pie h�bil del jugador
	 */
	public char getPieHabil() {
		return pieHabil;
	}
	
	/**
	 * M�todo que cambia autom�ticamente el pie h�bil del jugador
	 */
	public void setPieHabil() {
		if (pieHabil == 'd' || pieHabil == 'D') {
			this.pieHabil = 'I';
		} else {
			this.pieHabil = 'D';
		}
	}
	
	/**
	 * @return movimientos h�biles del jugador
	 */
	public int getMovHabiles() {
		return movHabiles;
	}
	
	/**
	 * @param movHabiles nuevos movimientos h�biles del jugador
	 */
	public void setMovHabiles(int movHabiles) {
		this.movHabiles = movHabiles;
	}
	
	/**
	 * @return posici�n del jugador
	 */
	public String getPosicion() {
		return posicion;
	}
	
	/**
	 * @param posicion nueva posici�n del jugador
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return club del jugador
	 */
	public String getClub() {
		return super.getClub();
	}	
	
	/**
	 * @return precio del jugador
	 */
	public double getPrecio() {
		return super.getPrecio();
	}
	
	/**
	 * M�todo para la creaci�n de un jugador, con men�es internos
	 * @return nuevo jugador creado en el men�
	 */
	public Jugador crearJugador() {
		System.out.println("Bienvenido al men� de creaci�n de Jugador.");
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre y apellido del Jugador:");
		String nombreJugador = Simulador.getScanner().nextLine().toUpperCase();
		Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
		if (equipoSeleccionado.hayEspacioEnPlantilla(true)) {
			if (!equipoSeleccionado.jugadorYaCargado(nombreJugador)) {
				System.out.println("  Ingrese la posici�n del Jugador:");
				String posicionJugador = seleccionDePosicion();
				if (equipoSeleccionado.getPlantillaEquipo().hayEspacioEnPosicion(posicionJugador)) {
					Simulador.getScanner().nextLine();
					System.out.println("  Ingrese la nacionalidad del Jugador:");
					String nacionalidadJugador = Simulador.getScanner().nextLine().toUpperCase();
					System.out.println("  Ingreso de edad del Jugador:");
					int edadJugador = Simulador.ingresoOpcion(15, 40);
					System.out.println("  Ingreso de calificaci�n del Jugador:");
					int calificacionJugador = Simulador.ingresoOpcion(49, 99);
					String calidadJugador = seleccionDeCalidad(calificacionJugador);
					System.out.println("  Ingrese el pie h�bil del Jugador (i/d):");
					char pieHabilJugador = Simulador.getScanner().nextLine().charAt(0);
					while (pieHabilJugador != 'i' && pieHabilJugador != 'I' && pieHabilJugador != 'd' && pieHabilJugador != 'D') {
						System.out.println("  Por favor ingrese un valor correcto (i/d): ");
						pieHabilJugador = Simulador.getScanner().nextLine().charAt(0);
					}
					if (pieHabilJugador == 'i') {
						pieHabilJugador = 'I';
					} else {
						if (pieHabilJugador == 'd') {
							pieHabilJugador = 'D';
						}
					}
					System.out.println("  Ingreso del nivel de movimientos h�biles del Jugador:");
					int movHabilesJugador = Simulador.ingresoOpcion(1, 5);
					Simulador.getScanner().nextLine();
					double precioJugador = seleccionDePrecio(calificacionJugador, calidadJugador);
					Jugador nuevoJugador = new Jugador(nombreJugador, equipoSeleccionado.getNombreEquipo(), equipoSeleccionado.getNombreLiga(), nacionalidadJugador, edadJugador, calidadJugador, precioJugador, calificacionJugador, pieHabilJugador, movHabilesJugador, posicionJugador);
					return nuevoJugador;
				}
			} else {
				System.out.println("  Ya hay un jugador con el nombre " + nombreJugador + " cargado en el equipo.");
			}
		} else {
			System.out.println("  No hay espacio en el equipo seleccionado. Intente modificar uno de los jugadores ya existentes.");
		}
		return null;
	}
	
	/**
	 * M�todo que lee el precio del jugador seg�n la valoraci�n y calidad del mismo
	 * @param calificacionJugador calificaci�n num�rica del jugador
	 * @param calidadJugador calidad del jugador
	 * @return nuevo precio
	 */
	public double seleccionDePrecio(int calificacionJugador, String calidadJugador) {
		double retorno;
		if (calificacionJugador<65) {
			if (calidadJugador.equals("ESPECIAL")) { //Bronce especial
				System.out.println(" Ingreso de precio: ");
				retorno = Simulador.ingresoOpcion(1200, 3000);
				return retorno;
			} else { //Bronce normal
				System.out.println(" Ingreso de precio: ");
				retorno = Simulador.ingresoOpcion(200, 2000);
				return retorno;
			}
		} else {
			if (calificacionJugador<75) {
				if (calidadJugador.equals("ESPECIAL")) { //Plata especial
					System.out.println(" Ingreso de precio: ");
					retorno = Simulador.ingresoOpcion(2400, 7000);
					return retorno;
				} else { //Plata normal
					System.out.println(" Ingreso de precio: ");
					retorno = Simulador.ingresoOpcion(400, 5000);
					return retorno;
				}
			} else {
				if (calificacionJugador<85) {
					if (calidadJugador.equals("ESPECIAL")) { //Oro especial <85
						System.out.println(" Ingreso de precio: ");
						retorno = Simulador.ingresoOpcion(3700, 13000);
						return retorno;
					} else { //Oro normal <85
						System.out.println(" Ingreso de precio: ");
						retorno = Simulador.ingresoOpcion(700, 10000);
						return retorno;
					}
			} else {
					//Oro especial >85
				System.out.println(" Ingreso de precio: ");
				retorno = Simulador.ingresoOpcion(15000, 100000);
					return retorno;
				}
			}
		}
	}

	/**
	 * M�todo para seleccionar la calidad del jugador dentro de las opciones dadas
	 * @return nueva calidad del jugador
	 */
	public String seleccionDeCalidad(int calificacionDeJugador) {
		System.out.println("�El jugador es de calidad especial? (s para confirmar): ");
		Simulador.getScanner().nextLine();
		char opcion = Simulador.getScanner().nextLine().charAt(0);
		if (opcion == 's' || opcion == 'S') {
			System.out.println("  Calidad asignada: ESPECIAL.");
			return "ESPECIAL";
		} else {
			if (calificacionDeJugador < 65) {
				System.out.println("  Calidad asignada seg�n la calificaci�n del jugador: BRONCE.");
				return "BRONCE";
			} else {
				if (calificacionDeJugador < 75) {
					System.out.println("  Calidad asignada seg�n la calificaci�n del jugador: PLATA.");
					return "PLATA";
				} else {
					System.out.println("  Calidad asignada seg�n la calificaci�n del jugador: ORO.");
					return "ORO";
				}
			}
		}
	}

	/**
	 * M�todo para seleccionar la posici�n del jugador dentro de las opciones dadas
	 * @return nueva posici�n del jugador
	 */
	public String seleccionDePosicion() {
		System.out.println("  A continuaci�n est�n las posiciones disponibles:");
		System.out.println("    1. Portero.");
		System.out.println("    2. Defensor.");
		System.out.println("    3. Mediocampista.");
		System.out.println("    4. Delantero.");
		System.out.println("  Ingrese la posici�n deseada: ");
		int opcion = Simulador.ingresoOpcion(1, 4);
		switch (opcion) {
			case 1:
				return "PO";
			case 2:
				System.out.println("  Opciones de Defensor disponibles:");
				System.out.println("    1. Defensor Central (DFC).");
				System.out.println("    2. Lateral Izquierdo (LI).");
				System.out.println("    3. Lateral Derecho (LD).");
				System.out.println("  Ingrese la posici�n deseada: ");
				opcion = Simulador.ingresoOpcion(1, 3);
				switch (opcion) {
					case 1:
						return "DFC";
					case 2:
						return "LI";
					case 3:
						return "LD";
				}
				break;
			case 3:
				System.out.println("  Opciones de Mediocampista disponibles:");
				System.out.println("    1. Mediocampista Central (MC).");
				System.out.println("    2. Mediocampista Izquierdo (MI).");
				System.out.println("    3. Mediocampista Derecho (MD).");
				System.out.println("    4. Mediocampista Ofensivo (MCO).");
				System.out.println("  Ingrese la posici�n deseada: ");
				opcion = Simulador.ingresoOpcion(1, 4);
				switch (opcion) {
					case 1:
						return "MC";
					case 2:
						return "MI";
					case 3:
						return "MD";
					case 4:
						return "MCO";
				}
				break;
			case 4:
				System.out.println("  Opciones de Delantero disponibles:");
				System.out.println("    1. Delantero Central (DC).");
				System.out.println("    2. Extremo Izquierdo (EI).");
				System.out.println("    3. Extremo Derecho (ED).");
				System.out.println("  Ingrese la posici�n deseada: ");
				opcion = Simulador.ingresoOpcion(1, 3);
				switch (opcion) {
					case 1:
						return "DC";
					case 2:
						return "EI";
					case 3:
						return "ED";
				}
				break;
		}
		return "";
	}
	
	/**
	 * Listado de informaci�n del Jugador
	 */
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.toString() + " Calificaci�n: " + this.calificacion + ".\n Pie h�bil: "+ this.pieHabil + ".\n Movimientos h�biles: ");
		for (int i=0; i<this.movHabiles; i++) {
			retorno.append("*");
		}
		retorno.append(".\n Posici�n: " + this.posicion + ".\n ID: " + super.getID() + ".");
		return retorno.toString();
	}
	
}
