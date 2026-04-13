package main.java.app;

import java.io.IOException;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

public class winController {

    @FXML private Label lblTitulo;
    @FXML private Label lblScore;
    @FXML private Label lblHighScore;
    @FXML private AnchorPane particlesPane;

    private AudioClip winSound;

 
    @FXML
    public void initialize() {
       // gameData.puntajeAlto = HighScoreManager.cargarHighScore();
        lblScore.setText(String.valueOf(gameData.puntaje));
        lblHighScore.setText(String.valueOf(gameData.puntajeAlto));

        animarTextoCaida();
        animarGlow();
        generarParticulas();
        reproducirSonido();
    }

    

    
    private void animarTextoCaida() {

        lblTitulo.setTranslateY(-500);

        TranslateTransition drop = new TranslateTransition(Duration.seconds(1), lblTitulo);
        drop.setToY(0);

        drop.setInterpolator(Interpolator.EASE_OUT);
        drop.play();
    }

    
    private void animarGlow() {

        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1), lblTitulo);
        pulse.setFromX(1);
        pulse.setToX(1.1);
        pulse.setFromY(1);
        pulse.setToY(1.1);

        pulse.setAutoReverse(true);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.play();
    }

    private void generarParticulas() {

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(100), e -> crearParticula())
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void crearParticula() {

        Circle p = new Circle(2, Color.CYAN);

        double x = Math.random() * 1920;
        p.setLayoutX(x);
        p.setLayoutY(0);

        particlesPane.getChildren().add(p);

        TranslateTransition fall = new TranslateTransition(Duration.seconds(3), p);
        fall.setToY(1080);

        FadeTransition fade = new FadeTransition(Duration.seconds(3), p);
        fade.setFromValue(1);
        fade.setToValue(0);

        ParallelTransition anim = new ParallelTransition(fall, fade);

        anim.setOnFinished(e -> particlesPane.getChildren().remove(p));

        anim.play();
    }

    
    private void reproducirSonido() {

        winSound = new AudioClip(
            getClass().getResource("/main/resources/audio/Level Complete Jingle.mp3").toExternalForm()
        );

        winSound.play();
    }

    @FXML
    private void reiniciar() throws IOException {
        detenerSonido();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/view/game.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) lblScore.getScene().getWindow();
        stage.getScene().setRoot(root); 
    }

    @FXML
    private void inicio() throws IOException {
        detenerSonido();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/view/main.fxml"));
       Parent root = loader.load();

       Stage stage = (Stage) lblScore.getScene().getWindow();
       stage.getScene().setRoot(root); 
    }

    private void detenerSonido() {
    if (winSound != null) {
        winSound.stop();
    }
}
}