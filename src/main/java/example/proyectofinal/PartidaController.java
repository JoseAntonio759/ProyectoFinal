package example.proyectofinal;

public class PartidaController {


    protected class ProgramaTablero {

        protected class Tablero {
            private TableroController ancho;
            private TableroController largo;
            private Casilla[][] casillas;
            private int largo1;
            private int ancho1;

            public Tablero() {
                this.ancho1 = ancho.getMedidaAncho();
                this.largo1= largo.getMedidaLargo();
                inicializarTablero();
            }

            protected void inicializarTablero() {
                casillas = new Casilla[largo1][ ancho1 ];
                for (int i = 1; i < largo1; i++) {
                    for (int j = 1; j <  ancho1; j++) {
                        casillas[i][j] = new Casilla();
                    }
                }
            }

            protected Casilla obtenerCasilla(int fila, int columna) {
                if (fila >= 0 && fila < largo1 && columna >= 0 && columna <  ancho1) {
                    return casillas[fila][columna];
                }
                return null;
            }

            protected class Casilla {
                private boolean ocupada;

                protected Casilla() {
                    this.ocupada = false;
                }

                protected boolean isOcupada() {
                    return ocupada;
                }

                protected void setPropiedad(boolean trueofalse) {
                    this.ocupada = trueofalse;
                }
            }
        }
    }
}

