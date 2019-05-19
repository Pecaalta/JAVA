/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sebad
 */
@Entity
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
            name = "Tienda",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "Tienda")
    private Integer id;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date packegedTime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date minimumTime;
    @OneToMany(mappedBy = "storeId")
    private Collection<Producto> productCol;
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID")
    private Cliente creadorUserId;

    public Tienda() {
    }

    public Tienda(Integer id) {
        this.id = id;
    }

    public Tienda(Integer id, String name, Date packegedTime, Date minimumTime) {
        this.id = id;
        this.name = name;
        this.packegedTime = packegedTime;
        this.minimumTime = minimumTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPackegedTime() {
        return packegedTime;
    }

    public void setPackegedTime(Date packegedTime) {
        this.packegedTime = packegedTime;
    }

    public Date getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(Date minimumTime) {
        this.minimumTime = minimumTime;
    }

    public Collection<Producto> getProductCol() {
        return productCol;
    }

    public void setProductCol(Collection<Producto> productCol) {
        this.productCol = productCol;
    }

    public Cliente getCreadorUserId() {
        return creadorUserId;
    }

    public void setCreadorUserId(Cliente creadorUserId) {
        this.creadorUserId = creadorUserId;
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
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tienda[ id=" + id + " ]";
    }

}
