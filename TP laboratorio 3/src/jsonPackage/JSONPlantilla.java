package jsonPackage;

import java.util.Iterator;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Clases.Jugador;
import Clases.Plantilla;
import Clases.Simulador;

public class JSONPlantilla {
	
	public JSONObject toJSONPlantilla (Plantilla recibida) throws JSONException{
		JSONObject retorno = new JSONObject();
		Iterator<Integer> it = recibida.getIterator();
		JSONJugador auxJSONJugador = new JSONJugador();
		Jugador aux;
		while (it.hasNext()) {
			aux = Simulador.getMercado().getListadoJugadores().buscar((int)it.next());
			retorno.put(aux.getNombre(), auxJSONJugador.toJSONJugador(aux));
		}
		return retorno;
	}
	
	// Si se quiere la Plantilla como JSONArray, descomentar este método y cambiar un poco la lógica en JSONEquipo
	/*public JSONArray toJSONPlantilla (Plantilla recibida) throws JSONException{
		JSONArray retorno = new JSONArray();
		Iterator<Integer> it = recibida.getIterator();
		JSONJugador auxJSONJugador = new JSONJugador();
		while (it.hasNext()) {
			retorno.put(auxJSONJugador.toJSONJugador(Simulador.getMercado().getListadoJugadores().buscar((int)it.next())));
		}
		return retorno;
	}*/
}
