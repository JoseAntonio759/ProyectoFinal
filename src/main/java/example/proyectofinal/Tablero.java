package example.proyectofinal;

public class Tablero {
    private final int filas;
    private final int columnas;
    private final Casilla[][] casillas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.casillas = new Casilla[filas][columnas];
        inicializarCasillas();
    }

    private void inicializarCasillas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Por ahora, todas las casillas iguales (puedes luego hacer aleatorio o con tipo)
                casillas[i][j] = new Casilla(1, 0, 0); // costo movimiento 1, sin modificadores
            }
        }
    }

    public Casilla getCasilla(int fila, int columna) {
        if (esPosicionValida(fila, columna)) {
            return casillas[fila][columna];
        }
        throw new IndexOutOfBoundsException("Posición fuera del tablero");
    }

    public boolean esPosicionValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public boolean puedeMoverA(Unidades unidad, int destinoFila, int destinoCol) {
        if (!esPosicionValida(destinoFila, destinoCol)) return false;

        Casilla destino = getCasilla(destinoFila, destinoCol);
        if (destino.estaOcupada()) return false;

        int dx = Math.abs(unidad.getX() - destinoFila);
        int dy = Math.abs(unidad.getY() - destinoCol);
        int distancia = dx + dy;

        return distancia <= unidad.getRangoMovimiento() &&
                unidad.getRangoMovimiento() >= destino.getCostoMovimiento();
    }

    public boolean moverUnidad(Unidades unidad, int nuevaFila, int nuevaCol) {
        if (!puedeMoverA(unidad, nuevaFila, nuevaCol)) return false;

        Casilla origen = getCasilla(unidad.getX(), unidad.getY());
        Casilla destino = getCasilla(nuevaFila, nuevaCol);

        origen.removerUnidad();
        destino.colocarUnidad(unidad);
        unidad.setPosicion(nuevaFila, nuevaCol);

        return true;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    // Para depurar en consola
    public void imprimir() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(casillas[i][j]);
            }
            System.out.println();
        }
    }
}

