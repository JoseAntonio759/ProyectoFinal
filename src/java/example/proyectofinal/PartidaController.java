package example.proyectofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class PartidaController {

    @FXML
    private GridPane gridPane;
    private int ancho;
    private int largo;
    private Button selectedUnit;
    private Unidades selectedUnidad;
    private UnidadesController unidadesController;

    public void setDimensiones(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        inicializarGridPane();
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
        if (selectedUnit == null) {
            if (!button.getText().isEmpty() && !(button.getText().length()>3)) {
                selectedUnit = button;
                marcarCasillasMovimientoPosibles(button);
                if (event.getClickCount() == 2) {
                    abrirVentanaUnidades(traductorUnidades(button));
                }
            }
        } else {
            if (button.getStyle().contains("-fx-background-color: #00ff00")) {
                button.setText(selectedUnit.getText());
                selectedUnit.setText("");
                limpiarMarcas();
                selectedUnit = null;
            } else if (!button.getText().isEmpty() && !(button.getText().length()>3)) {
                selectedUnit = button;
                marcarCasillasMovimientoPosibles(button);
                if (event.getClickCount() == 2) {
                    abrirVentanaUnidades(traductorUnidades(button));
                }
            }
        }
    }
//comentario para commit
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
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    marcarCasillaValida(row + i, col + j); 
                }
            }
        }
    }

    private void marcarCasillaValida(int row, int col) {
        if (row >= 0 && row < largo && col >= 0 && col < ancho) {
            Button targetButton = getButtonAt(row, col);
            if (targetButton != null && targetButton.getText().isEmpty()) {
                targetButton.setStyle("-fx-background-color: #00ff00;");
            }
        }
    }

    private Button getButtonAt(int row, int col) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
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
    }


    private void abrirVentanaUnidades(Unidades unidad) {
        if (unidad != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Menu.class.getResource("unidades.fxml"));
                Scene scene = new Scene(loader.load(), 800, 500);
                Stage stage = new Stage();

                unidadesController = loader.getController();
                if (selectedUnit != null ) {
                    stage.setTitle("Informacion de Unidad");
                    stage.setScene(scene);
                    stage.show();
                    unidadesController.showStats(unidad);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private Unidades traductorUnidades(Button button) {
        if (button.getText()== "MAT"){Unidades unidad = new ProgramaTablero.Matematico();
        return unidad;}
        if (button.getText()== "HIS"){Unidades unidad = new ProgramaTablero.Historiador();
        return unidad;}
        if (button.getText()== "POE"){Unidades unidad = new ProgramaTablero.Poeta();
        return unidad;}
        if (button.getText()== "MED"){Unidades unidad = new ProgramaTablero.Medico();
        return unidad;}
        else return null;
    }
}

    


    class ProgramaTablero {
        private static int rondas = 0;

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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();
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
                    rondas++;
                    aparicion_personaje();

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

        protected static void aparicion_personaje() {
            int alea = obtenerNumeroDel1Arondas();
            if (alea == rondas) {
                int numero = obtenerNumeroDel1Al5();
                if (numero == 1) {
                    Matematico matematico = new Matematico();
                    Filosofo filosofo = new Filosofo();
                }
                if (numero == 2) {
                    Medico medico = new Medico();
                    Historiador historiador = new Historiador();
                }
                if (numero == 3) {
                    Filologo filologo = new Filologo();
                    Arquitecto arquitecto = new Arquitecto();
                }
                if (numero == 4) {
                    Poeta poeta = new Poeta();
                    Fisico fisico = new Fisico();
                }
                if (numero == 5) {
                    Ingeniero ingeniero = new Ingeniero();
                    Periodista periodista = new Periodista();
                }
            }

        }

        protected void aparicion_personajeInicial() {
            if (0 == rondas) {
                int numero = obtenerNumeroDel1Al5();
                if (numero == 1) {
                    Matematico matematico = new Matematico();
                    Filosofo filosofo = new Filosofo();
                    Medico medico = new Medico();
                    Historiador historiador = new Historiador();
                }
                if (numero == 2) {
                    Medico medico = new Medico();
                    Historiador historiador = new Historiador();
                    Filologo filologo = new Filologo();
                    Arquitecto arquitecto = new Arquitecto();
                }
                if (numero == 3) {
                    Filologo filologo = new Filologo();
                    Arquitecto arquitecto = new Arquitecto();
                    Poeta poeta = new Poeta();
                    Fisico fisico = new Fisico();
                }
                if (numero == 4) {
                    Poeta poeta = new Poeta();
                    Fisico fisico = new Fisico();
                    Ingeniero ingeniero = new Ingeniero();
                    Periodista periodista = new Periodista();
                }
                if (numero == 5) {
                    Ingeniero ingeniero = new Ingeniero();
                    Periodista periodista = new Periodista();
                    Matematico matematico = new Matematico();
                    Filosofo filosofo = new Filosofo();
                }
            }
        }
    }


