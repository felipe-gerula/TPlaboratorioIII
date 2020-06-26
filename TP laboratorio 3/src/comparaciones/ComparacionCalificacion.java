package comparaciones;

import Clases.Jugador;
import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar por calificación.
 * Si bien hablamos de PersonaFutbol, este criterio aplica solamente para jugadores
 */
public class ComparacionCalificacion extends Comparacion<PersonaFutbol>{

	/**
	 * Método que realiza la comparación por calificación
	 */
	@Override
	public int compare(PersonaFutbol jugador1, PersonaFutbol jugador2) {
		Jugador aux1 = (Jugador)jugador1;
		Jugador aux2 = (Jugador)jugador2;
		return aux2.getCalificacion() - aux1.getCalificacion();
	}

}
