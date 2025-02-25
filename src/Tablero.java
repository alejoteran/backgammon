import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
    Cono[] tablero = new Cono[26];

    public Tablero() {
        for (int i = 0; i < 26; i++) {
            tablero[i] = new Cono(false, 0);
            switch (i) {
                case 1:
                    tablero[i].setNumero(2);
                    break;
                case 6, 13:
                    tablero[i].setColor(true);
                    tablero[i].setNumero(5);
                    break;
                case 8:
                    tablero[i].setColor(true);
                    tablero[i].setNumero(3);
                    break;
                case 12, 19:
                    tablero[i].setNumero(5);
                    break;
                case 17:
                    tablero[i].setNumero(3);
                    break;
                case 24:
                    tablero[i].setColor(true);
                    tablero[i].setNumero(2);
                    break;
            }
        }
    }

    public List<Movimiento> generarSucesores(boolean color) {
        List<Movimiento> sucesores = new ArrayList<>();
        Random rand = new Random();
        int dado = rand.nextInt(6) + 1;

        for (int i = 0; i < 26; i++) {
            if (tablero[i].isColor() == color && tablero[i].getNumero() > 0) {
                int nuevaPosicion = i + dado;
                if (nuevaPosicion < 26) {
                    Tablero nuevoTablero = this.clone();
                    nuevoTablero.tablero[i].setNumero(nuevoTablero.tablero[i].getNumero() - 1);
                    nuevoTablero.tablero[nuevaPosicion].setNumero(nuevoTablero.tablero[nuevaPosicion].getNumero() + 1);
                    sucesores.add(new Movimiento(nuevoTablero, i, nuevaPosicion, dado));
                }
            }
        }
        return sucesores;
    }

    public boolean esMovimientoValido(int origen, int destino, boolean color) {
        if (origen < 1 || origen > 24 || destino < 1 || destino > 24) {
            return false; // Fuera de los límites del tablero
        }
        if (tablero[origen].getNumero() == 0 || tablero[origen].isColor() != color) {
            return false; // No hay fichas en el origen o no son del color correcto
        }
        if (tablero[destino].getNumero() > 1 && tablero[destino].isColor() != color) {
            return false; // El destino está bloqueado por más de una ficha del oponente
        }
        return true;
    }

    public boolean juegoTerminado() {
        return tablero[0].getNumero() == 15 || tablero[25].getNumero() == 15;
    }

    public int evaluarEstado() {
        int ind1 = 0; // Cantidad de fichas aliadas que se pueden asegurar
        int ind2 = 0; // Cantidad de fichas aliadas que pueden llegar al último sector
        int ind3 = 0; // Cantidad de fichas rivales que pueden ser eliminadas
        int ind4 = 0; // Cantidad de fichas aliadas en peligro

        for (int i = 0; i < 26; i++) {
            if (tablero[i].isColor()) { // Fichas aliadas
                if (i >= 19) {
                    ind2 += tablero[i].getNumero(); // Fichas aliadas que pueden llegar al último sector
                }
                if (tablero[i].getNumero() == 1) {
                    ind4 += 1; // Fichas aliadas en peligro
                }
                if (i == 0) {
                    ind1 += tablero[i].getNumero(); // Fichas aliadas aseguradas
                }
            } else { // Fichas rivales
                if (tablero[i].getNumero() == 1) {
                    ind3 += 1; // Fichas rivales que pueden ser eliminadas
                }
            }
        }

        int w1 = 5;
        int w2 = 3;
        int w3 = 1;
        int w4 = -1;

        return w1 * ind1 + w2 * ind2 + w3 * ind3 + w4 * ind4;
    }

    public Tablero clone() {
        Tablero nuevoTablero = new Tablero();
        for (int i = 0; i < 26; i++) {
            nuevoTablero.tablero[i] = new Cono(tablero[i].isColor(), tablero[i].getNumero());
        }
        return nuevoTablero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Posición: ");
        for (int i = 0; i < 26; i++) {
            sb.append(String.format("%2d ", i));
        }
        sb.append("\nFichas:   ");
        for (int i = 0; i < 26; i++) {
            sb.append(String.format("%2d ", tablero[i].getNumero()));
        }
        sb.append("\nColor:    ");
        for (int i = 0; i < 26; i++) {
            sb.append(tablero[i].isColor() ? " B " : " N ");
        }
        return sb.toString();
    }
}