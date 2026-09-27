/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.journeytounemployment;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author david
 */
public class MyPlayer {

    private Media media;
    private MediaPlayer mediaplayer;

    public void PlayOnBucle(String song) {
        media = new Media(new File(song).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaplayer.setVolume(0.09);
        mediaplayer.play();
    }

    public void Pause() {
        mediaplayer.pause();
    }
    public void Click(){
        media = new Media(new File("src/main/resources/Songs/click.mp3").toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setVolume(0.57);
        mediaplayer.play();
    }
     public void Voice(String file){
        media = new Media(new File(file).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setVolume(0.57);
        mediaplayer.play();
    }
}
