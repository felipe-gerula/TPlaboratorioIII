package comparaciones;

import Clases.Jugador;
import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar por calificaci�n.
 * Si bien hablamos de PersonaFutbol, este criterio aplica solamente para jugadores
 */
public class ComparacionCalificacion extends Comparacion<PersonaFutbol>{

	/**
	 * M�todo que realiza la comparaci�n por calificaci�n
	 */
	@Override
	public int compare(PersonaFutbol jugador1, PersonaFutbol jugador2) {
		Jugador aux1 = (Jugador)jugador1;
		Jugador aux2 = (Jugador)jugador2;
		return aux2.getCalificacion() - aux1.getCalificacion();
	}

}
