package comparaciones;

import Clases.PersonaFutbol;

public class ComparacionEdad extends Comparacion<PersonaFutbol>{
	
	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return  persona1.getEdad() - persona2.getEdad();
	}
}
