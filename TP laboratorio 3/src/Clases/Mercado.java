package Clases;

import java.util.Iterator;

/** 
 *  Esta clase nos permite crear objetos de tipo Mercado
 *  Es un contenedor con todos los jugadores y DTs del juego
 *  Utiliza un HashSet con enteros que almacenan los ID´s de los jugadores y DT´s
 *  @author 
 */

public class Mercado { /// No implementamos la Interfaz IMenu porque los menúes de Mercado tienen parámetro
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
	 * Este método es usado desde clases externas (GestionAdministrador) para agregar un jugador al Mercado y evitar el acceso a los atributos privados
	 * 
	 * @param idJugador ID del jugador a agregar
	 */
	public void agregarJugador(Jugador nuevoJugador) {
		listadoJugadores.agregar(nuevoJugador);
	}
	
	/**
	 * Este método es usado desde clases externas (GestionAdministrador) para agregar un DT al Mercado y evitar el acceso a los atributos privados
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
	}
	
	public String ingresarAOpcionVerMercado(boolean ingresoAdmin) {
		String retorno = "";
		System.out.println("  Ingrese el número de opción deseada: ");
		int opcion = Simulador.ingresoOpcion(1, 3);
		switch (opcion) {
			case 1:
				if (!listadoJugadores.estaVacio(ingresoAdmin)) {
					retorno = listadoJugadores.opcionesListadoJugador(ingresoAdmin);
				} else {
					retorno = "No hay jugadores en el Mercado.";
				}
				break;
			case 2:
				if (!listadoDTs.estaVacio(ingresoAdmin)) {
					retorno = listadoDTs.opcionesListadoDT(ingresoAdmin);
				} else {
					retorno = "No hay DTs en el Mercado.";
				}
				break;
			case 3:
				Equipo aux = Simulador.getListadoLigasEquipos().listarLigasEquipos(ingresoAdmin);
				System.out.println(aux.toString(ingresoAdmin));
				break;
		}
		return retorno;
	}
	
	
	/**
	 * Método común para Usuarios y Administradores.
	 * @param ingresoAdmin true si se ingresa desde el Administrador: muestra los que están dados de baja y el estado de todos.
	 *
	 */
	public String verMercado(boolean ingresoAdmin) {
		System.out.println("  Opciones de listado de Mercado:");
		System.out.println("    1. Ver Jugadores disponibles en el Mercado.");
		System.out.println("    2. Ver Directores Técnicos disponibles en el Mercado.");
		System.out.println("    3. Ver Director Técnico y Jugadores de un Equipo (ordenados por posición).");
		return ingresarAOpcionVerMercado(ingresoAdmin);
	}
	
	public void listadoOpcionesMercado(ClubUsuario clubRecibido) {
		System.out.println("\n\nBienvenido al Mercado, " + clubRecibido.getNombre());
		if(this.listadoJugadores.estaVacio(false) && this.listadoDTs.estaVacio(false)) {
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
	
	public void ingresarAOpcionMercado(ClubUsuario clubRecibido) {
		int opcion = Simulador.ingresoOpcion(1, 6);
		switch (opcion) {
			case 1: //Ver Mercado
				System.out.println(verMercado(false));
				listadoOpcionesMercado(clubRecibido);
				break;
			case 2: //Comprar Jugador
				if (clubRecibido.getPlantillaClub().cantidadJugadores(false)<11) {
					if (!listadoJugadores.estaVacio(false)) {
						comprarJugador(clubRecibido);
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
					if (!listadoDTs.estaVacio(false)) {
						comprarDT(clubRecibido);
					} else {
						System.out.println("No hay DTs en el Mercado.");
					}
				} else {
					System.out.println("Tu club ya cuenta con un DT. Intentá venderlo.");
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
		System.out.println(clubRecibido.getDTClub().getNombre() + " vendido con éxito. Fondos restantes: $" + clubRecibido.getFondos() + "."); 
		clubRecibido.setDT(new DirectorTecnico());
		Simulador.guardarArchivoUsuarios();
	}

	private void comprarDT(ClubUsuario clubRecibido) {
		System.out.println("  Ingreso de ID del Director Técnico a comprar: ");
		int idBuscado = Simulador.ingresoOpcion(0, listadoDTs.cantidad()-1);
		DirectorTecnico dtBuscado = listadoDTs.buscar(idBuscado);
		if (dtBuscado.getEstado()) {
			if (dtBuscado.getPrecio() < clubRecibido.getFondos()) {
				clubRecibido.setDT(new DirectorTecnico(dtBuscado)); //Le pasamos una copia para que no se modifique la vestimenta del DT del mercado
				clubRecibido.setFondos(clubRecibido.getFondos() - dtBuscado.getPrecio());
				Simulador.guardarArchivoUsuarios();
				System.out.println(dtBuscado.getNombre() + " comprado con éxito. Fondos restantes: $" + clubRecibido.getFondos() + ".");
			} else {
				System.out.println("Fondos insuficientes. Faltan $" + (dtBuscado.getPrecio() - clubRecibido.getFondos()) + ".");
			}
		} else {
			System.out.println("El Director Técnico seleccionado no puede utilizarse.");
		}
	}

	private void comprarJugador(ClubUsuario clubRecibido) {
		System.out.println("  Ingreso de ID del Jugador a comprar: ");
		int idBuscado = Simulador.ingresoOpcion(0, listadoJugadores.cantidad()-1);
		Jugador jugadorBuscado = listadoJugadores.buscar(idBuscado);
		if (jugadorBuscado.getEstado()) {
			if (!clubRecibido.jugadorExistentePlantilla(idBuscado)) {
				if (clubRecibido.getPlantillaClub().hayEspacioEnPosicion(jugadorBuscado.getPosicion())) {
					if (jugadorBuscado.getPrecio() < clubRecibido.getFondos()) {
						clubRecibido.agregarJugadorPlantilla(idBuscado);
						clubRecibido.setFondos(clubRecibido.getFondos() - jugadorBuscado.getPrecio());
						Simulador.guardarArchivoUsuarios();
						System.out.println(jugadorBuscado.getNombre() + " comprado con éxito. Fondos restantes: $" + clubRecibido.getFondos() + ".");
					} else {
						System.out.println("Fondos insuficientes. Faltan $" + (jugadorBuscado.getPrecio() - clubRecibido.getFondos()) + ".");
					}
				} else {
					System.out.println("  No hay más espacios para la posición de " + jugadorBuscado.getPosicion() + " en tu club.");
				}
			} else {
				System.out.println("El jugador seleccionado ya forma parte de su plantilla.");
			}
		} else {
			System.out.println("El jugador seleccionado no puede utilizarse.");
		}
		
	}
	
	private void venderJugador(ClubUsuario clubRecibido) {
		System.out.println("  Ingreso de ID del Jugador a vender: ");
		int idBuscado = Simulador.ingresoOpcion(0, listadoJugadores.cantidad()-1);
		Jugador jugadorBuscado = listadoJugadores.buscar(idBuscado);
		if (jugadorBuscado.getEstado()) {
			if (clubRecibido.jugadorExistentePlantilla(idBuscado)) {
				clubRecibido.eliminarJugadorPlantilla(idBuscado);
				clubRecibido.setFondos(clubRecibido.getFondos() + jugadorBuscado.getPrecio());
				Simulador.guardarArchivoUsuarios();
				System.out.println(jugadorBuscado.getNombre() + " vendido con éxito. Fondos restantes: $" + clubRecibido.getFondos() + "."); 
			} else {
				System.out.println("El jugador seleccionado no forma parte de su plantilla.");
			}
		} else {
			System.out.println("El jugador seleccionado no puede utilizarse.");
		}
		
	}
	
}
