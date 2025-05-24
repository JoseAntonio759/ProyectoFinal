package example.proyectofinal.IA;

import example.proyectofinal.IA.Vertice;

import java.util.List;

public class Camino<T> {
    List<Vertice<T>> camino;
    int coste ;

    public Camino(List<Vertice<T>> camino, int coste) {
        this.camino = camino;
        this.coste = coste;
    }

    public List<Vertice<T>> getCamino() {
        return camino;
    }

    public double getCoste() {
        return coste;
    }

    @Override
    public String toString() {
        StringBuffer salida = new StringBuffer();
        salida.append("======= Volcado del camino desde [" + getCamino().getFirst().data + "] hasta [" + getCamino().getLast().data + "]: ======\n");
        salida.append("Referencias a los vértices: " + this.getCamino() + "\n");
        salida.append("Lista de datos en vértices: [");
        for (Vertice<T> vertexx : this.getCamino()) {
            salida.append(vertexx.data);
        }
        salida.append("] - Coste: " + this.getCoste() + "\n");

        return salida.toString();
    }
}