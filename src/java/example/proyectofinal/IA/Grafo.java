package example.proyectofinal.IA;

import example.proyectofinal.IA.Arista;
import example.proyectofinal.IA.Camino;
import example.proyectofinal.IA.Vertice;

import java.util.*;

public class Grafo<T> {
    List<example.proyectofinal.IA.Vertice<T>> vertices;
    List<example.proyectofinal.IA.Arista<T>> arista;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arista = new ArrayList<>();
    }

    public void addVertice(example.proyectofinal.IA.Vertice<T> vertice) {
        this.vertices.add(vertice);
    }

    public void addArista(example.proyectofinal.IA.Arista<T> arista) {
        this.arista.add(arista);
        arista.origen.addAristaSalida(arista);
        arista.destino.addAristaEntrada(arista);
    }


    public Map<example.proyectofinal.IA.Vertice<T>, example.proyectofinal.IA.Camino<T>> dijkstra(example.proyectofinal.IA.Vertice<T> origen) {

        Map<example.proyectofinal.IA.Vertice<T>, Integer> distancias = new HashMap<>();
        Queue<example.proyectofinal.IA.Vertice<T>> colaPendientes = new LinkedList<>();
        Map<example.proyectofinal.IA.Vertice<T>, example.proyectofinal.IA.Vertice<T>> verticesAnteriores = new HashMap<>();

        this.dijkstra_init(origen,distancias,colaPendientes,verticesAnteriores);
        this.dijkstra_calcula(distancias,colaPendientes,verticesAnteriores);
        return this.dijkstra_procesaResultados(distancias,verticesAnteriores);
    }



    protected void dijkstra_init(example.proyectofinal.IA.Vertice<T> origen, Map<example.proyectofinal.IA.Vertice<T>, Integer> distancias, Queue<example.proyectofinal.IA.Vertice<T>> colaPendientes, Map<example.proyectofinal.IA.Vertice<T>, example.proyectofinal.IA.Vertice<T>> verticesAnteriores){
        for (example.proyectofinal.IA.Vertice<T> vertice : vertices) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }

        distancias.put(origen, 0);
        colaPendientes.add(origen);
    }


    protected void dijkstra_calcula(Map<example.proyectofinal.IA.Vertice<T>, Integer> distancias, Queue<example.proyectofinal.IA.Vertice<T>> colaPendientes, Map<example.proyectofinal.IA.Vertice<T>, example.proyectofinal.IA.Vertice<T>> verticesAnteriores){
        while (!colaPendientes.isEmpty()) {
            example.proyectofinal.IA.Vertice<T> verticeActual = colaPendientes.poll();

            for (Arista<T> arista : verticeActual.aristaSalida) {
                example.proyectofinal.IA.Vertice<T> verticeVecino = arista.destino;
                int nuevoCalculoDeDistancia = distancias.get(verticeActual) + arista.coste;

                if (nuevoCalculoDeDistancia < distancias.get(verticeVecino)) {
                    distancias.put(verticeVecino, nuevoCalculoDeDistancia);
                    verticesAnteriores.put(verticeVecino, verticeActual);
                    colaPendientes.add(verticeVecino);
                }
            }
        }
    }


    protected Map<example.proyectofinal.IA.Vertice<T>, example.proyectofinal.IA.Camino<T>>  dijkstra_procesaResultados(Map<example.proyectofinal.IA.Vertice<T>, Integer> distancias, Map<example.proyectofinal.IA.Vertice<T>, example.proyectofinal.IA.Vertice<T>> verticesAnteriores){
        Map<example.proyectofinal.IA.Vertice<T>, example.proyectofinal.IA.Camino<T>> caminos = new HashMap<>();

        for (example.proyectofinal.IA.Vertice<T> verticeDestino : verticesAnteriores.keySet()) {
            List<example.proyectofinal.IA.Vertice<T>> caminoCalculado = new ArrayList<>();
            Vertice<T> v = verticeDestino;
            while (v != null) {
                caminoCalculado.add(v);
                v = verticesAnteriores.get(v);
            }
            Collections.reverse(caminoCalculado);
            caminos.put(verticeDestino,new Camino<T>(caminoCalculado,distancias.get(verticeDestino)));
        }
        return caminos;
    }
}
