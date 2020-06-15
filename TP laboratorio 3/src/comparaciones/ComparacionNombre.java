package comparaciones;

import Clases.PersonaFutbol;

public class ComparacionNombre extends Comparacion<PersonaFutbol>{

	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return persona1.getNombre().compareTo(persona2.getNombre());
	}

}
