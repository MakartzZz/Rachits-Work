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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author david
 */
public class LevelController implements Initializable {

    @FXML
    private Button btn_up;
    @FXML
    private Button btn_left;
    @FXML
    private Button btn_right;
    @FXML
    private Button btn_down;
    @FXML
    private Button btn_back;
    @FXML
    private GridPane gp_map;
    @FXML
    private Button btn_nextLevel;
    @FXML
    private Label lbl_sms;
    @FXML
    private Label lbl_level;
    @FXML
    private Button btn_close;
    @FXML
    private Button btn_Again;
    @FXML
    private Label lbl_moves;
    /**
     * Initializes the controller class.
     */
    private Integer[][] level = new Integer[7][9];
    private Integer[][] goBack = new Integer[7][9];
    private String[] lockedCells = new String[5];
    private int counter = 0;
    private Integer PsayduckX = null;
    private Integer PsayduckY = null;
    private Integer backPsayduckX = null;
    private Integer backPsayduckY = null;
    private String frame = "S";
    private Boolean restart = false, angry = false, lose = false;
    private Integer playing = 1;
    private final MyPlayer player = new MyPlayer();
    private final MyPlayer playVoices = new MyPlayer();
    private int moves = 0, patience = 2;
    private Boolean levelomplete = false;
    private Progress pg_player = new Progress();
    private Progress_dto progressDTO = new Progress_dto();
    private Progress_dao progressDAO = new Progress_dao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ButtonsConfig();
        playing = App.getLevel();
        addLevel(false, playing);
    }

    @FXML
    private void Up(ActionEvent event) throws IOException {
        moveUp(level, PsayduckX, PsayduckY);

    }

    @FXML
    private void Left(ActionEvent event) throws IOException {
        moveLeft(level, PsayduckX, PsayduckY);
    }

    @FXML
    private void Right(ActionEvent event) throws IOException {
        moveRight(level, PsayduckX, PsayduckY);
    }

    @FXML
    private void Down(ActionEvent event) throws IOException {
        moveDown(level, PsayduckX, PsayduckY);
    }

    @FXML
    private void Reverse(ActionEvent event) throws IOException {
        GoBack(goBack, backPsayduckX, backPsayduckY);
    }

    @FXML
    private void NextLevel(ActionEvent event) throws IOException {
        if (playing <= 5) {
            player.Pause();
            addLevel(false, playing);
            lbl_sms.setText("");
            btn_nextLevel.setDisable(true);
            counter = 0;
            moves = 0;
            btn_back.setDisable(false);
            lbl_moves.setText("");
            btn_down.setDisable(false);
            btn_up.setDisable(false);
            btn_left.setDisable(false);
            btn_right.setDisable(false);
            levelomplete = false;
        } else {
            App.setRoot("title");
            player.Pause();
        }

    }

    private void SaveProgress() throws SQLException {
        progressDAO = new Progress_dao();
        progressDAO.DeletePlayer(App.getPlayer());
        progressDAO.closeConnection();

        progressDAO = new Progress_dao();
        if (playing <= 5) {
            pg_player = new Progress(App.getPlayer(), Short.valueOf(playing.toString()));
        } else {
            pg_player = new Progress(App.getPlayer(), Short.valueOf(String.valueOf(5)));
        }
        progressDTO = new Progress_dto(pg_player);
        progressDAO.savePlayer(progressDTO);
        progressDAO.closeConnection();
    }

    @FXML
    private void Close(ActionEvent event) throws IOException, SQLException {
        playVoices.Click();
        player.Pause();
        //Guardar en base de datos
        SaveProgress();
        //Cambiar Scene
        App.setRoot("title");
        
        

    }

    @FXML
    private void TryAgain(ActionEvent event) {
        if (restart == true) {
            player.Pause();
            if (levelomplete == true) {
                addLevel(false, this.playing - 1);
            } else {
                addLevel(false, this.playing);
            }
            lbl_sms.setText("");
            btn_nextLevel.setDisable(true);
            counter = 0;
            moves = 0;
            btn_back.setDisable(false);
            lbl_moves.setText("");
            goBack = new Integer[7][9];
            backPsayduckX = null;
            backPsayduckY = null;
            btn_down.setDisable(false);
            btn_up.setDisable(false);
            btn_left.setDisable(false);
            btn_right.setDisable(false);
            levelomplete = false;
            lose = false;
        } else {
            lbl_sms.setText("No has echo ningun movimiento.");
        }

    }

    private void ButtonsConfig() {
        btn_up.setBackground(Background.EMPTY);
        btn_right.setBackground(Background.EMPTY);
        btn_down.setBackground(Background.EMPTY);
        btn_left.setBackground(Background.EMPTY);
        btn_back.setBackground(Background.EMPTY);
        btn_nextLevel.setBackground(Background.EMPTY);
        btn_Again.setBackground(Background.EMPTY);
        btn_close.setBackground(Background.EMPTY);
        btn_up.setGraphic(new ImageView(new Image("file:src/main/resources/ImagesRows/up.png")));
        btn_down.setGraphic(new ImageView(new Image("file:src/main/resources/ImagesRows/down.png")));
        btn_right.setGraphic(new ImageView(new Image("file:src/main/resources/ImagesRows/right.png")));
        btn_left.setGraphic(new ImageView(new Image("file:src/main/resources/ImagesRows/left.png")));
        btn_back.setGraphic(new ImageView(new Image("file:src/main/resources/ImagesRows/again.png")));
        btn_close.setGraphic(new ImageView(new Image("file:src/main/resources/Buttons/titulo.png")));
        btn_Again.setGraphic(new ImageView(new Image("file:src/main/resources/Buttons/Reiniciar.png")));
        btn_nextLevel.setGraphic(new ImageView(new Image("file:src/main/resources/Buttons/Siguiente.png")));
    }

    private void addLevel(Boolean flag, Integer level) {
        this.playing = level;
        if (flag == false) {
            switch (level) {

                case 1: {
                    btn_nextLevel.setDisable(true);
                    player.PlayOnBucle("src/main/resources/Songs/level1.mp3");
                    lbl_level.setText("Level: " + playing);
                    this.level = new Integer[][]{
                        {1, 3, 7, 6, 9, 9, 9, 3, 9, 2},
                        {3, 9, 9, 9, 1, 5, 9, 9, 0, 4},
                        {1, 9, 8, 9, 9, 9, 9, 3, 9, 2},
                        {3, 10, 11, 9, 5, 9, 9, 9, 9, 4},
                        {1, 9, 4, 9, 6, 7, 9, 3, 9, 2},
                        {3, 9, 9, 9, 9, 9, 9, 9, 9, 4},
                        {1, 9, 1, 1, 9, 6, 7, 3, 10, 2},
                        {9, 9, 9, 9, 9, 11, 9, 9, 11, 4}
                    };
                    this.PsayduckX = 8;
                    this.PsayduckY = 1;
                    this.lockedCells = new String[]{"", "", "", "", ""};
                    this.levelomplete = false;
                    break;
                }
                case 2: {
                    btn_nextLevel.setDisable(true);
                    player.PlayOnBucle("src/main/resources/Songs/level2.mp3");
                    lbl_level.setText("Level: " + playing);
                    this.level = new Integer[][]{
                        {4, 3, 1, 1, 1, 1, 1, 1, 2, 4},
                        {6, 4, 4, 0, 4, 9, 4, 4, 10, 6},
                        {8, 4, 4, 5, 10, 9, 9, 9, 9, 8},
                        {8, 9, 5, 9, 9, 9, 4, 9, 9, 8},
                        {8, 9, 9, 9, 9, 9, 5, 9, 4, 8},
                        {8, 9, 10, 9, 5, 10, 4, 9, 4, 8},
                        {7, 9, 9, 9, 9, 9, 9, 9, 9, 7},
                        {4, 3, 1, 1, 1, 1, 1, 1, 2, 4}
                    };
                    this.PsayduckX = 3;
                    this.PsayduckY = 1;
                    this.lockedCells = new String[]{"", "", "", "", ""};
                    this.levelomplete = false;
                    break;
                }
                case 3: {
                    btn_nextLevel.setDisable(true);
                    player.PlayOnBucle("src/main/resources/Songs/level3.mp3");
                    lbl_level.setText("Level: " + playing);
                    this.level = new Integer[][]{
                        {11, 11, 11, 11, 11, 11, 11, 11, 11, 3},
                        {1, 1, 4, 1, 4, 1, 9, 4, 4, 8},
                        {9, 9, 5, 10, 1, 10, 9, 9, 4, 8},
                        {9, 1, 9, 1, 6, 1, 9, 9, 9, 8},
                        {9, 9, 9, 4, 9, 5, 9, 3, 0, 8},
                        {9, 1, 5, 1, 9, 1, 9, 9, 9, 8},
                        {9, 9, 9, 9, 9, 9, 9, 9, 10, 8},
                        {4, 1, 1, 1, 1, 1, 1, 1, 1, 3}
                    };
                    this.PsayduckX = 8;
                    this.PsayduckY = 4;
                    this.lockedCells = new String[]{"", "", "", "", ""};
                    this.levelomplete = false;
                    break;
                }
                case 4: {
                    btn_nextLevel.setDisable(true);
                    player.PlayOnBucle("src/main/resources/Songs/level4.mp3");
                    lbl_level.setText("Level: " + playing);
                    this.level = new Integer[][]{
                        {9, 9, 9, 9, 10, 1, 9, 9, 9, 4},
                        {9, 2, 9, 9, 9, 5, 5, 9, 5, 0},
                        {9, 5, 9, 9, 3, 10, 9, 9, 3, 4},
                        {9, 2, 9, 4, 10, 6, 6, 6, 6, 4},
                        {9, 9, 9, 9, 9, 7, 7, 7, 7, 9},
                        {9, 2, 9, 9, 5, 9, 9, 9, 9, 10},
                        {9, 9, 1, 1, 1, 11, 8, 8, 11, 3},
                        {10, 2, 1, 1, 1, 4, 4, 4, 4, 4}
                    };
                    this.PsayduckX = 9;
                    this.PsayduckY = 1;
                    this.lockedCells = new String[]{"", "", "", "", ""};
                    this.levelomplete = false;
                    break;
                }
                case 5: {
                    btn_nextLevel.setDisable(true);
                    player.PlayOnBucle("src/main/resources/Songs/level5.mp3");
                    lbl_level.setText("Level: " + playing);
                    this.level = new Integer[][]{
                        {9, 9, 9, 9, 4, 4, 9, 9, 9, 10},
                        {9, 4, 9, 4, 4, 4, 9, 4, 9, 4},
                        {9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        {9, 4, 9, 4, 5, 4, 9, 4, 9, 4},
                        {9, 4, 9, 5, 0, 5, 9, 9, 9, 4},
                        {9, 4, 9, 4, 5, 4, 9, 4, 9, 4},
                        {9, 9, 9, 10, 9, 5, 9, 9, 9, 10},
                        {10, 4, 9, 4, 9, 4, 9, 4, 10, 4}
                    };
                    this.PsayduckX = 4;
                    this.PsayduckY = 4;
                    this.lockedCells = new String[]{"", "", "", "", ""};
                    this.levelomplete = false;
                    break;
                }
            }

        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                switch (this.level[i][j]) {

                    case 1: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/1.png")), j, i);
                        break;
                    }
                    case 2: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/2.png")), j, i);
                        break;
                    }
                    case 3: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/3.png")), j, i);
                        break;
                    }
                    case 4: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/4.gif")), j, i);
                        break;
                    }
                    case 5: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/5.gif")), j, i);
                        break;
                    }
                    case 6: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/6.gif")), j, i);
                        break;
                    }
                    case 7: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/7.png")), j, i);
                        break;
                    }
                    case 8: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/8.png")), j, i);
                        break;
                    }
                    case 9: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/9.png")), j, i);
                        break;
                    }
                    case 10: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/10.gif")), j, i);
                        break;
                    }
                    case 11: {
                        gp_map.add(new ImageView(new Image("file:src/main/resources/Images/11.png")), j, i);
                        break;
                    }
                    case 0: {
                        switch (frame) {
                            case "S": {
                                gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Down.gif")), j, i);
                                break;
                            }
                            case "N": {
                                gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Up.gif")), j, i);
                                break;
                            }
                            case "O": {
                                gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Left.gif")), j, i);
                                break;
                            }
                            case "E": {
                                gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Right.gif")), j, i);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private void Refresh() throws IOException {
        gp_map.getChildren().clear();
        addLevel(true, this.playing);
        if (this.levelomplete == false) {
            LevelComplete(this.playing);
        }

    }

    private void PastPosition(Integer X, Integer Y) {
        for (int i = 0; i < 7; i++) {
            System.arraycopy(this.level[i], 0, this.goBack[i], 0, 9);
        }
        this.backPsayduckX = X;
        this.backPsayduckY = Y;
    }

    private void moveUp(Integer level[][], Integer X, Integer Y) throws IOException {
        Boolean flag = false, lose = false;
        //Mietras no se salga de los limites
        if (Y - 1 >= 0) {
            //Mover si es piso o caja
            if (level[Y - 1][X] == 9 || level[Y - 1][X] == 5) {
                //Guardar posicion anterior
                PastPosition(X, Y);
                //Mover
                if (level[Y - 1][X] == 9) {//Si el espacio de adelante es 9 (Piso)
                    this.level[Y][X] = 9;
                    this.level[Y - 1][X] = 0;
                    this.PsayduckY = Y - 1;
                    this.restart = true;
                    this.frame = "N";
                    //sumar paso
                    moves++;
                    lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                } else {//Si el espacio de adelante es 5 (Caja)
                    if (Y - 2 >= 0) {
                        if (this.level[Y - 2][X] == 9 || this.level[Y - 2][X] == 10) {
                            //Validador
                            for (int i = 0; i < 5; i++) {
                                if (this.lockedCells[i] != null) {
                                    if (this.lockedCells[i].equals(String.valueOf((Y - 1) + "," + X))) {
                                        flag = true;
                                    }
                                }
                            }
                            //Bloqueador
                            Locker(X, Y, "-Y");
                            //Realizar Accion
                            if (flag == false) {
                                this.level[Y][X] = 9;
                                this.level[Y - 1][X] = 0;
                                this.level[Y - 2][X] = 5;
                                this.PsayduckY = Y - 1;
                                this.restart = true;
                                this.frame = "N";
                                flag = false;
                                //sumar paso
                                moves++;
                                lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                                lose = true;
                            } else {
                                if (patience != 0) {
                                    lbl_sms.setText("Ya esta a salvo en su lugar");
                                    playVoices.Voice("src/main/resources/Voices/Lista.wav");
                                    patience--;

                                } else {
                                    angry = true;
                                }

                            }
                        } else {
                            //Futuro dialo de personaje
                            if (this.level[Y - 2][X] == 1 || this.level[Y - 2][X] == 2 || this.level[Y - 2][X] == 3 || this.level[Y - 2][X] == 4 || this.level[Y - 2][X] == 6 || this.level[Y - 2][X] == 7 || this.level[Y - 2][X] == 8) {
                                lbl_sms.setText("Hasta aqui llegamos");
                                playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                            } else {
                                playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                                lbl_sms.setText("Jamas! Que tal si se rompen por llevar las dos a la vez!?");
                            }
                        }
                    }

                }
                Refresh();
                if (angry == true) {
                    playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                    gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Angry.gif")), PsayduckX, PsayduckY);
                    patience = 2;
                    angry = false;
                }
                if (lose == true) {
                    IsClose("-Y", X, Y);
                }
            }
        }
    }

    private void moveLeft(Integer level[][], Integer X, Integer Y) throws IOException {
        Boolean flag = false, lose = false;
        //Mietras no se salga de los limites
        if (X - 1 >= 0) {
            //Mover si es piso o caja
            if (level[Y][X - 1] == 9 || level[Y][X - 1] == 5) {
                //Guardar posicion anterior
                PastPosition(X, Y);
                //Mover
                if (level[Y][X - 1] == 9) {//Si el espacio de adelante es 9 (Piso)
                    this.level[Y][X] = 9;
                    this.level[Y][X - 1] = 0;
                    this.PsayduckX = X - 1;
                    this.restart = true;
                    this.frame = "O";
                    //sumar paso
                    moves++;
                    lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                } else {//Si el espacio de adelante es 5 (Caja)
                    if (X - 2 >= 0) {
                        if (this.level[Y][X - 2] == 9 || this.level[Y][X - 2] == 10) {
                            //Validador
                            for (int i = 0; i < 5; i++) {
                                if (this.lockedCells[i] != null) {
                                    if (this.lockedCells[i].equals(String.valueOf(Y + "," + (X - 1)))) {
                                        flag = true;
                                    }
                                }
                            }
                            //Bloqueador
                            Locker(X, Y, "-X");
                            //Realizar Accion
                            if (flag == false) {
                                this.level[Y][X] = 9;
                                this.level[Y][X - 1] = 0;
                                this.level[Y][X - 2] = 5;
                                this.PsayduckX = X - 1;
                                this.restart = true;
                                this.frame = "O";
                                flag = false;
                                //sumar paso
                                moves++;
                                lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                                //Aqui va el validador de  Juego bloqueado
                                lose = true;
                            } else {
                                if (patience != 0) {
                                    lbl_sms.setText("Ya esta a salvo en su lugar");
                                    playVoices.Voice("src/main/resources/Voices/Lista.wav");
                                    patience--;

                                } else {
                                    angry = true;
                                }
                            }
                        } else {
                            //Futuro dialo de personaje 
                            if (this.level[Y][X - 2] == 1 || this.level[Y][X - 2] == 2 || this.level[Y][X - 2] == 3 || this.level[Y][X - 2] == 4 || this.level[Y][X - 2] == 6 || this.level[Y][X - 2] == 7 || this.level[Y][X - 2] == 8) {
                                lbl_sms.setText("Hasta aqui llegamos");
                                playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                            } else {
                                playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                                lbl_sms.setText("Jamas! Que tal si se rompen por llevar las dos a la vez!?");
                            }
                        }
                    }

                }
                Refresh();
                if (angry == true) {
                    playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                    gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Angry.gif")), PsayduckX, PsayduckY);
                    patience = 2;
                    angry = false;
                }
                if (lose == true) {
                    IsClose("-X", X, Y);
                }
            }
        }
    }

    private void moveDown(Integer level[][], Integer X, Integer Y) throws IOException {
        Boolean flag = false, lose = false;
        //Mietras no se salga de los limites
        if (Y + 1 <= 7) {
            //Mover si es piso o caja
            if (level[Y + 1][X] == 9 || level[Y + 1][X] == 5) {
                //Guardar posicion anterior
                PastPosition(X, Y);
                //Mover
                if (level[Y + 1][X] == 9) {//Si el espacio de adelante es 9 (Piso)
                    this.level[Y][X] = 9;
                    this.level[Y + 1][X] = 0;
                    this.PsayduckY = Y + 1;
                    this.restart = true;
                    this.frame = "S";
                    //sumar paso
                    moves++;
                    lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                } else {//Si el espacio de adelante es 5 (Caja)
                    if (Y + 2 <= 7) {
                        if (this.level[Y + 2][X] == 9 || this.level[Y + 2][X] == 10) {
                            //Validador
                            for (int i = 0; i < 5; i++) {
                                if (this.lockedCells[i] != null) {
                                    if (this.lockedCells[i].equals(String.valueOf((Y + 1) + "," + X))) {
                                        flag = true;
                                    }
                                }
                            }
                            //Bloqueador
                            Locker(X, Y, "+Y");
                            //Realizar Accion
                            if (flag == false) {
                                this.level[Y][X] = 9;
                                this.level[Y + 1][X] = 0;
                                this.level[Y + 2][X] = 5;
                                this.PsayduckY = Y + 1;
                                this.restart = true;
                                this.frame = "S";
                                flag = false;
                                //sumar paso
                                moves++;
                                lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                                lose = true;
                            } else {
                                if (patience != 0) {
                                    lbl_sms.setText("Ya esta a salvo en su lugar");
                                    playVoices.Voice("src/main/resources/Voices/Lista.wav");
                                    patience--;

                                } else {
                                    angry = true;
                                }
                            }
                        } else {
                            //Futuro dialo de personaje 
                            if (this.level[Y + 2][X] == 1 || this.level[Y + 2][X] == 2 || this.level[Y + 2][X] == 3 || this.level[Y + 2][X] == 4 || this.level[Y + 2][X] == 6 || this.level[Y + 2][X] == 7 || this.level[Y + 2][X] == 8) {
                                lbl_sms.setText("Hasta aqui llegamos");
                                playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                            } else {
                                playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                                lbl_sms.setText("Jamas! Que tal si se rompen por llevar las dos a la vez!?");
                            }
                        }

                    }

                }
                Refresh();
                if (angry == true) {
                    playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                    gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Angry.gif")), PsayduckX, PsayduckY);
                    patience = 2;
                    angry = false;
                }
                if (lose == true) {
                    IsClose("+Y", X, Y);
                }
            }
        }
    }

    private void moveRight(Integer level[][], Integer X, Integer Y) throws IOException {
        Boolean flag = false, lose = false;
        //Mietras no se salga de los limites
        if (X + 1 <= 9) {
            //Mover si es piso o caja
            if (level[Y][X + 1] == 9 || level[Y][X + 1] == 5) {
                //Guardar posicion anterior
                PastPosition(X, Y);
                //Mover
                if (level[Y][X + 1] == 9) {//Si el espacio de adelante es 9 (Piso)
                    this.level[Y][X] = 9;
                    this.level[Y][X + 1] = 0;
                    this.PsayduckX = X + 1;
                    this.restart = true;
                    this.frame = "E";
                    //sumar paso
                    moves++;
                    lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                } else {//Si el espacio de adelante es 5 (Caja)
                    if (X + 2 <= 9) {
                        if (this.level[Y][X + 2] == 9 || this.level[Y][X + 2] == 10) {
                            //Validador
                            for (int i = 0; i < 5; i++) {
                                if (this.lockedCells[i] != null) {
                                    if (this.lockedCells[i].equals(String.valueOf(Y + "," + (X + 1)))) {
                                        flag = true;
                                    }
                                }
                            }
                            //Bloqueador
                            Locker(X, Y, "+X");
                            //Realizar Accion
                            if (flag == false) {
                                this.level[Y][X] = 9;
                                this.level[Y][X + 1] = 0;
                                this.level[Y][X + 2] = 5;
                                this.PsayduckX = X + 1;
                                this.restart = true;
                                this.frame = "E";
                                flag = false;
                                //sumar paso
                                moves++;
                                lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
                                //Aqui va el validador de juego encerrado
                                lose = true;

                            } else {
                                if (patience != 0) {
                                    lbl_sms.setText("Ya esta a salvo en su lugar");
                                    playVoices.Voice("src/main/resources/Voices/Lista.wav");
                                    patience--;

                                } else {
                                    angry = true;
                                }
                            }
                        } else {
                            //Futuro dialo de personaje 
                            if (this.level[Y][X + 2] == 1 || this.level[Y][X + 2] == 2 || this.level[Y][X + 2] == 3 || this.level[Y][X + 2] == 4 || this.level[Y][X + 2] == 6 || this.level[Y][X + 2] == 7 || this.level[Y][X + 2] == 8) {
                                playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                                lbl_sms.setText("Hasta aqui llegamos");
                            } else {
                                playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                                lbl_sms.setText("Jamas! Que tal si se rompen por llevar las dos a la vez!?");
                            }
                        }
                    }

                }
                Refresh();
                if (angry == true) {
                    playVoices.Voice("src/main/resources/Voices/Psayyyy.mp3");
                    gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Angry.gif")), PsayduckX, PsayduckY);
                    patience = 2;
                    angry = false;
                }
                if (lose == true) {
                    IsClose("+X", X, Y);
                }
            }
        }
    }

    private void GoBack(Integer level[][], Integer X, Integer Y) throws IOException {
        if (this.backPsayduckX != null && this.backPsayduckY != null) {
            for (int i = 0; i < 7; i++) {
                System.arraycopy(level[i], 0, this.level[i], 0, 9);
            }
            this.PsayduckX = X;
            this.PsayduckY = Y;
            Refresh();
            this.goBack = new Integer[7][9];
            this.backPsayduckX = null;
            this.backPsayduckY = null;
            //sumar paso
            moves++;
            lbl_moves.setText("Pasos: " + String.valueOf(this.moves));
        }

    }

    @FXML
    private void MovePsayduck(KeyEvent event) throws IOException {
        if (lose == false) {
            if (levelomplete == false) {
                KeyCode keyCode = event.getCode();
                switch (keyCode) {
                    case UP:
                        moveUp(level, PsayduckX, PsayduckY);
                        break;
                    case DOWN:
                        moveDown(level, PsayduckX, PsayduckY);
                        break;
                    case LEFT:
                        moveLeft(level, PsayduckX, PsayduckY);
                        break;
                    case RIGHT:
                        moveRight(level, PsayduckX, PsayduckY);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    private void Locker(Integer X, Integer Y, String axis) {
        Random random = new Random();
        int randomNumber = random.nextInt(2);

        switch (axis) {

            case "-Y": {
                if (this.level[Y - 2][X] == 10) {
                    boolean locked = false;
                    for (String lockedCell : lockedCells) {
                        if (lockedCell != null && lockedCell.equals(String.valueOf(Y - 1 + "," + X))) {//verificador si hay una cenda bloqueda antes de la siguiente a bloquear
                            locked = true;
                            break;
                        }
                    }
                    //Si la de adelante no esta bloqueada pero la anterior si no se debe bloquear
                    if (locked == false) {
                        this.lockedCells[this.counter] = Y - 2 + "," + X;
                        this.counter++;
                        if (randomNumber == 1) {
                            playVoices.Voice("src/main/resources/Voices/Una Menos.wav");
                        } else {
                            playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                        }

                    }
                }
                break;
            }
            case "+Y": {
                if (this.level[Y + 2][X] == 10) {
                    boolean locked = false;
                    for (String lockedCell : lockedCells) {
                        if (lockedCell != null && lockedCell.equals(String.valueOf(Y + 1 + "," + X))) {//verificador si hay una cenda bloqueda antes de la siguiente a bloquear
                            locked = true;
                            break;
                        }
                    }
                    //Si la de adelante no esta bloqueada pero la anterior si no se debe bloquear
                    if (locked == false) {
                        this.lockedCells[this.counter] = Y + 2 + "," + X;
                        this.counter++;
                        if (randomNumber == 1) {
                            playVoices.Voice("src/main/resources/Voices/Una Menos.wav");
                        } else {
                            playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                        }
                    }
                }
                break;
            }
            case "-X": {
                if (this.level[Y][X - 2] == 10) {
                    boolean locked = false;
                    for (String lockedCell : lockedCells) {
                        if (lockedCell != null && lockedCell.equals(String.valueOf(Y + "," + (X - 1)))) {//verificador si hay una cenda bloqueda antes de la siguiente a bloquear
                            locked = true;
                            break;
                        }
                    }
                    //Si la de adelante no esta bloqueada pero la anterior si no se debe bloquear
                    if (locked == false) {
                        this.lockedCells[this.counter] = Y + "," + (X - 2);
                        this.counter++;
                        if (randomNumber == 1) {
                            playVoices.Voice("src/main/resources/Voices/Una Menos.wav");
                        } else {
                            playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                        }
                    }
                }
                break;
            }
            case "+X": {
                if (this.level[Y][X + 2] == 10) {
                    boolean locked = false;
                    for (String lockedCell : lockedCells) {
                        if (lockedCell != null && lockedCell.equals(String.valueOf(Y + "," + (X + 1)))) {//verificador si hay una cenda bloqueda antes de la siguiente a bloquear
                            locked = true;
                            break;
                        }
                    }
                    //Si la de adelante no esta bloqueada pero la anterior si no se debe bloquear
                    if (locked == false) {
                        this.lockedCells[this.counter] = Y + "," + (X + 2);
                        this.counter++;
                        if (randomNumber == 1) {
                            playVoices.Voice("src/main/resources/Voices/Una Menos.wav");
                        } else {
                            playVoices.Voice("src/main/resources/Voices/Psayduck.mp3");
                        }
                    }
                }
                break;
            }
        }
    }

    private void LevelComplete(Integer level) throws IOException {
        switch (level) {
            case 1: {
                if (this.level[3][1] == 5 && this.level[6][8] == 5) {
                    lbl_sms.setText("Nivel completado!");
                    btn_nextLevel.setDisable(false);
                    btn_back.setDisable(true);
                    playing++;
                    this.levelomplete = true;
                }
                break;
            }
            case 2: {
                if (this.level[2][4] == 5 && this.level[5][2] == 5 && this.level[5][5] == 5 && this.level[1][8] == 5) {
                    lbl_sms.setText("Nivel completado!");
                    btn_nextLevel.setDisable(false);
                    btn_back.setDisable(true);
                    playing++;
                    this.levelomplete = true;
                }
                break;
            }
            case 3: {
                if (this.level[2][3] == 5 && this.level[2][5] == 5 && this.level[6][8] == 5) {
                    lbl_sms.setText("Nivel completado!");
                    btn_nextLevel.setDisable(false);
                    btn_back.setDisable(true);
                    playing++;
                    this.levelomplete = true;
                }
                break;
            }
            case 4: {
                if (this.level[0][4] == 5 && this.level[7][0] == 5 && this.level[2][5] == 5 && this.level[3][4] == 5 && this.level[5][9] == 5) {
                    lbl_sms.setText("Nivel completado!");
                    btn_nextLevel.setDisable(false);
                    btn_back.setDisable(true);
                    playing++;
                    this.levelomplete = true;
                }
                break;
            }
            case 5: {
                if (this.level[0][9] == 5 && this.level[6][3] == 5 && this.level[6][9] == 5 && this.level[7][0] == 5 && this.level[7][8] == 5) {
                    lbl_sms.setText("Juego terminado!");
                    btn_nextLevel.setDisable(false);
                    btn_back.setDisable(true);
                    playing++;
                    this.levelomplete = true;
                    btn_nextLevel.setDisable(true);

                }
                break;
            }

        }
        if (this.levelomplete == true) {
            btn_down.setDisable(true);
            btn_up.setDisable(true);
            btn_left.setDisable(true);
            btn_right.setDisable(true);
            gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Happy.gif")), PsayduckX, PsayduckY);
            player.Pause();
            player.PlayOnBucle("src/main/resources/Songs/LevelCompleted.mp3");
            playVoices.Voice("src/main/resources/Voices/fake mario.wav");

        }

    }

    private void IsClose(String axis, Integer X, Integer Y) {
        Boolean flag = false;
        Boolean close = false;
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch (axis) {
            case "-X": {
                //Ciclo for para validar si la pokeball va ahi
                for (int i = 0; i < 5; i++) {
                    if (this.lockedCells[i] != null) {
                        if (this.lockedCells[i].equals(String.valueOf(Y + "," + (X - 2)))) {
                            flag = true;
                        }
                    }
                }
                if (flag == false) {//Si la pokeball esta fuera de su lugar
                    if (PsayduckX - 1 == 0) {//Si la pokeball esta en el borde Izquierdo
                        if (PsayduckY == 0 || PsayduckY == 7) {//Si la pokebola esta del todo arriba o del todo abbajo
                            close = true;
                        } else {//Si no esta pegando al borde Izquierdo
                            if (level[PsayduckY - 1][PsayduckX - 1] != 10 && level[PsayduckY - 1][PsayduckX - 1] != 9) {//Si arriba hay algo diferente a espacio para colocar o piso
                                close = true;
                            }
                            if (level[PsayduckY + 1][PsayduckX - 1] != 10 && level[PsayduckY + 1][PsayduckX - 1] != 9) {//Si debajo hay algo diferente a espacio para colocar
                                close = true;
                            }

                        }
                    } else {//Si la pokeball esta en cualquier lugar menos el borde izquierdo
                        if (PsayduckX - 2 >= 0 && PsayduckX - 1 >= 0) {
                            if (PsayduckY - 1 >= 0) {
                                if (level[PsayduckY - 1][PsayduckX - 1] != 10 && level[PsayduckY - 1][PsayduckX - 1] != 9 && level[PsayduckY - 1][PsayduckX - 1] != 5) {//Validar si al frente y arriba hay algo que bloquee 
                                    if (level[PsayduckY][PsayduckX - 2] != 10 && level[PsayduckY][PsayduckX - 2] != 9 && level[PsayduckY][PsayduckX - 2] != 5) {
                                        close = true;
                                    }
                                }
                                if (PsayduckY + 1 <= 7) {
                                    if (level[PsayduckY + 1][PsayduckX - 1] != 10 && level[PsayduckY + 1][PsayduckX - 1] != 9 && level[PsayduckY + 1][PsayduckX - 1] != 5) {//Validar si al frente y debajo hay algo que bloquee
                                        if (level[PsayduckY][PsayduckX - 2] != 10 && level[PsayduckY][PsayduckX - 2] != 9 && level[PsayduckY][PsayduckX - 2] != 5) {
                                            close = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (PsayduckY == 0 && PsayduckX - 2 >= 0) {
                        if (level[PsayduckY][PsayduckX - 2] != 10 && level[PsayduckY][PsayduckX - 2] != 9) {
                            close = true;
                        }
                    }
                    if (PsayduckY == 7 && PsayduckX - 2 >= 0) {
                        if (level[PsayduckY][PsayduckX - 2] != 10 && level[PsayduckY][PsayduckX - 2] != 9) {
                            close = true;
                        }
                    }

                }

                break;
            }
            case "+X": {
                //Ciclo for para validar si la pokeball va ahi
                for (int i = 0; i < 5; i++) {
                    if (this.lockedCells[i] != null) {
                        if (this.lockedCells[i].equals(String.valueOf(Y + "," + (X + 2)))) {
                            flag = true;
                        }
                    }
                }
                if (flag == false) {//Si la pokeball esta fuera de su lugar
                    if (PsayduckX + 1 == 9) {//Si la pokeball esta en el borde derecho
                        if (PsayduckY == 0 || PsayduckY == 7) {//Si la pokeball esta del todo ARRIBA o del todo DEBAJO
                            close = true;
                        } else {//Si no esta pegando al borde Izquierdo
                            if (level[PsayduckY - 1][PsayduckX + 1] != 10 && level[PsayduckY - 1][PsayduckX + 1] != 9) {//Si arriba hay algo diferente a espacio para colocar o piso
                                close = true;
                            }
                            if (level[PsayduckY + 1][PsayduckX + 1] != 10 && level[PsayduckY + 1][PsayduckX + 1] != 9) {//Si debajo hay algo diferente a espacio para colocar
                                close = true;
                            }

                        }
                    } else {//Si la pokeball esta en cualquier lugar menos el borde izquierdo
                        if (PsayduckX + 2 <= 9) {
                            if (PsayduckY - 1 >= 0 && PsayduckY + 1 <= 7) {
                                if (level[PsayduckY - 1][PsayduckX + 1] != 10 && level[PsayduckY - 1][PsayduckX + 1] != 9 && level[PsayduckY - 1][PsayduckX + 1] != 5) {//Validar si al frente y arriba hay algo que bloquee 
                                    if (level[PsayduckY][PsayduckX + 2] != 10 && level[PsayduckY][PsayduckX + 2] != 9 && level[PsayduckY][PsayduckX + 2] != 5) {
                                        close = true;
                                    }
                                }
                                if (PsayduckY + 1 <= 7) {
                                    if (level[PsayduckY + 1][PsayduckX + 1] != 10 && level[PsayduckY + 1][PsayduckX + 1] != 9 && level[PsayduckY + 1][PsayduckX + 1] != 5) {//Validar si al frente y debajo hay algo que bloquee
                                        if (level[PsayduckY][PsayduckX + 2] != 10 && level[PsayduckY][PsayduckX + 2] != 9 && level[PsayduckY][PsayduckX + 2] != 5) {
                                            close = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //LO acabo de hacer
                    if (PsayduckY == 0 && PsayduckX + 2 <= 9) {
                        if (level[PsayduckY][PsayduckX + 2] != 10 && level[PsayduckY][PsayduckX + 2] != 9) {
                            close = true;
                        }
                    }
                    if (PsayduckY == 7 && PsayduckX + 2 <= 9) {
                        if (level[PsayduckY][PsayduckX + 2] != 10 && level[PsayduckY][PsayduckX + 2] != 9) {
                            close = true;
                        }
                    }
                }

                break;
            }
            case "+Y": {
                //Ciclo for para validar si la pokeball va ahi
                for (int i = 0; i < 5; i++) {
                    if (this.lockedCells[i] != null) {
                        if (this.lockedCells[i].equals(String.valueOf((Y + 2) + "," + X))) {
                            flag = true;
                        }
                    }
                }
                if (flag == false) {//Si la pokeball esta fuera de su lugar
                    if (PsayduckY + 1 == 7) {//Si la pokeball esta del todo abajo
                        if (PsayduckX == 0 || PsayduckX == 9) {//Si la pokebola esta del todo IZQUIERDA o del todo DERECHA
                            close = true;
                        } else {//Si no esta pegando al borde IZQUIERDO O DERECHO
                            if (level[PsayduckY + 1][PsayduckX - 1] != 10 && level[PsayduckY + 1][PsayduckX - 1] != 9) {//Si a LA IZQUIERDA hay algo diferente a espacio para colocar o piso
                                close = true;
                            }
                            if (level[PsayduckY + 1][PsayduckX + 1] != 10 && level[PsayduckY + 1][PsayduckX + 1] != 9) {//Si A LA DERECHA hay algo diferente a espacio para colocar O PISO
                                close = true;
                            }

                        }
                    } else {//Si la pokeball esta en cualquier lugar menos el borde INFERIOR
                        if (PsayduckY + 2 <= 7) {
                            if (PsayduckX - 1 >= 0) {
                                if (level[PsayduckY + 1][PsayduckX - 1] != 10 && level[PsayduckY + 1][PsayduckX - 1] != 9 && level[PsayduckY + 1][PsayduckX - 1] != 5) {//Validar si al frente y arriba hay algo que bloquee 
                                    if (level[PsayduckY + 2][PsayduckX] != 10 && level[PsayduckY + 2][PsayduckX] != 9 && level[PsayduckY + 2][PsayduckX] != 5) {
                                        close = true;
                                    }
                                }
                                if (PsayduckX + 1 <= 9) {
                                    if (level[PsayduckY + 1][PsayduckX + 1] != 10 && level[PsayduckY + 1][PsayduckX + 1] != 9 && level[PsayduckY + 1][PsayduckX + 1] != 5) {//Validar si al frente y debajo hay algo que bloquee
                                        if (level[PsayduckY + 2][PsayduckX] != 10 && level[PsayduckY + 2][PsayduckX] != 9 && level[PsayduckY + 2][PsayduckX] != 5) {
                                            close = true;
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                if (PsayduckX == 0 && PsayduckY + 2 <= 7) {
                    if (level[PsayduckY + 2][PsayduckX] != 10 && level[PsayduckY + 2][PsayduckX] != 9) {
                        close = true;
                    }
                }
                if (PsayduckX == 9 && PsayduckY + 2 <= 7) {
                    if (level[PsayduckY + 2][PsayduckX] != 10 && level[PsayduckY + 2][PsayduckX] != 9) {
                        close = true;
                    }
                }

                break;
            }
            case "-Y": {
                //Ciclo for para validar si la pokeball va ahi
                for (int i = 0; i < 5; i++) {
                    if (this.lockedCells[i] != null) {
                        if (this.lockedCells[i].equals(String.valueOf((Y - 2) + "," + X))) {
                            flag = true;
                        }
                    }
                }
                if (flag == false) {//Si la pokeball esta fuera de su lugar
                    if (PsayduckY - 1 == 0) {//Si la pokeball esta del todo arriba
                        if (PsayduckX == 0 || PsayduckX == 9) {//Si la pokebola esta del todo IZQUIERDA o del todo DERECHA
                            close = true;
                        } else {//Si no esta pegando al borde IZQUIERDO O DERECHO
                            if (level[PsayduckY - 1][PsayduckX - 1] != 10 && level[PsayduckY - 1][PsayduckX - 1] != 9) {//Si a LA IZQUIERDA hay algo diferente a espacio para colocar o piso
                                close = true;
                            }
                            if (level[PsayduckY - 1][PsayduckX + 1] != 10 && level[PsayduckY - 1][PsayduckX + 1] != 9) {//Si A LA DERECHA hay algo diferente a espacio para colocar O PISO
                                close = true;
                            }

                        }
                    } else {//Si la pokeball esta en cualquier lugar menos el borde SUPERIOR
                        if (PsayduckY - 2 >= 0) {
                            if (PsayduckX - 1 >= 0) {
                                if (level[PsayduckY - 1][PsayduckX - 1] != 10 && level[PsayduckY - 1][PsayduckX - 1] != 9 && level[PsayduckY - 1][PsayduckX - 1] != 5) {//Validar si al frente y arriba hay algo que bloquee 
                                    if (level[PsayduckY - 2][PsayduckX] != 10 && level[PsayduckY - 2][PsayduckX] != 9 && level[PsayduckY - 2][PsayduckX] != 5) {
                                        close = true;
                                    }
                                }
                                if (PsayduckX + 1 <= 9) {
                                    if (level[PsayduckY - 1][PsayduckX + 1] != 10 && level[PsayduckY - 1][PsayduckX + 1] != 9 && level[PsayduckY - 1][PsayduckX + 1] != 5) {//Validar si al frente y debajo hay algo que bloquee
                                        if (level[PsayduckY - 2][PsayduckX] != 10 && level[PsayduckY - 2][PsayduckX] != 9 && level[PsayduckY - 2][PsayduckX] != 5) {
                                            close = true;
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

                if (PsayduckX == 0 && PsayduckY - 2 >= 0) {
                    if (level[PsayduckY - 2][PsayduckX] != 10 && level[PsayduckY - 2][PsayduckX] != 9) {
                        close = true;
                    }
                }
                if (PsayduckX == 9 && PsayduckY - 2 >= 0) {
                    if (level[PsayduckY - 2][PsayduckX] != 10 && level[PsayduckY - 2][PsayduckX] != 9) {
                        close = true;
                    }
                }

                break;
            }
        }
        if (close == true) {
            lbl_sms.setText("Juego bloqueado. Debes Reiniciar.");
            btn_down.setDisable(true);
            btn_up.setDisable(true);
            btn_left.setDisable(true);
            btn_right.setDisable(true);
            btn_back.setDisable(true);
            gp_map.add(new ImageView(new Image("file:src/main/resources/Psayduck/Psayduck_Sad.gif")), PsayduckX, PsayduckY);
            player.Pause();
            player.PlayOnBucle("src/main/resources/Songs/You Lose.mp3");
            lose = true;
            switch (randomNumber) {
                case 1:
                    playVoices.Voice("src/main/resources/Voices/error.wav");
                    break;
                case 0:
                    playVoices.Voice("src/main/resources/Voices/Psayyayay.mp3");
                    break;
                default:
                    playVoices.Voice("src/main/resources/Voices/ex.wav");
                    break;
            }
        }
    }
}
