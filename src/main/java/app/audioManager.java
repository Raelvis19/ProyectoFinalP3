package main.java.app;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.AudioClip;

public class audioManager {

    private static MediaPlayer mediaPlayer;

    
    private static AudioClip sonidoDisparo;

    private static AudioClip explosion;

    public static void init() {

        if (mediaPlayer == null) {

            
            Media media = new Media(
                audioManager.class.getResource("/main/resources/audio/musica.mp3").toExternalForm()
            );

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

            
            sonidoDisparo = new AudioClip(
                audioManager.class.getResource("/main/resources/audio/disparo.mp3").toExternalForm()
            );

            explosion = new AudioClip(
                audioManager.class.getResource("/main/resources/audio/explocion.mp3").toExternalForm()
            );
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


    public static void reproducirDisparo() {
        if (sonidoDisparo != null) {

            
            if (!isMuted()) {
                sonidoDisparo.play();
            }
        }
    }



    public static void reproducirExplosion() {
    if (!isMuted()) explosion.play();
    }
}