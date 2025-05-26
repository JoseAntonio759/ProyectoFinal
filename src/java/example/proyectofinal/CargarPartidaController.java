package example.proyectofinal;

import com.google.gson.Gson;
import example.proyectofinal.IA.Grafo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;

public class CargarPartidaController {
    @FXML
    private Button cargarPartidaButton;
    @FXML
    private Button volveratras;
    @FXML
    private Label labelCargarPartida;

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
    protected  Grafo cargarPartidaButtonClick() {
        Gson gson = new Gson();
        labelCargarPartida.setText("Cargando Partida");
        try (FileReader reader = new FileReader("partida.json")) {
            return gson.fromJson(reader, Grafo.class);
        } catch (IOException e) {
            labelCargarPartida.setText("Error  al cargar la partida.");
            e.printStackTrace();
            return null;
        }

    }
}
