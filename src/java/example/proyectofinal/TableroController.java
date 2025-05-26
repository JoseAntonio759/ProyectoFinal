package example.proyectofinal;


import example.proyectofinal.IA.IAController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TableroController {

    @FXML
    private Button volveratras;
    @FXML
    private Button siguiente;
    @FXML
    private Slider anchoSlider;
    @FXML
    private Slider largoSlider;
    @FXML
    private Label valorAncho;
    @FXML
    private Label valorLargo;
    @FXML
    private GridPane Grid;

    protected IntegerProperty medidaAncho = new SimpleIntegerProperty(10);
    protected IntegerProperty medidaLargo = new SimpleIntegerProperty(10);


    public TableroController() {
    }


    @FXML
    protected void initialize() {
        anchoSlider.setMin(5);
        anchoSlider.setMax(20);
        largoSlider.setMin(5);
        largoSlider.setMax(20);

        anchoSlider.valueProperty().bindBidirectional(medidaAncho);
        largoSlider.valueProperty().bindBidirectional(medidaLargo);
        valorAncho.textProperty().bind(medidaAncho.asString());
        valorLargo.textProperty().bind(medidaLargo.asString());
    }


    @FXML
    protected void clickVolver() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("menu.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            stage.setTitle("Pantalla Inicio");
            stage.setScene(scene);
            stage.show();
            Stage ventanaActual = (Stage) volveratras.getScene().getWindow();
            ventanaActual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void clickSiguiente() {
        int ancho = medidaAncho.get();
        int largo = medidaLargo.get();

        double tamañoAncho = Math.min(100, 1200 / ancho);
        double tamañoAlto = Math.min(100, 1000.0 / largo);

        Grid.getChildren().clear();
        for (int fila = 0; fila < ancho; fila++) {
            for (int columna = 0; columna < largo; columna++) {

                if (fila == 0 && columna == 0) {
                    Button matButton = new Button("MAT");
                    matButton.setMinSize(tamañoAncho, tamañoAlto);
                    matButton.setMaxSize(tamañoAncho, tamañoAlto);
                    matButton.setAlignment(Pos.CENTER);
                    matButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    ProgramaTablero.Matematico matematico = new ProgramaTablero.Matematico();
                    matButton.setUserData(matematico);
                    Grid.add(matButton, fila, columna);
                    continue;
                }
                if (fila == 0 && columna == largo - 1) {
                    Button hisButton = new Button("HIS");
                    hisButton.setMinSize(tamañoAncho, tamañoAlto);
                    hisButton.setMaxSize(tamañoAncho, tamañoAlto);
                    hisButton.setAlignment(Pos.CENTER);
                    hisButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    ProgramaTablero.Historiador historiador = new ProgramaTablero.Historiador();
                    hisButton.setUserData(historiador);
                    Grid.add(hisButton, fila, columna);
                    continue;
                }

                if (fila == ancho - 1 && columna == 0) {
                    Button poeButton = new Button("POE");
                    poeButton.setMinSize(tamañoAncho, tamañoAlto);
                    poeButton.setMaxSize(tamañoAncho, tamañoAlto);
                    poeButton.setAlignment(Pos.CENTER);
                    poeButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    ProgramaTablero.Poeta poeta = new ProgramaTablero.Poeta();
                    poeButton.setUserData(poeta);
                    Grid.add(poeButton, fila, columna);
                    continue;
                }

                if (fila == ancho - 1 && columna == largo - 1) {
                    Button medButton = new Button("MED");
                    medButton.setMinSize(tamañoAncho, tamañoAlto);
                    medButton.setMaxSize(tamañoAncho, tamañoAlto);
                    medButton.setAlignment(Pos.CENTER);
                    medButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    ProgramaTablero.Medico medico = new ProgramaTablero.Medico();
                    medButton.setUserData(medico);
                    Grid.add(medButton, fila, columna);
                } else {
                    int numero1 = (int) (Math.random() * 30);
                    if (numero1 == 0) {
                        Button casilla = new Button("+1MOV");
                        casilla.setMinSize(tamañoAncho, tamañoAlto);
                        casilla.setMaxSize(tamañoAncho, tamañoAlto);
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                    } else if (numero1 == 1) {
                        Button casilla = new Button("+1ATQ");
                        casilla.setMinSize(tamañoAncho, tamañoAlto);
                        casilla.setMaxSize(tamañoAncho, tamañoAlto);
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                    } else if (numero1 == 2) {
                        Button casilla = new Button("+1HP");
                        casilla.setMinSize(tamañoAncho, tamañoAlto);
                        casilla.setMaxSize(tamañoAncho, tamañoAlto);
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                    } else if (numero1 == 3) {
                        Button casilla = new Button("-1MOV");
                        casilla.setMinSize(tamañoAncho, tamañoAlto);
                        casilla.setMaxSize(tamañoAncho, tamañoAlto);
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                    } else if (numero1 == 4) {
                        Button casilla = new Button("-1ATQ");
                        casilla.setMinSize(tamañoAncho, tamañoAlto);
                        casilla.setMaxSize(tamañoAncho, tamañoAlto);
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                    } else if (numero1 == 5) {
                        Button casilla = new Button("-1HP");
                        casilla.setMinSize(tamañoAncho, tamañoAlto);
                        casilla.setMaxSize(tamañoAncho, tamañoAlto);
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                    } else {
                        Button casilla = new Button("");
                        casilla.setMinSize(tamañoAncho, tamañoAlto);
                        casilla.setMaxSize(tamañoAncho, tamañoAlto);
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                    }
                }
            }
        }

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("partida.fxml"));
        IAController IAController = new IAController();
        IAController.setDimensiones(ancho, largo);
        fxmlLoader.setController(IAController);

        PartidaController partidaController = new PartidaController();
        partidaController.setDimensiones(ancho, largo);
        fxmlLoader.setController(partidaController);

        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
            stage.setTitle("Partida");
            stage.setScene(scene);
            partidaController.generarTablero(Grid);

            stage.show();
            Stage ventanaActual = (Stage) siguiente.getScene().getWindow();
            ventanaActual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}