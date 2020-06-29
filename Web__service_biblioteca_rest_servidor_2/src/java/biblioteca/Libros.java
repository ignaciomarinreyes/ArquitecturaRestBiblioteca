/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "libros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libros.findAll", query = "SELECT l FROM Libros l")
    , @NamedQuery(name = "Libros.findByLibID", query = "SELECT l FROM Libros l WHERE l.libID = :libID")
    , @NamedQuery(name = "Libros.findByNombre", query = "SELECT l FROM Libros l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Libros.findByAno", query = "SELECT l FROM Libros l WHERE l.ano = :ano")
    , @NamedQuery(name = "Libros.findByAutor", query = "SELECT l FROM Libros l WHERE l.autor = :autor")
    , @NamedQuery(name = "Libros.findByEditorial", query = "SELECT l FROM Libros l WHERE l.editorial = :editorial")
    , @NamedQuery(name = "Libros.findByCantidad", query = "SELECT l FROM Libros l WHERE l.cantidad = :cantidad")
    , @NamedQuery(name = "Libros.findByPopularidad", query = "SELECT l FROM Libros l WHERE l.popularidad = :popularidad")})
public class Libros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "libID")
    private String libID;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "popularidad")
    private int popularidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "libros")
    private Collection<Alquiler> alquilerCollection;

    public Libros() {
    }

    public Libros(String libID) {
        this.libID = libID;
    }

    public Libros(String libID, String nombre, String autor, String editorial, int cantidad, int popularidad) {
        this.libID = libID;
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.cantidad = cantidad;
        this.popularidad = popularidad;
    }

    public String getLibID() {
        return libID;
    }

    public void setLibID(String libID) {
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

    public int getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(int popularidad) {
        this.popularidad = popularidad;
    }

    @XmlTransient
    public Collection<Alquiler> getAlquilerCollection() {
        return alquilerCollection;
    }

    public void setAlquilerCollection(Collection<Alquiler> alquilerCollection) {
        this.alquilerCollection = alquilerCollection;
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
        if (!(object instanceof Libros)) {
            return false;
        }
        Libros other = (Libros) object;
        if ((this.libID == null && other.libID != null) || (this.libID != null && !this.libID.equals(other.libID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.Libros[ libID=" + libID + " ]";
    }
    
}
