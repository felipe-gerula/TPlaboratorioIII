package comparaciones;

import Clases.Jugador;
import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar por posición.
 * Si bien hablamos de PersonaFutbol, este criterio aplica solamente para jugadores
 */
public class ComparacionPosicion extends Comparacion<PersonaFutbol>{
	
	/**
	 * Método que realiza la comparación por posición
	 */
	@Override
	public int compare(PersonaFutbol jugador1, PersonaFutbol jugador2) {
		Jugador aux1 = (Jugador)jugador1;
		Jugador aux2 = (Jugador)jugador2;
		String pos1 = aux1.getPosicion();
		String pos2 = aux2.getPosicion();
		int num1 = 0;
		int num2 = 0;
		if (pos1.equals("PO")) {
			num1 = 0;
		} else {
			if (pos1.equals("DFC") || pos1.equals("LI") || pos1.equals("LD")) {
				num1 = 1;
			} else {
				if (pos1.equals("MC") || pos1.equals("MI") || pos1.equals("MD") || pos1.equals("MCO")) {
					num1 = 2;
				} else {
					if (pos1.equals("DC") || pos1.equals("EI") || pos1.equals("ED")) {
						num1 = 3;
					}
				}
			}
		}
		if (pos2.equals("PO")) {
			num2 = 0;
		} else {
			if (pos2.equals("DFC") || pos2.equals("LI") || pos2.equals("LD")) {
				num2 = 1;
			} else {
				if (pos2.equals("MC") || pos2.equals("MI") || pos2.equals("MD") || pos2.equals("MCO")) {
					num2 = 2;
				} else {
					if (pos2.equals("DC") || pos2.equals("EI") || pos2.equals("ED")) {
						num2 = 3;
					}
				}
			}
		}
		return num1 - num2;
	}
}
