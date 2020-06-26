package Clases;

import java.util.ArrayList;
import java.util.Collections;
import comparaciones.ComparacionPosicion;

/** 
 *  Esta clase nos permite crear objetos del tipo Partido, la cual cuenta con los m�todos 
 * necesarios para su gesti�n. Trabaja con el club del usuario y el equipo rival, sea uno cargado
 * por el administrador o uno de un usuario, siempre y cuando cuenten con 11 jugadores v�lidos y un DT
 * que tambi�n lo sea
 */

public class Partido {
	
	/**
	 * Constructor vac�o. La clase Partido no necesita atributos
	 */
	public Partido () {
	}
	
	/**
	 * M�todo que pide liga y equipo a enfrentar. Si los encuentra y los equipos cuentan con 11 jugadores,
	 * recibe las monedas ganadas y las cambia en el club.
	 * @param clubRecibido club del usuario que enfrentar� a otro rival
	 */
	public void listadoOpcionesJugarPartido (ClubUsuario clubRecibido){
	    int validosJugador = clubRecibido.getPlantillaClub().cantidadJugadores(false);
	    if (validosJugador == 11) {
	    	if (clubRecibido.getDTClub().getEstado()) {
	    		System.out.println("  A continuaci�n est�n las opciones:");
				System.out.println("    1. Jugar contra un equipo del Simulador.");
				System.out.println("    2. Jugar contra un equipo de otro usuario.");
				System.out.println("    3. Regresar al Men� del Club.");
				ingresarAOpcionJugarPartido(clubRecibido);
	    	} else {
	    		System.out.println ("\n  Necesit�s un Director T�cnico para jugar un partido.\n\n");
		    }		    
		            
	    } else {
	    	System.out.println ("\n  Los jugadores en tu equipo no son suficientes. Necesit�s 11 jugadores en tu Plantilla.\n\n");
	    }
	}
	
	/**
	 * Acceso a las opciones para jugar un partido
	 * @param clubRecibido club del usuario que enfrentar� a otro rival
	 */
	public void ingresarAOpcionJugarPartido (ClubUsuario clubRecibido){
		int opcion = Simulador.ingresoOpcion(1, 3);
		switch (opcion) {
			case 1:
				jugarPartido(clubRecibido, Simulador.getListadoLigasEquipos().listarLigasEquipos(false));
				System.out.println("Regresando al men� de Club.");
				break;
			case 2:
				Equipo equipoRival = Simulador.seleccionUsuarioRival();
				if (equipoRival != null) {
					jugarPartido(clubRecibido, equipoRival);
				}
				System.out.println("Regresando al men� de Club.");
				break;
			default:
				System.out.println("Regresando al men� de Club.");
				break;
		}
	}

	/**
	 * M�todo que determina si el club rival cumple con los requisitos para ser enfrentado y, de ser as�,
	 * llama a los m�todos necesarios para la simulaci�n del partido 
	 * @param clubRecibido club del usuario que enfrentar� a otro rival
	 * @param equipoRival club del rival que enfrentar� al usuario
	 */
	private void jugarPartido(ClubUsuario clubRecibido, Equipo equipoRival) {
	    if (!equipoRival.hayEspacioEnPlantilla(false)) {
	    	if (!equipoRival.hayEspacioParaDT()) {
	                System.out.println("\n   Todo correcto �A jugar!\n\n    ");
	                int resultado = resultadoPartido(clubRecibido.getPlantillaClub().promedioJugadores(clubRecibido.getDTClub().getTipo()), equipoRival.promedioJugadores());
	                clubRecibido.setFondos(clubRecibido.getFondos() + calcularMonedas(resultado, clubRecibido, equipoRival)); /// Resultado: -1 si pierde el jugador, 0 si hay empate, 1 si gana el jugador
	                Simulador.guardarArchivoUsuarios();
	    	} else {
	    		System.out.println ("\n El equipo rival necesita un Director T�cnico para jugar el partido.\n\n");
		    }        
	    } else {
	    	System.out.println ("\n  Los jugadores en el equipo rival no son suficientes.\n\n");
	    }
	}
	
	/**
	 * M�todo que recibe los promedios de calificaciones del equipo del usuario y del equipo del rival.
	 * Segun la diferencia, tenemos dos casos:
	 *     � Que el usuario tenga m�s promedio: la probabilidad de ganar es de 50+la diferencia.
	 *         Si la prob es mayor a 76.5, se limita a 76.5.
	 *     � Que el rival tenga m�s promedio: �dem anterior.
	 * Luego se hace un random de 0 a 100. Seg�n d�nde caiga ser� el resultado del partido, 
	 * siempre que caiga entre 95 y 100 ser� empate.
	 * Tomamos los dos casos como separados por c�mo se le asignan valores a la variable resultado: 
	 *     � -1 por perder, 
	 *     � 0 por empatar, 
	 *     � 1 por ganar.
	 * @param promedioUsuario valor de referencia de la calidad del equipo del usuario
	 * @param promedioRival valor de referencia de la calidad del equipo del rival
	 * @return cantidad de monedas ganadas en el partido
	 */
	private int resultadoPartido (double promedioUsuario, double promedioRival){
	    double diferencia = promedioUsuario - promedioRival;
	    double x;
	    int resultado;
	    if (diferencia > 0){
	        if (diferencia>26.5){
	            diferencia = 26.5; ///Seteamos un tope de diferencia del 26.5%, que viene dado por el 20% + 11*0.5 (jug especiales) + 1 (DT ESPECIAL)
	        }
	        x = (Math.random() * 100) + 1;
	        if (x <= 50 + diferencia){
	            resultado = 1; ///Gana el usuario
	        } else {
	            if (x >= 95){
	                resultado = 0; ///Empate
	            } else {
	                resultado = -1; ///Gana el equipo rival
	            }
	        }
	    } else { ///No tomamos valor absoluto porque tomamos dos casos, seg�n qui�n tenga el mayor promedio
	        diferencia = promedioRival - promedioUsuario;
	        if (diferencia>26.5){
	        	diferencia = 26.5; ///Seteamos un tope de diferencia del 26.5%, que viene dado por el 20% + 11*0.5 (jug especiales) + 1 (DT ESPECIAL)
	        }
	        x = (Math.random() * 100) + 1;
	        if (x<=50+diferencia){
	            resultado = -1; ///Gana el equipo rival
	        } else {
	            if (x>=95){
	                resultado = 0; ///Empate
	            } else {
	                resultado = 1; ///Gana el usuario
	            }
	        }
	    }
	    return resultado;
	}
	
	/**
	 * M�todo que recibe el valor del resultado, calcula los goles, como m�ximo 5 por equipo, y calcula
	 * al azar los jugadores que los marcaron.
	 * @param resultado -1 por perder, 0 por empatar, 1 por ganar.
	 * @param clubRecibido club del usuario que enfrentar� a otro rival
	 * @param equipoRival club del rival que enfrentar� al usuario
	 * @return Monedas ganadas en el partido: un m�nimo de 50 (perder 5-0) y un m�ximo de 400 (ganar 5-0).
	 */
	private double calcularMonedas (int resultado, ClubUsuario clubUsuario, Equipo equipoRival){
	    double retorno = 0;
	    int x = -1; ///Goles usuario
	    int y = -1; ///Goles rival
	    int jugX=-1; ///Variable para calcular posiciones de jugadores en los arreglos
	    System.out.println("\n\n      SIMULANDO PARTIDO    ");
	    for (int i=0; i<10; i++){
	        System.out.print(" . ");
	        try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
	    }
	    switch (resultado){
	        case -1:                    /// Usuario pierde
	            while (y<=0){           /// Le asignamos a Y un random mayor a 0 y menor a 6
	                y = (int)((Math.random() * 6));
	            }
	            if (y==1){              /// Le asignamos a x un valor menor a Y
	                x = 0;
	            } else {
	                while (x>=y || x<0){
	                    x = (int)((Math.random() * 6));
	                }
	            }
	            System.out.println("\n DERROTA. ");
	            for (int i=0; i<5; i++){
	    	        System.out.print(" . ");
	    	        try {
	    				Thread.sleep(200);
	    			} catch (InterruptedException e) {
	    			}
	    	    }
	            System.out.println("\n        Resultado del partido: " + clubUsuario.getNombre() + " " + x + " - " + equipoRival.getNombreEquipo() + " - " + y + ".");
	            for (int j=0; j<10; j++){
        	        System.out.print(" . ");
        	        try {
        				Thread.sleep(200);
        			} catch (InterruptedException e) {
        			}
        	    }
	            retorno += 150 + x*30 - y*20; ///150 monedas por perder, 30 por gol a favor y -20 por gol en contra. Rango de ganancias: [50,170]
	            if (x>0){
	                System.out.println("\n        Goles de tu equipo " + clubUsuario.getNombre() + ":\n");
	                ArrayList<Jugador> aux = clubUsuario.getPlantillaClub().listado();
            		Collections.sort(aux, new ComparacionPosicion());
	                for (int i=0; i<x; i++){
	                    jugX = -1;
	                    while (jugX<0 || jugX>10){
	                        jugX = (int)((Math.random() * 10) + 1);
	                    }
	                    for (int j=0; j<5; j++){
	            	        System.out.print(" . ");
	            	        try {
	            				Thread.sleep(200);
	            			} catch (InterruptedException e) {
	            			}
	            	    }
	                    System.out.println("\n         Gol de " + aux.get(jugX).getNombre()+ ".\n");
	                }
	            } else {
	                System.out.println("\n        Tu equipo " + clubUsuario.getNombre() + " no hizo goles.\n");
	            }
	            System.out.println("\n\n        Goles del equipo rival " + equipoRival.getNombreEquipo()+ ":\n");
	            ArrayList<Jugador> aux = equipoRival.listado();
        		Collections.sort(aux, new ComparacionPosicion());
	            for (int i=0; i<y; i++){
	                jugX = -1;
	                while (jugX<0 || jugX>10){
                        jugX = (int)((Math.random() * 10) + 1);
                    }
	                for (int j=0; j<5; j++){
	        	        System.out.print(" . ");
	        	        try {
	        				Thread.sleep(200);
	        			} catch (InterruptedException e) {
	        			}
	        	    }
	                System.out.println("\n         Gol de " + aux.get(jugX).getNombre()+ ".\n");
	            }
	            for (int j=0; j<10; j++){
        	        System.out.print(" . ");
        	        try {
        				Thread.sleep(200);
        			} catch (InterruptedException e) {
        			}
        	    }
	            System.out.println("\n\n         Informe de monedas:");
	            System.out.println("\n          Monedas por la derrota: $150.");
	            System.out.println("\n          Monedas por goles a favor: $" + x*30 + ".");
	            System.out.println("\n          Monedas por goles en contra: - $" + y*20 + ".");
	            System.out.println("\n\n         Total de monedas ganadas en el partido: $" + retorno + ".");
	            break;
	        case 0:                     /// Empate
	            while (y<=0){           /// Le asignamos a Y un random mayor a 0 y menor a 6
	            	y = (int)((Math.random() * 6));
	            }
	            x = y;                  /// Le asignamos a x un valor igual a Y
	            System.out.println("\n EMPATE.  ");
	            System.out.println("\n        Resultado del partido: " + clubUsuario.getNombre() + " " + x + " - " + equipoRival.getNombreEquipo() + " - " + y + ".");
	            for (int j=0; j<10; j++){
        	        System.out.print(" . ");
        	        try {
        				Thread.sleep(200);
        			} catch (InterruptedException e) {
        			}
        	    }
	            retorno += 200 + x*30 - y*20; ///200 monedas por empatar, 30 por gol a favor y -20 por gol en contra. Rango de ganancias: [200,250]
	            if (x>0){
	            	System.out.println("\n        Goles de tu equipo " + clubUsuario.getNombre() + ":\n");
	                aux = clubUsuario.getPlantillaClub().listado();
            		Collections.sort(aux, new ComparacionPosicion());
	                for (int i=0; i<x; i++){
	                    jugX = -1;
	                    for (int j=0; j<5; j++){
		        	        System.out.print(" . ");
		        	        try {
		        				Thread.sleep(200);
		        			} catch (InterruptedException e) {
		        			}
		        	    }
	                    while (jugX<0 || jugX>10){
	                        jugX = (int)((Math.random() * 10) + 1);
	                    }
	                    System.out.println("\n         Gol de " + aux.get(jugX).getNombre()+ ".\n");
	                }
	                System.out.println("\n\n        Goles del equipo rival " + equipoRival.getNombreEquipo()+ ":\n");
		            aux = equipoRival.listado();
	        		Collections.sort(aux, new ComparacionPosicion());
		            for (int i=0; i<y; i++){
		                jugX = -1;
		                while (jugX<0 || jugX>10){
	                        jugX = (int)((Math.random() * 10) + 1);
	                    }
		                for (int j=0; j<5; j++){
		        	        System.out.print(" . ");
		        	        try {
		        				Thread.sleep(200);
		        			} catch (InterruptedException e) {
		        			}
		        	    }
		                System.out.println("\n         Gol de " + aux.get(jugX).getNombre()+ ".\n");
		            }
	            } else {
	                System.out.println("\n        Fue un partido sin goles.\n");
	            }
	            for (int j=0; j<10; j++){
        	        System.out.print(" . ");
        	        try {
        				Thread.sleep(200);
        			} catch (InterruptedException e) {
        			}
        	    }
	            System.out.println("\n\n         Informe de monedas:");
	            System.out.println("\n          Monedas por el empate: $200.");
	            System.out.println("\n          Monedas por goles a favor: $" + x*30 + ".");
	            System.out.println("\n          Monedas por goles en contra: - $" + y*20 + ".");
	            System.out.println("\n\n         Total de monedas ganadas en el partido: $" + retorno + ".");
	            break;
	        case 1:                     /// Usuario gana
	            while (x<=0){           /// Le asignamos a Y un random mayor a 0 y menor a 6
	                x = (int)(Math.random() * 6);
	            }
	            if (x==1){              /// Le asignamos a x un valor menor a Y
	                y = 0;
	            } else {
	                while (y>=x || y<0){
	                    y = (int)((Math.random() * 6));
	                }
	            }
	            System.out.print("\n VICTORIA.");
	            System.out.println("\n        Resultado del partido: " + clubUsuario.getNombre() + " " + x + " - " + equipoRival.getNombreEquipo() + " - " + y + ".");
	            for (int j=0; j<10; j++){
        	        System.out.print(" . ");
        	        try {
        				Thread.sleep(200);
        			} catch (InterruptedException e) {
        			}
        	    }
	            retorno += 250 + x*30 - y*20; ///250 monedas por ganar, 30 por gol a favor y -20 por gol en contra. Rango de ganancias: [320,400]
	            System.out.println("\n        Goles de tu equipo " + clubUsuario.getNombre() + ":\n");
                aux = clubUsuario.getPlantillaClub().listado();
        		Collections.sort(aux, new ComparacionPosicion());
                for (int i=0; i<x; i++){
                    jugX = -1;
                    while (jugX<0 || jugX>10){
                        jugX = (int)((Math.random() * 10) + 1);
                    }
                    for (int j=0; j<5; j++){
	        	        System.out.print(" . ");
	        	        try {
	        				Thread.sleep(200);
	        			} catch (InterruptedException e) {
	        			}
	        	    }
                    System.out.println("\n         Gol de " + aux.get(jugX).getNombre()+ ".\n");
                }
	            if (y>0){
	            	System.out.println("\n\n        Goles del equipo rival " + equipoRival.getNombreEquipo()+ ":\n");
		            aux = equipoRival.listado();
	        		Collections.sort(aux, new ComparacionPosicion());
		            for (int i=0; i<y; i++){
		                jugX = -1;
		                while (jugX<0 || jugX>10){
	                        jugX = (int)((Math.random() * 10) + 1);
	                    }
		                for (int j=0; j<5; j++){
		        	        System.out.print(" . ");
		        	        try {
		        				Thread.sleep(200);
		        			} catch (InterruptedException e) {
		        			}
		        	    }
		                System.out.println("\n         Gol de " + aux.get(jugX).getNombre()+ ".\n");
		            }
	            } else {
	                System.out.println("\n        El equipo rival " + equipoRival.getNombreEquipo() + " no hizo goles.\n");
	            }
	            for (int j=0; j<10; j++){
        	        System.out.print(" . ");
        	        try {
        				Thread.sleep(200);
        			} catch (InterruptedException e) {
        			}
        	    }
	            System.out.println("\n\n         Informe de monedas:");
	            System.out.println("\n          Monedas por la victoria: $250.");
	            System.out.println("\n          Monedas por goles a favor: $" + x*30 + ".");
	            System.out.println("\n          Monedas por goles en contra: - $" + y*20 + ".");
	            System.out.println("\n\n         Total de monedas ganadas en el partido: $" + retorno + ".");
	            break;
	    }
	    for (int j=0; j<15; j++){
	        System.out.print(" . ");
	        try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
	    }
	    System.out.println("\n\n");
	    return retorno;
	}
}



