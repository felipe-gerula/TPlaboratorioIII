package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Mercado
 *  Es un contenedor con todos los jugadores y DTs del juego
 *  Utiliza un HashSet con enteros que almacenan los ID�s de los jugadores y DT�s
 *  @author 
 */
import java.util.HashSet;

public class Mercado { /// No implementamos la Interfaz IMenu porque los men�es de Mercado tienen par�metro
	private HashSet<Integer> listadoJugadores;
	private HashSet<Integer> listadoDTs;
	
	public Mercado() {
		listadoJugadores = new HashSet<>();
		listadoDTs = new HashSet<>();
	}
	
	public String listarJugadores() {
		return ""; //TODO programar m�todo, solo DTs v�lidos
	}
	
	public String listarDTs() {
		return ""; //TODO programar m�todo, solo jugadores v�lidos
	}
	
	public String ingresarAOpcionVerMercado() {
		String retorno = "";
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>2) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				if (!listadoJugadores.isEmpty()) { ///TODO cambiar por an�lisis de alta y baja en vez de isEmpty
					retorno = listarJugadores(); //TODO lista jugadores v�lidos
				} else {
					retorno = "No hay jugadores en el Mercado.";
				}
				break;
			case 2:
				if (!listadoDTs.isEmpty()) { ///TODO cambiar por an�lisis de alta y baja en vez de isEmpty
					retorno = listarDTs(); //TODO lista DTs v�lidos
				} else {
					retorno = "No hay DTs en el Mercado.";
				}
				break;
		}
		return retorno;
	}
	
	public String verMercado() {
		System.out.println("  Opciones de listado de Mercado:");
		System.out.println("    1. Ver Jugadores disponibles en el Mercado.");
		System.out.println("    2. Ver Directores T�cnicos disponibles en el Mercado.");
		return ingresarAOpcionVerMercado();
	}
	
	public void listadoOpcionesMercado(ClubUsuario clubRecibido) {
		System.out.println("\n\nBienvenido al Mercado, " + clubRecibido.getNombre());
		if(this.listadoJugadores.isEmpty() && this.listadoDTs.isEmpty()) { ///TODO cambiar por an�lisis de alta y baja en vez de isEmpty
			System.out.println(clubRecibido.getNombre() + ", lamentamos informarte que el Mercado est� vac�o. Un Administrador debe cargar datos.");
		} else {
			System.out.println("  A continuaci�n est�n las opciones:");
			System.out.println("    1. Ver Mercado.");
			System.out.println("    2. Comprar Jugador.");
			System.out.println("    3. Comprar Director T�cnico.");
			System.out.println("    4. Vender Jugador.");
			System.out.println("    5. Vender Director T�cnico.");
			System.out.println("    6. Regresar al Men� del Club.");
			System.out.println("");
			ingresarAOpcionMercado(clubRecibido);
		}
	}
	
	public void ingresarAOpcionMercado(ClubUsuario clubRecibido) { //TODO usar scan
			int opcion = 0; //TODO sacar inicializaci�n
			System.out.println("  Ingrese el n�mero de opci�n deseada: ");
			//scanner (opcion);
			opcion = 3;
			while (opcion<1 || opcion>6) {
				System.out.println("  Por favor ingrese una opci�n correcta: ");
				//scanner (opcion);
			}
			switch (opcion) {
				case 1: //Ver Mercado
					System.out.println(verMercado());
					listadoOpcionesMercado(clubRecibido);
					break;
				case 2: //Comprar Jugador
					if (clubRecibido.getPlantillaClub().cantidadJugadores()<11) {
						if (!listadoJugadores.isEmpty()) { ///TODO cambiar por an�lisis de alta y baja en vez de isEmpty
							///System.out.println(comprarJugador()); ///TODO crear funci�n que agrega a la plantilla, el ID ingresado, si es que es v�lido en el archivo
						} else {
							System.out.println("No hay jugadores en el Mercado");
						}
						listadoOpcionesMercado(clubRecibido);
					} else {
						System.out.println("Tu Club ya tiene 11 jugadores. Intent� vender uno.");
					}
					break;
				case 3: //Comprar DT
					if (!clubRecibido.getDTClub().getEstado()) {
						if (!listadoDTs.isEmpty()) { ///TODO cambiar por an�lisis de alta y baja en vez de isEmpty
							///System.out.println(comprarDT()); ///TODO crear funci�n que agrega al Club el DT con el ID ingresado, y si es que es v�lido en el archivo
						} else {
							System.out.println("No hay DTs en el Mercado.");
						}
					} else {
						System.out.println("Tu club ya cuenta con un DT. Intent� venderlo.");
					}
					listadoOpcionesMercado(clubRecibido);
					break;
				case 4: //Vender Jugador
					if (!clubRecibido.getPlantillaClub().plantillaVacia()) { ///TODO cambiar por an�lisis de alta y baja en vez de isEmpty
						///System.out.println(venderJugador()); ///TODO crear funci�n que busca en la la plantilla el ID ingresado, lo elimina y agrega al Club las monedas
					} else {
						System.out.println("No hay jugadores en el Club");
					}
					listadoOpcionesMercado(clubRecibido);
					break;
				case 5: //Vender DT
					if (clubRecibido.getDTClub().getEstado()) { ///TODO cambiar por an�lisis de alta y baja en vez de isEmpty
						///System.out.println(venderJugador()); ///TODO crear funci�n que busca en la la plantilla el ID ingresado, lo elimina y agrega al Club las monedas
					} else {
						System.out.println("No hay jugadores en el Club");
					}
					///System.out.println(venderDT()); ///TODO crear funci�n que elimina el DT, si es que hay uno en el Club, y agrega al Club las monedas
					listadoOpcionesMercado(clubRecibido);
					break;
				default:
					System.out.println("Gracias por usar el Mercado.");
					break;
			}
	}
	
}
