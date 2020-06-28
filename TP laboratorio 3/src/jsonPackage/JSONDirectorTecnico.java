package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.DirectorTecnico;

/**
 * Clase de gesti�n de un JSONObject que contiene la informaci�n de un Director T�cnico
 */
public class JSONDirectorTecnico {
	
	/**
	 * M�todo que crea y devuelve el JSONObject en base al Director T�cnico recibido
	 * @param recibido DT recibido
	 * @return JSONObject con informaci�n del DT recibido
	 * @throws JSONException excepci�n lanzada en caso de haber un error de manejo de JSON
	 */
	public JSONObject toJSONDirectorTecnico (DirectorTecnico recibido) throws JSONException {
		JSONObject retorno = new JSONObject();
		JSONVestimenta jsonVestimenta = new JSONVestimenta();
		JSONObject auxiObj = jsonVestimenta.toJSONVestimenta(recibido.getVestimenta());
	
		retorno.put("Estado", recibido.getEstado());
		retorno.put("Nombre y Apellido", recibido.getNombre());
		retorno.put("Club", recibido.getClub());
		retorno.put("Liga", recibido.getLiga());
		retorno.put("Nacionalidad", recibido.getNacionalidad());
		retorno.put("Edad", recibido.getEdad());
		retorno.put("Tipo", recibido.getTipo());
		retorno.put("Precio", recibido.getPrecio());
		retorno.put("ID", recibido.getID());
		retorno.put("Vestimenta", auxiObj);
		
		return retorno;
	}
}