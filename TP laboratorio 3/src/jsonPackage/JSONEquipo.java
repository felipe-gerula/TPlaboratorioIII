package jsonPackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Clases.Equipo;

/**
 * Clase de gestión de un JSONObject que contiene la información de un Equipo
 */
public class JSONEquipo {
	
	/**
	 * Método que crea y devuelve el JSONObject en base al Equipo recibido
	 * @param recibido Equipo recibido
	 * @return JSONObject con información del Equipo recibido
	 * @throws JSONException excepción lanzada en caso de haber un error de manejo de JSON
	 */
	public JSONObject toJSONEquipo (Equipo recibido) throws JSONException{
		JSONObject retorno = new JSONObject();
		JSONDirectorTecnico jsonDT = new JSONDirectorTecnico();
		JSONObject objectAux = jsonDT.toJSONDirectorTecnico(recibido.getDTEquipo());
		
		retorno.put("Nombre Equipo", recibido.getNombreEquipo());
		retorno.put("Nombre Liga", recibido.getNombreLiga());
		retorno.put("Director Técnico", objectAux);
		JSONPlantilla plantillaAux = new JSONPlantilla();
		JSONArray plantillaArray = plantillaAux.toJSONPlantilla(recibido.getPlantillaEquipo());
		retorno.put("Plantilla Equipo", plantillaArray);
		return retorno;
	}
	
}
