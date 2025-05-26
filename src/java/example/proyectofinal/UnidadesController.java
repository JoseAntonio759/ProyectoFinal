package example.proyectofinal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UnidadesController {

    @FXML
    private Button salir;
    @FXML
    private Label hp;
    @FXML
    private Label nombre;
    @FXML
    private Label daño;
    @FXML
    private Label movimiento;

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
                manejarClick(fila, col);
            }
        }

        // Inicializar labels
        hp.setText("HP: --");
        daño.setText("Daño: --");
        nombre.setText("Nombre: --");
        movimiento.setText("Movimiento: --");
    }

    private void manejarClick(int fila, int col) {
        Casilla casilla = tablero.getCasilla(fila, col);
        if (casilla.estaOcupada()) {
            Unidades u = casilla.getUnidad();
            hp.setText("HP: " + u.getHp());
            daño.setText("Daño: " + u.getDaño());
            nombre.setText("Nombre: " + u.getNombre());
            movimiento.setText("Movimiento: " + u.getMovimiento());
        } else {
            hp.setText("HP: --");
            daño.setText("Daño: --");
            nombre.setText("Nombre: --");
            movimiento.setText("Movimiento: --");
        }
    }
    public void showStats(Unidades u) {
        hp.setText("HP: " + u.getHp());
        daño.setText("Daño: " + u.getDaño());
        nombre.setText("Nombre: " + u.getNombre());
        movimiento.setText("Movimiento: " + u.getMovimiento());

    }
    @FXML
    private void clickSalir() {
        Stage ventanaActual = (Stage) salir.getScene().getWindow();
        ventanaActual.close();
    }
}