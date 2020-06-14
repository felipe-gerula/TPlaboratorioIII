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

	public static int getCantidadDTs() {
		return cantidadTecnicos;
	}

	public DirectorTecnico crearDirectorTecnico() {
		System.out.println("Bienvenido al men� de creaci�n de DT.");
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre y apellido del DT:");
		String nombreDT = Simulador.getScanner().nextLine();
		Equipo equipoSeleccionado = Simulador.getListadoLigasEquipos().seleccionLigasEquipos();
		if (equipoSeleccionado.hayEspacioParaDT()) {
				System.out.println("  Ingrese la nacionalidad del DT:");
				String nacionalidadDT = Simulador.getScanner().nextLine();
				System.out.println("  Ingrese la edad del DT:");
				int edadDT = Simulador.getScanner().nextInt();
				Simulador.getScanner().nextLine();
				System.out.println("  Ingrese el nivel de calidad del DT (bronce, plata, oro, o especial):");
				String calidadDT = Simulador.getScanner().nextLine();
				System.out.println("  Ingrese el precio del DT:"); //TODO hacer m�todo que limite los precios seg�n si es especial y la calificaci�n del jugador
				double precioDT = Simulador.getScanner().nextDouble();
				DirectorTecnico nuevoDT = new DirectorTecnico(nombreDT, equipoSeleccionado.getNombreEquipo(), equipoSeleccionado.getNombreLiga(), nacionalidadDT, edadDT, calidadDT, precioDT);
				nuevoDT.cambiarVestimenta();
				return nuevoDT;
		} else {
			System.out.println("  Ya hay un DT cargado en el equipo seleccionado. Intente modificar sus datos.");
		}		
		return null;
	}
	
	public void cambiarVestimenta() {
		int opcion;
		String opcionSuperior = "";
		String opcionInferior = "";
		System.out.println("\n\nOpciones de vestimenta: " /*+ nombreAdmin*/);
		System.out.println("  Parte superior:");
		System.out.println("    1. Saco negro.");
		System.out.println("    2. Saco azul.");
		System.out.println("    3. Camper�n Club.");
		System.out.println("    4. Sweater gris.");
		System.out.println("    5. Remera color club.");
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>5) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
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
				opcionSuperior = "Camper�n Club";
				break;
			case 4:
				opcionSuperior = "Sweater gris";
				break;
			case 5:
				opcionSuperior = "Remera color club";
				break;
		}
		System.out.println("  Parte inferior:");
		System.out.println("    1. Pantal�n de vestir negro.");
		System.out.println("    2. Pantal�n de vestir azul.");
		System.out.println("    3. Jogging Club.");
		System.out.println("    4. Pantal�n de vestir gris.");
		System.out.println("    5. Pantal�n corto color club.");
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>5) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
			opcion = Simulador.getScanner().nextInt();
		}
		switch (opcion) {
			case 1:
				opcionInferior = "Pantal�n de vestir negro";
				break;
			case 2:
				opcionInferior = "Pantal�n de vestir azul";
				break;
			case 3:
				opcionInferior = "Jogging Club";
				break;
			case 4:
				opcionInferior = "Pantal�n de vestir gris";
				break;
			case 5:
				opcionInferior = "Pantal�n corto color club";
				break;
		}
		this.vestimentaEquipada.setVestimenta(opcionSuperior, opcionInferior);
	}
	
	public static String opcionesListado (ArrayList<DirectorTecnico> listadoDTs) {
		System.out.println("  A continuaci�n est�n los criterios de ordenaci�n:");
		System.out.println("    1. Por nombre.");
		System.out.println("    2. Por liga.");
		System.out.println("    3. Por club.");
		System.out.println("    4. Por tipo.");
		System.out.println("    5. Por precio.");
		System.out.println("");
		return ingresarAOpcionesListado(listadoDTs);
	}
	
	public static String ingresarAOpcionesListado(ArrayList<DirectorTecnico> listadoDTs) {
		System.out.println("  Ingrese el n�mero de opci�n deseada: ");
		int opcion = Simulador.getScanner().nextInt();
		while (opcion<1 || opcion>5) {
			System.out.println("  Por favor ingrese una opci�n correcta: ");
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
