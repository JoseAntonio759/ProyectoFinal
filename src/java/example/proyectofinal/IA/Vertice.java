package example.proyectofinal.IA;

import example.proyectofinal.IA.Arista;




public class Vertice<T> {
    public T data;
    public Lista<Arista<T>> aristaEntrada;
    public Lista<Arista<T>> aristaSalida;

    public Vertice(T data) {
        this.data = data;
        this.aristaEntrada = new ListaBasica<>();
        this.aristaSalida = new ListaBasica<>();
    }

    public void addAristaSalida(Arista<T> arista) {
        this.aristaSalida.add(arista);
    }

    public void addAristaEntrada(Arista<T> arista) {
        this.aristaEntrada.add(arista);
    }
}
