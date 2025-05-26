package example.proyectofinal.IA;

import example.proyectofinal.IA.Vertice;

public class Camino<T> {
    private Lista<Vertice<T>> camino;
    private int coste;

    public Camino(Lista<Vertice<T>> camino, int coste) {
        this.camino = camino;
        this.coste = coste;
    }

    public Lista<Vertice<T>> getCamino() {
        return camino;
    }

    public int getCoste() {
        return coste;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        Iterador<Vertice<T>> it = camino.getIterador();
        if (!it.hasNext()) return "[Camino vacío]";

        Vertice<T> first = it.next();
        Vertice<T> last = first;
        salida.append("======= Volcado del camino desde [").append(first.data).append("]");

        while (it.hasNext()) {
            last = it.next();
        }

        salida.append(" hasta [").append(last.data).append("]: ======\n");

        salida.append("Referencias a los vértices: [");
        it = camino.getIterador();
        while (it.hasNext()) {
            salida.append(it.next()).append(", ");
        }
        salida.append("]\nLista de datos en vértices: [");

        it = camino.getIterador();
        while (it.hasNext()) {
            salida.append(it.next().data).append(", ");
        }
        salida.append("] - Coste: ").append(this.getCoste()).append("\n");

        return salida.toString();
    }
}
