package comparaciones;

import Clases.PersonaFutbol;

public class ComparacionTipo  extends Comparacion<PersonaFutbol>{

	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		String tipo1 = persona1.getTipo();
		String tipo2 = persona2.getTipo();
		if (tipo1.equals(tipo2)) { //Si son iguales
			return 0;
		} else { //Son diferentes
			if (tipo1.equals("ESPECIAL")) { //Si el primero es especial, el primero es mayor
				return 1;
			} else {
				if (tipo2.equals("ESPECIAL")) { //Si el segundo es especial, el primero es menor
					return -1;
				} else { //Ninguno es especial
					if (tipo1.equals("ORO")) { //Si el primero es de oro, el primero es mayor
						return 1;
					} else {
						if (tipo2.equals("ORO")) { //Si el segundo es de oro, el primero es menor
							return -1;
						} else { //Ninguno es de oro
							if (tipo1.equals("PLATA")) { //Si el primero es de plata, el primero es mayor
								return 1;
							} else { //Si el segundo es de plata, el primero es menor
								return -1;
							}
						}
					}
				}
			}
		}
		
		
	}
}
