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
	
	public void setPieHabil(char pieHabil) {
		this.pieHabil = pieHabil;
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
		System.out.println("Bienvenido al menú de creación de Jugador.");
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre y apellido del Jugador:");
		String nombreJugador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre de la liga del Jugador:");
		String ligaJugador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre del equipo del Jugador:");
		String equipoJugador = Simulador.getScanner().nextLine();
		//TODO controlar que no exista, y seguir. Si ya existe devuelve null
		System.out.println("  Ingrese la nacionalidad del Jugador:");
		String nacionalidadJugador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la edad del Jugador:");
		int edadJugador = Simulador.getScanner().nextInt();
		System.out.println("  Ingrese la calificación del Jugador:");
		int calificacionJugador = Simulador.getScanner().nextInt();
		///TODO método que pregunte si es especial, y si no que asigne el nivel de calidad según la calificación
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nivel de calidad del Jugador (bronce, plata, oro, o especial):");
		String calidadJugador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el pie hábil del Jugador (i/d):"); //TODO validación
		char pieHabilJugador = Simulador.getScanner().nextLine().charAt(0);
		System.out.println("  Ingrese el nivel de movimientos hábiles del Jugador (1-5):"); //TODO validación
		int movHabilesJugador = Simulador.getScanner().nextInt();
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la posición del Jugador:"); //TODO hacer menú que muestra las opciones disponibles
		String posicionJugador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el precio del Jugador:"); //TODO hacer método que limite los precios según si es especial y la calificación del jugador
		double precioJugador = Simulador.getScanner().nextDouble();
		Jugador nuevoJugador = new Jugador(nombreJugador, equipoJugador, ligaJugador, nacionalidadJugador, edadJugador, calidadJugador, precioJugador, calificacionJugador, pieHabilJugador, movHabilesJugador, posicionJugador);
		return nuevoJugador;
	}
	
	public static String opcionesListado (ArrayList<Jugador> listadoJugadores) {
		System.out.println("  A continuación están los criterios de ordenación:");
		System.out.println("    1. Por nombre.");
		System.out.println("    2. Por liga.");
		System.out.println("    3. Por club.");
		System.out.println("    4. Por calificación.");
		System.out.println("    5. Por movimientos hábiles.");
		System.out.println("    6. Por tipo.");
		System.out.println("    7. Por posición.");
		System.out.println("    8. Por precio.");
		System.out.println("");
		return ingresarAOpcionesListado(listadoJugadores);
	}
	
	public static String ingresarAOpcionesListado(ArrayList<Jugador> listadoJugadores) {
		System.out.println("  Ingrese el número de opción deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>8) {
			System.out.println("  Por favor ingrese una opción correcta: ");
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
		return super.toString() + " Calificación: " + this.calificacion + ".\n Pie hábil: "+ this.pieHabil + ".\n Estrellas movimientos hábiles: " + this.movHabiles + ".\n Posición: " + this.posicion + ".\n ID: " + super.getID() + "."; //TODO que meustre estrellas en vez de número
	}

	/*@Override
	public int compareTo(Object o) {
		Jugador recibido = (Jugador)o;
		return recibido.getNombre().compareTo(this.getNombre());
	}*/
	
}
