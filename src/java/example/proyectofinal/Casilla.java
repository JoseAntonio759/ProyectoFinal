package example.proyectofinal;


import example.proyectofinal.Unidades;

public class Casilla {
    private final int costoMovimiento;
    private final int modificadorDefensa;
    private final int modificadorMovimiento;
    private final int modificadorDaño;
    private Unidades unidad;
    private int x;
    private int y;

    public Casilla(int costoMovimiento, int modificadorDefensa, int modificadorMovimiento, int modificadorDaño) {
        this.costoMovimiento = costoMovimiento;
        this.modificadorDefensa = modificadorDefensa;
        this.modificadorMovimiento = modificadorMovimiento;
        this.modificadorDaño = modificadorDaño;
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

    public int getModificadorDaño() {
        return modificadorDaño;
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
            unidad.setHp(unidad.getHp() + (getModificadorDefensa()));
            unidad.setMovimiento(unidad.getMovimiento() + (getModificadorMovimiento()));
            unidad.setDaño(unidad.getDaño() + getModificadorDaño());
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

