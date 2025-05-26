package example.proyectofinal.IA;

import example.proyectofinal.IA.Arista;
import example.proyectofinal.IA.Camino;
import example.proyectofinal.IA.Vertice;

public class Grafo<T> {
    public Lista<Vertice<T>> vertices;
    private Lista<Arista<T>> aristas;

    public Grafo() {
        this.vertices = new ListaBasica<>();
        this.aristas = new ListaBasica<>();
    }

    public void addVertice(Vertice<T> vertice) {
        this.vertices.add(vertice);
    }

    public void addArista(Arista<T> arista) {
        this.aristas.add(arista);
        arista.origen.addAristaSalida(arista);
        arista.destino.addAristaEntrada(arista);
    }

    public Arista<T> getArista(int index) {
        Iterador<Arista<T>> iterador = aristas.getIterador();
        int i = 0;
        while (iterador.hasNext()) {
            Arista<T> a = iterador.next();
            if (i == index) return a;
            i++;
        }
        throw new IndexOutOfBoundsException("Índice de arista fuera de rango: " + index);
    }

    public Vertice<T> getVertice(int index) {
        Iterador<Vertice<T>> iterador = vertices.getIterador();
        int i = 0;
        while (iterador.hasNext()) {
            Vertice<T> v = iterador.next();
            if (i == index) return v;
            i++;
        }
        throw new IndexOutOfBoundsException("Índice de vértice fuera de rango: " + index);
    }
}
