/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByBoID", query = "SELECT p FROM Pedido p WHERE p.boID = :boID")
    , @NamedQuery(name = "Pedido.findByCantidad", query = "SELECT p FROM Pedido p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "Pedido.findByEntregado", query = "SELECT p FROM Pedido p WHERE p.entregado = :entregado")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "boID")
    private String boID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entregado")
    private boolean entregado;

    public Pedido() {
    }

    public Pedido(String boID) {
        this.boID = boID;
    }

    public Pedido(String boID, int cantidad, boolean entregado) {
        this.boID = boID;
        this.cantidad = cantidad;
        this.entregado = entregado;
    }

    public String getBoID() {
        return boID;
    }

    public void setBoID(String boID) {
        this.boID = boID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boID != null ? boID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.boID == null && other.boID != null) || (this.boID != null && !this.boID.equals(other.boID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.Pedido[ boID=" + boID + " ]";
    }
    
}
