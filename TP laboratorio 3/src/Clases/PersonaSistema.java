package Clases;

public abstract class PersonaSistema {
	private String nombre;
	private String password;
	
	public boolean cambiarPassword() {
		return true; //TODO programar funcion
	}
	
	public abstract boolean crearPersona();
	
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
	
	
}
