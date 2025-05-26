package example.proyectofinal.IA;

import example.proyectofinal.IA.Arista;
import example.proyectofinal.IA.Grafo;
import example.proyectofinal.IA.Vertice;
import example.proyectofinal.Unidades;

import java.util.ArrayList;

public class IAController {
    private int ancho;
    private int largo;

    public void setDimensiones(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        System.out.println("aaaa" + ancho + "bbbb" + largo);
    }

    Grafo<String> grafo = new Grafo();
    private ArrayList<Unidades> unidades = new ArrayList<>();


    public void generarMapa() {
        Vertice<String>[][] vertices = new Vertice[ancho][largo];

        for (int filas = 0; filas < ancho; filas++) {
            for (int columnas = 0; columnas < largo; columnas++) {
                vertices[filas][columnas] = new Vertice<>("vertice" + filas + columnas);
                grafo.addVertice(vertices[filas][columnas]);
            }
        }

        for (int filas = 0; filas < ancho; filas++) {
            for (int columnas = 0; columnas < largo; columnas++) {
                if (filas + 1 < ancho) {
                    grafo.addArista(new Arista<>(vertices[filas][columnas], vertices[filas + 1][columnas], 1));
                }
                if (columnas + 1 < largo) {
                    grafo.addArista(new Arista<>(vertices[filas][columnas], vertices[filas][columnas + 1], 1));
                }
            }
        }
    }


    private Unidades encontrarEnemigoMasCercano(Unidades unidad) {
        Unidades enemigoMasCercano = null;
        int distanciaMinima = Integer.MAX_VALUE;

        for (Unidades enemigo : unidades) {
            if (!enemigo.getFaccion().equals(unidad.getFaccion())) {
                int distancia = calcularDistancia(unidad, enemigo);
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    enemigoMasCercano = enemigo;
                }
            }
        }
        return enemigoMasCercano;
    }

    private int calcularDistancia(Unidades unidad1, Unidades unidad2) {
        return Math.abs(unidad1.getX() - unidad2.getX()) +
                Math.abs(unidad1.getY() - unidad2.getY());
    }

    public void turno() {
        for (Unidades unidad : unidades) {
            Unidades enemigoMasCercano = encontrarEnemigoMasCercano(unidad);
            if (enemigoMasCercano != null) {
                int distancia = calcularDistancia(unidad, enemigoMasCercano);
                if (distancia <= unidad.getMovimiento()) {
                    unidad.ataque(unidad, enemigoMasCercano);
                } else {
                    int nuevaX = unidad.getX();
                    int nuevaY = unidad.getY();
                    if (unidad.getX() < enemigoMasCercano.getX()) nuevaX += 1;
                    if (unidad.getX() > enemigoMasCercano.getX()) nuevaX -= 1;
                    if (unidad.getY() < enemigoMasCercano.getY()) nuevaY += 1;
                    if (unidad.getY() > enemigoMasCercano.getY()) nuevaY -= 1;
                    unidad.setPosicion(nuevaX, nuevaY);
                }
            }
        }
    }
}

