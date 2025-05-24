package example.proyectofinal;

import example.proyectofinal.Casilla;
import example.proyectofinal.Unidades;

public class Movimiento {
    private final example.proyectofinal.Casilla casillaOrigen;
    private final example.proyectofinal.Casilla casillaDestino;
    private boolean puedeAtacar;

    public boolean PuedeAtacar() {
        return puedeAtacar;
    }

    public Movimiento(example.proyectofinal.Casilla casillaOrigen, example.proyectofinal.Casilla casillaDestino) {
        this.casillaOrigen = casillaOrigen;
        this.casillaDestino = casillaDestino;
        this.puedeAtacar = false;
    }

    public boolean mover() {
        if (casillaOrigen == null || casillaDestino == null) {
            return false;
        }

        Unidades unidadOrigen = casillaOrigen.getUnidad();
        Unidades unidadDestino = casillaDestino.getUnidad();

        if (unidadOrigen == null) {
            return false;
        }

        int distanciaX = Math.abs(casillaDestino.getX() - casillaOrigen.getX());
        int distanciaY = Math.abs(casillaDestino.getY() - casillaOrigen.getY());
        int distanciaTotal = distanciaX + distanciaY;

        if (distanciaTotal <= unidadOrigen.getMovimiento() && !casillaDestino.estaOcupada()) {
            casillaOrigen.removerUnidad();
            casillaDestino.colocarUnidad(unidadOrigen);
            unidadOrigen.setPosicion(casillaDestino.getX(), casillaDestino.getY());
            return true;
        }

        if (unidadDestino != null && distanciaTotal <= unidadOrigen.getRango_ataque()) {
            if (!unidadDestino.getFaccion().equals(unidadOrigen.getFaccion())) {
                puedeAtacar = true;
                unidadOrigen.ataque(unidadOrigen, unidadDestino);
                if (!unidadDestino.isVivo()) {
                    casillaDestino.removerUnidad();
                }
                return true;
            }
        }

        return false;
    }

    public boolean analizarRecorrido(Casilla[][] tablero) {
        int origenX = casillaOrigen.getX();
        int origenY = casillaOrigen.getY();
        int destinoX = casillaDestino.getX();
        int destinoY = casillaDestino.getY();

        if (origenX == destinoX || origenY == destinoY) {
            int inicio, fin;

            if (origenX == destinoX) {
                inicio = Math.min(origenY, destinoY) + 1;
                fin = Math.max(origenY, destinoY);

                for (int i = inicio; i < fin; i++) {
                    if (tablero[origenX][i].estaOcupada()) {
                        return false;
                    }
                }
            } else {
                inicio = Math.min(origenX, destinoX) + 1;
                fin = Math.max(origenX, destinoX);

                for (int i = inicio; i < fin; i++) {
                    if (tablero[i][origenY].estaOcupada()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}