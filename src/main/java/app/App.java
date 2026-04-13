package main.java.app;



import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("../../resources/view/main.fxml")
        );

        stage.getIcons().add(
        new Image(getClass().getResourceAsStream("/main/resources/img/icon.png"))
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}