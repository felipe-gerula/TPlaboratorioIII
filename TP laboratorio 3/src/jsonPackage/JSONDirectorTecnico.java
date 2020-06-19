package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.DirectorTecnico;
import jsonPackage.JSONVestimenta;

public class JSONDirectorTecnico {
	
	public JSONObject toJSONDirectorTecnico (DirectorTecnico recibido) throws JSONException {
		JSONObject retorno = new JSONObject();
		retorno.put("Estado", recibido.getEstado());
		retorno.put("Nombre y Apellido", recibido.getNombre());
		retorno.put("Club", recibido.getClub());
		retorno.put("Liga", recibido.getLiga());
		retorno.put("Nacionalidad", recibido.getNacionalidad());
		retorno.put("Edad", recibido.getEdad());
		retorno.put("Tipo", recibido.getTipo());
		retorno.put("Precio", recibido.getPrecio());
		retorno.put("ID", recibido.getID());
		retorno.put("Vestimenta", toJSONVestimenta(recibido.getVestimenta()));
		return retorno;
	}
}