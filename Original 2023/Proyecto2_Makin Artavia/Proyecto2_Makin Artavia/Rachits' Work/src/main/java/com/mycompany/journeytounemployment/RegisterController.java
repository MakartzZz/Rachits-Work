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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author david
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfl_newPlayer;
    @FXML
    private TextField tfl_deletePlayer;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_Start;
    @FXML
    private Button btn_Back;
    @FXML
    private RadioButton rbt_levelOne;
    @FXML
    private RadioButton rbt_levelTwo;
    @FXML
    private RadioButton rbt_levelThree;
    @FXML
    private RadioButton rbt_levelFour;
    @FXML
    private RadioButton rbt_levelFive;
    @FXML
    private ToggleGroup Levels;

    /**
     * Initializes the controller class.
     */
    private Progress player = new Progress();
    private Progress_dto progressDTO = new Progress_dto();
    private Progress_dao progressDAO = new Progress_dao();
    private final MyPlayer song = new MyPlayer();
    private final MyPlayer clicks = new MyPlayer();
    @FXML
    private Label lbl_sms;
    @FXML
    private Label lbl_msg;
    @FXML
    private ImageView imv_wallpaper;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Entry();
    }

    @FXML
    private void DeletePlayer(ActionEvent event) throws SQLException {
        clicks.Click();
        Boolean flag = progressDAO.IsRegister(tfl_deletePlayer.getText());
        if (flag == true) {
            try {
                progressDAO = new Progress_dao();
                progressDAO.DeletePlayer(tfl_deletePlayer.getText());
                progressDAO.closeConnection();
                lbl_msg.setText("Eliminado.");
                lbl_sms.setText("");
                tfl_deletePlayer.setText("");
            } catch (Exception e) {
                lbl_msg.setText("No se pudo eliminar.");
            }
        } else {
            lbl_msg.setText("No se encontro al entrenador: " + tfl_deletePlayer.getText());
        }

    }

    @FXML
    private void StartGame(ActionEvent event) throws IOException, SQLException {
        clicks.Click();
        Toggle selectedToggle = Levels.getSelectedToggle();
        if (!"".equals(tfl_newPlayer.getText())) {
            if (selectedToggle != null) {
                RadioButton selectedRadioButton = (RadioButton) selectedToggle;
                Integer selectedNumber = (Integer) selectedRadioButton.getUserData();
                App.setSharedData(selectedNumber, tfl_newPlayer.getText());
                progressDAO = new Progress_dao();
                Boolean flag = progressDAO.IsRegister(tfl_newPlayer.getText());
                if (flag == false) {
                    try {
                        progressDAO = new Progress_dao();
                        player = new Progress(tfl_newPlayer.getText(), Short.valueOf(App.getLevel().toString()));
                        progressDTO = new Progress_dto(player);
                        progressDAO.savePlayer(progressDTO);
                        progressDAO.closeConnection();
                        song.Pause();
                        App.setRoot("Level");
                    } catch (Exception e) {
                        System.out.println("Ocurrio un error al registrar el nuevo entrenador");
                    }
                } else {
                    lbl_sms.setText("Entrenador ya registrado");
                }

            } else {
                lbl_sms.setText("Nivel no seleccionado.");
            }
        } else {
            lbl_sms.setText("Apodo del entrenador no especificado.");
        }

    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        clicks.Click();
        song.Pause();
        App.setRoot("title");
        
    }

    private void Entry() {
        song.PlayOnBucle("src/main/resources/Songs/RegisterSong.mp3");
        imv_wallpaper.setImage(new Image("file:src/main/resources/RegisterImages/RegisterWall.gif"));
        btn_delete.setBackground(Background.EMPTY);
        btn_Back.setBackground(Background.EMPTY);
        btn_Start.setBackground(Background.EMPTY);
        ImageView loadImage = new ImageView(new Image("file:src/main/resources/Buttons/eliminar.png"));
        btn_delete.setGraphic(loadImage);
        loadImage = new ImageView(new Image("file:src/main/resources/Buttons/Salir.png"));
        btn_Back.setGraphic(loadImage);
        loadImage = new ImageView(new Image("file:src/main/resources/Buttons/Jugar.png"));
        btn_Start.setGraphic(loadImage);
        rbt_levelOne.setUserData(1);
        rbt_levelTwo.setUserData(2);
        rbt_levelThree.setUserData(3);
        rbt_levelFour.setUserData(4);
        rbt_levelFive.setUserData(5);
    }

    @FXML
    private void Sound(MouseEvent event) {
        clicks.Click();
    }

    @FXML
    private void Sounds(KeyEvent event) {
        clicks.Click();
    }
}
