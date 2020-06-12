package Clases;

import java.io.Serializable;

/** 
 *  Esta clase nos permite crear objetos de tipo Vestimenta
 *  Utilizada por el DT´s
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

	@Override
	public String toString() {
		return " Vestimenta equipada: \n  Prenda Superior: " + prendaSuperior + ".\n  Prenda Inferior: " + prendaInferior + ".";
	}
	
	
}
