package Clases;

import java.io.Serializable;

/** 
 *  Esta clase nos permite crear objetos de tipo Vestimenta
 *  Utilizada por el DT�s
 *  @author 
 */
public class Vestimenta implements Serializable{
	private static final long serialVersionUID = 1L;
	private String prendaSuperior;
	private String prendaInferior;
	
	public Vestimenta() {
		prendaSuperior = "";
		prendaInferior = "";
	}
	
	public void setVestimenta(String nuevaParteSuperior, String nuevaParteInferior) {
		this.prendaSuperior = nuevaParteSuperior;
		this.prendaInferior = nuevaParteInferior;
	}
	
	public String getPrendaSuperior () {
		return this.prendaSuperior;
	}
	
	public String getPrendaInferior () {
		return this.prendaInferior;
	}
	
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

	@Override
	public String toString() {
		return " Vestimenta equipada: \n  Prenda Superior: " + prendaSuperior + ".\n  Prenda Inferior: " + prendaInferior + ".";
	}
	
	
}
