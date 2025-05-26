package example.proyectofinal;

import example.proyectofinal.Casilla;
import example.proyectofinal.Tablero;
import example.proyectofinal.Unidades;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UnidadesController {

    @FXML
    private Button salir;
    @FXML
    private Label nombre;
    public Label rango_ataque;
    public Label movimiento;
    public Label hp;
    public Label daño;

    @FXML
    private Button moverse;
    @FXML
    private Button atacar;


    private final int filas = 10;
    private final int columnas = 10;
    private Tablero tablero;
    private Button[][] botones;

    @FXML
    public void initialize() {
        tablero = new Tablero(filas, columnas);
        botones = new Button[filas][columnas];

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                Button btn = new Button(" ");
                btn.setMinSize(40, 40);

                int f = fila;
                int c = col;
                btn.setOnAction(e -> manejarClick(f, c));

                botones[fila][col] = btn;
            }
        }
    }

    private void manejarClick(int fila, int col) {
        Casilla casilla = tablero.getCasilla(fila, col);
        if (casilla.estaOcupada()) {
            Unidades u = casilla.getUnidad();
        }
    }


    @FXML
    private void clickSalir() {
        Stage ventanaActual = (Stage) salir.getScene().getWindow();
        ventanaActual.close();
    }
    @FXML
    public  void showStats(Unidades unidad) {

        hp.setText("Salud: " + unidad.getHp());
        daño.setText("Daño: " +  unidad.getDaño());
        movimiento.setText("Movimiento: " + unidad.getMovimiento());
        rango_ataque.setText("Rango ataque: " + unidad.getRango_ataque());
        nombre.setText("Unidad: "+ unidad.getNombre());
    }




}
