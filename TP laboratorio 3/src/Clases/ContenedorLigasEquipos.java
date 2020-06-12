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
	
	public boolean agregarJugador (Jugador nuevoJugador) {
		agregarEquipo (nuevoJugador.getClub(), nuevoJugador.getLiga());
		Equipo copia = hashMapLigasEquipos.get(nuevoJugador.getLiga()).get(nuevoJugador.getClub());
		if (copia.hayEspacioEnPlantilla()) {
			copia.agregarJugador(nuevoJugador.getIDJugador());
			return true;
		}
		return false;
	}
	
	public boolean agregarDT (DirectorTecnico nuevoDT) { //TODO solamente un DT
		agregarEquipo (nuevoDT.getClub(), nuevoDT.getLiga());
		Equipo copia = hashMapLigasEquipos.get(nuevoDT.getLiga()).get(nuevoDT.getClub());
		return copia.agregarDT(nuevoDT.getID());
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
		for (int i=0; i<nombresLigas.size(); i++){
			System.out.println((i+1) + ". " + nombresLigas.get(i) + ".\n");
		}
		System.out.println((nombresLigas.size()+1) + ". Agregar liga.");
		System.out.println("  Ingrese la opción que desea utilizar: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<0 || opcion>(nombresLigas.size()+1)) {
			System.out.println("  Por favor ingrese una opción correcta (entre 0 y " + (nombresLigas.size()+1) + "): ");
			idBuscado = Simulador.getScanner().nextInt();
		}
			HashMap<String, Equipo> auxB = auxA.getValue();
			System.out.println("Equipos de la Liga " + auxA.getKey() + ": \n");
			Set<Entry<String, Equipo>> varSet2 = auxB.entrySet();
			Iterator<Entry<String, Equipo>> it2 = varSet2.iterator();
			while (it2.hasNext()) {
				Equipo equipoAux = it2.next().getValue();
				retorno.append("Datos del equipo " + equipoAux.getNombreEquipo() + ": \n\n" + equipoAux.toString());
			
		}
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

















