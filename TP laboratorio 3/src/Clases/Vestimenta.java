package Clases;

import java.io.Serializable;

/** 
 *  Clase que nos permite crear objetos del tipo Vestimenta. Cuenta con los atributos y m�todos necesarios
 *  para su gesti�n y modificaci�n. Es utilizada por los DTs 
 */
public class Vestimenta implements Serializable{
	private static final long serialVersionUID = 1L;
	private String prendaSuperior;
	private String prendaInferior;
	
	/**
	 * Constructor vac�o que asigna valores predeterminados a las prendas
	 */
	public Vestimenta() {
		prendaSuperior = "";
		prendaInferior = "";
	}
	
	/**
	 * @param nuevaParteSuperior nueva parte superior de la vestimenta
	 * @param nuevaParteInferior nueva parte inferior de la vestimenta
	 */
	public void setVestimenta(String nuevaParteSuperior, String nuevaParteInferior) {
		this.prendaSuperior = nuevaParteSuperior;
		this.prendaInferior = nuevaParteInferior;
	}
	
	/**
	 * @return prenda superior de la vestimenta
	 */
	public String getPrendaSuperior () {
		return this.prendaSuperior;
	}
	
	/**
	 * @return prenda inferior de la vestimenta
	 */
	public String getPrendaInferior () {
		return this.prendaInferior;
	}
	
	/**
	 * M�todo que lista y lee las opciones de vestimenta
	 */
	public void cambiarVestimenta() {
		int opcion;
		String opcionSuperior = "";
		String opcionInferior = "";
		System.out.println("\n\nOpciones de vestimenta: " /*+ nombreAdmin*/);
		System.out.println("  Parte superior:");
		System.out.println("    1. Saco negro.");
		System.out.println("    2. Saco azul.");
		System.out.println("    3. Camper�n Club.");
		System.out.println("    4. Sweater gris.");
		System.out.println("    5. Remera color club.");
		opcion = Simulador.ingresoOpcion(1, 5);
		switch (opcion) {
			case 1:
				opcionSuperior = "Saco negro";
				break;
			case 2:
				opcionSuperior = "Saco azul";
				break;
			case 3:
				opcionSuperior = "Camper�n Club";
				break;
			case 4:
				opcionSuperior = "Sweater gris";
				break;
			case 5:
				opcionSuperior = "Remera color club";
				break;
		}
		System.out.println("  Parte inferior:");
		System.out.println("    1. Pantal�n de vestir negro.");
		System.out.println("    2. Pantal�n de vestir azul.");
		System.out.println("    3. Jogging Club.");
		System.out.println("    4. Pantal�n de vestir gris.");
		System.out.println("    5. Pantal�n corto color club.");
		opcion = Simulador.ingresoOpcion(1, 5);
		switch (opcion) {
			case 1:
				opcionInferior = "Pantal�n de vestir negro";
				break;
			case 2:
				opcionInferior = "Pantal�n de vestir azul";
				break;
			case 3:
				opcionInferior = "Jogging Club";
				break;
			case 4:
				opcionInferior = "Pantal�n de vestir gris";
				break;
			case 5:
				opcionInferior = "Pantal�n corto color club";
				break;
		}
		prendaSuperior = opcionSuperior;
		prendaInferior = opcionInferior;
	}

	/**
	 * Informaci�n de la vestimenta
	 */
	@Override
	public String toString() {
		return " Vestimenta equipada: \n  Prenda Superior: " + prendaSuperior + ".\n  Prenda Inferior: " + prendaInferior + ".";
	}
	
	
}
