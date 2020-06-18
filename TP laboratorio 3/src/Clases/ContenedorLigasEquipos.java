package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ContenedorLigasEquipos {
	private HashMap<String, HashMap<String, Equipo>> hashMapLigasEquipos;
	
	public ContenedorLigasEquipos() {
		hashMapLigasEquipos = new HashMap<>();
	}
	
	public Equipo getEquipo (String ligaEquipo, String nombreEquipo) {
		HashMap<String, Equipo> aux = hashMapLigasEquipos.get(ligaEquipo);
		return aux.get(nombreEquipo);
	}
	
	public boolean agregarLiga(String nuevaLiga) {
		if (hashMapLigasEquipos.containsKey(nuevaLiga)) {
			return false;
		} else {
			hashMapLigasEquipos.put(nuevaLiga, new HashMap<>());
			return true;
		}
	}
	
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
	
	public void agregarJugador (Jugador nuevoJugador) {
		agregarEquipo (nuevoJugador.getClub(), nuevoJugador.getLiga());
		Equipo copia = hashMapLigasEquipos.get(nuevoJugador.getLiga()).get(nuevoJugador.getClub());
		copia.agregarJugador(nuevoJugador.getIDJugador());
	}

	public void eliminarJugador(Jugador jugadorAEliminar) {
		Equipo copia = hashMapLigasEquipos.get(jugadorAEliminar.getLiga()).get(jugadorAEliminar.getClub());
		copia.eliminarJugador(jugadorAEliminar.getIDJugador());
	}
	
	public boolean agregarDT (DirectorTecnico nuevoDT) {
		agregarEquipo (nuevoDT.getClub(), nuevoDT.getLiga());
		Equipo copia = hashMapLigasEquipos.get(nuevoDT.getLiga()).get(nuevoDT.getClub());
		return copia.agregarDT(nuevoDT.getID());
	}

	public void eliminarDT(DirectorTecnico dtAEliminar) {
		Equipo copia = hashMapLigasEquipos.get(dtAEliminar.getLiga()).get(dtAEliminar.getClub());
		copia.elimiarDT();
	}
	
	public Equipo seleccionLigasEquipos () {
		Set<Entry<String, HashMap<String, Equipo>>> varSet = hashMapLigasEquipos.entrySet();
		Iterator<Entry<String, HashMap<String, Equipo>>> it = varSet.iterator();
		ArrayList<String> nombresLigas = new ArrayList<>();
		while (it.hasNext()) {
			Entry<String, HashMap<String, Equipo>> auxA = it.next();
			nombresLigas.add(auxA.getKey());
		}
		Collections.sort(nombresLigas);
		System.out.println("  Ligas disponibles: ");
		for (int i=0; i<nombresLigas.size(); i++){
			System.out.println((i+1) + ". " + nombresLigas.get(i) + ".\n");
		}
		System.out.println((nombresLigas.size()+1) + ". Agregar liga.");
		System.out.println("  Ingrese la opción que desea utilizar: ");
		int opcion = Simulador.ingresoOpcion(0, nombresLigas.size()+1);
		if (opcion == (nombresLigas.size()+1)) { //Opción de agregar liga
			System.out.println("  Eligió la opción de agregar una liga.\n");
			System.out.println("  Ingrese el nombre de la nueva liga: ");
			Simulador.getScanner().nextLine();
			String nuevaLiga = Simulador.getScanner().nextLine();
			nuevaLiga = nuevaLiga.toUpperCase();
			System.out.println("  Ingrese el nombre del nuevo equipo: ");
			String nuevoEquipo = Simulador.getScanner().nextLine();
			nuevoEquipo = nuevoEquipo.toUpperCase();
			return new Equipo(nuevoEquipo, nuevaLiga);
		} else { //Opción de liga existente
			HashMap<String, Equipo> auxB = hashMapLigasEquipos.get(nombresLigas.get(opcion-1)); //Usamos la opción como índice para buscar la clave
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
			if (opcion2 == (nombresEquipos.size()+1)) { //Opción de agregar equipo
				System.out.println("  Eligió la opción de agregar un equipo.\n");
				Simulador.getScanner().nextLine();
				System.out.println("  Ingrese el nombre del nuevo equipo: ");
				String nuevoEquipo = Simulador.getScanner().nextLine();
				nuevoEquipo = nuevoEquipo.toUpperCase();
				return new Equipo(nuevoEquipo, nombresLigas.get(opcion-1));
			} else {
				Simulador.getScanner().nextLine();
				return auxB.get(nombresEquipos.get(opcion2-1));
			}
		}
	}
	
	public Equipo listarLigasEquipos (boolean ingresoAdmin) {
		Set<Entry<String, HashMap<String, Equipo>>> varSet = hashMapLigasEquipos.entrySet();
		Iterator<Entry<String, HashMap<String, Equipo>>> it = varSet.iterator();
		ArrayList<String> nombresLigas = new ArrayList<>();
		while (it.hasNext()) {
			Entry<String, HashMap<String, Equipo>> auxA = it.next();
			nombresLigas.add(auxA.getKey());
		}
		Collections.sort(nombresLigas);
		System.out.println("  Ligas disponibles: ");
		for (int i=0; i<nombresLigas.size(); i++){
			System.out.println((i+1) + ". " + nombresLigas.get(i) + ".\n");
		}
		int opcion = Simulador.ingresoOpcion(0, nombresLigas.size());
		HashMap<String, Equipo> auxB = hashMapLigasEquipos.get(nombresLigas.get(opcion-1)); //Usamos la opción como índice para buscar la clave
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
				System.out.println(" Equipo sin Director Técnico.\n");
			}
		}
		opcion = Simulador.ingresoOpcion(0, nombresEquipos.size());
		Simulador.getScanner().nextLine();
		return auxB.get(nombresEquipos.get(opcion-1));
	}
	
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