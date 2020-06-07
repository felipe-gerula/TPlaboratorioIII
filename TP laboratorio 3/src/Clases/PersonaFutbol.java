package Clases;
/** 
 *  Esta clase abstracta contiene los atributos de PersonaFutbol
 *  Clase padre de Jugador y DirectorTecnico
 *  @author 
 */
public abstract class PersonaFutbol {
	private boolean estado; //true si es v�lido (alta)
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
	
	public PersonaFutbol(String nombreApellido, String club, String liga, String nacionalidad, int edad, String tipo, double precio) {
		super();
		this.estado = true;
		this.nombreApellido = nombreApellido;
		this.club = club;
		this.liga = liga;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.tipo = tipo;
		this.precio = precio;
	}



	public boolean getEstado () {
		return this.estado;
	}
	
	public String getClub () {
		return club;
	}
	
	public String getLiga () {
		return liga;
	}
	
}