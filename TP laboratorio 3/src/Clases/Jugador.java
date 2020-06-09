package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Jugador
 *  @see "Jugadores FIFA 20"<a href=https://www.fifaindex.com/es/players/fifa20/> JugadorFIFA 20</a>
 *  @author 
 */
public class Jugador extends PersonaFutbol{
	private static int cantidadJugadores;
	private int idJugador;
	private int calificacion;
	private char pieHabil;
	private int movHabiles;
	private String posicion;
	
	
	
	public Jugador(String nombreApellido, String club, String liga, String nacionalidad, int edad, String tipo, double precio, int calificacion, char pieHabil, int movHabiles, String posicion) {
		super(nombreApellido, club, liga, nacionalidad, edad, tipo, precio);
		this.idJugador = cantidadJugadores;
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
	
	public static void setCantidadJugadores(int cantidadJugadores) {
		Jugador.cantidadJugadores = cantidadJugadores;
	}
	
	public int getIDJugador() {
		return idJugador;
	}
	
	public void setIDJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	
	public int getCalificacion() {
		return calificacion;
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
	
	public Jugador crearJugador() {
		System.out.println("Bienvenido al men� de creaci�n de Jugador.");
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
		System.out.println("  Ingrese la calificaci�n del Jugador:");
		int calificacionJugador = Simulador.getScanner().nextInt();
		///TODO m�todo que pregunte si es especial, y si no que asigne el nivel de calidad seg�n la calificaci�n
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nivel de calidad del Jugador (bronce, plata, oro, o especial):");
		String calidadJugador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el pie h�bil del Jugador (i/d):"); //TODO validaci�n
		char pieHabilJugador = Simulador.getScanner().nextLine().charAt(0);
		System.out.println("  Ingrese el nivel de movimientos h�biles del Jugador (1-5):"); //TODO validaci�n
		int movHabilesJugador = Simulador.getScanner().nextInt();
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la posici�n del Jugador:"); //TODO hacer men� que muestra las opciones disponibles
		String posicionJugador = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el precio del Jugador:"); //TODO hacer m�todo que limite los precios seg�n si es especial y la calificaci�n del jugador
		double precioJugador = Simulador.getScanner().nextDouble();
		Jugador nuevoJugador = new Jugador(nombreJugador, equipoJugador, ligaJugador, nacionalidadJugador, edadJugador, calidadJugador, precioJugador, calificacionJugador, pieHabilJugador, movHabilesJugador, posicionJugador);
		return nuevoJugador;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Calificaci�n: " + this.calificacion + ".\n Pie h�bil: "+ this.pieHabil + ".\n Estrellas movimientos h�biles: " + this.movHabiles + ".\n Posici�n: " + this.posicion + ".\n ID: " + this.idJugador + "."; //TODO que meustre estrellas en vez de n�mero
	}
	
}
