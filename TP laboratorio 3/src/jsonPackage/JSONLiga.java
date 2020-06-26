package jsonPackage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Clases.ContenedorLigasEquipos;
import Clases.Equipo;

public class JSONLiga {
	
	private JSONArray toJSONArrayEquipos (HashMap<String, Equipo> recibido) throws JSONException{
		JSONArray retorno = new JSONArray();
		Set<Entry<String, Equipo>> varSet = recibido.entrySet();
		Iterator<Entry<String, Equipo>> it = varSet.iterator();
		Entry<String, Equipo> entryAux;
		JSONEquipo jsonEquipo = new JSONEquipo();
		while (it.hasNext()) {
			entryAux = it.next();
			retorno.put(jsonEquipo.toJSONEquipo(entryAux.getValue()));
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
