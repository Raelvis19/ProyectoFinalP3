package main.java.app;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GameController {
    @FXML
    private ImageView nave;

    private boolean moveLeft = false;
    private boolean moveRight = false;

    private double velocidad = 5;

    @FXML
    public void initialize() {

    // Esperar a que la escena exista
    nave.sceneProperty().addListener((obs, oldScene, newScene) -> {
        if (newScene != null) {

            newScene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case A -> moveLeft = true;
                    case D -> moveRight = true;
                }
            });

            newScene.setOnKeyReleased(event -> {
                switch (event.getCode()) {
                    case A -> moveLeft = false;
                    case D -> moveRight = false;
                }
            });
        }
    });

    iniciarMovimiento();
    }

    private void iniciarMovimiento() {

        new AnimationTimer() {
        @Override
        public void handle(long now) {

            double x = nave.getTranslateX();

            if (moveLeft) {
                nave.setTranslateX(x - velocidad);
            }

            if (moveRight) {
                nave.setTranslateX(x + velocidad);
            }
        }
        }.start();
    }
}
