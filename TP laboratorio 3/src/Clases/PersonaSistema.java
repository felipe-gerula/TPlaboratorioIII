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

	public boolean cambiarPassword() {
		return true; //TODO programar funcion
	}
	
	public abstract PersonaSistema crearPersona();
	
	public abstract boolean acceder();

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
