package example.proyectofinal.IA;

import example.proyectofinal.IA.Vertice;

public class Arista<T> {
    Vertice<T> origen;
    Vertice<T> destino;
    int coste;

    public Arista(Vertice<T> origen, Vertice<T> destino, int coste) {
        this.origen = origen;
        this.destino = destino;
        this.coste = coste;
    }
}