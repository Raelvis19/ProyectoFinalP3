package main.java.app;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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

    private ImageView bala;
    private boolean puedeDisparar = true;

    private Image balaEnemigaImg;

    private Image corazonVacio;

    private int vidas = 3;

    @FXML
    private Label lblScore;

    //private int puntaje = 0;

   // private  static int puntajeAlto = 0;

    private int score = 0;

    private List<ImageView> balasEnemigas = new ArrayList<>();

    @FXML
    public void initialize() {

        gameData.puntajeAlto = HighScoreManager.cargarHighScore();
        gameData.puntaje = 0;
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
        balaEnemigaImg = new Image(
        getClass().getResource("/main/resources/img/14.png").toExternalForm()
        );

        corazonVacio = new Image(
         getClass().getResource("/main/resources/img/corazonVacio.png").toExternalForm()
        );

        
        nave.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {

                newScene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case A -> moveLeft = true;
                        case D -> moveRight = true;
                        case SPACE -> disparar();
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

        Timeline disparoEnemigo = new Timeline(
        new KeyFrame(Duration.seconds(2), e -> dispararEnemigo())
        );

        disparoEnemigo.setCycleCount(Timeline.INDEFINITE);
        disparoEnemigo.play();
        moverBalasEnemigas();
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

    private void disparar() {
    audioManager.reproducirDisparo();

    if (!puedeDisparar) return;

    puedeDisparar = false;

    bala = new ImageView(new Image(
        getClass().getResource("/main/resources/img/15.png").toExternalForm()
    ));

    bala.setFitWidth(20);
    bala.setFitHeight(50);

    
    bala.setLayoutX(nave.getLayoutX() + nave.getTranslateX() + 45);
    bala.setLayoutY(nave.getLayoutY());

    gamePane.getChildren().add(bala);

    moverBala();
    }

    private void moverBala() {

    new AnimationTimer() {
        @Override
        public void handle(long now) {

            if (bala == null) {
                stop();
                return;
            }

            
            bala.setLayoutY(bala.getLayoutY() - 8);

        
            if (bala.getLayoutY() < 0) {
                gamePane.getChildren().remove(bala);
                bala = null;
                puedeDisparar = true;
                stop();
            }

            
            detectarColision();
        }
    }.start();
    }

    private void detectarColision() {

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {

            ImageView enemigo = enemigos[f][c];

            if (enemigo == null) continue;

            if (bala != null && bala.getBoundsInParent().intersects(enemigo.getBoundsInParent())) {

            
            int fila = f;
            int columna = c;

            
            Image explosion = new Image(
                getClass().getResource("/main/resources/img/explosion-01.png").toExternalForm()
            );
            audioManager.reproducirExplosion();
            enemigo.setImage(explosion);
            sumarPuntos(100, enemigo.getLayoutX(), enemigo.getLayoutY());

            Timeline eliminar = new Timeline(
                new KeyFrame(Duration.millis(200), e -> {
                    gamePane.getChildren().remove(enemigo);
                    enemigos[fila][columna] = null; 
                    if(todosMuertos()){
                        ganarJuego();
                    }
                })
            );

            eliminar.play();

            
            gamePane.getChildren().remove(bala);
            bala = null;
            puedeDisparar = true;
            

            return;
                    }
            }
        }
    }

    private void dispararEnemigo() {


    List<ImageView> vivos = new ArrayList<>();
    

    for (int f = 0; f < filas; f++) {
        for (int c = 0; c < columnas; c++) {

            if (enemigos[f][c] != null) {
                vivos.add(enemigos[f][c]);
            }
        }
    }

    if (vivos.isEmpty()) return;

    
    ImageView shooter = vivos.get((int)(Math.random() * vivos.size()));

    
    ImageView bala = new ImageView(balaEnemigaImg);
    bala.setFitWidth(10);
    bala.setFitHeight(30);

    bala.setLayoutX(shooter.getLayoutX() + 20);
    bala.setLayoutY(shooter.getLayoutY() + 40);

    gamePane.getChildren().add(bala);
    balasEnemigas.add(bala);
    }

    private void moverBalasEnemigas() {

    new AnimationTimer() {
        @Override
        public void handle(long now) {

            for (int i = 0; i < balasEnemigas.size(); i++) {

                ImageView bala = balasEnemigas.get(i);

        
                bala.setTranslateY(bala.getTranslateY() + 4);

                
                if (bala.getBoundsInParent().intersects(nave.getBoundsInParent())) {

                    gamePane.getChildren().remove(bala);
                    balasEnemigas.remove(bala);

                    perderVida();
                    return;
                }

                
                if (bala.getLayoutY() > gamePane.getHeight()) {
                    gamePane.getChildren().remove(bala);
                    balasEnemigas.remove(bala);
                }
            }
        }
    }.start();
    }

    private void perderVida() {

    if (vidas > 0) {

        vidas--; 

        ImageView corazon = (ImageView) vidasBox.getChildren().get(vidas);

        corazon.setImage(corazonVacio); 
    }

    if (vidas == 0) {
        try {

            if (gameData.puntaje > gameData.puntajeAlto) {
            gameData.puntajeAlto = gameData.puntaje;
            HighScoreManager.guardarHighScore(gameData.puntajeAlto);
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/view/gameover.fxml"));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setScores(gameData.puntaje, gameData.puntajeAlto);

           Stage stage = (Stage) gamePane.getScene().getWindow();
          
           stage.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }
    }

    private void sumarPuntos(int puntos, double x, double y) {
    score += puntos;
    gameData.puntaje += puntos;
   
    lblScore.setText(String.valueOf(score));

    mostrarPuntosFlotantes(x, y, puntos);
    }

    private void mostrarPuntosFlotantes(double x, double y, int puntos) {

    Label texto = new Label("+" + puntos);
    texto.setStyle("-fx-text-fill: yellow; -fx-font-size: 24px; -fx-font-weight: bold;");

    texto.setLayoutX(x);
    texto.setLayoutY(y);

    gamePane.getChildren().add(texto);

    
    TranslateTransition mover = new TranslateTransition(Duration.seconds(1), texto);
    mover.setByY(-50);

    FadeTransition fade = new FadeTransition(Duration.seconds(1), texto);
    fade.setFromValue(1);
    fade.setToValue(0);

    ParallelTransition animacion = new ParallelTransition(mover, fade);

    animacion.setOnFinished(e -> gamePane.getChildren().remove(texto));

    animacion.play();
    }

    private void ganarJuego() {

    try {
        audioManager.stopMusic();
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/main/resources/view/victoria.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) gamePane.getScene().getWindow();
        stage.getScene().setRoot(root);

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    private boolean todosMuertos() {

    for (int f = 0; f < filas; f++) {
        for (int c = 0; c < columnas; c++) {

            if (enemigos[f][c] != null) {
                return false; 
            }
        }
    }

    return true; 
    }

}