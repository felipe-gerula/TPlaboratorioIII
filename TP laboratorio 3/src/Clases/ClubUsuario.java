package Clases;
import java.io.Serializable;
import java.util.Iterator;

import interfaces.IMenu;

public class ClubUsuario implements IMenu, Serializable{
	private static final long serialVersionUID = 8888764086344279621L;
	private String nombreClub;
	private Plantilla plantillaClub;
	private double fondos;
	private DirectorTecnico dtClub;
	private Estadio estadio; 
	private String camiseta;
	
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

	public DirectorTecnico getDTClub () {
		return dtClub;
	}
	
	public Plantilla getPlantillaClub () {
		return plantillaClub;
	}
	
	public String getNombre () {
		return nombreClub;
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
	
	public double jugarPartido () {
		return (Double)0.00; //TODO programar método
	}
	
	@Override
	public void listadoOpciones() {
		sincronizarClub ();
		System.out.println("\n\nBienvenido al Club " /*+ nombreClub*/);
		System.out.println("  A continuación están las opciones:");
		System.out.println("    1. Acceder al Mercado.");
		System.out.println("    2. Jugar Partido.");
		System.out.println("    3. Modificar información de Club.");
		System.out.println("    4. Listado de información de Club.");
		System.out.println("    5. Ver Plantilla y Director Técnico.");
		System.out.println("    6. Regresar al Menú de Usuario.");
		System.out.println("");
		ingresarAOpcion();
	}
	
	private void sincronizarClub() {
		setFondos(sincronizarJugadores());
		if (dtClub.getID()!=-1) {
			setFondos(sincronizarDT());
		}
		Simulador.guardarArchivoUsuarios();
	}

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
			}
		}
		retorno += sincronizarPosiciones ();
		return retorno;
	}
	
	/**
	 * Método que controla que los jugadores cumplan con la cantidad de posiciones de la plantilla. Puede ser que el Administrador modifique las posiciones de los jugadores, generando errores en las cantidades.
	 * Se trabaja con variables locales para el conteo, ya que si no se generaban errores.
	 * @return cantidad de monedas a sumar según la cantidad de jugadores incorrectos.
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
					System.out.println("  El jugador " + jugAux.getNombre() + " será eliminado de su Club, ya que su nueva posición es " + posicionActual + " y hay otro jugador asignado a la posición. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
					retorno += jugAux.getPrecio();
					plantillaClub.eliminarJugador(jugAux.getID());
				} else {
					cantidadPorteros++;
				}
			} else {
				if (posicionActual.equals("DFC") || posicionActual.equals("LI") || posicionActual.equals("LD")) {
					if (cantidadDefensores == 4 ) {
						System.out.println("  El jugador " + jugAux.getNombre() + " será eliminado de su Club, ya que su nueva posición es " + posicionActual + " y hay otro jugador asignado a la posición. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
						retorno += jugAux.getPrecio();
						plantillaClub.eliminarJugador(jugAux.getID());
					} else {
						cantidadDefensores++;
					}
				} else {
					if (posicionActual.equals("MC") || posicionActual.equals("MI") || posicionActual.equals("MD") || posicionActual.equals("MCO")) {
						if (cantidadMediocampistas == 3 ) {
							System.out.println("  El jugador " + jugAux.getNombre() + " será eliminado de su Club, ya que su nueva posición es " + posicionActual + " y hay otro jugador asignado a la posición. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
							retorno += jugAux.getPrecio();
							plantillaClub.eliminarJugador(jugAux.getID());
						} else {
							cantidadMediocampistas++;
						}
					} else {
						if (cantidadDelanteros == 3 ) {
							System.out.println("  El jugador " + jugAux.getNombre() + " será eliminado de su Club, ya que su nueva posición es " + posicionActual + " y hay otro jugador asignado a la posición. Se le agregaron a tu Club $" + jugAux.getPrecio() + " monedas.");
							retorno += jugAux.getPrecio();
							plantillaClub.eliminarJugador(jugAux.getID());
						} else {
							cantidadDelanteros++;
						}
					}
				}
			}
		}
		return retorno;
	}

	private double sincronizarDT() {
		double retorno = fondos;
		DirectorTecnico aux = Simulador.getMercado().getListadoDTs().buscar(dtClub.getID());
		dtClub.sincronizarDatos(aux);
		if (!aux.getEstado()) {
			System.out.println("  El Director Técnico " + aux.getNombre() + " fue dado de baja por el Administrador. Se le agregaron a tu Club $" + aux.getPrecio() + " monedas.");
			retorno += aux.getPrecio();
			dtClub = new DirectorTecnico(); //Elimina los datos del DT y pone el ID = -1
		}
		return retorno;
	}

	@Override
	public void ingresarAOpcion() {
		int opcion = 0; //TODO sacar inicialización
		System.out.println("  Ingrese el número de opción deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>6) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				Simulador.getMercado().listadoOpcionesMercado(this);
				listadoOpciones();
				break;
			case 2:
				//jugar partido;
				listadoOpciones();
				break;
			case 3:
				//modificar info club;
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
	
	private void verPlantilla() {
		if (this.dtClub.getEstado()) {
			System.out.println("\n\nInformación del Director Técnico: ");
			System.out.println(this.dtClub.toString());
		} else {
			System.out.println("\n\nNo hay un Director Técnico en el Club.");
		}
		System.out.println("\n\nInformación de Plantilla: ");
		System.out.println(plantillaClub.toString());
	}

	@Override
	public void regresar() {
		System.out.println("Regresando al Menú de Usuario.");
	}
	
	@Override
	public String toString() {
		return ("Información del Club " + getNombre() + ": \n  Fondos: $" + getFondos() +".\n  Información del Estadio: " + estadio.toString() + ".\n  Camiseta: " + camiseta + ".");
	}

	public void setDT(DirectorTecnico nuevoDT) {
		dtClub = nuevoDT;
	}
	
}
