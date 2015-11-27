package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage scena;
    @Override
    public void start(Stage primaryStage) throws Exception{
        scena = primaryStage;
        scena.getIcons().add(new Image("file:src\\iconos\\iconoFinal.png"));
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tiempo");
        primaryStage.setScene(new Scene(root, 650, 500));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
