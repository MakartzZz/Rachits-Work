/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author david
 */
public class Progress implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pgNickname;
    private Short pgLevel;

    public Progress() {
    }

    public Progress(String pgNickname, Short level) {
        this.pgNickname = pgNickname;
        this.pgLevel= level;
    }

    public String getPgNickname() {
        return pgNickname;
    }

    public void setPgNickname(String pgNickname) {
        this.pgNickname = pgNickname;
    }

    public Short getPgLevel() {
        return pgLevel;
    }

    public void setPgLevel(Short pgLevel) {
        this.pgLevel = pgLevel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pgNickname != null ? pgNickname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Progress)) {
            return false;
        }
        Progress other = (Progress) object;
        if ((this.pgNickname == null && other.pgNickname != null) || (this.pgNickname != null && !this.pgNickname.equals(other.pgNickname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Progress[ pgNickname=" + pgNickname + " ]";
    }
    
}
