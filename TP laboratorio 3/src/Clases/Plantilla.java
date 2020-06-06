package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Plantilla
 *  Es un contenedor con todos los jugadores del equipo creado
 *  Utiliza un HashSet con los ID´s de los jugadores que conforman la plantilla
 *  @ass "Plantilla FIFA 20" <a href=https://www.futbin.com/squad-builder>PlantillaFIFA 20</a>
 *  @author 
 */
import java.util.HashSet;

public class Plantilla {
	private HashSet<Integer> listadoJugadores; ///TODO capaz que conviene que sea arreglo o arreglo JSON
	
	public Plantilla() {
		listadoJugadores = new HashSet<>();
	}
	
	/**
	 * consulta si la plantilla esta vacia
	 * @return true si la plantilla esta vacia
	 */
	public boolean plantillaVacia() {
		return listadoJugadores.isEmpty();
	}
	
	public boolean agregarJugador (int idJugador) {
		return true; //TODO programar método
	}
	
	public boolean eliminarJugador (int idJugador) {
		return true; //TODO programar método
	}
	
	public int cantidadJugadores() {
		return listadoJugadores.size(); //TODO programar método
	}
	
	public Double promedioJugadores() {
		return (Double)0.00; //TODO programar método
	}
}
