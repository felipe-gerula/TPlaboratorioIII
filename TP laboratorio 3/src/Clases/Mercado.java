package Clases;

import java.util.Iterator;

/** 
 *  Esta clase nos permite crear objetos de tipo Mercado
 *  Es un contenedor con todos los jugadores y DTs del juego
 *  Utiliza un HashSet con enteros que almacenan los ID�s de los jugadores y DT�s
 *  @author 
 */

public class Mercado { /// No implementamos la Interfaz IMenu porque los men�es de Mercado tienen par�metro
	private ContenedorPersonaFutbol<Jugador> listadoJugadores;
	private ContenedorPersonaFutbol<DirectorTecnico> listadoDTs;
	
	public Mercado() {
		listadoJugadores = new ContenedorPersonaFutbol<>();
		listadoDTs = new ContenedorPersonaFutbol<>();
		Simulador.getArchivoJugadores().cargarListadoJugadores(listadoJugadores);
		Simulador.getArchivoDTs().cargarListadoDTs(listadoDTs);
	}
	
	public ContenedorPersonaFutbol<Jugador> getListadoJugadores(){
		return listadoJugadores;
	}
	
	public ContenedorPersonaFutbol<DirectorTecnico> getListadoDTs(){
		return listadoDTs;
	}
	
	/**
	 * Este m�todo es usado desde clases externas (GestionAdministrador) para agregar un jugador al Mercado y evitar el acceso a los atributos privados
	 * 
	 * @param idJugador ID del jugador a agregar
	 */
	public void agregarJugador(Jugador nuevoJugador) {
		listadoJugadores.agregar(nuevoJugador);
	}
	
	/**
	 * Este m�todo es usado desde clases externas (GestionAdministrador) para agregar un DT al Mercado y evitar el acceso a los atributos privados
	 * 
	 * @param idDT ID del DT a agregar
	 */
	public void agregarDirectorTecnico(DirectorTecnico nuevoDT) {
		listadoDTs.agregar(nuevoDT);
	}
	
	public void cargarLigasEquipos(ContenedorLigasEquipos listadoRecibido) {
		Iterator<Jugador> itJugadores = listadoJugadores.getIterator();
		while (itJugadores.hasNext()) {
			listadoRecibido.agregarJugador(itJugadores.next());
		}
		Iterator<DirectorTecnico> itDTs = listadoDTs.getIterator();
		while (itDTs.hasNext()) {
			listadoRecibido.agregarDT(itDTs.next());
		}
		System.out.println(listadoRecibido.toString());
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
				if (!listadoJugadores.estaVacio()) {
					retorno = listadoJugadores.opcionesListado();
				} else {
					retorno = "No hay jugadores en el Mercado.";
				}
				break;
			case 2:
				if (!listadoDTs.estaVacio()) {
					retorno = listadoDTs.opcionesListado();
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
		if(this.listadoJugadores.estaVacio() && this.listadoDTs.estaVacio()) {
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
	
	public void ingresarAOpcionMercado(ClubUsuario clubRecibido) {
			System.out.println("  Ingrese el n�mero de opci�n deseada: ");
			int opcion = Simulador.getScanner().nextInt();
			while (opcion<1 || opcion>6) {
				System.out.println("  Por favor ingrese una opci�n correcta: ");
				opcion = Simulador.getScanner().nextInt();
			}
			switch (opcion) {
				case 1: //Ver Mercado
					System.out.println(verMercado());
					listadoOpcionesMercado(clubRecibido);
					break;
				case 2: //Comprar Jugador
					if (clubRecibido.getPlantillaClub().cantidadJugadores()<11) {
						if (!listadoJugadores.estaVacio()) {
							comprarJugador(clubRecibido);
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
						if (!listadoDTs.estaVacio()) {
							comprarDT(clubRecibido);
						} else {
							System.out.println("No hay DTs en el Mercado.");
						}
					} else {
						System.out.println("Tu club ya cuenta con un DT. Intent� venderlo.");
					}
					listadoOpcionesMercado(clubRecibido);
					break;
				case 4: //Vender Jugador
					if (!clubRecibido.getPlantillaClub().plantillaVacia()) {
						venderJugador(clubRecibido);
					} else {
						System.out.println("No hay jugadores en el Club");
					}
					listadoOpcionesMercado(clubRecibido);
					break;
				case 5: //Vender DT
					if (clubRecibido.getDTClub().getEstado()) {
						venderDT(clubRecibido);
					} else {
						System.out.println("No hay un DT en el Club");
					}
					listadoOpcionesMercado(clubRecibido);
					break;
				default:
					System.out.println("Gracias por usar el Mercado.");
					break;
			}
	}

	private void venderDT(ClubUsuario clubRecibido) {
		clubRecibido.setFondos(clubRecibido.getFondos() + clubRecibido.getDTClub().getPrecio());
		System.out.println(clubRecibido.getDTClub().getNombre() + " vendido con �xito. Fondos restantes: $" + clubRecibido.getFondos() + "."); 
		clubRecibido.setDT(new DirectorTecnico());
		Simulador.guardarArchivoUsuarios();
	}

	private void comprarDT(ClubUsuario clubRecibido) {
		System.out.println("  Ingrese el ID del DT que desea comprar: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(listadoDTs.cantidad()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (listadoDTs.cantidad()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		DirectorTecnico dtBuscado = listadoDTs.buscar(idBuscado);
		if (dtBuscado.getEstado()) {
			if (dtBuscado.getPrecio() < clubRecibido.getFondos()) {
				clubRecibido.setDT(dtBuscado);
				clubRecibido.setFondos(clubRecibido.getFondos() - dtBuscado.getPrecio());
				Simulador.guardarArchivoUsuarios();
				System.out.println(dtBuscado.getNombre() + " comprado con �xito. Fondos restantes: $" + clubRecibido.getFondos() + ".");
			} else {
				System.out.println("Fondos insuficientes. Faltan $" + (dtBuscado.getPrecio() - clubRecibido.getFondos()) + ".");
			}
		} else {
			System.out.println("El DT seleccionado no puede utilizarse.");
		}
	}

	private void comprarJugador(ClubUsuario clubRecibido) {
		System.out.println("  Ingrese el ID del Jugador que desea comprar: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(listadoJugadores.cantidad()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (listadoJugadores.cantidad()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		Jugador jugadorBuscado = listadoJugadores.buscar(idBuscado);
		if (jugadorBuscado.getEstado()) {
			if (!clubRecibido.jugadorExistentePlantilla(idBuscado)) {
				if (jugadorBuscado.getPrecio() < clubRecibido.getFondos()) {
					clubRecibido.agregarJugadorPlantilla(idBuscado);
					clubRecibido.setFondos(clubRecibido.getFondos() - jugadorBuscado.getPrecio());
					Simulador.guardarArchivoUsuarios();
					System.out.println(jugadorBuscado.getNombre() + " comprado con �xito. Fondos restantes: $" + clubRecibido.getFondos() + ".");
				} else {
					System.out.println("Fondos insuficientes. Faltan $" + (jugadorBuscado.getPrecio() - clubRecibido.getFondos()) + ".");
				}
			} else {
				System.out.println("El jugador seleccionado ya forma parte de su plantilla.");
			}
		} else {
			System.out.println("El jugador seleccionado no puede utilizarse.");
		}
		
	}
	
	private void venderJugador(ClubUsuario clubRecibido) {
		System.out.println("  Ingrese el ID del Jugador perteneciente a su Plantilla que desea vender: ");
		int idBuscado = Simulador.getScanner().nextInt();
		while (idBuscado<0 || idBuscado>(listadoJugadores.cantidad()-1)) {
			System.out.println("  Por favor ingrese un ID correcto (entre 0 y " + (listadoJugadores.cantidad()-1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
		Jugador jugadorBuscado = listadoJugadores.buscar(idBuscado);
		if (jugadorBuscado.getEstado()) {
			if (clubRecibido.jugadorExistentePlantilla(idBuscado)) {
				clubRecibido.eliminarJugadorPlantilla(idBuscado);
				clubRecibido.setFondos(clubRecibido.getFondos() + jugadorBuscado.getPrecio());
				Simulador.guardarArchivoUsuarios();
				System.out.println(jugadorBuscado.getNombre() + " vendido con �xito. Fondos restantes: $" + clubRecibido.getFondos() + "."); 
			} else {
				System.out.println("El jugador seleccionado no forma parte de su plantilla.");
			}
		} else {
			System.out.println("El jugador seleccionado no puede utilizarse.");
		}
		
	}
	
}
