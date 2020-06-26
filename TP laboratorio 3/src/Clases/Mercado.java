package Clases;

import java.util.Iterator;

import org.json.JSONException;

import excepciones.FondosInsuficientesException;
import jsonPackage.JSONLiga;

/** 
 *  Esta clase nos permite crear objetos del tipo Equipo, la cual cuenta con los atributos y m�todos 
 * necesarios para su gesti�n. Es un contenedor con todos los jugadores y DTs del juego.
 *  Utiliza un HashSet con enteros que almacenan los IDs de los jugadores y de los DTs
 */

public class Mercado { /// No implementamos la Interfaz IMenu porque los men�es de Mercado tienen par�metros
	private ContenedorPersonaFutbol<Jugador> listadoJugadores;
	private ContenedorPersonaFutbol<DirectorTecnico> listadoDTs;
	
	/**
	 * Constructor vac�o que automatiza la carga de datos al mercado desde los archivos
	 */
	public Mercado() {
		listadoJugadores = new ContenedorPersonaFutbol<>();
		listadoDTs = new ContenedorPersonaFutbol<>();
		Simulador.getArchivoJugadores().cargarListadoJugadores(listadoJugadores);
		Simulador.getArchivoDTs().cargarListadoDTs(listadoDTs);
	}
	
	/**
	 * M�todo usado para acceder al listado de jugadores para hacer consultas o modificaciones
	 * @return listado de jugadores
	 */
	public ContenedorPersonaFutbol<Jugador> getListadoJugadores(){
		return listadoJugadores;
	}
	
	/**
	 * M�todo usado para acceder al listado de DTs para hacer consultas o modificaciones
	 * @return listado de DTs
	 */
	public ContenedorPersonaFutbol<DirectorTecnico> getListadoDTs(){
		return listadoDTs;
	}
	
	/**
	 * M�todo usado desde clases externas (GestionAdministrador) para agregar un jugador al Mercado y 
	 * evitar el acceso a los atributos privados
	 * 
	 * @param idJugador ID del jugador a agregar
	 */
	public void agregarJugador(Jugador nuevoJugador) {
		listadoJugadores.agregar(nuevoJugador);
	}
	
	/**
	 * M�todo usado desde clases externas (GestionAdministrador) para agregar un DT al Mercado y 
	 * evitar el acceso a los atributos privados
	 * 
	 * @param idDT ID del DT a agregar
	 */
	public void agregarDirectorTecnico(DirectorTecnico nuevoDT) {
		listadoDTs.agregar(nuevoDT);
	}
	
	/**
	 * M�todo que carga el listado de ligas y equipos con los IDs de jugadores y DTs
	 * @param listadoRecibido listado de ligas y equipos a ser cargado de datos
	 */
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
	
	/**
	 * M�todo de acceso a las opciones de mvisualizaci�n de mercado
	 * @param ingresoAdmin determina si el mercado ser� visto por un admin o no
	 * @return listado de informaci�n o mensaje de error
	 */
	public String ingresarAOpcionVerMercado(boolean ingresoAdmin) {
		String retorno = "";
		int opcion = Simulador.ingresoOpcion(1, 4);
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
			case 4:
				JSONLiga retornoJSON = new JSONLiga();
				try {
					System.out.println("\nSitio para visualizaci�n de JSON recomendado: http://jsonviewer.stack.hu/\n");
					System.out.println(retornoJSON.toJSONLiga(Simulador.getListadoLigasEquipos()));
				} catch (JSONException e) {
					System.out.println("Error JSON");
				}
				break;
		}
		return retorno;
	}
	
	/**
	 * M�todo com�n para Usuarios y Administradores.
	 * @param ingresoAdmin true si se ingresa desde el Administrador: muestra los que est�n dados de baja 
	 * y el estado de todos.
	 */
	public String verMercado(boolean ingresoAdmin) {
		System.out.println("  Opciones de listado de Mercado:");
		System.out.println("    1. Ver Jugadores disponibles en el Mercado.");
		System.out.println("    2. Ver Directores T�cnicos disponibles en el Mercado.");
		System.out.println("    3. Ver Director T�cnico y Jugadores de un Equipo (ordenados por posici�n).");
		System.out.println("    4. Ver datos de Ligas, Equipos, DTs y Jugadores en formato JSON.");
		return ingresarAOpcionVerMercado(ingresoAdmin);
	}
	
	/**
	 * Listado de opciones de acceso al mercado para el usuario
	 * @param clubRecibido club del usuario que est� accediendo al mercado para hacer operaciones
	 */
	public void listadoOpcionesMercado(ClubUsuario clubRecibido) {
		System.out.println("\n\nBienvenido al Mercado, " + clubRecibido.getNombre());
		if(this.listadoJugadores.estaVacio(false) && this.listadoDTs.estaVacio(false)) {
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
	
	/**
	 * Acceso a opciones de mercado para el usuario
	 * @param clubRecibido club del usuario que est� accediendo al mercado para hacer operaciones
	 */
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
					System.out.println("Tu Club ya tiene 11 jugadores. Intent� vender uno.");
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

	/**
	 * M�todo que, de haber un DT en el club del usuario, lo vende autom�ticamente
	 * @param clubRecibido club del usuario que est� accediendo al mercado para hacer operaciones
	 */
	private void venderDT(ClubUsuario clubRecibido) {
		clubRecibido.setFondos(clubRecibido.getFondos() + clubRecibido.getDTClub().getPrecio());
		System.out.println(clubRecibido.getDTClub().getNombre() + " vendido con �xito. Fondos restantes: $" + clubRecibido.getFondos() + "."); 
		clubRecibido.setDT(new DirectorTecnico());
		Simulador.guardarArchivoUsuarios();
	}

	/**
	 * M�todo que, de no haber un DT, pide un ID del deseado y si hay fondos lo compra
	 * @param clubRecibido club del usuario que est� accediendo al mercado para hacer operaciones
	 */
	private void comprarDT(ClubUsuario clubRecibido) {
		System.out.println("  Ingreso de ID del Director T�cnico a comprar: ");
		int idBuscado = Simulador.ingresoOpcion(0, listadoDTs.cantidad()-1);
		DirectorTecnico dtBuscado = listadoDTs.buscar(idBuscado);
		if (dtBuscado.getEstado()) {
			if (dtBuscado.getPrecio() < clubRecibido.getFondos()) {
				clubRecibido.setDT(new DirectorTecnico(dtBuscado)); //Le pasamos una copia para que no se modifique la vestimenta del DT del mercado
				clubRecibido.setFondos(clubRecibido.getFondos() - dtBuscado.getPrecio());
				Simulador.guardarArchivoUsuarios();
				System.out.println(dtBuscado.getNombre() + " comprado con �xito. Fondos restantes: $" + clubRecibido.getFondos() + ".");
			} else {
				System.out.println("Fondos insuficientes. Faltan $" + (dtBuscado.getPrecio() - clubRecibido.getFondos()) + ".");
			}
		} else {
			System.out.println("El Director T�cnico seleccionado no puede utilizarse.");
		}
	}

	/**
	 * M�todo que compara el precio del jugador a comprar con los fondos del club.
	 * De ser los fondos insuficientes, lanza una excepci�n del tipo FondosInsuficientesException
	 * @param precioJugador precio del jugador a comprar
	 * @param fondosClub fondos del club del usuario
	 * @throws FondosInsuficientesException excepci�n lanzada si el jugador no puede ser comprado por falta de fondos
	 */
	private void comprobarFondos (double precioJugador, double fondosClub) throws FondosInsuficientesException{
		if (precioJugador > fondosClub) {
			throw new FondosInsuficientesException("Fondos insuficientes.", (precioJugador-fondosClub));
		}
	}
	
	/**
	 * M�todo que pide el ID del jugador a comprar y, de haber espacio en la posici�n y fondos
	 * suficientes en el club para comprarlo, lo agrega al club del usuario
	 * @param clubRecibido club del usuario que est� accediendo al mercado para hacer operaciones
	 */
	private void comprarJugador(ClubUsuario clubRecibido) {
		System.out.println("  Ingreso de ID del Jugador a comprar: ");
		int idBuscado = Simulador.ingresoOpcion(0, listadoJugadores.cantidad()-1);
		Jugador jugadorBuscado = listadoJugadores.buscar(idBuscado);
		if (jugadorBuscado.getEstado()) {
			if (!clubRecibido.jugadorExistentePlantilla(idBuscado)) {
				if (clubRecibido.getPlantillaClub().hayEspacioEnPosicion(jugadorBuscado.getPosicion())) {
					try {
						comprobarFondos(jugadorBuscado.getPrecio(), clubRecibido.getFondos());
						clubRecibido.agregarJugadorPlantilla(idBuscado);
						clubRecibido.setFondos(clubRecibido.getFondos() - jugadorBuscado.getPrecio());
						Simulador.guardarArchivoUsuarios();
						System.out.println(jugadorBuscado.getNombre() + " comprado con �xito. Fondos restantes: $" + clubRecibido.getFondos() + ".");
					}
					catch (FondosInsuficientesException e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				System.out.println("El jugador seleccionado ya forma parte de su plantilla.");
			}
		} else {
			System.out.println("El jugador seleccionado no puede utilizarse.");
		}
		
	}
	
	/**
	 * M�todo que pide el ID del jugador a vender y, de estar en el club, lo elimina y agrega las
	 * monedas de su precio a los fondos del club
	 * @param clubRecibido club del usuario que est� accediendo al mercado para hacer operaciones
	 */
	private void venderJugador(ClubUsuario clubRecibido) {
		System.out.println("  Ingreso de ID del Jugador a vender: ");
		int idBuscado = Simulador.ingresoOpcion(0, listadoJugadores.cantidad()-1);
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
