package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Jugador
 *  @author 
 */
public class Jugador {
	private static int cantidadJugadores;
	private int idJugador;
	private int calificacion;
	private char pieHabil;
	private int movHabiles;
	private String posicion;
	public static int getCantidadJugadores() {
		return cantidadJugadores;
	}
	public static void setCantidadJugadores(int cantidadJugadores) {
		Jugador.cantidadJugadores = cantidadJugadores;
	}
	public int getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(int idJugador) {
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
	
	
}
