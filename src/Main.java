import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        MinMax minMax = new MinMax();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean turno = true;

        System.out.println("Bienvenido al juego de backgammon");
        System.out.println("Para ganar las fichas blancas tienen que llegar a la posición 0 y las negras a la posición 25");
        while (!tablero.juegoTerminado()) {

            if (turno) {
                System.out.println("Estado actual del tablero:");
                System.out.println(tablero);
                System.out.println("Fichas blancas en la cárcel: " + tablero.getCarcelBlancas());
                System.out.println("Fichas negras en la cárcel: " + tablero.getCarcelNegras());

                System.out.println("Tu turno (blancas). Ingresa la posición de la ficha que deseas mover:");
                int dado = random.nextInt(6) + 1;
                System.out.println("El valor del dado es: " + dado);

                if (tablero.hayFichasEnCarcel(true)) {
                    tablero.liberarFicha(dado, true);
                } else {
                    int posicion;
                    int nuevaPosicion;
                    do {
                        posicion = scanner.nextInt();
                        nuevaPosicion = posicion - dado;

                        if (tablero.esMovimientoValido(posicion, nuevaPosicion, true)) {
                            tablero.moverFicha(posicion, nuevaPosicion, true);
                        } else {
                            System.out.println("Movimiento inválido. Intenta de nuevo.");
                        }
                    } while (!tablero.esMovimientoValido(posicion, nuevaPosicion, true));
                }

            } else {
                System.out.println("Turno de la IA:");
                int dado = random.nextInt(6) + 1;
                if (tablero.hayFichasEnCarcel(false)) {
                    tablero.liberarFicha(dado, false);
                } else {
                    Movimiento movimientoIA = minMax.minMax(tablero, 2, false);
                    tablero.moverFicha(movimientoIA.getOrigen(), movimientoIA.getDestino(), false);
                    System.out.println("La IA movió de la posición " + movimientoIA.getOrigen() + " a la posición " + movimientoIA.getDestino() + " con el dado de valor " + movimientoIA.getDado());
                }
            }

            System.out.println("Estado actual del tablero:");
            System.out.println(tablero);
            System.out.println("Fichas blancas en la cárcel: " + tablero.getCarcelBlancas());
            System.out.println("Fichas negras en la cárcel: " + tablero.getCarcelNegras());

            System.out.println("\n\n\n");
            if (tablero.juegoTerminado()) {
                break;
            }

            turno = !turno;
        }

        System.out.println("Juego terminado. Estado final del tablero:");
        System.out.println(tablero);
    }
}