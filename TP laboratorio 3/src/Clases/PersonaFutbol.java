package Clases;

import java.io.Serializable;


/** 
 *  Clase abstracta que contiene los atributos de PersonaFutbol.
 *  Clase padre de Jugador y DirectorTecnico
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
	
	/**
	 * Constructor vacío que asigna automáticamente valores de una nueva instancia
	 */
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
	
	/**
	 * Constructor de PersonaFutbol con los parámetros a asignar al nuevo objeto
	 */
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

	/**
	 * @param estado nuevo estado de la PersonaFutbol
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * @param nombreApellido nuevo nombre y apellido de la PersonaFutbol
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * @param club nuevo club de la PersonaFutbol
	 */
	public void setClub(String club) {
		this.club = club;
	}

	/**
	 * @param liga nueva liga de la PersonaFutbol
	 */
	public void setLiga(String liga) {
		this.liga = liga;
	}

	/**
	 * @param nacionalidad nueva nacionalidad de la PersonaFutbol
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @param edad nueva edad de la PersonaFutbol
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @param tipo nuevo tipo de la PersonaFutbol
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @param precio nuevo precio de la PersonaFutbol
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return estado de la PersonaFutbol
	 */
	public boolean getEstado () {
		return this.estado;
	}
	
	/**
	 * @return club de la PersonaFutbol
	 */
	public String getClub () {
		return club;
	}
	
	/**
	 * @return liga de la PersonaFutbol
	 */
	public String getLiga () {
		return liga;
	}
	
	/**
	 * @return ID de la PersonaFutbol
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return nombre y apellido de la PersonaFutbol
	 */
	public String getNombre() {
		return this.nombreApellido;
	}
	
	/**
	 * @return precio de la PersonaFutbol
	 */
	public double getPrecio() {
		return this.precio;
	}

	/**
	 * @return edad de la PersonaFutbol
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @return nacionalidad de la PersonaFutbol
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @return tipo de la PersonaFutbol
	 */
	public String getTipo() {
		return tipo;
	}
	
	//Muestra de datos. No muestra el estado por si lo llama el Usuario desde ver Mercado
	@Override
	public String toString() {
		return "Datos de " + nombreApellido + ":\n Liga: " + liga + ".\n Club: " + club + ".\n Nacionalidad: " + nacionalidad + ".\n Edad: " + edad + ".\n Tipo: " + tipo + ".\n Precio: $" + precio + ".\n";
	}
}