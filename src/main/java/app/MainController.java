package main.java.app;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button btnSound;

    @FXML
    private Button btnAyuda;

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = true;

    private Image imgOn;
    private Image imgOff;
    

    public void initialize() {
        // 🔊 Cargar imágenes
        imgOn = new Image(getClass().getResource("../../resources/img/soundON.png").toExternalForm());
        imgOff = new Image(getClass().getResource("../../resources/img/soundOFF.png").toExternalForm());

        btnSound.setGraphic(new ImageView(imgOn));

        //🎵 Inicializar audio global
        audioManager.init();
    }

    @FXML
    private void toggleSound() {

    audioManager.toggleMute();

     if (audioManager.isMuted()) {
        btnSound.setGraphic(new ImageView(imgOff));
        } else {
        btnSound.setGraphic(new ImageView(imgOn));
        }
    }

    @FXML
        private void ayuda() throws Exception {

        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("../../resources/view/ayuda1.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) btnAyuda.getScene().getWindow();
        stage.getScene().setRoot(root);
        
    }

    @FXML
    private VBox panelAyuda;

    @FXML
    private void mostrarAyuda() {
    panelAyuda.setVisible(true);
    }

 
}