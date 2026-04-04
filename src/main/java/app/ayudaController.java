package main.java.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ayudaController {

    @FXML
    private Button btnVolver;

    @FXML
    private void cerrarAyuda() throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("../../resources/view/main.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}