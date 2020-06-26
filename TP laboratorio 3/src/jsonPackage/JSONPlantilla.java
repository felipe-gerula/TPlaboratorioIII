package jsonPackage;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import Clases.Plantilla;
import Clases.Simulador;

public class JSONPlantilla {
	
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