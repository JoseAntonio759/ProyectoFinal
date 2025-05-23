package example.proyectofinal.IA;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene la información de un vértice del grafo.
 *
 * @param <T>
 */
public class Vertice<T> {
    T data;
    List<Arista<T>> aristaEntrada;
    List<Arista<T>> aristaSalida;

    public Vertice(T data) {
        this.data = data;
        this.aristaEntrada = new ArrayList<>();
        this.aristaSalida = new ArrayList<>();
    }

    public void addAristaSalida(Arista<T> arista) {
        this.aristaSalida.add(arista);
    }

    public void addAristaEntrada(Arista<T> arista) {
        this.aristaEntrada.add(arista);
    }
}