package example.proyectofinal;


public class Casilla {
    private final int costoMovimiento;
    private final int modificadorDefensa;
    private final int modificadorMovimiento;
    private Unidades unidad;

    public Casilla(int costoMovimiento, int modificadorDefensa, int modificadorMovimiento) {
        this.costoMovimiento = costoMovimiento;
        this.modificadorDefensa = modificadorDefensa;
        this.modificadorMovimiento = modificadorMovimiento;
        this.unidad = null;
    }

    public int getCostoMovimiento() {
        return costoMovimiento;
    }

    public int getModificadorDefensa() {
        return modificadorDefensa;
    }

    public int getModificadorMovimiento() {
        return modificadorMovimiento;
    }

    public Unidades getUnidad() {
        return unidad;
    }

    public boolean estaOcupada() {
        return unidad != null;
    }

    public void colocarUnidad(Unidades unidad) {
        if (this.unidad == null) {
            this.unidad = unidad;
        } else {
            throw new IllegalStateException("La casilla ya está ocupada");
        }
    }

    public void removerUnidad() {
        this.unidad = null;
    }

    @Override
    public String toString() {
        return (unidad != null) ? "[" + unidad.getNombre().charAt(0) + "]" : "[ ]";
    }
}
