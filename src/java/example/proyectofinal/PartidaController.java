package example.proyectofinal;

import example.proyectofinal.IA.IAController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class PartidaController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label Acciones;
    @FXML
    private Label Turnos;
    private int ancho;
    private int largo;
    private Button selectedUnit;
    private Unidades selectedUnidad;
    private UnidadesController unidadesController;
    private int contadorTurnos = 0;
    private Unidades matematico = new ProgramaTablero.Matematico();
    private Unidades medico = new ProgramaTablero.Medico();
    private Unidades poeta = new ProgramaTablero.Poeta();
    private Unidades historiador = new ProgramaTablero.Historiador();


    @FXML
    public void initialize() {
        contarUnidades();
    }

    public void setDimensiones(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        inicializarGridPane();
        if (Turnos != null)
            Turnos.setText("Turno: " + contadorTurnos);
    }

    private void contarUnidades() {
        if (gridPane != null) {
            int total = 0;

            for (Node node : gridPane.getChildren()) {
                if (node instanceof Button) {
                    Button button = (Button) node;
                    String texto = button.getText();

                    if (texto.equals("MAT")) {
                        total++;
                    } else if (texto.equals("MED")) {
                        total++;
                    } else if (texto.equals("POE")) {
                        total++;
                    } else if (texto.equals("HIS")) {
                        total++;
                    }
                }
            }

            System.out.println("Total unidades: " + total);
        }
    }

    private void inicializarGridPane() {
        if (gridPane != null) {
            gridPane.getChildren().clear();
            for (int i = 0; i < ancho; i++) {
                for (int j = 0; j < largo; j++) {
                    Button button = new Button();
                    button.setPrefSize(100, 100);
                    button.setOnMouseClicked(e -> Control(button, e));
                    gridPane.add(button, i, j);
                }
            }
        }
    }



    private void Control(Button button, MouseEvent event) {
        String faccionTurno = (contadorTurnos % 2 == 0) ? "Ciencias" : "Letras";

        if (selectedUnit == null) {
            if (button.getUserData() instanceof Unidades tempUnidad && tempUnidad.getFaccion().equals(faccionTurno)) {
                selectedUnit = button;
                selectedUnidad = tempUnidad;
                marcarCasillasMovimientoPosibles(button);
                if (event.getClickCount() == 2) {
                    abrirVentanaUnidades();
                }
            }
        } else {
            if (button.getStyle().contains("-fx-background-color: #ff0000") && button.getUserData() instanceof Unidades targetUnidad) {
                targetUnidad.setHp(targetUnidad.getHp() - selectedUnidad.getDaño());
                if (Acciones != null) {
                    Acciones.setText(selectedUnidad.getNombre() + " ha atacado a " + targetUnidad.getNombre() +
                            " con " + selectedUnidad.getDaño() + " puntos de daño.");
                }
                if (targetUnidad.getHp() <= 0) {
                    button.setText("");
                    button.setUserData(null);
                    button.setStyle("");
                    if (Acciones != null) {
                        Acciones.setText(selectedUnidad.getNombre() + " ha matado a " + targetUnidad.getNombre());
                    }
                    if (targetUnidad.getNombre().equals("Filosofo")) {
                        ProgramaTablero.incRondas();
                    }
                    if (targetUnidad.getNombre().equals("Poeta")) {
                        ProgramaTablero.incRondas();
                    }
                }
                selectedUnit = null;
                selectedUnidad = null;
                contadorTurnos++;
                Turnos.setText("Turno: " + contadorTurnos);
                aparicionPersonaje();
                limpiarMarcas();
                contarUnidades();
                return;
            }

            if (button.getStyle().contains("-fx-background-color: #00ff00") || button.getStyle().contains("-fx-background-color: #ffff00")) {
                String powerUp = button.getText();
                button.setText(selectedUnit.getText());
                button.setUserData(selectedUnit.getUserData());
                selectedUnit.setText("");
                selectedUnit.setUserData(null);
                limpiarMarcas();

                if (powerUp.equals("+1ATQ")){
                    selectedUnidad.setDaño(selectedUnidad.getDaño() + 1);
                    if (Acciones != null) {
                        Acciones.setText(selectedUnidad.getNombre() + " ha recibido un powerUp de +1ATQ");
                    }
                }
                else if (powerUp.equals("-1ATQ")){ selectedUnidad.setDaño(selectedUnidad.getDaño() - 1)
                ; if (Acciones != null) {
                    Acciones.setText(selectedUnidad.getNombre() + " ha recibido un powerUp de -1ATQ");
                }
                }
                else if (powerUp.equals("+1HP")){ selectedUnidad.setHp(selectedUnidad.getHp() + 1)
                ; if (Acciones != null) {
                    Acciones.setText(selectedUnidad.getNombre() + " ha recibido un powerUp de +1HP");
                }
                }
                else if (powerUp.equals("-1HP")){ selectedUnidad.setHp(selectedUnidad.getHp() - 1)
                ; if (Acciones != null) {
                    Acciones.setText(selectedUnidad.getNombre() + " ha recibido un powerUp de -1HP");
                }
                }
                else if (powerUp.equals("+1MOV")){ selectedUnidad.setMovimiento(selectedUnidad.getMovimiento() + 1)
                ; if (Acciones != null) {
                    Acciones.setText(selectedUnidad.getNombre() + " ha recibido un powerUp de +1MOV");
                }
                }
                else if (powerUp.equals("-1MOV")){
                    selectedUnidad.setMovimiento(selectedUnidad.getMovimiento() - 1);
                    if (Acciones != null) {
                        Acciones.setText(selectedUnidad.getNombre() + " ha recibido un powerUp de -1MOV");
                    }
                }

                selectedUnit = null;
                selectedUnidad = null;
                contadorTurnos++;
                Turnos.setText("Turno: " + contadorTurnos);
                aparicionPersonaje();
                contarUnidades();
            } else if (button.getUserData() instanceof Unidades tempUnidad && tempUnidad.getFaccion().equals(faccionTurno)) {
                selectedUnit = button;
                selectedUnidad = tempUnidad;
                marcarCasillasMovimientoPosibles(button);
                if (event.getClickCount() == 2) {
                    abrirVentanaUnidades();
                }
            }
        }
    }


    private void marcarCasillasMovimientoPosibles(Button unit) {
        limpiarMarcas();

        int row;
        if (GridPane.getRowIndex(unit) != null) {
            row = GridPane.getRowIndex(unit);
        } else {
            row = 0;
        }
        int col;
        if (GridPane.getColumnIndex(unit) != null) {
            col = GridPane.getColumnIndex(unit);
        } else {
            col = 0;
        }

        int rango = selectedUnidad.getMovimiento();

        for (int i = -rango; i <= rango; i++) {
            for (int j = -rango; j <= rango; j++) {
                if (!(i == 0 && j == 0)) {
                    marcarCasillaValida(row + i, col + j);
                }
            }
        }
    }

    private void marcarCasillaValida(int row, int col) {
        if (row >= 0 && row < largo && col >= 0 && col < ancho) {
            Button targetButton = getButtonAt(row, col);
            if (targetButton != null) {
                String texto = targetButton.getText();
                if (texto.equals("+1MOV") || texto.equals("-1MOV") ||
                        texto.equals("+1ATQ") || texto.equals("-1ATQ") ||
                        texto.equals("+1HP") || texto.equals("-1HP")) {
                    targetButton.setStyle("-fx-background-color: #ffff00;");
                } else if (texto.isEmpty()) {
                    targetButton.setStyle("-fx-background-color: #00ff00;");
                } else {
                    if (targetButton.getUserData() instanceof Unidades targetUnidad) {
                        if (!targetUnidad.getFaccion().equals(selectedUnidad.getFaccion())) {
                            targetButton.setStyle("-fx-background-color: #ff0000;");
                        }
                    }
                }
            }
        }
    }
    private Button getButtonAt(int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button &&
                    GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) == col) {
                return (Button) node;
            }
        }
        return null;
    }

    private void limpiarMarcas() {
        gridPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                node.setStyle("");
            }
        });
    }

    public void generarTablero(GridPane originalgridPane) {
        if (gridPane == null) return;

        gridPane.getChildren().setAll(originalgridPane.getChildren());
        gridPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnMouseClicked(e -> Control(button, e));
            }
        });
        contarUnidades();
    }

    private void abrirVentanaUnidades() {
        try {
            FXMLLoader loader = new FXMLLoader(Menu.class.getResource("unidades.fxml"));
            Scene scene = new Scene(loader.load(), 800, 500);
            Stage stage = new Stage();

            unidadesController = loader.getController();
            if (selectedUnit != null) {
                stage.setTitle("Informacion de Unidad");
                stage.setScene(scene);
                stage.show();
                unidadesController.showStats(selectedUnidad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int contarMatematicos() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("MAT".equals(texto)) {
                    contador++;
                }
            }
        }

        return contador;
    }
    private int contarMedicos() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("MED".equals(texto)) {
                    contador++;
                }
            }
        } return contador;
    }
    private int contarPoetas() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("POE".equals(texto)) {
                    contador++;
                }
            }
        } return contador;
    }
    private int contarHistoriadores() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("HIS".equals(texto)) {
                    contador++;
                }
            }
        } return contador;
    }
    private int contarArquitectos() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("ARQ".equals(texto)) {
                    contador++;
                }
            }
        } return contador;
    }
    private int contarFisicos() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("FIS".equals(texto)) {
                    contador++;
                }
            }
        } return contador;
    }
    private int contarIngenieros() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("ING".equals(texto)) {
                    contador++;
                }
            }
        } return contador;
    }
    private int contarFilosofos() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("FIL".equals(texto)) {
                    contador++;
                }
            }
        }
        return contador;}
    private int contarPeriodistas() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("PER".equals(texto)) {
                    contador++;
                }
            }
        }
        return contador;}
    private int contarFilologo() {
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("FI".equals(texto)) {
                    contador++;
                }
            }
        }
        return contador;}
    private int contarPoeta(){
        int contador = 0;

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button boton) {
                String texto = boton.getText();
                if ("POE".equals(texto)) {
                    contador++;
                }
            }
        }
        return contador;}

    private void aparicionPersonaje() {
        if (contadorTurnos == 0) return;

        int probabilidad = Math.max(10, 100 / contadorTurnos);
        int random = (int) (Math.random() * 100);
        if (random >= probabilidad) return;

        int fila = (int) (Math.random() * ancho);
        int columna = (int) (Math.random() * largo);

        Button target = getButtonAt(fila, columna);

        if (target != null && target.getText().isEmpty()) {
            int tipo = (int) (Math.random() * 5);
            Unidades nuevaUnidad = null;
            String texto;

            if (contadorTurnos % 2 == 0) {
                // Turno de Ciencias
                switch (tipo) {
                    case 0 -> { if (contarMatematicos()<1) nuevaUnidad = new ProgramaTablero.Matematico(); texto = "MAT";}
                    case 1 -> { if (contarIngenieros()<1)nuevaUnidad = new ProgramaTablero.Ingeniero(); texto = "ING"; }
                    case 2 -> { if (contarFisicos()<1) nuevaUnidad = new ProgramaTablero.Fisico(); texto = "FIS"; }
                    case 3 -> { if (contarMedicos()<1)nuevaUnidad = new ProgramaTablero.Medico(); texto = "MED"; }
                    case 4 -> { if (contarArquitectos()<1)nuevaUnidad = new ProgramaTablero.Arquitecto(); texto = "ARQ"; }
                    default -> { if (contarMatematicos()<1)nuevaUnidad = new ProgramaTablero.Matematico(); texto = "MAT"; }
                }
            } else {
                // Turno de Letras
                switch (tipo) {
                    case 0 -> { if (contarHistoriadores()<1)nuevaUnidad = new ProgramaTablero.Historiador(); texto = "HIS"; }
                    case 1 -> { if (contarFilosofos()<1) nuevaUnidad = new ProgramaTablero.Filosofo(); texto = "FIL"; }
                    case 2 -> {  if (contarPoeta()<1)nuevaUnidad = new ProgramaTablero.Poeta(); texto = "POE"; }
                    case 3 -> {  if (contarPeriodistas()<1)nuevaUnidad = new ProgramaTablero.Periodista(); texto = "PER"; }
                    case 4 -> { if (contarFilologo()<1) nuevaUnidad = new ProgramaTablero.Filologo(); texto = "FI"; }
                    default -> { if (contarHistoriadores()<1) nuevaUnidad = new ProgramaTablero.Historiador(); texto = "HIS"; }
                }
            }

            nuevaUnidad.setPosicion(fila, columna);
            target.setText(texto);
            target.setUserData(nuevaUnidad);

        }
    }

}




class ProgramaTablero {
    private static int rondas = 0;
    protected static void incRondas() {
        rondas++;
    }

    protected class Tablero {
        private TableroController ancho;
        private TableroController largo;
        private Casilla[][] casillas;
        private int largo1;
        private int ancho1;


        public Tablero() {
            inicializarTablero();
        }

        protected void inicializarTablero() {
            casillas = new Casilla[largo1][ancho1];
            for (int i = 1; i < largo1; i++) {
                for (int j = 1; j < ancho1; j++) {
                    casillas[i][j] = new Casilla();
                }
            }
        }

        protected Casilla obtenerCasilla(int fila, int columna) {
            if (fila >= 0 && fila < largo1 && columna >= 0 && columna < ancho1) {
                return casillas[fila][columna];
            }
            return null;
        }

        protected class Casilla {
            private boolean ocupada;

            protected Casilla() {
                this.ocupada = false;
            }

            protected boolean isOcupada() {
                return ocupada;
            }

            protected void setPropiedad(boolean trueofalse) {
                this.ocupada = trueofalse;
            }
        }
    }

    protected static class Matematico extends Unidades {
        protected Matematico() {
            super("Matematico", 90, 21, 2, 3, "Ciencias");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Ingeniero extends Unidades {
        protected Ingeniero() {
            super("Ingeniero", 70, 32, 4, 1, "Ciencias");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Medico extends Unidades {
        protected Medico() {
            super("Medico", 120, 17, 2, 3, "Ciencias");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {

                return true;
            }
            return false;
        }
    }

    protected static class Arquitecto extends Unidades {
        protected Arquitecto() {
            super("Arquitecto", 80, 27, 3, 2, "Ciencias");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Fisico extends Unidades {
        protected Fisico() {
            super("Fisico", 100, 15, 1, 1, "Ciencias");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Filologo extends Unidades {
        protected Filologo() {
            super("Filologo", 80, 27, 3, 2, "Letras");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Poeta extends Unidades {
        protected Poeta() {
            super("Poeta", 75, 22, 3, 4, "Letras");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Historiador extends Unidades {
        protected Historiador() {
            super("Historiador", 120, 17, 2, 2, "Letras");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Periodista extends Unidades {
        protected Periodista() {
            super("Periodista", 70, 32, 4, 1, "Letras");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {
                return true;
            }
            return false;
        }
    }

    protected static class Filosofo extends Unidades {
        protected Filosofo() {
            super("Filosofo", 90, 21, 2, 3, "Letras");
        }

        @Override
        protected String getStats() {
            return super.getStats();
        }

        protected boolean isVivo() {
            return super.isVivo();
        }

        protected void setHp(int hp) {
            super.setHp(hp);
        }

        protected void eliminarUnidad() {
            super.eliminarUnidad();
        }

        protected boolean isInRango() {
            return super.isInRango();
        }

        public boolean ataque(Unidades unidadAtaque, Unidades unidadDefensa) {
            if (super.ataque(unidadAtaque, unidadDefensa) == true) {

                return true;
            }
            return false;
        }
    }

    public static int obtenerNumeroDel1Al5() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int obtenerNumeroDel1Arondas() {
        return (int) (Math.random() * rondas) + 1;
    }



}



