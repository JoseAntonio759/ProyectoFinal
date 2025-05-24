package example.proyectofinal.IA;

import example.proyectofinal.IA.Arista;

import java.util.ArrayList;
import java.util.List;


public class Vertice<T> {
    T data;
    List<example.proyectofinal.IA.Arista<T>> aristaEntrada;
    List<example.proyectofinal.IA.Arista<T>> aristaSalida;

    public Vertice(T data) {
        this.data = data;
        this.aristaEntrada = new ArrayList<>();
        this.aristaSalida = new ArrayList<>();
    }

    public void addAristaSalida(example.proyectofinal.IA.Arista<T> arista) {
        this.aristaSalida.add(arista);
    }

    public void addAristaEntrada(Arista<T> arista) {
        this.aristaEntrada.add(arista);
    }
}