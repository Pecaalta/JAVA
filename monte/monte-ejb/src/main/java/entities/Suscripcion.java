/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author sebad
 */
@Entity
public class Suscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
        name = "Suscripcion",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Suscripcion")
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cliente UseridSusc;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Producto productoidSusc;

    public Suscripcion() {
    }

    public Suscripcion(Long id) {
        this.id = id;
    }

    public Suscripcion(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cliente getUseridSusc() {
        return UseridSusc;
    }

    public void setUseridSusc(Cliente UseridSusc) {
        this.UseridSusc = UseridSusc;
    }

    public Producto getProductoidSusc() {
        return productoidSusc;
    }

    public void setProductoidSusc(Producto productoidSusc) {
        this.productoidSusc = productoidSusc;
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
        if (!(object instanceof Suscripcion)) {
            return false;
        }
        Suscripcion other = (Suscripcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Suscripcion[ id=" + id + " ]";
    }
    
}
