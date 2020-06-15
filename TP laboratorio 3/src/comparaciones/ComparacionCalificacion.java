package comparaciones;

import Clases.Jugador;
import Clases.PersonaFutbol;

public class ComparacionCalificacion extends Comparacion<PersonaFutbol>{

	@Override
	public int compare(PersonaFutbol jugador1, PersonaFutbol jugador2) {
		Jugador aux1 = (Jugador)jugador1;
		Jugador aux2 = (Jugador)jugador2;
		return aux2.getCalificacion() - aux1.getCalificacion();
	}

}
