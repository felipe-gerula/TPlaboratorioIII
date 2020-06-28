package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.Jugador;

/**
 * Clase de gesti�n de un JSONObject que contiene la informaci�n de un Jugador
 */
public class JSONJugador {
	
	/**
	 * M�todo que crea y devuelve el JSONObject en base al Jugador recibido
	 * @param recibido Jugador recibido
	 * @return JSONObject con informaci�n del Jugador recibido
	 * @throws JSONException excepci�n lanzada en caso de haber un error de manejo de JSON
	 */
	public JSONObject toJSONJugador (Jugador recibido) throws JSONException{
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
		retorno.put("Calificaci�n", recibido.getCalificacion());
		retorno.put("Pie h�bil", recibido.getPieHabil());
		retorno.put("Movimientos h�biles", recibido.getMovHabiles());
		retorno.put("Posici�n", recibido.getPosicion());
		return retorno;
	}
}
