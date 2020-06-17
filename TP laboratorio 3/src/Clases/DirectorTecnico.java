package Clases;

import java.util.ArrayList;
//import java.util.Collections;

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
	}

	public static int getCantidadDTs() {
		return cantidadTecnicos;
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
				int edadDT = Simulador.getScanner().nextInt();
				while (edadDT<35 || edadDT>75) {
					System.out.println("  Por favor ingrese una edad correcta (entre 35 y 75): ");
					edadDT = Simulador.getScanner().nextInt();
				}
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
			System.out.println(" Ingrese un precio entre $200 y $700.");
			retorno = Simulador.getScanner().nextDouble();
			while (retorno<200 || retorno>700) {
				System.out.println("  Por favor ingrese un precio correcto (entre $200 y $700): ");
				retorno = Simulador.getScanner().nextDouble();
			}
			return retorno;
		} else {
			if (calidadDT.equals("PLATA")) {
				System.out.println(" Ingrese un precio entre $400 y $2000.");
				retorno = Simulador.getScanner().nextDouble();
				while (retorno<400 || retorno>2000) {
					System.out.println("  Por favor ingrese un precio correcto (entre $400 y $2000): ");
					retorno = Simulador.getScanner().nextDouble();
				}
				return retorno;
			} else {
				if (calidadDT.contentEquals("ORO")) {
					System.out.println(" Ingrese un precio entre $700 y $5000.");
					retorno = Simulador.getScanner().nextDouble();
					while (retorno<700 || retorno>5000) {
						System.out.println("  Por favor ingrese un precio correcto (entre $700 y $5000): ");
						retorno = Simulador.getScanner().nextDouble();
					}
					return retorno;
				} else {
					System.out.println(" Ingrese un precio entre $6000 y $20000.");
					retorno = Simulador.getScanner().nextDouble();
					while (retorno<6000 || retorno>20000) {
						System.out.println("  Por favor ingrese un precio correcto (entre $6000 y $20000): ");
						retorno = Simulador.getScanner().nextDouble();
					}
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
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>4) {
			System.out.println("  Por favor ingrese una opción correcta (entre 1 y 4): ");
			opcion = Simulador.getScanner().nextInt();
		}
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
		int opcion;
		String opcionSuperior = "";
		String opcionInferior = "";
		System.out.println("\n\nOpciones de vestimenta: " /*+ nombreAdmin*/);
		System.out.println("  Parte superior:");
		System.out.println("    1. Saco negro.");
		System.out.println("    2. Saco azul.");
		System.out.println("    3. Camperón Club.");
		System.out.println("    4. Sweater gris.");
		System.out.println("    5. Remera color club.");
		System.out.println("  Ingrese el número de opción deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>5) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				opcionSuperior = "Saco negro";
				break;
			case 2:
				opcionSuperior = "Saco azul";
				break;
			case 3:
				opcionSuperior = "Camperón Club";
				break;
			case 4:
				opcionSuperior = "Sweater gris";
				break;
			case 5:
				opcionSuperior = "Remera color club";
				break;
		}
		System.out.println("  Parte inferior:");
		System.out.println("    1. Pantalón de vestir negro.");
		System.out.println("    2. Pantalón de vestir azul.");
		System.out.println("    3. Jogging Club.");
		System.out.println("    4. Pantalón de vestir gris.");
		System.out.println("    5. Pantalón corto color club.");
		System.out.println("  Ingrese el número de opción deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>5) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				opcionInferior = "Pantalón de vestir negro";
				break;
			case 2:
				opcionInferior = "Pantalón de vestir azul";
				break;
			case 3:
				opcionInferior = "Jogging Club";
				break;
			case 4:
				opcionInferior = "Pantalón de vestir gris";
				break;
			case 5:
				opcionInferior = "Pantalón corto color club";
				break;
		}
		this.vestimentaEquipada.setVestimenta(opcionSuperior, opcionInferior);
	}
	
	public static String opcionesListado (ArrayList<DirectorTecnico> listadoDTs) {
		System.out.println("  A continuación están los criterios de ordenación:");
		System.out.println("    1. Por nombre.");
		System.out.println("    2. Por liga.");
		System.out.println("    3. Por club.");
		System.out.println("    4. Por tipo.");
		System.out.println("    5. Por precio.");
		System.out.println("");
		return ingresarAOpcionesListado(listadoDTs);
	}
	
	public static String ingresarAOpcionesListado(ArrayList<DirectorTecnico> listadoDTs) {
		System.out.println("  Ingrese el número de opción deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>5) {
			System.out.println("  Por favor ingrese una opción correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1: //Por nombre
				//Collections.sort(listadoDTs);
				break;
			default:
				System.out.println("Gracias por usar el Listado.");
				break;
		}
		return listadoDTs.toString();
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

	

	/*@Override
	public int compareTo(Object o) {
		DirectorTecnico recibido = (DirectorTecnico)o;
		return recibido.getNombre().compareTo(this.getNombre());
	}*/
	
}
