public class Movimiento {
    private Tablero tablero;
    private int origen;
    private int destino;
    private int dado;

    public Movimiento(Tablero tablero, int origen, int destino, int dado) {
        this.tablero = tablero;
        this.origen = origen;
        this.destino = destino;
        this.dado = dado;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public int getDado() {
        return dado;
    }
}