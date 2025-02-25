import java.util.List;

public class MinMax {
    public Movimiento minMax(Tablero tablero, int profundidad, boolean maximizador) {
        if (profundidad == 0 || tablero.juegoTerminado()) {
            return new Movimiento(tablero, -1, -1, -1);
        }

        List<Movimiento> sucesores = tablero.generarSucesores(maximizador);
        Movimiento mejorMovimiento = null;

        if (maximizador) {
            int mejorValor = Integer.MIN_VALUE;
            for (Movimiento sucesor : sucesores) {
                Movimiento valor = minMax(sucesor.getTablero(), profundidad - 1, false);
                int evaluacion = valor.getTablero().evaluarEstado();
                if (evaluacion > mejorValor) {
                    mejorValor = evaluacion;
                    mejorMovimiento = sucesor;
                }
            }
        } else {
            int mejorValor = Integer.MAX_VALUE;
            for (Movimiento sucesor : sucesores) {
                Movimiento valor = minMax(sucesor.getTablero(), profundidad - 1, true);
                int evaluacion = valor.getTablero().evaluarEstado();
                if (evaluacion < mejorValor) {
                    mejorValor = evaluacion;
                    mejorMovimiento = sucesor;
                }
            }
        }
        return mejorMovimiento;
    }
}