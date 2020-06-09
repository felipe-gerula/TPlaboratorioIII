package Clases;
/** 
 *  Esta clase nos permite crear objetos de tipo DirectorTecnico
 *  @author 
 */
public class DirectorTecnico extends PersonaFutbol {
	private static int cantidadTecnicos;
	private int idTecnico;
	private Vestimenta vestimentaEquipada;
	
	public DirectorTecnico() {
		super();
		idTecnico = 0;
		vestimentaEquipada = new Vestimenta();
	}
	
	public DirectorTecnico(String nombreDT, String equipoDT, String ligaDT, String nacionalidadDT, int edadDT, String calidadDT, double precioDT) {
		super(nombreDT, equipoDT, ligaDT, nacionalidadDT, edadDT, calidadDT, precioDT);
		this.idTecnico = cantidadTecnicos;
		cantidadTecnicos++;
		this.vestimentaEquipada = new Vestimenta();
	}

	public DirectorTecnico crearDirectorTecnico() {
		System.out.println("Bienvenido al men� de creaci�n de DT.");
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre y apellido del DT:");
		String nombreDT = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre de la liga del DT:");
		String ligaDT = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nombre del equipo del DT:");
		String equipoDT = Simulador.getScanner().nextLine();
		//TODO controlar que no exista, y seguir. Si ya existe devuelve null
		System.out.println("  Ingrese la nacionalidad del DT:");
		String nacionalidadDT = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese la edad del DT:");
		int edadDT = Simulador.getScanner().nextInt();
		Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el nivel de calidad del DT (bronce, plata, oro, o especial):");
		String calidadDT = Simulador.getScanner().nextLine();
		System.out.println("  Ingrese el precio del DT:"); //TODO hacer m�todo que limite los precios seg�n si es especial y la calificaci�n del jugador
		double precioDT = Simulador.getScanner().nextDouble();
		DirectorTecnico nuevoDT = new DirectorTecnico(nombreDT, equipoDT, ligaDT, nacionalidadDT, edadDT, calidadDT, precioDT);
		nuevoDT.cambiarVestimenta();
		return nuevoDT;
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

	public int getIDDT() {
		return idTecnico;
	}

	@Override
	public String toString() {
		return super.toString() + vestimentaEquipada.toString() + "\n ID: " + idTecnico + ".";
	}
	
	
}
