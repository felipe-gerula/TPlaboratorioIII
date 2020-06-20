package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.Equipo;

public class JSONEquipo {
	
	public JSONObject toJSONEquipo (Equipo recibido) throws JSONException{
		JSONObject retorno = new JSONObject();
		JSONDirectorTecnico jsonDT = new JSONDirectorTecnico();
		JSONObject objectAux = jsonDT.toJSONDirectorTecnico(recibido.getDTEquipo());
		
		retorno.put("Nombre Equipo", recibido.getNombreEquipo());
		retorno.put("Nombre Liga", recibido.getNombreLiga());
		retorno.put("Director Técnico", objectAux);
		JSONPlantilla plantillaAux = new JSONPlantilla();
		JSONObject plantillaArray = plantillaAux.toJSONPlantilla(recibido.getPlantillaEquipo());
		retorno.put("Plantilla Equipo", plantillaArray);
		return retorno;
	}
	
}
