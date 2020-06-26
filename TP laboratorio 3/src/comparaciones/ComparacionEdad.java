package comparaciones;

import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar dos elementos de PersonaFutbol por edad
 */
public class ComparacionEdad extends Comparacion<PersonaFutbol>{
	
	/**
	 * Método que realiza la comparación por edad
	 */
	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return  persona1.getEdad() - persona2.getEdad();
	}
}
