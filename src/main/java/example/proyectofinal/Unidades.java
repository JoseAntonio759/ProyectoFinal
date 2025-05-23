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
        this.movimiento=movimiento;
        this.rango_ataque=rango_ataque;
        this.faccion=faccion;
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
    void setRangoMovimiento(int rangoMovimiento){
        this.movimiento=rangoMovimiento;
    }
    public int getRangoMovimiento(){
        return movimiento;
    }
    void setRango_ataque(int rango_ataque){
        this.rango_ataque=rango_ataque;
    }
    public int getRango_ataque(){
        return rango_ataque;
    }
    String getStats() {
        return ("Nombre: " + nombre + "daño:" +hp + "daño:" + daño + "movimiento:" + movimiento + "rango:" + rango_ataque);
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
