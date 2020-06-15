package comparaciones;

import Clases.PersonaFutbol;

public class ComparacionPrecio extends Comparacion<PersonaFutbol>{
	
	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return  (int)(persona1.getPrecio() - persona2.getPrecio());
	}

}