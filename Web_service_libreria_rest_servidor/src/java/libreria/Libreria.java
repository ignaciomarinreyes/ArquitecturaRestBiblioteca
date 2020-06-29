/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "libreria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libreria.findAll", query = "SELECT l FROM Libreria l")
    , @NamedQuery(name = "Libreria.findByLibID", query = "SELECT l FROM Libreria l WHERE l.libID = :libID")
    , @NamedQuery(name = "Libreria.findByNombre", query = "SELECT l FROM Libreria l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Libreria.findByAno", query = "SELECT l FROM Libreria l WHERE l.ano = :ano")
    , @NamedQuery(name = "Libreria.findByAutor", query = "SELECT l FROM Libreria l WHERE l.autor = :autor")
    , @NamedQuery(name = "Libreria.findByEditorial", query = "SELECT l FROM Libreria l WHERE l.editorial = :editorial")
    , @NamedQuery(name = "Libreria.findByCantidad", query = "SELECT l FROM Libreria l WHERE l.cantidad = :cantidad")})
public class Libreria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "libID")
    private Integer libID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ano")
    @Temporal(TemporalType.DATE)
    private Date ano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "editorial")
    private String editorial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;

    public Libreria() {
    }

    public Libreria(Integer libID) {
        this.libID = libID;
    }

    public Libreria(Integer libID, String nombre, String autor, String editorial, int cantidad) {
        this.libID = libID;
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.cantidad = cantidad;
    }

    public Integer getLibID() {
        return libID;
    }

    public void setLibID(Integer libID) {
        this.libID = libID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (libID != null ? libID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libreria)) {
            return false;
        }
        Libreria other = (Libreria) object;
        if ((this.libID == null && other.libID != null) || (this.libID != null && !this.libID.equals(other.libID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.Libreria[ libID=" + libID + " ]";
    }
    
}
