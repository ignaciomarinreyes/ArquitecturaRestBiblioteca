/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.AlquilerPK;
import entities.Clientes;
import entities.Libros;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "alquiler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquiler.findAll", query = "SELECT a FROM Alquiler a")
    , @NamedQuery(name = "Alquiler.findByCliDNI", query = "SELECT a FROM Alquiler a WHERE a.alquilerPK.cliDNI = :cliDNI")
    , @NamedQuery(name = "Alquiler.findByBoID", query = "SELECT a FROM Alquiler a WHERE a.alquilerPK.boID = :boID")
    , @NamedQuery(name = "Alquiler.findByRetorno", query = "SELECT a FROM Alquiler a WHERE a.retorno = :retorno")})
public class Alquiler implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlquilerPK alquilerPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "retorno")
    @Temporal(TemporalType.DATE)
    private Date retorno;
    @JoinColumn(name = "cliDNI", referencedColumnName = "dni", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientes clientes;
    @JoinColumn(name = "boID", referencedColumnName = "libID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Libros libros;

    public Alquiler() {
    }

    public Alquiler(AlquilerPK alquilerPK) {
        this.alquilerPK = alquilerPK;
    }

    public Alquiler(AlquilerPK alquilerPK, Date retorno) {
        this.alquilerPK = alquilerPK;
        this.retorno = retorno;
    }

    public Alquiler(String cliDNI, String boID) {
        this.alquilerPK = new AlquilerPK(cliDNI, boID);
    }

    public AlquilerPK getAlquilerPK() {
        return alquilerPK;
    }

    public void setAlquilerPK(AlquilerPK alquilerPK) {
        this.alquilerPK = alquilerPK;
    }

    public Date getRetorno() {
        return retorno;
    }

    public void setRetorno(Date retorno) {
        this.retorno = retorno;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alquilerPK != null ? alquilerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alquiler)) {
            return false;
        }
        Alquiler other = (Alquiler) object;
        if ((this.alquilerPK == null && other.alquilerPK != null) || (this.alquilerPK != null && !this.alquilerPK.equals(other.alquilerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.Alquiler[ alquilerPK=" + alquilerPK + " ]";
    }
    
}
