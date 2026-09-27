package com.mycompany.journeytounemployment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Integer level=0;
    private static String player;
    @Override
    public void start(Stage stage) throws IOException {
        Image cursorImage = new Image(getClass().getResource("/Images/Cursor.gif").toExternalForm());
        ImageCursor customCursor = new ImageCursor(cursorImage);
        scene = new Scene(loadFXML("title"), 1420, 800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Rachits' Work");
        scene.setCursor(customCursor);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    static void Close() throws IOException {
        System.exit(0);
    }
    
     public static void setSharedData(Integer level_, String player_) {
        level = level_;
        player = player_;
    }
      public static Integer getLevel() {
        return level;
    }
       public static String getPlayer() {
        return player;
    }
}