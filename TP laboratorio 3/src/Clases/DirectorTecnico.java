package Clases;

/** 
 *  Esta clase nos permite crear objetos del tipo Director Técnico, la cual cuenta con los atributos y métodos 
 * necesarios para su gestión. Hereda de PersonaFutbol
 */
public class DirectorTecnico extends PersonaFutbol /*implements Comparable*/{
	private static final long serialVersionUID = 1L;
	private static int cantidadTecnicos;
	private Vestimenta vestimentaEquipada;
	
	/**
	 * Constructor vacío que llama al constructor vacío de PersonaFutbol y al constructor vacío de Vestimenta
	 */
	public DirectorTecnico() {
		super();
		vestimentaEquipada = new Vestimenta();
	}
	
	/**
	 * Constructor de Director Técnico con los parámetros a asignar al nuevo objeto 
	 * @param nombreDT nombre y apellido del nuevo DT
	 * @param equipoDT equipo del nuevo DT
	 * @param ligaDT liga del nuevo DT
	 * @param nacionalidadDT nacionalidad del nuevo DT
	 * @param edadDT edad del nuevo DT
	 * @param calidadDT calidad del nuevo DT
	 * @param precioDT precio del nuevo DT
	 */
	public DirectorTecnico(String nombreDT, String equipoDT, String ligaDT, String nacionalidadDT, int edadDT, String calidadDT, double precioDT) {
		super(nombreDT, equipoDT, ligaDT, nacionalidadDT, edadDT, calidadDT, precioDT, cantidadTecnicos);
		cantidadTecnicos++;
		this.vestimentaEquipada = new Vestimenta();
	}

	/**
	 * Constructor por copia. No se suma otro DT a la cantidad total.
	 * @param dtRecibido DT a ser copiado en la nueva instancia
	 */
	public DirectorTecnico(DirectorTecnico dtRecibido) {
		super(dtRecibido.getNombre(), dtRecibido.getClub(), dtRecibido.getLiga(), dtRecibido.getNacionalidad(), dtRecibido.getEdad(), dtRecibido.getTipo(), dtRecibido.getPrecio(), dtRecibido.getID());
		this.vestimentaEquipada = new Vestimenta();
		this.vestimentaEquipada.setVestimenta(dtRecibido.getVestimenta().getPrendaSuperior(), dtRecibido.getVestimenta().getPrendaInferior());
	}

	/**
	 * @return cantidad total de DTs
	 */
	public static int getCantidadDTs() {
		return cantidadTecnicos;
	}

	/**
	 * Método para obtener el tipo de la instancia
	 */
	public String getTipo() {
		return super.getTipo();
	}

	/**
	 * Método para obtener la edad de la instancia
	 */
	public int getEdad() {
		return super.getEdad();
	}
	
	/**
	 * Método para obtener la nacionalidad de la instancia
	 */
	public String getNacionalidad() {
		return super.getNacionalidad();
	}
	
	/**
	 * Método para cambiar la cantidad total de DTs
	 * @param cantidad nueva cantidad total
	 */
	public static void setCantidadDTs(int cantidad) {
		cantidadTecnicos = cantidad;		
	}
	
	/**
	 * @return vestimenta del DT
	 */
	public Vestimenta getVestimenta() {
		return this.vestimentaEquipada;
	}

	/**
	 * Método para la creación de un Director Técnico, con menúes internos
	 * @return nuevo Director Técnico creado en el menú
	 */
	public DirectorTecnico crearDirectorTecnico() {
		Simulador.esperar();
		System.out.println("Bienvenido al menú de creación de DT.");
		Simulador.getScanner().nextLine();
		System.out.print("  Ingrese el nombre y apellido del DT:");
		String nombreDT = Simulador.getScanner().nextLine().toUpperCase();
		Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
		if (equipoSeleccionado.hayEspacioParaDTAdmin()) {
				System.out.print("  Ingrese la nacionalidad del DT:");
				String nacionalidadDT = Simulador.getScanner().nextLine().toUpperCase();
				System.out.println("  Ingrese la edad del DT:");
				int edadDT = Simulador.ingresoOpcion(35, 75);
				Simulador.getScanner().nextLine();
				String calidadDT = seleccionDeCalidad();
				double precioDT = seleccionDePrecio(calidadDT);
				DirectorTecnico nuevoDT = new DirectorTecnico(nombreDT, equipoSeleccionado.getNombreEquipo(), equipoSeleccionado.getNombreLiga(), nacionalidadDT, edadDT, calidadDT, precioDT);
				nuevoDT.cambiarVestimenta();
				return nuevoDT;
		} else {
			System.out.println("  Ya hay un DT cargado en el equipo seleccionado. Intente modificar sus datos.");
		}		
		return null;
	}
	
	/**
	 * Método que lee el precio del DT según la valoración del mismo
	 * @param calidadDT valoración del DT
	 * @return nuevo precio
	 */
	public double seleccionDePrecio(String calidadDT) {
		double retorno;
		if (calidadDT.equals("BRONCE")) {
			System.out.println(" Ingreso de precio:\n");
			retorno = Simulador.ingresoOpcion(200, 700);
			return retorno;
		} else {
			if (calidadDT.equals("PLATA")) {
				System.out.println(" Ingreso de precio:\n");
				retorno = Simulador.ingresoOpcion(400, 2000);
				return retorno;
			} else {
				if (calidadDT.contentEquals("ORO")) {
					System.out.println(" Ingreso de precio:\n");
					retorno = Simulador.ingresoOpcion(700, 5000);
					return retorno;
				} else {
					System.out.println(" Ingreso de precio:\n");
					retorno = Simulador.ingresoOpcion(6000, 20000);
					return retorno;
				}
			}
		}
	}
	
	/**
	 * Método para seleccionar la calidad del DT dentro de las opciones dadas
	 * @return nueva calidad del DT
	 */
	public String seleccionDeCalidad() {
		System.out.println("  A continuación están las calidades disponibles:");
		System.out.println("    1. BRONCE.");
		System.out.println("    2. PLATA.");
		System.out.println("    3. ORO.");
		System.out.println("    4. ESPECIAL.");
		System.out.println("  Ingrese la posición deseada: ");
		int opcion = Simulador.ingresoOpcion(1, 4);
		switch (opcion) {
			case 1:
				return "BRONCE";
			case 2:
				return "PLATA";
			case 3:
				return "ORO";
			default:
				return "ESPECIAL";
		}
	}
	
	/**
	 * Método que cambia la vestimenta del DT
	 */
	public void cambiarVestimenta() {
		vestimentaEquipada.cambiarVestimenta();
	}

	/**
	 * Método que copia los datos del DT recibido, excepto la vestimenta
	 * @param aux DT de referencia
	 */
	public void sincronizarDatos(DirectorTecnico aux) {
		this.setClub(aux.getClub());
		this.setEdad(aux.getEdad());
		this.setLiga(aux.getLiga());
		this.setNacionalidad(aux.getNacionalidad());
		this.setNombreApellido(aux.getNombre());
		this.setPrecio(aux.getPrecio());
		this.setTipo(aux.getTipo());
	}

	/**
	 * Informacion del DT
	 */
	@Override
	public String toString() {
		return super.toString() + vestimentaEquipada.toString() + "\n ID: " + super.getID() + ".";
	}
	
}
