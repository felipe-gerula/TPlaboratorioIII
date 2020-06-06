package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo PersonaSistema
 *  @author 
 */
public abstract class PersonaSistema {
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

	public boolean cambiarPassword() {
		return true; //TODO programar funcion
	}
	
	public abstract PersonaSistema crearPersona();
	
	public abstract PersonaSistema acceder();

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
