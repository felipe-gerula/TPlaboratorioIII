package excepciones;

import Clases.Plantilla;

public class SinEspacioEnPosicionException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8940924120787242438L;

	public SinEspacioEnPosicionException(String mensajeError) {
		super(mensajeError);
	}
	
	public static void comprobarPosicion (String posicionJugador, Plantilla plantilla) throws SinEspacioEnPosicionException{
		if (posicionJugador.equals("PO") && !hayEspacioPortero(plantilla.getCantidadPorteros())) {
			throw new SinEspacioEnPosicionException("No hay espacio en la posición de Portero.");
		} else {
			if (posicionJugador.equals("DFC") || posicionJugador.equals("LI") || posicionJugador.equals("LD") && !hayEspacioDefensor(plantilla.getCantidadDefensores())) {
				throw new SinEspacioEnPosicionException("No hay espacio en la posición de Defensor.");
			} else {
				if (posicionJugador.equals("MC") || posicionJugador.equals("MI") || posicionJugador.equals("MD") || posicionJugador.equals("MCO") && !hayEspacioMediocampista(plantilla.getCantidadMediocampistas())) {
					throw new SinEspacioEnPosicionException("No hay espacio en la posición de Mediocampista.");
				} else {
					if (!hayEspacioDelantero(plantilla.getCantidadDelanteros())) {
						throw new SinEspacioEnPosicionException("No hay espacio en la posición de Portero.");
					}
				}
			}
		}
	}

	private static boolean hayEspacioDelantero(int cantidadDelanteros) {
		if (cantidadDelanteros <3) {
			return true;
		}
		return false;
	}

	private static boolean hayEspacioMediocampista(int cantidadMediocampistas) {
		if (cantidadMediocampistas <3) {
			return true;
		}
		return false;
	}

	private static boolean hayEspacioDefensor(int cantidadDefensores) {
		if (cantidadDefensores <4) {
			return true;
		}
		return false;
	}

	private static boolean hayEspacioPortero(int cantidadPorteros) {
		if (cantidadPorteros == 0) {
			return true;
		}
		return false;
	}
	
}
