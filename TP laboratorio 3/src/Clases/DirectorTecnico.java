package Clases;

/** 
 *  Esta clase nos permite crear objetos de tipo DirectorTecnico
 *  @author 
 */
public class DirectorTecnico extends PersonaFutbol /*implements Comparable*/{
	private static final long serialVersionUID = 1L;
	private static int cantidadTecnicos;
	private Vestimenta vestimentaEquipada;
	
	public DirectorTecnico() {
		super();
		vestimentaEquipada = new Vestimenta();
	}
	
	public DirectorTecnico(String nombreDT, String equipoDT, String ligaDT, String nacionalidadDT, int edadDT, String calidadDT, double precioDT) {
		super(nombreDT, equipoDT, ligaDT, nacionalidadDT, edadDT, calidadDT, precioDT, cantidadTecnicos);
		cantidadTecnicos++;
		this.vestimentaEquipada = new Vestimenta();
	}

	public DirectorTecnico(DirectorTecnico dtRecibido) { //Constructor por copia. No se suma otro DT a la cantidad total
		super(dtRecibido.getNombre(), dtRecibido.getClub(), dtRecibido.getLiga(), dtRecibido.getNacionalidad(), dtRecibido.getEdad(), dtRecibido.getTipo(), dtRecibido.getPrecio(), dtRecibido.getID());
		this.vestimentaEquipada = new Vestimenta();
		this.vestimentaEquipada.setVestimenta(dtRecibido.getVestimenta().getPrendaSuperior(), dtRecibido.getVestimenta().getPrendaInferior());
	}

	public static int getCantidadDTs() {
		return cantidadTecnicos;
	}
	
	public Vestimenta getVestimenta() {
		return this.vestimentaEquipada;
	}

	public DirectorTecnico crearDirectorTecnico() {
		System.out.println("Bienvenido al menú de creación de DT.");
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre y apellido del DT:");
		String nombreDT = Simulador.getScanner().nextLine().toUpperCase();
		Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
		if (equipoSeleccionado.hayEspacioParaDTAdmin()) {
				System.out.println("  Ingrese la nacionalidad del DT:");
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
	
	public void cambiarVestimenta() {
		vestimentaEquipada.cambiarVestimenta();
	}
	
	public static void setCantidadDTs(int cantidad) {
		cantidadTecnicos = cantidad;		
	}

	public void sincronizarDatos(DirectorTecnico aux) {
		this.setClub(aux.getClub());
		this.setEdad(aux.getEdad());
		this.setLiga(aux.getLiga());
		this.setNacionalidad(aux.getNacionalidad());
		this.setNombreApellido(aux.getNombre());
		this.setPrecio(aux.getPrecio());
		this.setTipo(aux.getTipo());
	}

	public String getTipo() {
		return super.getTipo();
	}

	public int getEdad() {
		return super.getEdad();
	}
	
	public String getNacionalidad() {
		return super.getNacionalidad();
	}

	@Override
	public String toString() {
		return super.toString() + vestimentaEquipada.toString() + "\n ID: " + super.getID() + ".";
	}
	
}
