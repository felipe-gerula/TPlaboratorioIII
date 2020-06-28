package Menu;

import Clases.Simulador;

/**
 * Clase que instancia el Simulador e invoca a su listado de opciones, desde el cual se permite el acceso y
 * creación de usuarios y administradores
 */
public class Main {

	public static void main(String[] args) {
		Simulador menuSimulador = new Simulador();
		menuSimulador.listadoOpciones();
	}

}
