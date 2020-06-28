package jsonPackage;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import Clases.Plantilla;
import Clases.Simulador;

/**
 * Clase de gestión de un JSONArray que contiene la información de una Plantilla
 */
public class JSONPlantilla {
	
	/**
	 * Método que crea y devuelve el JSONArray en base a los Jugadores de una Plantilla recibida
	 * @param recibida Plantilla que se recibe, con sus Jugadores
	 * @return JSONArray con información de los Jugadores de la Plantilla
	 * @throws JSONException excepción lanzada en caso de haber un error de manejo de JSON
	 */
	public JSONArray toJSONPlantilla (Plantilla recibida) throws JSONException{
		JSONArray retorno = new JSONArray();
		Iterator<Integer> it = recibida.getIterator();
		JSONJugador auxJSONJugador = new JSONJugador();
		while (it.hasNext()) {
			retorno.put(auxJSONJugador.toJSONJugador(Simulador.getMercado().getListadoJugadores().buscar((int)it.next())));
		}
		return retorno;
	}
}