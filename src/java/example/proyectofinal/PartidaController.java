package example.proyectofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PartidaController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Button unidad1;
    @FXML
    private Button unidad2;
    @FXML
    private Button unidad3;
    @FXML
    private Button unidad4;
    private Stage scene;
    private int ancho;
    private int largo;
    private Button selectedUnit;
    private Unidades selectedUnidad;
    private UnidadesController unidadesController;

    public void setDimensiones(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
    }

    public void generarTablero(GridPane originalgridPane) {
        if (gridPane == null) return;

        gridPane.getChildren().setAll(originalgridPane.getChildren());

        gridPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(event -> {
                    if (selectedUnit != null && (button.getText().isEmpty() || button.getText().isBlank())) {
                        int startRow = GridPane.getRowIndex(selectedUnit) != null ? GridPane.getRowIndex(selectedUnit) : 0;
                        int startCol = GridPane.getColumnIndex(selectedUnit) != null ? GridPane.getColumnIndex(selectedUnit) : 0;
                        int endRow = GridPane.getRowIndex(button) != null ? GridPane.getRowIndex(button) : 0;
                        int endCol = GridPane.getColumnIndex(button) != null ? GridPane.getColumnIndex(button) : 0;

                        if (button.getStyle().contains("-fx-background-color: #00ff00") &&
                                isValidMove(startRow, startCol, endRow, endCol)) {
                            button.setText(selectedUnit.getText());
                            selectedUnit.setText("");
                            gridPane.getChildren().forEach(n -> {
                                if (n instanceof Button) {
                                    n.setStyle("");
                                }
                            });
                            selectedUnit = null;
                            selectedUnidad = null;
                        } else {
                            System.out.println("Movimiento no válido o casilla no disponible");
                        }
                    }
                });
            }
        });
    }

    private boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
        if (selectedUnidad == null) return false;

        int distance = Math.abs(endRow - startRow) + Math.abs(endCol - startCol);
        return distance <= selectedUnidad.getMovimiento();
    }

    public void colocarUnidades() {
        if (unidad1 != null) {
            unidad1.setLayoutX(1);
            unidad1.setLayoutY(1);
            unidad1.setPrefSize(100, 100);
        }
        if (unidad2 != null) {
            unidad2.setLayoutX(ancho * 100 - 100);
            unidad2.setLayoutY(1);
            unidad2.setPrefSize(100, 100);
        }

        if (unidad3 != null) {
            unidad3.setLayoutX(1);
            unidad3.setLayoutY(largo * 100 - 100);
            unidad3.setPrefSize(100, 100);
        }

        if (unidad4 != null) {
            unidad4.setLayoutX(ancho * 100 - 100);
            unidad4.setLayoutY(largo * 100 - 100);
            unidad4.setPrefSize(100, 100);
        }
    }

    private void abrirVentanaUnidades() {
        try {
            FXMLLoader loader = new FXMLLoader(Menu.class.getResource("unidades.fxml"));
            Scene scene = new Scene(loader.load(), 800, 500);
            Stage stage = new Stage();

            unidadesController = loader.getController();
            if (selectedUnidad != null) {

            }

            stage.setTitle("Informacion");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void marcarCasillasMovimiento(Button unit, Unidades unidad) {
        if (gridPane == null) return;

        gridPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                node.setStyle("");
            }
        });

        if (selectedUnit == unit) {
            selectedUnit = null;
            selectedUnidad = null;
            return;
        }

        selectedUnit = unit;
        selectedUnidad = unidad;

        int ach = (GridPane.getRowIndex(unit) == null) ? 0 : GridPane.getRowIndex(unit);
        int col = (GridPane.getColumnIndex(unit) == null) ? 0 : GridPane.getColumnIndex(unit);
        System.out.println(ach + " " + col);

        marcarCasilla(ach - 1, col);
        marcarCasilla(ach + 1, col);
        marcarCasilla(ach, col - 1);
        marcarCasilla(ach, col + 1);
    }

    private void marcarCasilla(int row, int col) {
        if (gridPane == null) return;

        gridPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Integer nodeRow = GridPane.getRowIndex(node);
                Integer nodeCol = GridPane.getColumnIndex(node);

                if ((nodeRow != null && nodeRow == row) &&
                        (nodeCol != null && nodeCol == col)) {
                    node.setStyle("-fx-background-color: #00ff00;");
                }
            }
        });
    }

    @FXML
    public void initialize() {
        ProgramaTablero.Matematico mat = new ProgramaTablero.Matematico();
        ProgramaTablero.Filosofo fil = new ProgramaTablero.Filosofo();
        ProgramaTablero.Medico med = new ProgramaTablero.Medico();
        ProgramaTablero.Historiador his = new ProgramaTablero.Historiador();

        if (unidad1 != null) {
            unidad1.setOnAction(event -> {
                marcarCasillasMovimiento(unidad1, mat);
                abrirVentanaUnidades();
            });
            unidad1.setText("MAT");
        }

        if (unidad2 != null) {
            unidad2.setOnAction(event -> {
                marcarCasillasMovimiento(unidad2, fil);
                abrirVentanaUnidades();
            });
            unidad2.setText("FIL");
        }

        if (unidad3 != null) {
            unidad3.setOnAction(event -> {
                marcarCasillasMovimiento(unidad3, med);
                abrirVentanaUnidades();
            });
            unidad3.setText("MED");
        }

        if (unidad4 != null) {
            unidad4.setOnAction(event -> {
                marcarCasillasMovimiento(unidad4, his);
                abrirVentanaUnidades();
            });
            unidad4.setText("HIS");
        }

        colocarUnidades();
    }
}


    class ProgramaTablero {
        private static int rondas = 0;

        protected class Tablero {
            private example.proyectofinal.TableroController ancho;
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

