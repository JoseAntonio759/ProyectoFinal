package example.proyectofinal;

import example.proyectofinal.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UnidadesController {

    @FXML
    private Button salir;

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

                actualizarBoton(fila, col);
            }
        }
    }

    private void manejarClick(int fila, int col) {
        Casilla casilla = tablero.getCasilla(fila, col);
        if (casilla.estaOcupada()) {
            Unidades u = casilla.getUnidad();
            System.out.println("Unidad: " + u.getNombre() + " | HP: " + u.getHp());
        } else {
            System.out.println("Casilla vacía");
        }
    }

    private void actualizarBoton(int fila, int col) {
        Unidades u = tablero.getCasilla(fila, col).getUnidad();
        botones[fila][col].setText((u != null) ? String.valueOf(u.getNombre().charAt(0)) : " ");
    }
    @FXML
    private void clickSalir() {
        Stage ventanaActual = (Stage) salir.getScene().getWindow();
        ventanaActual.close();
    }
}

