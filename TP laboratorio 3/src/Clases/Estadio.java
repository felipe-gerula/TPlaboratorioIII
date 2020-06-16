package Clases;

import java.io.Serializable;
import java.util.Scanner;

/** 
 *  Esta clase nos permite crear objetos de tipo Estadio
 *  @author 
 */
public class Estadio implements Serializable{
	private static final long serialVersionUID = 7254005285483003940L;
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

	public void listadoOpcionesModificacionEstadio() {
		System.out.println("  A continuación están las opciones de modificación del Estadio:");
		System.out.println("    1. Modificar Nombre del Estadio.");
		System.out.println("    2. Modificar Capacidad del Estadio.");
		System.out.println("    3. Modificar Ubicación del Estadio.");
		System.out.println("    4. Regresar al Menú del Club.");
		ingresarAOpcionModificacionEstadio();
	}
	
	private void ingresarAOpcionModificacionEstadio() {
		int opcion;
		System.out.println("  Ingrese el número de opción deseada: ");
		Scanner scanner = Simulador.getScanner(); //Lo pasamos a una variable local porque tiraba error de leaking resource
		opcion = scanner.nextInt();
		while (opcion<1 || opcion>4) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = scanner.nextInt();
		}
		switch (opcion) {
			case 1: //nombre del estadio
				System.out.println("  Ingrese el nuevo Nombre del Estadio: ");
				scanner.nextLine();
				setNombre(Simulador.getScanner().nextLine());
				Simulador.guardarArchivoUsuarios();
				break;
			case 2: //capacidad del estadio
				System.out.println("  Ingrese la nueva capacidad del Estadio (entre 1.000 y 100.000):");
				int capacidadEstadio = Simulador.getScanner().nextInt();
				while (capacidadEstadio<1000 || capacidadEstadio>100000) {
					System.out.println("Por favor, ingrese un valor entre 1.000 y 100.000: ");
					capacidadEstadio = Simulador.getScanner().nextInt();
				}
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
	
	@Override
	public String toString() {
		return ("Información del Estadio:\n  Nombre: " + getNombre() +".\n  Ubicación: " + getUbicacion() + ".\n  Capacidad: " + getCapacidad());
	}
}
