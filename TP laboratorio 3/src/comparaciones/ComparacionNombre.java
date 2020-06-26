package comparaciones;

import Clases.PersonaFutbol;

/**
 * Clase utilizada para comparar dos elementos de PersonaFutbol por nombre y apellido
 */
public class ComparacionNombre extends Comparacion<PersonaFutbol>{

	/**
	 * Método que realiza la comparación por nombre y apellido
	 */
	@Override
	public int compare(PersonaFutbol persona1, PersonaFutbol persona2) {
		return persona1.getNombre().compareTo(persona2.getNombre());
	}

}
