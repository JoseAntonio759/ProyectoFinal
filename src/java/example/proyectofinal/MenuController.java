package example.proyectofinal;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {



    @FXML
    private Button botonpartidanueva;
    @FXML
    private Button botoncargapartida;


    private static int tamañotablero = 0;
    private static int tamañocelda = 0;




    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

        @FXML
        private void clickIniciarNuevaPartida () {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MenuController.class.getResource("tablero.fxml"));
            try {

                Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                stage.setTitle("Pantalla Tablero");
                stage.setScene(scene);
                stage.show();
                Stage ventanaActual = (Stage) botonpartidanueva.getScene().getWindow();
                ventanaActual.close();


            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    @FXML
    private void clickCargarPartida () {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("cargarpartida.fxml"));
        try {

            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            stage.setTitle("Pantalla Cargar Partida");
            stage.setScene(scene);
            stage.show();
            Stage ventanaActual = (Stage) botoncargapartida.getScene().getWindow();
            ventanaActual.close();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }





}





