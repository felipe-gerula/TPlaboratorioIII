package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo Mercado
 *  Es un contenedor con todos los jugadores y DTs del juego
 *  Utiliza un HashSet con enteros que almacenan los ID´s de los jugadores y DT´s
 *  @author 
 */

public class Mercado { /// No implementamos la Interfaz IMenu porque los menúes de Mercado tienen parámetro
	private ContenedorPersonaFutbol<Integer> listadoJugadores;
	private ContenedorPersonaFutbol<Integer> listadoDTs;
	
	public Mercado() {
		listadoJugadores = new ContenedorPersonaFutbol<>(); //TODO cambiar por carga desde archivo
		listadoDTs = new ContenedorPersonaFutbol<>(); //TODO cambiar por carga desde archivo
		//Simulador.getArchivoJugadores().cargarListadoJugadores(listadoJugadores);
		//Simulador.getArchivoDTs().cargarListadoDTs(listadoDTs);
	}
	
	
	/**
	 * Este método es usado desde clases externas (GestionAdministrador) para agregar un jugador al Mercado y evitar el acceso a los atributos privados
	 * 
	 * @param idJugador ID del jugador a agregar
	 */
	public void agregarJugador(Integer idJugador) {
		listadoJugadores.agregar(idJugador);
	}
	
	/**
	 * Este método es usado desde clases externas (GestionAdministrador) para agregar un DT al Mercado y evitar el acceso a los atributos privados
	 * 
	 * @param idDT ID del DT a agregar
	 */
	public void agregarDirectorTecnico(Integer idDT) {
		listadoDTs.agregar(idDT);
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
				if (!listadoJugadores.estaVacio()) { ///TODO cambiar por análisis de alta y baja en vez de isEmpty
					retorno = listadoJugadores.listado(); //TODO lista jugadores válidos
				} else {
					retorno = "No hay jugadores en el Mercado.";
				}
				break;
			case 2:
				if (!listadoDTs.estaVacio()) { ///TODO cambiar por análisis de alta y baja en vez de isEmpty
					retorno = listadoDTs.listado(); //TODO lista DTs válidos
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
		System.out.println("    2. Ver Directores Técnicos disponibles en el Mercado.");
		return ingresarAOpcionVerMercado();
	}
	
	public void listadoOpcionesMercado(ClubUsuario clubRecibido) {
		System.out.println("\n\nBienvenido al Mercado, " + clubRecibido.getNombre());
		if(this.listadoJugadores.estaVacio() && this.listadoDTs.estaVacio()) { ///TODO cambiar por análisis de alta y baja en vez de isEmpty
			System.out.println(clubRecibido.getNombre() + ", lamentamos informarte que el Mercado está vacío. Un Administrador debe cargar datos.");
		} else {
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
				case 1: //Ver Mercado
					System.out.println(verMercado());
					listadoOpcionesMercado(clubRecibido);
					break;
				case 2: //Comprar Jugador
					if (clubRecibido.getPlantillaClub().cantidadJugadores()<11) {
						if (!listadoJugadores.estaVacio()) { ///TODO cambiar por análisis de alta y baja en vez de isEmpty
							///System.out.println(comprarJugador()); ///TODO crear función que agrega a la plantilla, el ID ingresado, si es que es válido en el archivo
						} else {
							System.out.println("No hay jugadores en el Mercado");
						}
						listadoOpcionesMercado(clubRecibido);
					} else {
						System.out.println("Tu Club ya tiene 11 jugadores. Intentá vender uno.");
					}
					break;
				case 3: //Comprar DT
					if (!clubRecibido.getDTClub().getEstado()) {
						if (!listadoDTs.estaVacio()) { ///TODO cambiar por análisis de alta y baja en vez de isEmpty
							///System.out.println(comprarDT()); ///TODO crear función que agrega al Club el DT con el ID ingresado, y si es que es válido en el archivo
						} else {
							System.out.println("No hay DTs en el Mercado.");
						}
					} else {
						System.out.println("Tu club ya cuenta con un DT. Intentá venderlo.");
					}
					listadoOpcionesMercado(clubRecibido);
					break;
				case 4: //Vender Jugador
					if (!clubRecibido.getPlantillaClub().plantillaVacia()) { ///TODO cambiar por análisis de alta y baja en vez de isEmpty
						///System.out.println(venderJugador()); ///TODO crear función que busca en la la plantilla el ID ingresado, lo elimina y agrega al Club las monedas
					} else {
						System.out.println("No hay jugadores en el Club");
					}
					listadoOpcionesMercado(clubRecibido);
					break;
				case 5: //Vender DT
					if (clubRecibido.getDTClub().getEstado()) { ///TODO cambiar por análisis de alta y baja en vez de isEmpty
						///System.out.println(venderJugador()); ///TODO crear función que busca en la la plantilla el ID ingresado, lo elimina y agrega al Club las monedas
					} else {
						System.out.println("No hay jugadores en el Club");
					}
					///System.out.println(venderDT()); ///TODO crear función que elimina el DT, si es que hay uno en el Club, y agrega al Club las monedas
					listadoOpcionesMercado(clubRecibido);
					break;
				default:
					System.out.println("Gracias por usar el Mercado.");
					break;
			}
	}
	
}
