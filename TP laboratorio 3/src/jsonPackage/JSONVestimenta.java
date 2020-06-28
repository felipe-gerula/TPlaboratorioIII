package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.Vestimenta;

/**
 * Clase de gesti�n de un JSONObject que contiene la informaci�n de una Vestimenta
 */
public class JSONVestimenta {
	
	/**
	 * M�todo que crea y devuelve el JSONObject en base a la Vestimenta de un DT recibida
	 * @param recibida Vestimenta que se recibe, para ser transformada a JSONObject
	 * @return JSONObject con la informaci�n de la Vestimenta
	 * @throws JSONException excepci�n lanzada en caso de haber un error de manejo de JSON
	 */
	protected JSONObject toJSONVestimenta (Vestimenta recibida) throws JSONException{
		JSONObject retorno = new JSONObject();
		retorno.put("Prenda Inferior", recibida.getPrendaInferior());
		retorno.put("Prenda Superior", recibida.getPrendaSuperior());
		return retorno;
	}
}
