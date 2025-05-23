package example.proyectofinal.IA;

public class IAController {
    private int ancho;
    private int largo;

    public void setDimensiones(int ancho,int largo){
        this.ancho = ancho;
        this.largo = largo;
        System.out.println("aaaa" + ancho + "bbbb" + largo );
    }

    Grafo grafo = new Grafo();

    public void generarVertice(){
        Vertice<String> vertice = new Vertice<>("vertice");
    }



}
