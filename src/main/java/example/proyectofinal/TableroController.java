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




    protected IntegerProperty medidaAncho = new SimpleIntegerProperty(25);
    protected IntegerProperty medidaLargo = new SimpleIntegerProperty(25);



    public TableroController() {
    }






    @FXML
    protected void initialize() {
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

        System.out.println("aaaa" + ancho + "bbbb" + largo );



        Grid.getChildren().clear();
        for (int fila = 0; fila < ancho; fila++) {
            for (int columna = 0; columna < largo; columna++) {
                int numero = (int)(Math.random()*30);
                if(numero == 0) {
                    Label casilla = new Label("+1 defensa");
                    casilla.setMinSize(100, 100);
                    casilla.setAlignment(Pos.CENTER);
                    casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    Grid.add(casilla, fila, columna);
                }
                if(numero == 1){
                        Label casilla = new Label("+1 ataque");
                        casilla.setMinSize(100, 100); // Tamaño mínimo para visualización
                        casilla.setAlignment(Pos.CENTER);
                        casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                        Grid.add(casilla, fila, columna);
                }
                if(numero == 2){
                    Label casilla = new Label("+1 movimiento");
                    casilla.setMinSize(100, 100); // Tamaño mínimo para visualización
                    casilla.setAlignment(Pos.CENTER);
                    casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    Grid.add(casilla, fila, columna);
                }
                if(numero == 3){
                    Label casilla = new Label("-1 defensa");
                    casilla.setMinSize(100, 100); // Tamaño mínimo para visualización
                    casilla.setAlignment(Pos.CENTER);
                    casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    Grid.add(casilla, fila, columna);
                }
                if(numero == 4){
                    Label casilla = new Label("-1 ataque");
                    casilla.setMinSize(100, 100); // Tamaño mínimo para visualización
                    casilla.setAlignment(Pos.CENTER);
                    casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    Grid.add(casilla, fila, columna);
                }
                if(numero == 5){
                    Label casilla = new Label("-1 movimiento");
                    casilla.setMinSize(100, 100); // Tamaño mínimo para visualización
                    casilla.setAlignment(Pos.CENTER);
                    casilla.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                    Grid.add(casilla, fila, columna);
                }

                else {
                    Label label = new Label();
                    label.setMinSize(100, 100);
                    label.setStyle("-fx-border-color: #000000;-fx-alignment: center");
                    Grid.add(label, fila, columna);

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

        try{



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



