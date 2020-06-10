package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo ContenedirPersonaSistema
 *  Contiene un HashSet con elementos genericos (V) que se extiendan de PersonaSistema
 *  @author 
 */
import java.util.HashSet;
import java.util.Iterator;

public class ContenedorPersonaSistema <V extends PersonaSistema>{
	
	private HashSet<V> hashSetElementos;
	
	public ContenedorPersonaSistema () {
		hashSetElementos = new HashSet<>();
	}
	
	public boolean agregarElemento (V valor) {
		return hashSetElementos.add(valor);
	}
	
	public boolean eliminarElemento (V valor) {
		return hashSetElementos.remove(valor);
	}
	
	public Iterator<V> getIterator () {
		Iterator<V> it = hashSetElementos.iterator();
		return it;
	}
	
	
	/**
	 * 
	 * @param valor variable de tipo PersonaSistema que se buscará en el HashSet.
	 * @return devuelve null si no fue encontrado, y el objeto buscado si se encuentra.
	 */
	public V buscarElemento (V valor) {
		V retorno = null;
		if (hashSetElementos.contains(valor)) { //TODO hacer override de equals y hashCode en Usuario y Administrador
			int flag = 0;
			Iterator<V> iterator = hashSetElementos.iterator();
			while (flag==0 && iterator.hasNext()) {
				V actual = iterator.next();
				if (actual != null && actual.equals(valor)) {
					retorno = actual;
					flag = 1;
				}
			}
		}
		return retorno;
	}
	
	public String listar() {
		StringBuilder retorno = new StringBuilder();
		Iterator<V> iterator = hashSetElementos.iterator();
		while (iterator.hasNext()) {
			V actual = iterator.next();
			if (actual != null) {
				retorno.append(actual.toString() + "\n");
			}
		}
		return retorno.toString();
	}
}
