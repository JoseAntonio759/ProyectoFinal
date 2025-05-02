package example.proyectofinal;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;


public class TableroController {



    @FXML
    private Button volveratras;
    @FXML
    private Button siguiente;






    @FXML
    protected void initialize() {

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



