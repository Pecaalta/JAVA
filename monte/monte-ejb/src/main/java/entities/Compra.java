/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author sebad
 */
@Entity
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
        name = "Compra",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Compra")
    private Long id;
    private String comentario;
    private String calificacion;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ubicacion ubicacionidCompra;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cliente UseridCompra;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Producto productoidCompra;

    public Compra() {
    }

    public Compra(Long id) {
        this.id = id;
    }
    
    public Compra(Long id, String comentario, String calificacion) {
        this.id = id;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Ubicacion getUbicacionidCompra() {
        return ubicacionidCompra;
    }

    public void setUbicacionidCompra(Ubicacion ubicacionidCompra) {
        this.ubicacionidCompra = ubicacionidCompra;
    }

    public Cliente getUseridCompra() {
        return UseridCompra;
    }

    public void setUseridCompra(Cliente UseridCompra) {
        this.UseridCompra = UseridCompra;
    }

    public Producto getProductoidCompra() {
        return productoidCompra;
    }

    public void setProductoidCompra(Producto productoidCompra) {
        this.productoidCompra = productoidCompra;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compra[ id=" + id + " ]";
    }
    
}
