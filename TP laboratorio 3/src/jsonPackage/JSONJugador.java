package jsonPackage;

import org.json.JSONException;
import org.json.JSONObject;
import Clases.Jugador;

/**
 * Clase de gestión de un JSONObject que contiene la información de un Jugador
 */
public class JSONJugador {
	
	/**
	 * Método que crea y devuelve el JSONObject en base al Jugador recibido
	 * @param recibido Jugador recibido
	 * @return JSONObject con información del Jugador recibido
	 * @throws JSONException excepción lanzada en caso de haber un error de manejo de JSON
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
		retorno.put("Calificación", recibido.getCalificacion());
		retorno.put("Pie hábil", recibido.getPieHabil());
		retorno.put("Movimientos hábiles", recibido.getMovHabiles());
		retorno.put("Posición", recibido.getPosicion());
		return retorno;
	}
}
