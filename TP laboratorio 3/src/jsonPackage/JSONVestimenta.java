package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.Vestimenta;

/**
 * Clase de gestión de un JSONObject que contiene la información de una Vestimenta
 */
public class JSONVestimenta {
	
	/**
	 * Método que crea y devuelve el JSONObject en base a la Vestimenta de un DT recibida
	 * @param recibida Vestimenta que se recibe, para ser transformada a JSONObject
	 * @return JSONObject con la información de la Vestimenta
	 * @throws JSONException excepción lanzada en caso de haber un error de manejo de JSON
	 */
	protected JSONObject toJSONVestimenta (Vestimenta recibida) throws JSONException{
		JSONObject retorno = new JSONObject();
		retorno.put("Prenda Inferior", recibida.getPrendaInferior());
		retorno.put("Prenda Superior", recibida.getPrendaSuperior());
		return retorno;
	}
}
