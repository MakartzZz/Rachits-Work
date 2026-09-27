/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.journeytounemployment;

/**
 *
 * @author david
 */
public class Player {
    private String Name;
    private Short level;
    
    public Player() {
        this.Name = new String();
        this.level = null;
    }
    
    public Player(String Name, Short level) {
        this.Name = Name;
        this.level = level;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }
    
    
}
