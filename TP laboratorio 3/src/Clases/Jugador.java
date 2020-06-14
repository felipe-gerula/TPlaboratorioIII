package Clases;

import java.util.ArrayList;
/*import java.util.Collections;*/

/** 
 *  Esta clase nos permite crear objetos de tipo Jugador
 *  @see "Jugadores FIFA 20"<a href=https://www.fifaindex.com/es/players/fifa20/> JugadorFIFA 20</a>
 *  @author 
 */
public class Jugador extends PersonaFutbol /*implements Comparable*/{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1017025389533618303L;
	private static int cantidadJugadores;
	private int calificacion;
	private char pieHabil;
	private int movHabiles;
	private String posicion;
	
	public Jugador(String nombreApellido, String club, String liga, String nacionalidad, int edad, String tipo, double precio, int calificacion, char pieHabil, int movHabiles, String posicion) {
		super(nombreApellido, club, liga, nacionalidad, edad, tipo, precio, cantidadJugadores);
		cantidadJugadores++;
		this.calificacion = calificacion;
		this.pieHabil = pieHabil;
		this.movHabiles = movHabiles;
		this.posicion = posicion;
	}
	
	

	public Jugador() {
		super();
	}

	public static int getCantidadJugadores() {
		return cantidadJugadores;
	}
	
	public int getIDJugador() {
		return super.getID();
	}
	
	public int getCalificacion() {
		return calificacion;
	}
	
	public static void setCantidadJugadores(int cantidad) {
		cantidadJugadores = cantidad;
	}
	
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public char getPieHabil() {
		return pieHabil;
	}
	
	public void setPieHabil() {
		if (pieHabil == 'd' || pieHabil == 'D') {
			this.pieHabil = 'I';
		} else {
			this.pieHabil = 'D';
		}
	}
	
	public int getMovHabiles() {
		return movHabiles;
	}
	
	public void setMovHabiles(int movHabiles) {
		this.movHabiles = movHabiles;
	}
	
	public String getPosicion() {
		return posicion;
	}
	
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getClub() {
		return super.getClub();
	}	
	
	public double getPrecio() {
		return super.getPrecio();
	}
	
	public Jugador crearJugador() {
		System.out.println("Bienvenido al men� de creaci�n de Jugador.");
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre y apellido del Jugador:");
		String nombreJugador = Simulador.getScanner().nextLine().toUpperCase();
		Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
		if (equipoSeleccionado.hayEspacioEnPlantilla()) {
			if (!equipoSeleccionado.jugadorYaCargado(nombreJugador)) {
				System.out.println("  Ingrese la posici�n del Jugador:"); //TODO hacer men� que muestra las opciones disponibles
				String posicionJugador = seleccionDePosicion();
				if (equipoSeleccionado.hayEspacioEnPosicion(posicionJugador)) {
					Simulador.getScanner().nextLine();
					System.out.println("  Ingrese la nacionalidad del Jugador:");
					String nacionalidadJugador = Simulador.getScanner().nextLine().toUpperCase();
					System.out.println("  Ingrese la edad del Jugador:");
					int edadJugador = Simulador.getScanner().nextInt();
					while (edadJugador<15 || edadJugador>40) {
						System.out.println("  Por favor ingrese una edad correcta (entre 15 y 40): ");
						edadJugador = Simulador.getScanner().nextInt();
					}
					System.out.println("  Ingrese la calificaci�n del Jugador:");
					int calificacionJugador = Simulador.getScanner().nextInt();
					while (calificacionJugador<49 || calificacionJugador>99) {
						System.out.println("  Por favor ingrese una calificaci�n correcta (entre 49 y 99): ");
						calificacionJugador = Simulador.getScanner().nextInt();
					}
					String calidadJugador = seleccionDeCalidad(calificacionJugador);
					System.out.println("  Ingrese el pie h�bil del Jugador (i/d):"); //TODO validaci�n
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
					System.out.println("  Ingrese el nivel de movimientos h�biles del Jugador (1-5):"); //TODO validaci�n
					int movHabilesJugador = Simulador.getScanner().nextInt();
					while (movHabilesJugador<1 || movHabilesJugador>5) {
						System.out.println("  Por favor ingrese un nivel de movimientos h�biles correcto (entre 1 y 5): ");
						movHabilesJugador = Simulador.getScanner().nextInt();
					}
					Simulador.getScanner().nextLine();
					double precioJugador = seleccionDePrecio(calificacionJugador, calidadJugador);
					Jugador nuevoJugador = new Jugador(nombreJugador, equipoSeleccionado.getNombreEquipo(), equipoSeleccionado.getNombreLiga(), nacionalidadJugador, edadJugador, calidadJugador, precioJugador, calificacionJugador, pieHabilJugador, movHabilesJugador, posicionJugador);
					return nuevoJugador;
				} else {
					System.out.println("  No hay m�s espacios para la posici�n de " + posicionJugador + " en el equipo seleccionado.");
				}
				
			} else {
				System.out.println("  Ya hay un jugador con el nombre " + nombreJugador + " cargado en el equipo.");
			}
		} else {
			System.out.println("  No hay espacio en el equipo seleccionado. Intente modificar uno de los jugadores ya existentes.");
		}
		return null;
	}
	
	private double seleccionDePrecio(int calificacionJugador, String calidadJugador) {
		double retorno;
		if (calificacionJugador<65) {
			if (calidadJugador.equals("ESPECIAL")) { //Bronce especial
				System.out.println(" Ingrese un precio entre $1200 y $3000.");
				retorno = Simulador.getScanner().nextDouble();
				while (retorno<1200 || retorno>3000) {
					System.out.println("  Por favor ingrese un precio correcto (entre $1200 y $3000): ");
					retorno = Simulador.getScanner().nextDouble();
				}
				return retorno;
			} else { //Bronce normal
				System.out.println(" Ingrese un precio entre $200 y $2000.");
				retorno = Simulador.getScanner().nextDouble();
				while (retorno<200 || retorno>2000) {
					System.out.println("  Por favor ingrese un precio correcto (entre $200 y $2000): ");
					retorno = Simulador.getScanner().nextDouble();
				}
				return retorno;
			}
		} else {
			if (calificacionJugador<75) {
				if (calidadJugador.equals("ESPECIAL")) { //Plata especial
					System.out.println(" Ingrese un precio entre $2400 y $7000.");
					retorno = Simulador.getScanner().nextDouble();
					while (retorno<2400 || retorno>7000) {
						System.out.println("  Por favor ingrese un precio correcto (entre $2400 y $7000): ");
						retorno = Simulador.getScanner().nextDouble();
					}
					return retorno;
				} else { //Plata normal
					System.out.println(" Ingrese un precio entre $400 y $5000.");
					retorno = Simulador.getScanner().nextDouble();
					while (retorno<400 || retorno>5000) {
						System.out.println("  Por favor ingrese un precio correcto (entre $400 y $5000): ");
						retorno = Simulador.getScanner().nextDouble();
					}
					return retorno;
				}
			} else {
				if (calificacionJugador<85) {
					if (calidadJugador.equals("ESPECIAL")) { //Oro especial <85
						System.out.println(" Ingrese un precio entre $3700 y $13000.");
						retorno = Simulador.getScanner().nextDouble();
						while (retorno<3700 || retorno>13000) {
							System.out.println("  Por favor ingrese un precio correcto (entre $3700 y $13000): ");
							retorno = Simulador.getScanner().nextDouble();
						}
						return retorno;
					} else { //Oro normal <85
						System.out.println(" Ingrese un precio entre $700 y $10000.");
						retorno = Simulador.getScanner().nextDouble();
						while (retorno<700 || retorno>10000) {
							System.out.println("  Por favor ingrese un precio correcto (entre $700 y $10000): ");
							retorno = Simulador.getScanner().nextDouble();
						}
						return retorno;
					}
			} else {
					//Oro especial >85
					System.out.println(" Ingrese un precio entre $15000 y $100000.");
					retorno = Simulador.getScanner().nextDouble();
					while (retorno<15000 || retorno>100000) {
						System.out.println("  Por favor ingrese un precio correcto (entre $15000 y $100000): ");
						retorno = Simulador.getScanner().nextDouble();
					}
					return retorno;
				}
			}
		}
	}



	private String seleccionDeCalidad(int calificacionDeJugador) {
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



	private String seleccionDePosicion() {
		System.out.println("  A continuaci�n est�n las posiciones disponibles:");
		System.out.println("    1. Portero.");
		System.out.println("    2. Defensor.");
		System.out.println("    3. Mediocampista.");
		System.out.println("    4. Delantero.");
		System.out.println("  Ingrese la posici�n deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>(4)) {
			System.out.println("  Por favor ingrese una opci�n correcta (entre 1 y " + (4) + "): ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				return "PO";
			case 2:
				System.out.println("  Opciones de Defensor disponibles:");
				System.out.println("    1. Defensor Central (DFC).");
				System.out.println("    2. Lateral Izquierdo (LI).");
				System.out.println("    3. Lateral Derecho (LD).");
				System.out.println("  Ingrese la posici�n deseada: ");
				opcion = Simulador.getScanner().nextInt();
				while (opcion<1 || opcion>(3)) {
					System.out.println("  Por favor ingrese una opci�n correcta (entre 1 y " + (3) + "): ");
					opcion = Simulador.getScanner().nextInt();
				}
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
				System.out.println("    4. Mediocampista Ofensico (MCO).");
				System.out.println("  Ingrese la posici�n deseada: ");
				opcion = Simulador.getScanner().nextInt();
				while (opcion<1 || opcion>(4)) {
					System.out.println("  Por favor ingrese una opci�n correcta (entre 1 y " + (4) + "): ");
					opcion = Simulador.getScanner().nextInt();
				}
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
				opcion = Simulador.getScanner().nextInt();
				while (opcion<1 || opcion>(3)) {
					System.out.println("  Por favor ingrese una opci�n correcta (entre 1 y " + (3) + "): ");
					opcion = Simulador.getScanner().nextInt();
				}
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



	public static String opcionesListado (ArrayList<Jugador> listadoJugadores) {
		System.out.println("  A continuaci�n est�n los criterios de ordenaci�n:");
		System.out.println("    1. Por nombre.");
		System.out.println("    2. Por liga.");
		System.out.println("    3. Por club.");
		System.out.println("    4. Por calificaci�n.");
		System.out.println("    5. Por movimientos h�biles.");
		System.out.println("    6. Por tipo.");
		System.out.println("    7. Por posici�n.");
		System.out.println("    8. Por precio.");
		System.out.println("");
		return ingresarAOpcionesListado(listadoJugadores);
	}
	
	public static String ingresarAOpcionesListado(ArrayList<Jugador> listadoJugadores) {
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>8) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1: //Por nombre
				/*Collections.sort(listadoJugadores);*/
				break;
			default:
				System.out.println("Gracias por usar el Listado.");
				break;
		}
		return listadoJugadores.toString();
	}
	
	@Override
	public String toString() {
		return super.toString() + " Calificaci�n: " + this.calificacion + ".\n Pie h�bil: "+ this.pieHabil + ".\n Estrellas movimientos h�biles: " + this.movHabiles + ".\n Posici�n: " + this.posicion + ".\n ID: " + super.getID() + "."; //TODO que meustre estrellas en vez de n�mero
	}

	/*@Override
	public int compareTo(Object o) {
		Jugador recibido = (Jugador)o;
		return recibido.getNombre().compareTo(this.getNombre());
	}*/
	
}
