package example.proyectofinal;

abstract class Unidades {
    int hp;
    int daño;
    int movimiento;
    int rango_ataque;
    String nombre;

    void setHp(int hp) {
        this.hp = hp;
    }
    void setDaño(int daño){
        this.daño=daño;
    }
    void setMovimiento(int movimiento){
        this.movimiento=movimiento;
    }
    void setRango_ataque(int rango_ataque){
        this.rango_ataque=rango_ataque;
    }
    String getStats() {
        return ("Nombre: " + nombre + "daño:" +hp + "daño:" + daño + "movimiento:" + movimiento + "rango:" + rango_ataque);
    }
    public Unidades(String nombre, int hp, int daño, int movimiento, int rango_ataque) {
            this.nombre = nombre;
            this.hp=hp;
            this.movimiento=movimiento;
            this.rango_ataque=rango_ataque;
    }
    boolean isVivo(){
        return hp>0;
    }
    boolean isInRango(){
    }
    void eliminarUnidad(){
    }
    boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa){
        if (isInRango()){
            unidadDefensa.setHp(unidadDefensa.hp-unidadAtaque.daño);
            if (unidadDefensa.isVivo()==false){
                unidadDefensa.eliminarUnidad();
            }
            return true;
        }
        return false;//djñsd
    }
}
