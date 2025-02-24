import java.util.Arrays;

public class Tablero {
    // Se crea un arreglo de conos con 26 posiciones
    // la posicion 0 es la meta de las fichas blancas
    // la posicion 25 es la meta de las fichas negras
    private Cono[] tablero = new Cono[26];

    public Tablero(){
        for(int i=0; i<26; i++){
            tablero[i] = new Cono(false, 0);
            switch (i){
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

    @Override
    public String toString() {
        return "Tablero{" +
                "tablero=" + Arrays.toString(tablero) +
                '}';
    }
}
