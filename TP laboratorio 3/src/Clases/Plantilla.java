package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Plantilla
 *  Es un contenedor con todos los jugadores del equipo creado
 *  @author 
 */
import java.util.HashSet;

public class Plantilla {
	private HashSet<Integer> listadoJugadores; ///TODO capaz que conviene que sea arreglo o arreglo JSON
	
	public Plantilla() {
		listadoJugadores = new HashSet<>();
	}
	
	public boolean plantillaVacia() {
		return listadoJugadores.isEmpty();
	}
	
	public boolean agregarJugador (int idJugador) {
		return true; //TODO programar m�todo
	}
	
	public boolean eliminarJugador (int idJugador) {
		return true; //TODO programar m�todo
	}
	
	public int cantidadJugadores() {
		return listadoJugadores.size(); //TODO programar m�todo
	}
	
	public Double promedioJugadores() {
		return (Double)0.00; //TODO programar m�todo
	}
}
