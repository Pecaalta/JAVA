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
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
     @TableGenerator(
        name = "Mensaje",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Mensaje")
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date end;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date start;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tienda storeIdMensaje;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cliente UseridMensaje;

    public Mensaje() {
    }

    public Mensaje(Long id) {
        this.id = id;
    }

    public Mensaje(Long id, Date end, Date start) {
        this.id = id;
        this.end = end;
        this.start = start;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Tienda getStoreIdMensaje() {
        return storeIdMensaje;
    }

    public void setStoreIdMensaje(Tienda storeIdMensaje) {
        this.storeIdMensaje = storeIdMensaje;
    }

    public Cliente getUseridMensaje() {
        return UseridMensaje;
    }

    public void setUseridMensaje(Cliente UseridMensaje) {
        this.UseridMensaje = UseridMensaje;
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
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Mensaje[ id=" + id + " ]";
    }
    
}
