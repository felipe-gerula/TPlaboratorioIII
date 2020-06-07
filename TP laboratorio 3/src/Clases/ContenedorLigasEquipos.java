package Clases;

import java.util.HashMap;

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
		return copia.agregarJugador(nuevoJugador.getIDJugador());
	}
	
	public boolean agregarDT (DirectorTecnico nuevoDT) {
		agregarEquipo (nuevoDT.getClub(), nuevoDT.getLiga());
		Equipo copia = hashMapLigasEquipos.get(nuevoDT.getLiga()).get(nuevoDT.getClub());
		return copia.agregarDT(nuevoDT);
	}
	
}

















