/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.journeytounemployment;

import javafx.scene.media.MediaPlayer;

/**
 *
 * @author david
 */
public class MyPlayer {

    private MediaPlayer mediaplayer;

    public void PlayOnBucle(String song) {
        stopCurrentPlayer();
        mediaplayer = new MediaPlayer(ResourceLoader.loadMedia(song));
        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaplayer.setVolume(0.09);
        mediaplayer.play();
    }

    public void Pause() {
        if (mediaplayer != null) {
            mediaplayer.pause();
        }
    }
    public void Click(){
        stopCurrentPlayer();
        mediaplayer = new MediaPlayer(ResourceLoader.loadMedia("src/main/resources/Songs/click.mp3"));
        mediaplayer.setVolume(0.57);
        mediaplayer.play();
    }
     public void Voice(String file){
        stopCurrentPlayer();
        mediaplayer = new MediaPlayer(ResourceLoader.loadMedia(file));
        mediaplayer.setVolume(0.57);
        mediaplayer.play();
    }

    private void stopCurrentPlayer() {
        if (mediaplayer != null) {
            mediaplayer.stop();
        }
    }
}
