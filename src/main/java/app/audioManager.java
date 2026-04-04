package main.java.app;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class audioManager {

    private static MediaPlayer mediaPlayer;

    public static void init() {
        if (mediaPlayer == null) {
            Media media = new Media(
                audioManager.class.getResource("../../resources/audio/musica.mp3").toExternalForm()
            );
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }

    public static void toggleMute() {
        if (mediaPlayer != null) {
            mediaPlayer.setMute(!mediaPlayer.isMute());
        }
    }
    public static boolean isMuted() {
    return mediaPlayer != null && mediaPlayer.isMute();
    }
}