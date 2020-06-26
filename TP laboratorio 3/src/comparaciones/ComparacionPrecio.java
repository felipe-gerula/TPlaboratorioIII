package comparaciones;

import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar dos elementos de PersonaFutbol por precio
 */
public class ComparacionPrecio extends Comparacion<PersonaFutbol>{
	
	/**
	 * M�todo que realiza la comparaci�n por precio
	 */
	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return  (int)(persona1.getPrecio() - persona2.getPrecio());
	}

}