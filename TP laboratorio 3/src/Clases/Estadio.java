package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Estadio
 *  @author 
 */
public class Estadio {
	private String nombre;
	private int capacidad;
	private String ubicacion;
	
	public Estadio(String nombreEstadio, int capacidadEstadio, String ubicacionEstadio) {
		nombre = nombreEstadio;
		capacidad = capacidadEstadio;
		ubicacion = ubicacionEstadio;
	}

	public Estadio() {
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Estadio crearEstadio() {
		System.out.println("Bienvenido al menú de creación de Estadio.");
		System.out.println("  Ingrese el nombre del Estadio:");
		String nombreEstadio = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la capacidad del Estadio (entre 1.000 y 100.000):");
		int capacidadEstadio = Simulador.getScanner().nextInt();
		while (capacidadEstadio<1000 || capacidadEstadio>100000) {
			System.out.println("Por favor, ingrese un valor entre 1.000 y 100.000: ");
			capacidadEstadio = Simulador.getScanner().nextInt();
		}
		System.out.println("  Ingrese la ubicación del Estadio (ciudad, país o continente): ");
		Simulador.getScanner().nextLine();
		String ubicacionEstadio = Simulador.getScanner().nextLine();
		Estadio nuevoEstadio = new Estadio(nombreEstadio, capacidadEstadio, ubicacionEstadio);
		return nuevoEstadio;
	}
}
