package Clases;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Clase que permite la creaci�n de Contenedores de Usuarios o Administradores, la cual cuenta con los atributos y m�todos 
 * necesarios para su gesti�n. Contiene un HashSet con elementos gen�ricos (V) que heredan de PersonaSistema
 *
 */
public class ContenedorPersonaSistema <V extends PersonaSistema>{
	
	private HashSet<V> hashSetElementos;
	
	/**
	 * Constructor vac�o que inicia el hashSet
	 */
	public ContenedorPersonaSistema () {
		hashSetElementos = new HashSet<>();
	}
	
	/** 
	 * M�todo que agrega un elemento al contenedor
	 * @param valor elemento a agregar
	 * @return true si pudo ser agregado, false si no
	 */
	public boolean agregarElemento (V valor) {
		return hashSetElementos.add(valor);
	}
	
	/**
	 * M�todo que elimina un elemento del contenedor
	 * @param valor elemento a agregar
	 * @return true si pudo ser eliminado, false si no
	 */
	public boolean eliminarElemento (V valor) {
		return hashSetElementos.remove(valor);
	}
	
	/**
	 * M�todo que devuelve el Iterator del hashSet
	 * @return el Iterator del hashSet
	 */
	public Iterator<V> getIterator () {
		Iterator<V> it = hashSetElementos.iterator();
		return it;
	}
	
	
	/**
	 * M�todo que busca un elemento por su nombre y l odevuelve de ser encontrado
	 * @param valor variable de tipo PersonaSistema que se buscar� en el HashSet.
	 * @return devuelve null si no fue encontrado, y el objeto buscado si se encuentra.
	 */
	public V buscarElemento (V valor) {
		V retorno = null;
		if (hashSetElementos.contains(valor)) {
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
	
	/**
	 * M�todo que devuelve el listado de los elementos almacenados en el hashSet
	 * @return Listado de los elementos almacenados en el hashSet
	 */
	public String listar() {
		StringBuilder retorno = new StringBuilder();
		Iterator<V> iterator = hashSetElementos.iterator();
		while (iterator.hasNext()) {
			V actual = iterator.next();
			if (actual != null) {
				retorno.append("\nNombre: " + actual.getNombre() + ".");
				if (actual instanceof GestionUsuario) {
					retorno.append("\n  Club: " + ((GestionUsuario) actual).getClubUsuario().getNombre() + ".\n");
				}
			}
		}
		return retorno.toString();
	}
}
