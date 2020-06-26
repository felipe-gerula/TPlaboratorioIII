package comparaciones;

import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar dos elementos de PersonaFutbol por ID
 */
public class ComparacionID extends Comparacion<PersonaFutbol>{
	
	/**
	 * M�todo que realiza la comparaci�n por ID
	 */
	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return  persona1.getID() - persona2.getID();
	}

}
