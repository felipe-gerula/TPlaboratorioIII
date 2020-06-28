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

/**
 * Clase de gesti�n de un JSONObject que contiene la informaci�n de las Ligas y sus equipos
 */
public class JSONLiga {
	
	/**
	 * M�todo que crea y devuelve el JSONArray en base a los Equipos de una Liga recibidos
	 * @param recibido HashMap que se recibe, con los Equipos de una Liga
	 * @return JSONArray con informaci�n de los Equipos de la Liga
	 * @throws JSONException excepci�n lanzada en caso de haber un error de manejo de JSON
	 */
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
	
	/**
	 * M�todo que crea y devuelve la informaci�n del JSONObject en base a las Ligas recibidas
	 * @param recibido contenedor de Ligas y Equipos recibido
	 * @return String con la informaci�n del JSONObject de Ligas y Equipos generado
	 * @throws JSONException excepci�n lanzada en caso de haber un error de manejo de JSON
	 */
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
	
	/**
	 * Informaci�n del JSONArray con los datos de la las ligas y sus Equipos
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.toString();
	}
}
