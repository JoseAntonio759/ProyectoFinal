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
        Queue<Vertice<T>> colaPendientes = new LinkedList<>();  // Mantiene cuáles son los siguientes vértices a calcular.
        Map<Vertice<T>, Vertice<T>> verticesAnteriores = new HashMap<>(); //Guarda el rastro del camino para recalcularlo después.

        this.dijkstra_init(origen,distancias,colaPendientes,verticesAnteriores);  //Inicialización
        this.dijkstra_calcula(distancias,colaPendientes,verticesAnteriores);      //Cálculo
        return this.dijkstra_procesaResultados(distancias,verticesAnteriores);    //Procesamiento de resultados
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
            Vertice<T> verticeActual = colaPendientes.poll();  // Sacamos un vértice de la cola

            for (Arista<T> arista : verticeActual.aristaSalida) {   // Exploramos los arcos de salida del vértice.
                Vertice<T> verticeVecino = arista.destino;       // Para cada vértice conectado a la salida del arco...
                int nuevoCalculoDeDistancia = distancias.get(verticeActual) + arista.coste;  // Calculamos su distancia basándonos en lo que nos ha costado llegar al actual. Primer elemento: Coste 0.

                if (nuevoCalculoDeDistancia < distancias.get(verticeVecino)) {  // Si resulta que la nueva distancia es mejor que la que se había calculado antes a ese vértice, sustituimos los valores por los nuevos.
                    distancias.put(verticeVecino, nuevoCalculoDeDistancia);  // Guardamos la nueva distancia.
                    verticesAnteriores.put(verticeVecino, verticeActual);    // Guardamos el nuevo vértice anterior
                    colaPendientes.add(verticeVecino);                       // Añadimos el nuevo vértice a la cola de procesamiento, para en el futuro explorar sus salidas....
                }
            }
        }
    }


    protected Map<Vertice<T>,Camino<T>>  dijkstra_procesaResultados(Map<Vertice<T>, Integer> distancias, Map<Vertice<T>, Vertice<T>> verticesAnteriores){
        Map<Vertice<T>,Camino<T>> caminos = new HashMap<>();

        for (Vertice<T> verticeDestino : verticesAnteriores.keySet()) {             // De todos los vértices calculados
            List<Vertice<T>> caminoCalculado = new ArrayList<>();                   // prepara un camino para cada uno
            Vertice<T> v = verticeDestino;                                          // y en un bucle recorre el camino
            while (v != null) {                                                     // hacia atrás.
                caminoCalculado.add(v);
                v = verticesAnteriores.get(v); //El bucle es sobre v, o sea, los vértices: actualizo hasta que no tenga un origen (primero)
            }
            Collections.reverse(caminoCalculado);  //Le damos la vuelta, para que el camino empiece en el origen, no en el último.
            caminos.put(verticeDestino,new Camino<T>(caminoCalculado,distancias.get(verticeDestino)));
        }
        return caminos;
    }
}
