package tresenraya;
import java.util.Scanner;

public class TresEnRaya {

    public static void main(String[] args) {
        // PROYECTO JUEGO TRES EN RAYA MODIFICADO
        Scanner sc = new Scanner (System.in);
        System.out.println("Bienvenido al juego N en Raya");
                      
        // Creación del primer Jugador
        System.out.print("Introduce el nombre del jugador X: ");
        String nombreJugador1 = sc.nextLine();
        char jugador1 = 'X';
        
        // Creación del segundo Jugador
        System.out.print("Introduce el nombre del jugador O: ");
        String nombreJugador2 = sc.nextLine();
        char jugador2 = 'O';
        
        // Creación del tablero
        // System.out.println("Elige de cuánto será el tablero: ");
        char [][] tablero = new char [3][3]; 

        // Empezar turno
        Scanner cs = new Scanner(System.in);
        System.out.print("¿Qué jugador empieza la partida? " + nombreJugador1 + " o " + nombreJugador2 + " (1-2): ");
        char turno = cs.next().charAt(0);
        
        //dibujarTablero(tablero); // Llama al método e imprime el tablero
                
        // Inicializar el tablero con espacios blancos en un bucle anidado
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tablero [i][j] = ' ';
            }
        }        
        Scanner scanner = null;        
        jugada(nombreJugador1, jugador1, nombreJugador2, jugador2, tablero, scanner, turno);
    }
    
    public static void dibujarTablero (char [][] tablero){  
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
        for (int j = 0; j < 3; j++) {
            char jugador = tablero[i][j] == ' ' ? ' ' : tablero[i][j];
            System.out.print(jugador + " | ");
        }
        System.out.println("\n-------------");
        }
    }
     
    public static void introducirFicha (String nombreJugador, char ficha, char[][] tablero, Scanner scanner){
        Scanner leer = new Scanner (System.in);
        int fila, columna;

        do {
            System.out.print(nombreJugador + ", ¿En qué fila quieres colocar la ficha jugador " + ficha + "? (1-3): ");
            fila = leer.nextInt() - 1;

            System.out.print(nombreJugador + ", ¿En qué columna quieres colocar la ficha jugador " + ficha + "? (1-3): ");
            columna = leer.nextInt() - 1;

            } while (fila < 0 || fila >= 4 || columna < 0 || columna >= 4 || tablero[fila][columna] != ' ');

            tablero[fila][columna] = ficha;
            dibujarTablero(tablero);
    }

    public static void jugada(String nombreJugador1, char jugador1, String nombreJugador2, char jugador2, char[][] tablero, Scanner scanner, char turnoElegido){
        boolean ganador = false;
        boolean empate = false;
        char turnoActual = turnoElegido;

        do {
            if (turnoActual == jugador1) {
                introducirFicha(nombreJugador1, jugador1, tablero, scanner);
            } else {
                introducirFicha(nombreJugador2, jugador2, tablero, scanner);
            }
            ganador = verificarGanador(turnoActual, tablero);
            empate = verificarEmpate(tablero);

            if (!ganador && !empate) {
                turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1;
            }
        // Muestra el resultado del Juego
        } while (!ganador && !empate);
        
        if (ganador) {
            if (turnoActual == jugador1){
                System.out.println("¡" + nombreJugador1 + " ha ganado!");
            }else{
                System.out.println("¡" + nombreJugador2 + " ha ganado!");
            }
        }else if(empate){
            System.out.println("¡Empate, ningún jugador ha ganado!");
        }
    }  
    public static boolean verificarGanador(char ficha, char[][] tablero) {
       // Comprobar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == ficha && tablero[i][1] == ficha && tablero[i][2] == ficha) {
                return true; // Ganador en la fila i
            }
        }
        // Comprobar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] == ficha && tablero[1][j] == ficha && tablero[2][j] == ficha) {
                return true; // Ganador en la columna j
            }
        }
        // Comprobar diagonales
        if (tablero[0][0] == ficha && tablero[1][1] == ficha && tablero[2][2] == ficha) {
            return true; // Ganador en la diagonal principal
        }
        if (tablero[0][2] == ficha && tablero[1][1] == ficha && tablero[2][0] == ficha) {
            return true; // Ganador en la diagonal secundaria
        }
        return false; // No hay ganador en filas, columnas o diagonales
    }

    public static boolean verificarEmpate(char[][] tablero) {
        // Verificar si no hay espacios en blanco en el tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false; // Todavía hay espacios en blanco, no hay empate
                }
            }
        }
        return true; // Todos los espacios están ocupados, es un empate
    }
}
