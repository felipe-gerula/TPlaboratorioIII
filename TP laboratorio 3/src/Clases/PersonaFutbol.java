package Clases;

import java.io.Serializable;

/** 
 *  Esta clase abstracta contiene los atributos de PersonaFutbol
 *  Clase padre de Jugador y DirectorTecnico
 *  @author 
 */
public abstract class PersonaFutbol implements Serializable {
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

	
	//No muestra el estado por si lo llama el Usuario desde ver Mercado
	@Override
	public String toString() {
		return "Datos de " + nombreApellido + ":\n Liga: " + liga + ".\n Club: " + club + ".\n Nacionalidad: " + nacionalidad + ".\n Edad: " + edad + ".\n Tipo: " + tipo + ".\n Precio: $" + precio + ".\n";
	}
	
	
	
}