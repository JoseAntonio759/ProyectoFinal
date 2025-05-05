package example.proyectofinal;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;


public class TableroController {



    @FXML
    private Button volveratras;
    @FXML
    private Button siguiente;
    @FXML
    private Slider ancho;
    @FXML
    private Slider largo;
    @FXML
    private Label valorAncho;
    @FXML
    private Label valorLargo;

    protected IntegerProperty medidaAncho = new SimpleIntegerProperty(25);
    protected IntegerProperty medidaLargo = new SimpleIntegerProperty(25);




    @FXML
    protected void initialize() {
        ancho.valueProperty().bindBidirectional(medidaAncho);
        largo.valueProperty().bindBidirectional(medidaLargo);
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
        Spinner<Integer> spinnerA = new Spinner<>(1,100,5);
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("partida.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            stage.setTitle("Partida");
            stage.setScene(scene);
            stage.show();
            Stage ventanaActual = (Stage) siguiente.getScene().getWindow();
            ventanaActual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }























}



