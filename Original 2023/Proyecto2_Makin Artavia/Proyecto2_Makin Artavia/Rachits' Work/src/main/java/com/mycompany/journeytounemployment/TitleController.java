/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.journeytounemployment;

import Model.Progress;
import Model_DAO.Progress_dao;
import Model_DTO.Progress_dto;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class TitleController implements Initializable {

    @FXML
    private Button btn_stardGame;
    @FXML
    private ImageView ivw_frontpage;
    @FXML
    private Button btn_close;
    @FXML
    private Button btn_more;
    @FXML
    private Button btn_continue;
    @FXML
    private TextField tfl_TrainerName;
    @FXML
    private Button btn_search;

    /**
     * Initializes the controller class.
     */
    private final MyPlayer player = new MyPlayer();
    private final MyPlayer clicks = new MyPlayer();
    private static final int duration = 400;
    private int currentIndex = 0;
    
    private Progress pg_player = new Progress();
    private Progress_dto progressDTO = new Progress_dto();
    private Progress_dao progressDAO = new Progress_dao();
    @FXML
    private Label lbl_msg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Format();
    }

    @FXML
    private void StarGame(ActionEvent event) throws IOException {
        player.Pause();
        clicks.Click();
        App.setRoot("Register");
    }

    @FXML
    private void CloseGame(ActionEvent event) throws IOException {
        player.Pause();
        clicks.Click();
        App.Close();
    }

    @FXML
    private void MoreInformation(ActionEvent event) throws IOException {
       player.Pause();
       clicks.Click();
       App.setRoot("More");
    }

    @FXML
    private void ContinuePastParty(ActionEvent event) throws IOException{
        player.Pause();
        clicks.Click();
        App.setRoot("Level");
    }

    private void Format() {
        player.PlayOnBucle("src/main/resources/Songs/titleSong.mp3");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(duration), event -> ShowNextImage())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        ivw_frontpage.setImage(new Image("file:src/main/resources/FrontPage/frame1.jpg"));
        btn_more.setBackground(Background.EMPTY);
        ImageView image = new ImageView(new Image("file:src/main/resources/FrontPage/MZ Time.gif"));
        image.setFitWidth(150);
        image.setPreserveRatio(true);
        btn_more.setGraphic(image);
        ImageView loadImage = new ImageView(new Image("file:src/main/resources/Buttons/Cargar.png"));
        btn_continue.setBackground(Background.EMPTY);
        btn_continue.setGraphic(loadImage);
        loadImage = new ImageView(new Image("file:src/main/resources/Buttons/iniciar.png"));
        btn_stardGame.setBackground(Background.EMPTY);
        btn_stardGame.setGraphic(loadImage);
        loadImage = new ImageView(new Image("file:src/main/resources/Buttons/Cerrar.png"));
        btn_close.setBackground(Background.EMPTY);
        btn_close.setGraphic(loadImage);
        btn_search.setBackground(Background.EMPTY);
        loadImage = new ImageView(new Image("file:src/main/resources/Buttons/buscar.png"));
        btn_search.setGraphic(loadImage);
        
    }

    private void ShowNextImage() {
        switch (currentIndex) {
            case 0: {
                ivw_frontpage.setImage(new Image("file:src/main/resources/FrontPage/frame1.jpg"));
                currentIndex++;
                break;
            }
            case 1: {
                ivw_frontpage.setImage(new Image("file:src/main/resources/FrontPage/frame2.jpg"));
                currentIndex++;
                break;
            }
            case 2:{
                ivw_frontpage.setImage(new Image("file:src/main/resources/FrontPage/frame3.jpg"));
                currentIndex++;
                break;
            }
            case 3:{
                ivw_frontpage.setImage(new Image("file:src/main/resources/FrontPage/frame4.jpg"));
                currentIndex++;
                break;
            }
             case 4:{
                ivw_frontpage.setImage(new Image("file:src/main/resources/FrontPage/frame5.jpg"));
                currentIndex++;
                break;
            }
             case 5:{
                ivw_frontpage.setImage(new Image("file:src/main/resources/FrontPage/frame4.jpg"));
                currentIndex=0;
                break;
            }
        }
        
    }

    @FXML
    private void SearchTrainer(ActionEvent event) throws SQLException {
        clicks.Click();
        Boolean flag = progressDAO.IsRegister(tfl_TrainerName.getText());
        if(flag==true){
        Player player= progressDAO.searchPlayer(tfl_TrainerName.getText());
        App.setSharedData(Integer.valueOf(player.getLevel().toString()), player.getName());
        btn_continue.setDisable(false);
        }else{
        lbl_msg.setText("-No eres Entrenador-");
        }
    }

    @FXML
    private void CleanSMS(MouseEvent event) {
        lbl_msg.setText("");
    }
}
