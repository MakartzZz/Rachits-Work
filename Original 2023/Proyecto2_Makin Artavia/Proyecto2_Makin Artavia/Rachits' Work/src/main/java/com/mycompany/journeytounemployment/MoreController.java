/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.journeytounemployment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author david
 */
public class MoreController implements Initializable {

    @FXML
    private ImageView imv_wallpaper;
    @FXML
    private Button btn_close;

    /**
     * Initializes the controller class.
     */
    private MyPlayer player = new MyPlayer();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imv_wallpaper.setImage(new Image("file:src/main/resources/FrontPage/moreWall.gif"));
        this.player.PlayOnBucle("src/main/resources/Songs/pokemon.mp3");
        btn_close.setBackground(Background.EMPTY);
        btn_close.setGraphic(new ImageView(new Image("file:src/main/resources/Buttons/Salir.png")));

    }    

    @FXML
    private void ChangeStage(ActionEvent event) throws IOException {
        player.Pause();
        player.Click();
        App.setRoot("title");
    }
    
}
