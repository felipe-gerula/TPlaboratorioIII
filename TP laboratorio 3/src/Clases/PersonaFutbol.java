package Clases;
/** 
 *  Esta clase abstracta contiene los atributos de PersonaFutbol
 *  Clase padre de Jugador y DirectorTecnico
 *  @author 
 */
public abstract class PersonaFutbol {
	private boolean estado; //true si es válido (alta)
	private String nombreApellido;
	private String club;
	private String liga;
	private String nacionalidad;
	private int edad;
	private String tipo; //bronce, plata, oro, especial
	private double precio;
	
	public PersonaFutbol(){
		estado = false;
		nombreApellido = "";
		club = "";
		liga = "";
		nacionalidad = "";
		edad = 0;
		tipo = "";
		precio = 0;
	}
	
	public boolean getEstado () {
		return this.estado;
	}
	
}
