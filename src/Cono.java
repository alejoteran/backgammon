public class Cono {
    /*
    El color es true si tiene fichas blancas
    false si tiene fichas negras
    El numero indica la cantidad de fichas que
    estan en el tablero
    */
    private boolean color;
    private int numero;

    public Cono(boolean color, int numero) {
        this.color = color;
        this.numero = numero;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Cono{" +
                "color=" + color +
                ", numero=" + numero +
                '}';
    }
}
