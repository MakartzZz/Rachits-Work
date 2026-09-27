/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_DTO;

import Model.Progress;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author david
 */
public class Progress_dto {
    public SimpleStringProperty level;
    public SimpleStringProperty nickName;

    public Progress_dto() {
        this.level =  new SimpleStringProperty();
        this.nickName =  new SimpleStringProperty();
    }
    
    public Progress_dto(Progress progress) {
        this();

        this.level.set(String.valueOf(progress.getPgLevel()));

        this.nickName.set(progress.getPgNickname());
    }
    
    public Short getPersonLevel() {
        return Short.valueOf(level.get());
    }

    public void setPersonLevel(Short level) {
        this.level.set(String.valueOf(level));
    }
    
    public String getNickName() {
        return nickName.get();

    }

    public void setNickName(String name) {
        this.nickName.set(name);
    }
}
