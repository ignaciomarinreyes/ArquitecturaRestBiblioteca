/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ignacio
 */
@Embeddable
public class AlquilerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "cliDNI")
    private String cliDNI;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "boID")
    private String boID;

    public AlquilerPK() {
    }

    public AlquilerPK(String cliDNI, String boID) {
        this.cliDNI = cliDNI;
        this.boID = boID;
    }

    public String getCliDNI() {
        return cliDNI;
    }

    public void setCliDNI(String cliDNI) {
        this.cliDNI = cliDNI;
    }

    public String getBoID() {
        return boID;
    }

    public void setBoID(String boID) {
        this.boID = boID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliDNI != null ? cliDNI.hashCode() : 0);
        hash += (boID != null ? boID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlquilerPK)) {
            return false;
        }
        AlquilerPK other = (AlquilerPK) object;
        if ((this.cliDNI == null && other.cliDNI != null) || (this.cliDNI != null && !this.cliDNI.equals(other.cliDNI))) {
            return false;
        }
        if ((this.boID == null && other.boID != null) || (this.boID != null && !this.boID.equals(other.boID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.AlquilerPK[ cliDNI=" + cliDNI + ", boID=" + boID + " ]";
    }
    
}
