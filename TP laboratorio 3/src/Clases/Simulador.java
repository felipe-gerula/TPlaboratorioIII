package Clases;

import Interfaces.IMenu;

public class Simulador implements IMenu{

	
	
	@Override
	public void listadoOpciones() {
		// TODO Auto-generated method stub
		System.out.println("Bienvenido al Simulador de Mercado de FIFA 20.");
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Menú Usuario.");
		System.out.println("    2. Menú Administrador.");
		System.out.println("    3. Salir.");
		System.out.println("");
		ingresarAOpcion();
	}

	@Override
	public void ingresarAOpcion() {
		int opcion = 0; //TODO sacar inicialización
		System.out.println("  Ingrese el número de opción deseada: ");
		//scanner (opcion);
		opcion = 3;
		while (opcion<1 || opcion>3) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			//scanner (opcion);
		}
		switch (opcion) {
			case 1:
				listadoOpcionesUsuario();
				break;
			case 2:
				listadoOpcionesAdministrador();
				break;
			default:
				System.out.println("Gracias por usar el Simulador. Esperamos que vuelva pronto.");
		}
	}

	@Override
	public void regresar() {
		// TODO Auto-generated method stub
		
	}
	
	public void listadoOpcionesUsuario() {
		System.out.println("Opciones usuario.");
		ingresarAOpcionUsuario();
		
	}
	
	public void ingresarAOpcionUsuario() {
		System.out.println("Ingresar opcion usuario");
	}
	
	public void listadoOpcionesAdministrador() {
		
	}
	
	public void ingresarAOpcionAdministrador() {
		
	}
	
	public Simulador() {
		
	}

}
