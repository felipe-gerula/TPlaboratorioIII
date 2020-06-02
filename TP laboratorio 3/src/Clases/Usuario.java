package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Usuario
 *  @author 
 */
public class Usuario extends PersonaSistema{

	private ClubUsuario clubUsuario;
	
	@Override
	public boolean crearPersona() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceder() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void listadoOpcionesClub() {
		System.out.println("\n\nBienvenido al Club " /*+ nombreClub*/);
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Acceder al Mercado.");
		System.out.println("    2. Jugar Partido.");
		System.out.println("    3. Modificar informaci�n de Club.");
		System.out.println("    4. Listado de informaci�n de Club.");
		System.out.println("    5. Ver Plantilla.");
		System.out.println("    6. Regresar al Men� Principal.");
		System.out.println("");
		ingresarAOpcionClub();
	}

	private void ingresarAOpcionClub() {
		int opcion = 0; //TODO sacar inicializaci�n
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		//scanner (opcion);
		opcion = 3;
		while (opcion<1 || opcion>6) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			//scanner (opcion);
		}
		switch (opcion) {
			case 1:
				Simulador.mercadoDePases.listadoOpcionesMercado(clubUsuario);
				break;
			case 2:
				listadoOpcionesAdministrador();
				break;
			default:
				System.out.println("Gracias por usar el Simulador. Esperamos que vuelva pronto.");
		}
	}
	
	
	
}
