package jsonPackage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
import Clases.ContenedorLigasEquipos;
import Clases.Equipo;

public class JSONLiga {
	
	private JSONObject toJSONArrayEquipos (HashMap<String, Equipo> recibido) throws JSONException{
		JSONObject retorno = new JSONObject();
		Set<Entry<String, Equipo>> varSet = recibido.entrySet();
		Iterator<Entry<String, Equipo>> it = varSet.iterator();
		Entry<String, Equipo> entryAux;
		JSONEquipo jsonEquipo = new JSONEquipo();
		while (it.hasNext()) {
			entryAux = it.next();
			retorno.put(entryAux.getKey(), jsonEquipo.toJSONEquipo(entryAux.getValue()));
		}
		return retorno;
	}
	
	public String toJSONLiga (ContenedorLigasEquipos recibido) throws JSONException{
		JSONObject retorno = new JSONObject();
		Iterator<Entry<String, HashMap<String, Equipo>>> it = recibido.getIterator();
		Entry<String, HashMap<String, Equipo>> entryAux;
		while (it.hasNext()) {
			entryAux = it.next();
			retorno.put(entryAux.getKey(), toJSONArrayEquipos(entryAux.getValue()));
		}
		return retorno.toString();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.toString();
	}
}
