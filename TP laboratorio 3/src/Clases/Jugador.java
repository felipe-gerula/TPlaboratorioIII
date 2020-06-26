package Clases;

/** 
 *  Esta clase nos permite crear objetos del tipo Jugador, la cual cuenta con los atributos y métodos 
 *  necesarios para su gestión. Hereda de PersonaFutbol
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
	 * Constructor de Jugador con los parámetros a asignar al nuevo objeto
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
	 * Constructor vacío que llama al constructor vacío de PersonaFutbol
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
	 * @return calificación de la instancia de Jugador
	 */
	public int getCalificacion() {
		return calificacion;
	}
	
	/**
	 * Método para cambiar la cantidad total de Jugadores
	 * @param cantidad nueva cantidad total
	 */
	public static void setCantidadJugadores(int cantidad) {
		cantidadJugadores = cantidad;
	}
	
	/**
	 * @param calificacion nueva calificación del jugador
	 */
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	/**
	 * @return pie hábil del jugador
	 */
	public char getPieHabil() {
		return pieHabil;
	}
	
	/**
	 * Método que cambia automáticamente el pie hábil del jugador
	 */
	public void setPieHabil() {
		if (pieHabil == 'd' || pieHabil == 'D') {
			this.pieHabil = 'I';
		} else {
			this.pieHabil = 'D';
		}
	}
	
	/**
	 * @return movimientos hábiles del jugador
	 */
	public int getMovHabiles() {
		return movHabiles;
	}
	
	/**
	 * @param movHabiles nuevos movimientos hábiles del jugador
	 */
	public void setMovHabiles(int movHabiles) {
		this.movHabiles = movHabiles;
	}
	
	/**
	 * @return posición del jugador
	 */
	public String getPosicion() {
		return posicion;
	}
	
	/**
	 * @param posicion nueva posición del jugador
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
	 * Método para la creación de un jugador, con menúes internos
	 * @return nuevo jugador creado en el menú
	 */
	public Jugador crearJugador() {
		System.out.println("Bienvenido al menú de creación de Jugador.");
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre y apellido del Jugador:");
		String nombreJugador = Simulador.getScanner().nextLine().toUpperCase();
		Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
		if (equipoSeleccionado.hayEspacioEnPlantilla(true)) {
			if (!equipoSeleccionado.jugadorYaCargado(nombreJugador)) {
				System.out.println("  Ingrese la posición del Jugador:");
				String posicionJugador = seleccionDePosicion();
				if (equipoSeleccionado.getPlantillaEquipo().hayEspacioEnPosicion(posicionJugador)) {
					Simulador.getScanner().nextLine();
					System.out.println("  Ingrese la nacionalidad del Jugador:");
					String nacionalidadJugador = Simulador.getScanner().nextLine().toUpperCase();
					System.out.println("  Ingreso de edad del Jugador:");
					int edadJugador = Simulador.ingresoOpcion(15, 40);
					System.out.println("  Ingreso de calificación del Jugador:");
					int calificacionJugador = Simulador.ingresoOpcion(49, 99);
					String calidadJugador = seleccionDeCalidad(calificacionJugador);
					System.out.println("  Ingrese el pie hábil del Jugador (i/d):");
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
					System.out.println("  Ingreso del nivel de movimientos hábiles del Jugador:");
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
	 * Método que lee el precio del jugador según la valoración y calidad del mismo
	 * @param calificacionJugador calificación numérica del jugador
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
	 * Método para seleccionar la calidad del jugador dentro de las opciones dadas
	 * @return nueva calidad del jugador
	 */
	public String seleccionDeCalidad(int calificacionDeJugador) {
		System.out.println("¿El jugador es de calidad especial? (s para confirmar): ");
		Simulador.getScanner().nextLine();
		char opcion = Simulador.getScanner().nextLine().charAt(0);
		if (opcion == 's' || opcion == 'S') {
			System.out.println("  Calidad asignada: ESPECIAL.");
			return "ESPECIAL";
		} else {
			if (calificacionDeJugador < 65) {
				System.out.println("  Calidad asignada según la calificación del jugador: BRONCE.");
				return "BRONCE";
			} else {
				if (calificacionDeJugador < 75) {
					System.out.println("  Calidad asignada según la calificación del jugador: PLATA.");
					return "PLATA";
				} else {
					System.out.println("  Calidad asignada según la calificación del jugador: ORO.");
					return "ORO";
				}
			}
		}
	}

	/**
	 * Método para seleccionar la posición del jugador dentro de las opciones dadas
	 * @return nueva posición del jugador
	 */
	public String seleccionDePosicion() {
		System.out.println("  A continuación están las posiciones disponibles:");
		System.out.println("    1. Portero.");
		System.out.println("    2. Defensor.");
		System.out.println("    3. Mediocampista.");
		System.out.println("    4. Delantero.");
		System.out.println("  Ingrese la posición deseada: ");
		int opcion = Simulador.ingresoOpcion(1, 4);
		switch (opcion) {
			case 1:
				return "PO";
			case 2:
				System.out.println("  Opciones de Defensor disponibles:");
				System.out.println("    1. Defensor Central (DFC).");
				System.out.println("    2. Lateral Izquierdo (LI).");
				System.out.println("    3. Lateral Derecho (LD).");
				System.out.println("  Ingrese la posición deseada: ");
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
				System.out.println("  Ingrese la posición deseada: ");
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
				System.out.println("  Ingrese la posición deseada: ");
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
	 * Listado de información del Jugador
	 */
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.toString() + " Calificación: " + this.calificacion + ".\n Pie hábil: "+ this.pieHabil + ".\n Movimientos hábiles: ");
		for (int i=0; i<this.movHabiles; i++) {
			retorno.append("*");
		}
		retorno.append(".\n Posición: " + this.posicion + ".\n ID: " + super.getID() + ".");
		return retorno.toString();
	}
	
}
