package example.proyectofinal.IA;

import example.proyectofinal.IA.Arista;
import example.proyectofinal.IA.Camino;
import example.proyectofinal.IA.Vertice;

import java.util.*;

public class Grafo<T> {
    public List<Vertice<T>> vertices;
    List<Arista<T>> arista;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arista = new ArrayList<>();
    }

    public void addVertice(Vertice<T> vertice) {
        this.vertices.add(vertice);
    }

    public void addArista(Arista<T> arista) {
        this.arista.add(arista);
        arista.origen.addAristaSalida(arista);
        arista.destino.addAristaEntrada(arista);
    }
    public Arista<T> getArista(int index){
        return arista.get(index);
    }
    public Vertice<T> getVertice(int index){
        return vertices.get(index);
    }



}
