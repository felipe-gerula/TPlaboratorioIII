package Clases;

import java.io.Serializable;

/** 
 *  Esta clase nos permite crear objetos del tipo Estadio, la cual cuenta con los atributos y métodos 
 *  necesarios para su gestión.
 */
public class Estadio implements Serializable{
	private static final long serialVersionUID = 7254005285483003940L;
	private String nombre;
	private int capacidad;
	private String ubicacion;
	
	/**
	 * Constructor con los datos del nuevo estadio
	 * @param nombreEstadio nombre del nuevo estadio
	 * @param capacidadEstadio capacidad del nuevo estadio
	 * @param ubicacionEstadio ubicacion del nuevo estadio
	 */
	public Estadio(String nombreEstadio, int capacidadEstadio, String ubicacionEstadio) {
		nombre = nombreEstadio;
		capacidad = capacidadEstadio;
		ubicacion = ubicacionEstadio;
	}

	/**
	 * Constructor vacío de un estadio
	 */
	public Estadio() {
	}
	
	/**
	 * @return nombre del estadio instanciado
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre nuevo nombre del estadio
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return capacidad del estadio instanciado
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad capacidad del estadio instanciado
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return ubicación del estadio instanciado
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion nueva ubicación del estadio
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Método que consta de un menú de ingreso de datos para la creación de un nuevo estadio
	 * @return nuevo estadio creado por el usuario
	 */
	public Estadio crearEstadio() {
		System.out.println("Bienvenido al menú de creación de Estadio.");
		System.out.println("  Ingrese el nombre del Estadio:");
		String nombreEstadio = Simulador.getScanner().nextLine();
		System.out.println("  Capacidad del Estadio.");
		int capacidadEstadio = Simulador.ingresoOpcion(1000, 100000);
		System.out.println("  Ingrese la ubicación del Estadio (ciudad, país o continente): ");
		Simulador.getScanner().nextLine();
		String ubicacionEstadio = Simulador.getScanner().nextLine();
		Estadio nuevoEstadio = new Estadio(nombreEstadio, capacidadEstadio, ubicacionEstadio);
		return nuevoEstadio;
	}

	/**
	 * Método que lista las opciones de modificación del estadio
	 */
	public void listadoOpcionesModificacionEstadio() {
		System.out.println("  A continuación están las opciones de modificación del Estadio:");
		System.out.println("    1. Modificar Nombre del Estadio.");
		System.out.println("    2. Modificar Capacidad del Estadio.");
		System.out.println("    3. Modificar Ubicación del Estadio.");
		System.out.println("    4. Regresar al Menú del Club.");
		ingresarAOpcionModificacionEstadio();
	}
	
	/**
	 * Método de acceso a la opción de modificación deseada
	 */
	private void ingresarAOpcionModificacionEstadio() {
		int opcion = Simulador.ingresoOpcion(1, 4);
		switch (opcion) {
			case 1: //nombre del estadio
				System.out.println("  Ingrese el nuevo Nombre del Estadio: ");
				Simulador.getScanner().nextLine();
				setNombre(Simulador.getScanner().nextLine());
				Simulador.guardarArchivoUsuarios();
				break;
			case 2: //capacidad del estadio
				System.out.println("  Cambio de capacidad del Estadio:");
				int capacidadEstadio = Simulador.ingresoOpcion(1000, 100000);
				setCapacidad(capacidadEstadio);
				Simulador.guardarArchivoUsuarios();
				break;
			case 3: //ubicación del estadio
				System.out.println("  Ingrese la ubicación del Estadio (ciudad, país o continente): ");
				Simulador.getScanner().nextLine();
				String ubicacionEstadio = Simulador.getScanner().nextLine();
				setUbicacion(ubicacionEstadio);
				Simulador.guardarArchivoUsuarios();
				break;
		}
	}
	
	/**
	 * Información del estadio
	 */
	@Override
	public String toString() {
		return ("Información del Estadio:\n  Nombre: " + getNombre() +".\n  Ubicación: " + getUbicacion() + ".\n  Capacidad: " + getCapacidad());
	}
}
