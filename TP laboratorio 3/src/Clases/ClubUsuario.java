package Clases;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

import interfaces.IMenu;

/**
 * Clase que permite la creaci�n de Clubes de Usuario, la cual cuenta con los atributos y m�todos necesarios para
 * su gesti�n.
 *
 */

public class ClubUsuario implements IMenu, Serializable{
	private static final long serialVersionUID = 8888764086344279621L;
	private String nombreClub;
	private Plantilla plantillaClub;
	private double fondos;
	private DirectorTecnico dtClub;
	private Estadio estadio; 
	private String camiseta;
	
	
	/**
	 * Constructor para crear un nuevo Club con los par�metros recibidos. El DT y la Plantilla se crean como nuevas
	 * instancias, y los fondos tienen un valor de creaci�n por defecto de $100.000.
	 * 
	 * @param nombreClub Nombre ingresado por el Usuario. Puede ser igual que el de otro club.
	 * @param nuevaCamiseta Nombre de la camiseta ingresado por el Usuario.
	 * @param nuevoEstadio Estadio creado por el Usuario en el men� de creaci�n de Estadio.
	 */
	public ClubUsuario(String nombreClub, String nuevaCamiseta, Estadio nuevoEstadio) {
		this.nombreClub = nombreClub;
		camiseta = nuevaCamiseta;
		estadio = nuevoEstadio;
		fondos = 100000;
		plantillaClub = new Plantilla();
		dtClub = new DirectorTecnico();
	}
	
	public double getFondos() {
		return fondos;
	}
	
	public void setFondos(double fondos) {
		this.fondos = fondos;
	}

	public Estadio getEstadio() {
		return estadio;
	}
	
	public String getCamiseta() {
		return camiseta;
	}

	private void setCamiseta(String nuevaCamiseta) {
		camiseta = nuevaCamiseta;
	}

	public DirectorTecnico getDTClub () {
		return dtClub;
	}
	
	public Plantilla getPlantillaClub () {
		return plantillaClub;
	}
	
	public String getNombre () {
		return nombreClub;
	}

	private void setNombre(String nuevoNombre) {
		this.nombreClub = nuevoNombre;
	}

	public void agregarJugadorPlantilla (int nuevoID) {
		this.plantillaClub.agregarJugador(nuevoID);
	}

	public void eliminarJugadorPlantilla (int idAEliminar) {
		this.plantillaClub.eliminarJugador(idAEliminar);
	}
	
	public boolean jugadorExistentePlantilla (int idBuscado) {
		return plantillaClub.jugadorEncontrado(idBuscado);
	}
	
	/**
	 * M�todo que crea una instancia de la Clase Partido y llama al listado de opciones.
	 * Dentro del mismo se modifican las monedas del Club en caso de poder jugarse un partido.
	 */
	public void jugarPartido () {
		Partido partido = new Partido();
		partido.listadoOpcionesJugarPartido(this);
	}
	
	/**
	 * Listado de opciones b�sicas dentro del Club.
	 */
	@Override
	public void listadoOpciones() {
		sincronizarClub ();
		System.out.println("\n\nBienvenido al Club " + nombreClub);
		System.out.println("  A continuaci�n est�n las opciones:");
		System.out.println("    1. Acceder al Mercado.");
		System.out.println("    2. Jugar Partido.");
		System.out.println("    3. Modificar informaci�n de Club.");
		System.out.println("    4. Listado de informaci�n de Club.");
		System.out.println("    5. Ver Plantilla y Director T�cnico.");
		System.out.println("    6. Regresar al Men� de Usuario.");
		System.out.println("");
		ingresarAOpcion();
	}
	
	/**
	 * M�todo que corrobora que la informaci�n del Club sea la correcta con respecto a la que aparece en
	 * el Mercado. Adem�s, controla que la plantilla siga siendo correcta. Es decir, si hay una cantidad
	 * de jugadores en una posici�n que no sea correcta, el jugador ser� eliminado del Club.
	 */
	private void sincronizarClub() {
		setFondos(sincronizarJugadores());
		if (dtClub.getID()!=-1) {
			setFondos(sincronizarDT());
		}
		Simulador.guardarArchivoUsuarios();
	}

	
	/**
	 * Controla que los jugadores de la plantilla no est�n dados de baja por el Administrador.
	 * Controla que las posiciones est�n correctamente ocupadas.
	 * De encontrar alg�n jugador que no lo cumpla, se devuelve su precio junto al retorno.
	 * 
	 * @return total de monedas correspondientes a la suma de los precios de los jugadores err�neos.
	 */
	private double sincronizarJugadores() {
		double retorno = fondos;
		Iterator<Integer> it = plantillaClub.getIterator();
		Jugador jugAux;
		while (it.hasNext()) {
			jugAux = Simulador.getMercado().getListadoJugadores().buscar(it.next());
			if (!jugAux.getEstado()) {
				System.out.println("  El jugador " + jugAux.getNombre() + " fue dado de baja por el Administrador. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
				retorno += jugAux.getPrecio();
				plantillaClub.eliminarJugador(jugAux.getID());
				it = plantillaClub.getIterator(); //Si no lo reseteamos lanza ConcurrentModificationException. Reiniciamos el iterator sabiendo que la cantidad de elementos es poca (como mucho 11)
			}
		}
		plantillaClub.sincronizarCantidadPosiciones();
		double respuestaSincroPosiciones = sincronizarPosiciones();
		while (respuestaSincroPosiciones > 0) {
			retorno += respuestaSincroPosiciones;
			respuestaSincroPosiciones = sincronizarPosiciones();
		}
		return retorno;
	}
	
	/**
	 * M�todo que controla que los jugadores cumplan con la cantidad de posiciones de la plantilla. Puede ser que el Administrador modifique las posiciones de los jugadores, generando errores en las cantidades.
	 * Se trabaja con variables locales para el conteo, ya que si no se generaban errores.
	 * @return cantidad de monedas a sumar seg�n la cantidad de jugadores incorrectos.
	 */
	
	private double sincronizarPosiciones() {
		Iterator<Integer> it = plantillaClub.getIterator();
		Jugador jugAux;
		double retorno = 0;
		int cantidadPorteros = 0;
		int cantidadDefensores = 0;
		int cantidadMediocampistas = 0;
		int cantidadDelanteros = 0;
		while (it.hasNext()) {
			jugAux = Simulador.getMercado().getListadoJugadores().buscar(it.next());
			String posicionActual = jugAux.getPosicion();
			if (posicionActual.equals("PO")) {
				if (cantidadPorteros == 1 ) {
					System.out.println("  El jugador " + jugAux.getNombre() + " ser� eliminado de su Club, ya que su nueva posici�n es " + posicionActual + " y hay otro jugador asignado a la posici�n. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
					retorno += jugAux.getPrecio();
					plantillaClub.eliminarJugador(jugAux.getID());
					return retorno;
				} else {
					cantidadPorteros++;
				}
			} else {
				if (posicionActual.equals("DFC") || posicionActual.equals("LI") || posicionActual.equals("LD")) {
					if (cantidadDefensores == 4 ) {
						System.out.println("  El jugador " + jugAux.getNombre() + " ser� eliminado de su Club, ya que su nueva posici�n es " + posicionActual + " y hay otro jugador asignado a la posici�n. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
						retorno += jugAux.getPrecio();
						plantillaClub.eliminarJugador(jugAux.getID());
						return retorno;
					} else {
						cantidadDefensores++;
					}
				} else {
					if (posicionActual.equals("MC") || posicionActual.equals("MI") || posicionActual.equals("MD") || posicionActual.equals("MCO")) {
						if (cantidadMediocampistas == 3 ) {
							System.out.println("  El jugador " + jugAux.getNombre() + " ser� eliminado de su Club, ya que su nueva posici�n es " + posicionActual + " y hay otro jugador asignado a la posici�n. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
							retorno += jugAux.getPrecio();
							plantillaClub.eliminarJugador(jugAux.getID());
							return retorno;
						} else {
							cantidadMediocampistas++;
						}
					} else {
						if (cantidadDelanteros == 3 ) {
							System.out.println("  El jugador " + jugAux.getNombre() + " ser� eliminado de su Club, ya que su nueva posici�n es " + posicionActual + " y hay otro jugador asignado a la posici�n. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
							retorno += jugAux.getPrecio();
							plantillaClub.eliminarJugador(jugAux.getID());
							return retorno;
						} else {
							cantidadDelanteros++;
						}
					}
				}
			}
		}
		return retorno;
	}

	
	/**
	 * M�todo que sincroniza los datos del DT del Club y controla que no haya sido dado de baja.
	 * De cumplirse lo anterior, se suma el valor del DT al retorno.
	 * 
	 * @return fondos del club + precio del DT (si fue eliminado)
	 */
	private double sincronizarDT() {
		double retorno = fondos;
		DirectorTecnico aux = Simulador.getMercado().getListadoDTs().buscar(dtClub.getID());
		dtClub.sincronizarDatos(aux);
		if (!aux.getEstado()) {
			System.out.println("  El Director T�cnico " + aux.getNombre() + " fue dado de baja por el Administrador. Se le agregaron a tu Club $" + aux.getPrecio() + " monedas.");
			retorno += aux.getPrecio();
			dtClub = new DirectorTecnico(); //Elimina los datos del DT y pone el ID = -1
		}
		return retorno;
	}

	
	/**
	 * M�todo que permite el acceso a las opciones principales del Club
	 */
	@Override
	public void ingresarAOpcion() {
		int opcion = Simulador.ingresoOpcion(1, 6);
		switch (opcion) {
			case 1:
				Simulador.getMercado().listadoOpcionesMercado(this);
				listadoOpciones();
				break;
			case 2:
				jugarPartido();
				listadoOpciones();
				break;
			case 3:
				listadoOpcionesModificacionClub();
				listadoOpciones();
				break;
			case 4:
				System.out.println(this.toString());
				listadoOpciones();
				break;
			case 5:
				verPlantilla();
				listadoOpciones();
				break;
			default:
				regresar();
				break;
		}
		
	}
	
	/**
	 * Men� de opciones de modificaci�n del Club
	 */
	private void listadoOpcionesModificacionClub () {
		System.out.println("  A continuaci�n est�n las opciones de modificaci�n del Club:");
		System.out.println("    1. Modificar Nombre del Club.");
		System.out.println("    2. Modificar Estadio.");
		System.out.println("    3. Modificar Camiseta.");
		System.out.println("    4. Modificar Vestimenta del DT.");
		System.out.println("    5. Regresar al Men� del Club.");
		System.out.println("");
		ingresarAOpcionModificacionClub();
	}
	
	/**
	 * Ingreso a opciones de modificaci�n del Club
	 */
	private void ingresarAOpcionModificacionClub() {
		Scanner scanner = Simulador.getScanner(); //Lo pasamos a una variable local porque tiraba error de leaking resource
		int opcion = Simulador.ingresoOpcion(1, 5);
		switch (opcion) {
			case 1: //nombre del club
				System.out.println("  Ingrese el nuevo Nombre del Club: ");
				scanner.nextLine();
				setNombre(Simulador.getScanner().nextLine());
				Simulador.guardarArchivoUsuarios();
				listadoOpcionesModificacionClub();
				break;
			case 2: //estadio
				estadio.listadoOpcionesModificacionEstadio();
				listadoOpcionesModificacionClub();
				break;
			case 3: //camiseta
				System.out.println("  Ingrese la nueva Camiseta del Club: ");
				scanner.nextLine();
				setCamiseta(Simulador.getScanner().nextLine());
				Simulador.guardarArchivoUsuarios();
				listadoOpcionesModificacionClub();
				break;
			case 4: //vestimenta DT
				dtClub.cambiarVestimenta();
				Simulador.guardarArchivoUsuarios();
				listadoOpcionesModificacionClub();
				break;
			default:
				System.out.println("Regresando al Men� del Club.");
		}
	}

	/**
	 * M�todo que muestra los datos del DT (de haber uno), y la plantilla de jugadores del Club.
	 */
	private void verPlantilla() {
		if (this.dtClub.getEstado()) {
			System.out.println("\n\nInformaci�n del Director T�cnico: ");
			System.out.println(this.dtClub.toString());
		} else {
			System.out.println("\n\nNo hay un Director T�cnico en el Club.");
		}
		System.out.println("\n\nInformaci�n de Plantilla: ");
		System.out.println(plantillaClub.listadoJugadores(false));
	}

	/**
	 * Regreso al men� de usuario.
	 */
	@Override
	public void regresar() {
		System.out.println("Regresando al Men� de Usuario.");
	}
	
	/** 
	 * Muestra de datos del Club.
	 */
	@Override
	public String toString() {
		return ("Informaci�n del Club " + getNombre() + ": \n  Fondos: $" + getFondos() +".\n" + estadio.toString() + ".\n  Camiseta: " + camiseta + ".");
	}

	public void setDT(DirectorTecnico nuevoDT) {
		dtClub = nuevoDT;
	}
	
}
