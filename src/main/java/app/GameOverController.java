package main.java.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverController {

    @FXML
    private Label lblScore;

    @FXML
    private Label lblHighScore;

    public void setScores(int score, int highScore) {
    lblScore.setText(String.valueOf(score));
    lblHighScore.setText(String.valueOf(highScore));
    }

    @FXML
    private void reiniciarJuego() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/view/game.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lblScore.getScene().getWindow();
        stage.getScene().setRoot(root); 
    }

    @FXML
    private void irInicio() throws Exception {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/view/main.fxml"));
       Parent root = loader.load();

        Stage stage = (Stage) lblScore.getScene().getWindow();
        stage.getScene().setRoot(root); 
        
    }
}