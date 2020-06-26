package Clases;

import java.io.Serializable;

/** 
 *  Clase abstracta que contiene los atributos de PersonaSistema.
 *  Clase padre de GestionAdministrador y GestionUsuario
 */
public abstract class PersonaSistema implements Serializable{
	private static final long serialVersionUID = -6734207281855156759L;
	private String nombre;
	private String password;
	
	/**
	 * Constructor de PersonaSistema que asigna nombre y contrase�a
	 * @param nombreUsuario nombre del nuevo usuario o administrador
	 * @param passUsuario constrase�a del nuevo usuario o administrador
	 */
	public PersonaSistema(String nombrePersona, String passPersona) {
		this.nombre = nombrePersona;
		this.password = passPersona;
	}

	/**
	 * Constructor vac�o que asigna autom�ticamente valores de una nueva instancia
	 */
	public PersonaSistema() {
		nombre = "";
		password = "";
	}

	/**
	 * Constructor que solamente asigna un nombre. Se utiliza para, por ejemplo, la b�squeda de datos
	 * @param nombrePersona nombre de la nueva PersonaSistema
	 */
	public PersonaSistema(String nombrePersona) {
		this.nombre = nombrePersona;
	}
	
	/**
	 * @return nombre de la PersonaSistema
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre nuevo nombre de la PersonaSistema
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return contrase�a de la PersonaSistema
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password nueva contrase�a de la PersonaSistema
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
    /**
     * M�todo para cambiar la contrase�a de la PersonaSistema instanciada
     */
	public void cambiarPassword() {
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la nueva contrase�a deseada: ");
		setPassword(Simulador.getScanner().nextLine());
		System.out.println("  Contrase�a cambiada con �xito.");
	}
	
	/**
	 * M�todo que permite la creaci�n de PersonaSistema nuevas para el sistema
	 * @return la nueva PersonaSistema creada  
	 */
	public abstract PersonaSistema crearPersona();
	
	/**
	 * M�todo que permite a una PersonaSistema acceder al sistema
	 * @return el usuario ingresado
	 */
	public abstract PersonaSistema acceder();
	
	/**
	 * M�todo que compara la contrase�a ingresada por el usuario o administrador con la que 
	 * est� cargada en el sistema
	 * @return true si la contrase�a ingresada es v�lida, false si no
	 */
	public boolean comparacionPassword() {
		int intentos = 3;
		String passwordLeida;
		System.out.println("Ingrese la contrase�a (3 intentos restantes): ");
		passwordLeida = Simulador.getScanner().nextLine();
		if (passwordLeida.equals(this.password)) {
			return true;
		} else {
			while (intentos>1) {
				intentos--;
				System.out.println("Contrase�a incorrecta. Ingres�la nuevamente ("+ intentos + " intentos restantes): ");
				passwordLeida = Simulador.getScanner().nextLine();
				if (passwordLeida.equals(this.password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * M�todo para determinar si dos PersonaSistema son equivalentes
	 */
	@Override
	public boolean equals(Object obj) { ///Comparamos dos PersonaSistema por su nombre
		if (obj != null && obj instanceof PersonaSistema) {
			PersonaSistema personaSistemaRecibida = (PersonaSistema)obj;
			return personaSistemaRecibida.getNombre().equals(this.getNombre());
		}
		return false;
	}
	
	/**
	 * M�todo usado para la carga a estructuras
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	
}
