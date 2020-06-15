package comparaciones;

import Clases.PersonaFutbol;

public class ComparacionID extends Comparacion<PersonaFutbol>{
	
	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return  persona1.getID() - persona2.getID();
	}

}
