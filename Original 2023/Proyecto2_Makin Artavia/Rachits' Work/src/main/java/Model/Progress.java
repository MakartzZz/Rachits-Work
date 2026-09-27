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
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_PROGRESS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Progress.findAll", query = "SELECT p FROM Progress p"),
    @javax.persistence.NamedQuery(name = "Progress.findByPgNickname", query = "SELECT p FROM Progress p WHERE p.pgNickname = :pgNickname"),
    @javax.persistence.NamedQuery(name = "Progress.findByPgLevel", query = "SELECT p FROM Progress p WHERE p.pgLevel = :pgLevel")})
public class Progress implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PG_NICKNAME")
    private String pgNickname;
    @javax.persistence.Column(name = "PG_LEVEL")
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
