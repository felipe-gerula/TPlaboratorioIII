package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.Vestimenta;

public class JSONVestimenta {
	
	protected JSONObject toJSONVestimenta (Vestimenta recibida) throws JSONException{
		JSONObject retorno = new JSONObject();
		retorno.put("Prenda Inferior", recibida.getPrendaInferior());
		retorno.put("Prenda Superior", recibida.getPrendaSuperior());
		return retorno;
	}
}
