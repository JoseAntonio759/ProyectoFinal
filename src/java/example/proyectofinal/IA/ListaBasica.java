package example.proyectofinal.IA;

public class ListaBasica<T> implements Lista<T> {
    private T[] elementos;
    private int maxElementos = 5;
    private int numElementos = 0;


    public ListaBasica() {
        elementos = (T[]) new Object[maxElementos];

    }

    @Override
    public boolean add(T elemento) {
        if (numElementos >= elementos.length) {
            return false;
        }
        elementos[numElementos++] = elemento;
        return true;
    }

    @Override
    public boolean delete(T elemento) {
        for (int i = 0; i < numElementos; i++) {
            if (elementos[i].equals(elemento)) {

                for (int j = i; j < numElementos - 1; j++) {
                    elementos[j] = elementos[j + 1];
                }
                elementos[--numElementos] = null;
                return true;
            }
        }
        return false; // Elemento no encontrado
    }


    @Override
    public Iterador<T> getIterador() {
        return new IteradorBasico();
    }

    @Override
    public int getNumElementos() {
        return numElementos;
    }

    private class IteradorBasico implements Iterador<T> {
        private int indice = 0;
        private boolean canDelete = false;
        @Override
        public boolean hasNext() {
            return indice < numElementos;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No hay más elementos");
            }
            canDelete = true;
            return elementos[indice++];
        }

        @Override
        public void delete() {
            if (!canDelete) {
                throw new IllegalStateException("No se puede eliminar el elemento actual");
            }
            ListaBasica.this.delete(elementos[indice - 1]);
            indice--;
            canDelete = false;
        }
    }
}
