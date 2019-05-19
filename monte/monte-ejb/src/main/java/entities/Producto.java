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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sebad
 */
@Entity
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
        name = "Producto",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Producto")
    private Integer id;
    @Lob
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date published;
    private String title;
    private short outstanding;
    private int available;
    @ManyToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tienda storeId;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String description, Date published, String title, short outstanding, int available) {
        this.id = id;
        this.description = description;
        this.published = published;
        this.title = title;
        this.outstanding = outstanding;
        this.available = available;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(short outstanding) {
        this.outstanding = outstanding;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Tienda getStoreId() {
        return storeId;
    }

    public void setStoreId(Tienda storeId) {
        this.storeId = storeId;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Producto[ id=" + id + " ]";
    }
    
}
