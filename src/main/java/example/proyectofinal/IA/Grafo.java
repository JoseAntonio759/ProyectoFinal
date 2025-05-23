package example.proyectofinal.IA;

import java.util.*;

public class Grafo<T> {
    List<Vertice<T>> vertices;
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


    public Map<Vertice<T>,Camino<T>> dijkstra(Vertice<T> origen) {

        Map<Vertice<T>, Integer> distancias = new HashMap<>();
        Queue<Vertice<T>> colaPendientes = new LinkedList<>();
        Map<Vertice<T>, Vertice<T>> verticesAnteriores = new HashMap<>();

        this.dijkstra_init(origen,distancias,colaPendientes,verticesAnteriores);
        this.dijkstra_calcula(distancias,colaPendientes,verticesAnteriores);
        return this.dijkstra_procesaResultados(distancias,verticesAnteriores);
    }



    protected void dijkstra_init(Vertice<T> origen, Map<Vertice<T>, Integer> distancias, Queue<Vertice<T>> colaPendientes, Map<Vertice<T>, Vertice<T>> verticesAnteriores){
        for (Vertice<T> vertice : vertices) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }

        distancias.put(origen, 0);
        colaPendientes.add(origen);
    }


    protected void dijkstra_calcula(Map<Vertice<T>, Integer> distancias, Queue<Vertice<T>> colaPendientes, Map<Vertice<T>, Vertice<T>> verticesAnteriores){
        while (!colaPendientes.isEmpty()) {
            Vertice<T> verticeActual = colaPendientes.poll();

            for (Arista<T> arista : verticeActual.aristaSalida) {
                Vertice<T> verticeVecino = arista.destino;
                int nuevoCalculoDeDistancia = distancias.get(verticeActual) + arista.coste;

                if (nuevoCalculoDeDistancia < distancias.get(verticeVecino)) {
                    distancias.put(verticeVecino, nuevoCalculoDeDistancia);
                    verticesAnteriores.put(verticeVecino, verticeActual);
                    colaPendientes.add(verticeVecino);
                }
            }
        }
    }


    protected Map<Vertice<T>,Camino<T>>  dijkstra_procesaResultados(Map<Vertice<T>, Integer> distancias, Map<Vertice<T>, Vertice<T>> verticesAnteriores){
        Map<Vertice<T>,Camino<T>> caminos = new HashMap<>();

        for (Vertice<T> verticeDestino : verticesAnteriores.keySet()) {
            List<Vertice<T>> caminoCalculado = new ArrayList<>();
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
