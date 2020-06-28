package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Clase que permite la creaci�n de Contenedores de Ligas y Equipos, la cual cuenta con los atributos y m�todos 
 * necesarios para su gesti�n.
 *
 */
public class ContenedorLigasEquipos {
	private HashMap<String, HashMap<String, Equipo>> hashMapLigasEquipos;
	
	/**
	 * Contructor vac�o
	 */
	public ContenedorLigasEquipos() {
		hashMapLigasEquipos = new HashMap<>();
	}
	
	/**
	 * M�todo que devuelve un equipo particular de una liga particular.
	 * @param ligaEquipo nombre de la liga del equipo buscado
	 * @param nombreEquipo nombre del equipo buscado
	 * @return equipo buscado
	 */
	public Equipo getEquipo (String ligaEquipo, String nombreEquipo) {
		HashMap<String, Equipo> aux = hashMapLigasEquipos.get(ligaEquipo);
		return aux.get(nombreEquipo);
	}
	
	/**
	 * M�todo que devuelve el Iterator del hashMap que contiene las ligas y los equipos
	 * @return Iterator del hashMap contenedor de ligas y equipos
	 */
	public Iterator<Entry<String, HashMap<String, Equipo>>> getIterator () {
		Set<Entry<String, HashMap<String, Equipo>>> varSet = hashMapLigasEquipos.entrySet();
		Iterator<Entry<String, HashMap<String, Equipo>>> it = varSet.iterator();
		return it;
	}
	
	/**
	 * M�todo que agrega una liga al hashMap contenedor de ligas y equipos
	 * @param nuevaLiga Nombre de la nueva liga
	 * @return true si la liga fue agregada, false si la liga ya estaba agregada.
	 */
	
	public boolean agregarLiga(String nuevaLiga) {
		if (hashMapLigasEquipos.containsKey(nuevaLiga)) {
			return false;
		} else {
			hashMapLigasEquipos.put(nuevaLiga, new HashMap<>());
			return true;
		}
	}
	
	/**
	 * M�todo que agrega un equipo al hashMap de equipos
	 * @param nuevoEquipo nombre del nuevo equipo
	 * @param ligaEquipo liga del nuevo equipo
	 * @return true si el equipo fue agregado, false si el equipo ya estaba agregado
	 */
	public boolean agregarEquipo (String nuevoEquipo, String ligaEquipo) {
		if (!hashMapLigasEquipos.containsKey(ligaEquipo)) {
			agregarLiga(ligaEquipo);
		}
		HashMap<String, Equipo> copia = hashMapLigasEquipos.get(ligaEquipo);
		if (copia.containsKey(nuevoEquipo)) {
			return false;
		} else {
			copia.put(nuevoEquipo, new Equipo(nuevoEquipo, ligaEquipo));
			return true;
		}
	}
	
	/**
	 * M�todo que agrega un jugador a su equipo
	 * @param nuevoJugador jugador a ser agregado
	 */
	public void agregarJugador (Jugador nuevoJugador) {
		agregarEquipo (nuevoJugador.getClub(), nuevoJugador.getLiga());
		Equipo copia = hashMapLigasEquipos.get(nuevoJugador.getLiga()).get(nuevoJugador.getClub());
		copia.agregarJugador(nuevoJugador.getIDJugador());
	}

	/**
	 * M�todo que elimia un jugador de su equipo
	 * @param jugadorAEliminar jugador a ser eliminado
	 */
	public void eliminarJugador(Jugador jugadorAEliminar) {
		Equipo copia = hashMapLigasEquipos.get(jugadorAEliminar.getLiga()).get(jugadorAEliminar.getClub());
		copia.eliminarJugador(jugadorAEliminar.getIDJugador());
	}
	
	/**
	 * M�todo que agrega un DT a su equipo
	 * @param nuevoDT Director T�cnico a agregar
	 * @return true si el DT pudo ser agregado, false si no
	 */
	public boolean agregarDT (DirectorTecnico nuevoDT) {
		agregarEquipo (nuevoDT.getClub(), nuevoDT.getLiga());
		Equipo copia = hashMapLigasEquipos.get(nuevoDT.getLiga()).get(nuevoDT.getClub());
		return copia.agregarDT(nuevoDT.getID());
	}

	/**
	 * M�todo que elimina un DT de su equipo
	 * @param dtAEliminar Director T�cnico a ser eliminado
	 */
	public void eliminarDT(DirectorTecnico dtAEliminar) {
		Equipo copia = hashMapLigasEquipos.get(dtAEliminar.getLiga()).get(dtAEliminar.getClub());
		copia.eliminarDT();
	}
	
	/**
	 * M�todo que permite al Administrador elegir una liga o equipo existentes, o agregar nuevos para un DT o Jugador
	 * @return el equipo seleccionado o agregado
	 */
	public Equipo seleccionLigasEquipos () {
		Set<Entry<String, HashMap<String, Equipo>>> varSet = hashMapLigasEquipos.entrySet();
		Iterator<Entry<String, HashMap<String, Equipo>>> it = varSet.iterator();
		ArrayList<String> nombresLigas = new ArrayList<>();
		while (it.hasNext()) {
			Entry<String, HashMap<String, Equipo>> auxA = it.next();
			nombresLigas.add(auxA.getKey());
		}
		Collections.sort(nombresLigas);
		System.out.println("  Ligas disponibles: \n");
		for (int i=0; i<nombresLigas.size(); i++){
			System.out.println((i+1) + ". " + nombresLigas.get(i) + ".\n");
		}
		System.out.println((nombresLigas.size()+1) + ". Agregar liga.\n");
		int opcion = Simulador.ingresoOpcion(0, nombresLigas.size()+1);
		if (opcion == (nombresLigas.size()+1)) { //Opci�n de agregar liga
			System.out.println("  Eligi� la opci�n de agregar una liga.\n");
			System.out.print("  Ingrese el nombre de la nueva liga: ");
			Simulador.getScanner().nextLine();
			String nuevaLiga = Simulador.getScanner().nextLine();
			nuevaLiga = nuevaLiga.toUpperCase();
			System.out.print("  Ingrese el nombre del nuevo equipo: ");
			String nuevoEquipo = Simulador.getScanner().nextLine();
			nuevoEquipo = nuevoEquipo.toUpperCase();
			return new Equipo(nuevoEquipo, nuevaLiga);
		} else { //Opci�n de liga existente
			HashMap<String, Equipo> auxB = hashMapLigasEquipos.get(nombresLigas.get(opcion-1)); //Usamos la opci�n como �ndice para buscar la clave
			System.out.println("Equipos de la Liga " + nombresLigas.get(opcion-1) + ": \n");
			ArrayList<String> nombresEquipos = new ArrayList<>();
			Set<Entry<String, Equipo>> varSet2 = auxB.entrySet();
			Iterator<Entry<String, Equipo>> it2 = varSet2.iterator(); 
			while (it2.hasNext()) {
				Entry<String, Equipo> auxC = it2.next();
				nombresEquipos.add(auxC.getKey());
			}
			Collections.sort(nombresEquipos);
			System.out.println("  Equipos disponibles: ");
			for (int i=0; i<nombresEquipos.size(); i++){
				System.out.println((i+1) + ". " + nombresEquipos.get(i) + ".\n");
			}
			System.out.println((nombresEquipos.size()+1) + ". Agregar equipo.");
			int opcion2 = Simulador.ingresoOpcion(0, nombresEquipos.size()+1);
			if (opcion2 == (nombresEquipos.size()+1)) { //Opci�n de agregar equipo
				System.out.println("  Eligi� la opci�n de agregar un equipo.\n");
				Simulador.getScanner().nextLine();
				System.out.print("  Ingrese el nombre del nuevo equipo: ");
				String nuevoEquipo = Simulador.getScanner().nextLine();
				nuevoEquipo = nuevoEquipo.toUpperCase();
				return new Equipo(nuevoEquipo, nombresLigas.get(opcion-1));
			} else {
				Simulador.getScanner().nextLine();
				return auxB.get(nombresEquipos.get(opcion2-1));
			}
		}
	}
	
	/**
	 * M�todo que permite la selecci�n de un equipo ya existente para, por ejemplo, ser visto o enfrentado
	 * @param ingresoAdmin determina si se accede desde el Administrador
	 * @return el equipo seleccionado
	 */
	public Equipo listarLigasEquipos (boolean ingresoAdmin) {
		Set<Entry<String, HashMap<String, Equipo>>> varSet = hashMapLigasEquipos.entrySet();
		Iterator<Entry<String, HashMap<String, Equipo>>> it = varSet.iterator();
		ArrayList<String> nombresLigas = new ArrayList<>();
		while (it.hasNext()) {
			Entry<String, HashMap<String, Equipo>> auxA = it.next();
			nombresLigas.add(auxA.getKey());
		}
		Collections.sort(nombresLigas);
		System.out.println("  Ligas disponibles: \n");
		for (int i=0; i<nombresLigas.size(); i++){
			System.out.println((i+1) + ". " + nombresLigas.get(i) + ".\n");
		}
		int opcion = Simulador.ingresoOpcion(1, nombresLigas.size());
		HashMap<String, Equipo> auxB = hashMapLigasEquipos.get(nombresLigas.get(opcion-1)); //Usamos la opci�n como �ndice para buscar la clave
		System.out.println("Equipos de la Liga " + nombresLigas.get(opcion-1) + ": \n");
		ArrayList<String> nombresEquipos = new ArrayList<>();
		Set<Entry<String, Equipo>> varSet2 = auxB.entrySet();
		Iterator<Entry<String, Equipo>> it2 = varSet2.iterator(); 
		while (it2.hasNext()) {
			Entry<String, Equipo> auxC = it2.next();
			nombresEquipos.add(auxC.getKey());
		}
		Collections.sort(nombresEquipos);
		System.out.println("  Equipos disponibles: ");
		for (int i=0; i<nombresEquipos.size(); i++){
			System.out.println((i+1) + ". " + nombresEquipos.get(i) + ".");
			if (auxB.get(nombresEquipos.get(i)).hayEspacioEnPlantilla(ingresoAdmin)) {
				System.out.println(" Equipo con menos de 11 jugadores.");
			} else {
				System.out.println(" Equipo con 11 jugadores.");
			}
			if (auxB.get(nombresEquipos.get(i)).hayEspacioParaDT() && !ingresoAdmin) {
				System.out.println(" Equipo sin Director T�cnico.\n");
			}
		}
		opcion = Simulador.ingresoOpcion(1, nombresEquipos.size());
		Simulador.getScanner().nextLine();
		return auxB.get(nombresEquipos.get(opcion-1));
	}
	
	/**
	 * Muestra de los datos del contenedor
	 */
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		Set<Entry<String, HashMap<String, Equipo>>> varSet = hashMapLigasEquipos.entrySet();
		Iterator<Entry<String, HashMap<String, Equipo>>> it = varSet.iterator();
		while (it.hasNext()) {
			Entry<String, HashMap<String, Equipo>> auxA = it.next();
			HashMap<String, Equipo> auxB = auxA.getValue();
			retorno.append("Equipos de la Liga " + auxA.getKey() + ": \n");
			Set<Entry<String, Equipo>> varSet2 = auxB.entrySet();
			Iterator<Entry<String, Equipo>> it2 = varSet2.iterator();
			while (it2.hasNext()) {
				Equipo equipoAux = it2.next().getValue();
				retorno.append("Datos del equipo " + equipoAux.getNombreEquipo() + ": \n\n" + equipoAux.toString());
			}
		}
		return retorno.toString();
	}
}