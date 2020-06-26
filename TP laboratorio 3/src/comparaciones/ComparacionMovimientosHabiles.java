package comparaciones;

import Clases.Jugador;
import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar por movimientos hábiles.
 * Si bien hablamos de PersonaFutbol, este criterio aplica solamente para jugadores
 */
public class ComparacionMovimientosHabiles extends Comparacion<PersonaFutbol>{
	
	/**
	 * Método que realiza la comparación por movimientos hábiles
	 */
	@Override
	public int compare(PersonaFutbol jugador1, PersonaFutbol jugador2) {
		Jugador aux1 = (Jugador)jugador1;
		Jugador aux2 = (Jugador)jugador2;
		return aux1.getMovHabiles() - aux2.getMovHabiles();
	}

}