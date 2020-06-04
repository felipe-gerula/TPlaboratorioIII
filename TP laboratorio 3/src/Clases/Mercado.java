package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Mercado
 *  Es un contenedor con todos los jugadores y DTs del juego
 *  @author 
 */
import java.util.HashSet;

public class Mercado { /// No implementamos la Interfaz IMenu porque los menúes de Mercado tienen parámetro
	private HashSet<Integer> listadoJugadores;
	private HashSet<Integer> listadoDTs;
	
	public String listarJugadores() {
		return ""; //TODO programar método
	}
	
	public String listarDTs() {
		return ""; //TODO programar método
	}
	
	public String ingresarAOpcionVerMercado() {
		String retorno = "";
		System.out.println("  Ingrese el número de opción deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>2) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				retorno = listarJugadores();
				break;
			case 2:
				retorno = listarDTs();
				break;
		}
		return retorno;
	}
	
	public String verMercado() {
		System.out.println("  Opciones de listado de Mercado:");
		System.out.println("    1. Ver Jugadores disponibles en el Mercado.");
		System.out.println("    2. Ver Directores Técnicos disponibles en el Mercado.");
		return ingresarAOpcionVerMercado();
	}
	
	public void listadoOpcionesMercado(ClubUsuario clubRecibido) {
		System.out.println("\n\nBienvenido al Mercado " /*+ nombreClub*/);
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Ver Mercado.");
		System.out.println("    2. Comprar Jugador.");
		System.out.println("    3. Comprar Director Técnico.");
		System.out.println("    4. Vender Jugador.");
		System.out.println("    5. Vender Director Técnico.");
		System.out.println("    6. Regresar al Menú del Club.");
		System.out.println("");
		ingresarAOpcionMercado(clubRecibido);
	}
	
	public void ingresarAOpcionMercado(ClubUsuario clubRecibido) { //TODO usar scan
			int opcion = 0; //TODO sacar inicialización
			System.out.println("  Ingrese el número de opción deseada: ");
			//scanner (opcion);
			opcion = 3;
			while (opcion<1 || opcion>6) {
				System.out.println("  Por favor ingrese una opción correcta: ");
				//scanner (opcion);
			}
			switch (opcion) {
				case 1:
					System.out.println(verMercado());
					break;
				case 2:
					///System.out.println(comprarJugador()); ///TODO crear función que agrega a la plantilla, si hay espacio, el ID ingresado, si es que es válido en el archivo
				case 3:
					///System.out.println(comprarDT()); ///TODO crear función que agrega al Club el DT con el ID ingresado, si hay espacio en el Club y si es que es válido en el archivo
				case 4:
					///System.out.println(venderJugador()); ///TODO crear función que busca en la la plantilla el ID ingresado, lo elimina y agrega al Club las monedas
				case 5:
					///System.out.println(venderDT()); ///TODO crear función que elimina el DT, si es que hay uno en el Club, y agrega al Club las monedas
				default:
					System.out.println("Gracias por usar el Simulador. Esperamos que vuelva pronto.");
			}
	}
	
}
