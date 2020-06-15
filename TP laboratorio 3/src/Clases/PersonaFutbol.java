package Clases;

import java.io.Serializable;

/** 
 *  Esta clase abstracta contiene los atributos de PersonaFutbol
 *  Clase padre de Jugador y DirectorTecnico
 *  @author 
 */
public abstract class PersonaFutbol implements Serializable {
	private static final long serialVersionUID = -7236000706191418574L;
	private boolean estado; //true si es válido (alta)
	private String nombreApellido;
	private String club;
	private String liga;
	private String nacionalidad;
	private int edad;
	private String tipo; //bronce, plata, oro, especial
	private double precio;
	private int id;
	
	public PersonaFutbol(){
		estado = false;
		nombreApellido = "";
		club = "";
		liga = "";
		nacionalidad = "";
		edad = 0;
		tipo = "";
		precio = 0;
		id = -1;
	}
	
	public PersonaFutbol(String nombreApellido, String club, String liga, String nacionalidad, int edad, String tipo, double precio, int id) {
		super();
		this.estado = true;
		this.nombreApellido = nombreApellido;
		this.club = club;
		this.liga = liga;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.tipo = tipo;
		this.precio = precio;
		this.id = id;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setPrecio(double precio) {
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
	
	public int getID() {
		return id;
	}
	
	public String getNombre() {
		return this.nombreApellido;
	}
	
	public double getPrecio() {
		return this.precio;
	}

	public int getEdad() {
		return edad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getTipo() {
		return tipo;
	}
	
	//No muestra el estado por si lo llama el Usuario desde ver Mercado
	@Override
	public String toString() {
		return "Datos de " + nombreApellido + ":\n Liga: " + liga + ".\n Club: " + club + ".\n Nacionalidad: " + nacionalidad + ".\n Edad: " + edad + ".\n Tipo: " + tipo + ".\n Precio: $" + precio + ".\n";
	}




	
	
	
}