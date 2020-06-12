package Clases;

import java.io.Serializable;

/** 
 *  Esta clase abstracta contiene los atributos de PersonaSistema
 *  clase padre de GestionAdministrador y GestionUsuario
 *  @author 
 */
public abstract class PersonaSistema implements Serializable{
	private static final long serialVersionUID = -6734207281855156759L;
	private String nombre;
	private String password;
	
	public PersonaSistema(String nombreUsuario, String passUsuario) {
		this.nombre = nombreUsuario;
		this.password = passUsuario;
	}

	public PersonaSistema() {
		nombre = "";
		password = "";
	}

	public PersonaSistema(String nombreUsuario) {
		this.nombre = nombreUsuario;
	}
	
    /**
     * Cambia la contraseña del usuario
     */
	public void cambiarPassword() {
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la nueva contraseña deseada: ");
		setPassword(Simulador.getScanner().nextLine());
		System.out.println("  Contraseña cambiada con éxito.");
	}
	
	/**
	 * permite crear usuarios nuevos para el sistema
	 * @return el nuevo usuario creado  
	 */
	public abstract PersonaSistema crearPersona();
	
	/**
	 * permite acceder al sistema a un usuario
	 * @return el usuario ingresado
	 */
	public abstract PersonaSistema acceder();
	
/**
 * compara la contraseña ingresada por el usuario con la que esta cargada en el sistema
 * @return si es true la contraseña ingresa es valido, sino retorna false
 */
	public boolean comparacionPassword() {
		int intentos = 3;
		String passwordLeida;
		System.out.println("Ingrese la contraseña (3 intentos restantes): ");
		passwordLeida = Simulador.getScanner().nextLine();
		if (passwordLeida.equals(this.password)) {
			return true;
		} else {
			while (intentos>1) {
				intentos--;
				System.out.println("Contraseña incorrecta. Ingreséla nuevamente ("+ intentos + " intentos restantes): ");
				passwordLeida = Simulador.getScanner().nextLine();
				if (passwordLeida.equals(this.password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) { ///Comparamos dos PersonaSistema por su nombre
		if (obj != null && obj instanceof PersonaSistema) {
			PersonaSistema personaSistemaRecibida = (PersonaSistema)obj;
			return personaSistemaRecibida.getNombre().equals(this.getNombre());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
}
