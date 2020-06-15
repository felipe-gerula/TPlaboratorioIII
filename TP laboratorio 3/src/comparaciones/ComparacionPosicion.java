package comparaciones;

import Clases.Jugador;
import Clases.PersonaFutbol;

public class ComparacionPosicion extends Comparacion<PersonaFutbol>{
	
	@Override
	public int compare(PersonaFutbol jugador1, PersonaFutbol jugador2) {
		Jugador aux1 = (Jugador)jugador1;
		Jugador aux2 = (Jugador)jugador2;
		String pos1 = aux1.getPosicion();
		String pos2 = aux2.getPosicion();
		if (pos1.equals(pos2)) { //Si son iguales
			return 0;
		} else { //Son diferentes
			if (pos1.equals("PO")) { //Si el primero es arquero, el primero es menor
				return -1;
			} else {
				if (pos2.equals("PO")) { //Si el segundo es arquero, el primero es mayor
					return 1;
				} else { //Ninguno es arquero
					if (pos1.equals("DFC") || pos1.equals("LI") || pos1.equals("LD")) { //Si el primero es defensor, el primero es menor
						return -1;
					} else {
						if (pos2.equals("DFC") || pos2.equals("LI") || pos2.equals("LD")) { //Si el segundo es defensor, el primero es mayor
							return 1;
						} else { //Ninguno es defensor
							if (pos1.equals("MC") || pos1.equals("MI") || pos1.equals("MD") || pos1.equals("MCO")) { //Si el primero es mediocampista, el primero es menor
								return -1;
							} else { //Si el segundo es mediocampista, el primero es mayor
								return 1;
							}
						}
					}
				}
			}
		}
	}
}
