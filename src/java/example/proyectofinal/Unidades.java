package example.proyectofinal;

public abstract class Unidades {
    int hp;
    int daño;
    int movimiento;
    int rango_ataque;
    String nombre;
    String faccion;

    protected int x;
    protected int y;



    public Unidades(String nombre, int hp, int daño, int movimiento, int rango_ataque, String faccion) {
        this.nombre = nombre;
        this.hp=hp;
        this.daño = daño;
        this.movimiento=movimiento;
        this.faccion=faccion;
        this.rango_ataque=rango_ataque;
    }


    public String getNombre() {
        return nombre;
    }
    void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }
    void setDaño(int daño){
        this.daño=daño;
    }
    public int getDaño(){
        return daño;
    }
    void setMovimiento(int movimiento){
        this.movimiento=movimiento;
    }
    public int getMovimiento(){
        return movimiento;
    }
    String getStats() {
        return ("Nombre: " + nombre + " hp: " +hp + " daño: " + daño + " movimiento: " + movimiento);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPosicion(int x, int y){
        this.x=x;
        this.y=y;
    }
    public String getFaccion() {
        return faccion;
    }


    boolean isVivo(){
        return hp>0;
    }
    boolean isInRango(){
        if (rango_ataque>=movimiento){
            return true;
        }
        return false;
    }
    void eliminarUnidad(){
    }
    public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa){
        if (isInRango()){
            unidadDefensa.setHp(unidadDefensa.hp-unidadAtaque.daño);
            if (unidadDefensa.isVivo()==false){
                unidadDefensa.eliminarUnidad();
            }
            return true;
        }
        return false;
    }
}
