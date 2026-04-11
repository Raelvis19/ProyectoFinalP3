package main.java.app;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class GameController {

    @FXML
    private ImageView nave;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private HBox vidasBox;

    private boolean moveLeft = false;
    private boolean moveRight = false;

    private double velocidad = 5;


    private ImageView[][] enemigos;

    
    private Image[][] modelos;

    private int filas = 3;
    private int columnas = 6;

    private boolean moverDerecha = true;
    private double velocidadUfo = 1;

    @FXML
    public void initialize() {

        
        modelos = new Image[][]{
            {
                new Image(getClass().getResource("/main/resources/img/gg1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/gg2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/gg3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/nm1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/nm2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/nm3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pulA1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pulA2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pulA3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pm1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pm2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pm3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pa1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pa2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pa3.png").toExternalForm())
            },
            {
                new Image(getClass().getResource("/main/resources/img/azul1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/azul2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/azul3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/ojoM1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/ojoM2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/ojoM3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pp1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pp2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/pp3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/v1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/v2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/v3.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/nVerde1.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/nVerde2.png").toExternalForm()),
                new Image(getClass().getResource("/main/resources/img/nVerde3.png").toExternalForm())
            }
        };

        crearFormacion();

        
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

                nave.requestFocus();
            }
        });

        iniciarMovimiento();
        moverFormacion();
        animarFormacion();
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


    private void crearFormacion() {

        enemigos = new ImageView[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {

                int tipo = f % modelos.length; 

                ImageView enemigo = new ImageView(modelos[tipo][0]);

                enemigo.setFitWidth(100);
                enemigo.setPreserveRatio(true);

                enemigo.setLayoutX(100 + c * 120);
                enemigo.setLayoutY(250 + f * 90);

                enemigos[f][c] = enemigo;
                gamePane.getChildren().add(enemigo);
            }
        }
    }

    
    private void moverFormacion() {

        new AnimationTimer() {
            @Override
            public void handle(long now) {

                boolean cambiarDireccion = false;

                for (int f = 0; f < filas; f++) {
                    for (int c = 0; c < columnas; c++) {

                        ImageView enemigo = enemigos[f][c];
                        if (enemigo == null) continue;

                        double x = enemigo.getLayoutX();

                        if (moverDerecha) {
                            enemigo.setLayoutX(x + velocidadUfo);

                            if (x >= gamePane.getWidth() - enemigo.getFitWidth()) {
                                cambiarDireccion = true;
                            }

                        } else {
                            enemigo.setLayoutX(x - velocidadUfo);

                            if (x <= 0) {
                                cambiarDireccion = true;
                            }
                        }
                    }
                }

                
                if (cambiarDireccion) {
                    moverDerecha = !moverDerecha;

                    for (int f = 0; f < filas; f++) {
                        for (int c = 0; c < columnas; c++) {

                            ImageView enemigo = enemigos[f][c];
                            if (enemigo != null) {
                                enemigo.setLayoutY(enemigo.getLayoutY() + 15);
                            }
                        }
                    }
                }
            }
        }.start();
    }

    
    private void animarFormacion() {

        Timeline animacion = new Timeline(
            new KeyFrame(Duration.millis(500), e -> {

                for (int f = 0; f < filas; f++) {
                    for (int c = 0; c < columnas; c++) {

                        ImageView enemigo = enemigos[f][c];
                        if (enemigo == null) continue;

                        int tipo = f % modelos.length;

                        int frame = (enemigo.getUserData() != null)
                                ? (int) enemigo.getUserData()
                                : 0;

                        int siguiente = (frame + 1) % modelos[tipo].length;

                        enemigo.setImage(modelos[tipo][siguiente]);
                        enemigo.setUserData(siguiente);
                    }
                }
            })
        );

        animacion.setCycleCount(Timeline.INDEFINITE);
        animacion.play();
    }
}