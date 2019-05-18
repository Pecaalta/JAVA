/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mauro
 */
@Entity
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
            name = "Store",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "Store")
    private Integer id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date packagedTime;
    @Temporal(TemporalType.DATE)
    private Date minimumTime;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId")
    private Collection<Product> productCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId")
    private Collection<Mensaje> mensajeCollection;
    @ManyToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Location locationid;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId")
    private Collection<Promotion> promotionCollection;*/
    
    @OneToOne (fetch = FetchType.LAZY)
    private User User;

    public Store() {
    }

    public Store(Integer id) {
        this.id = id;
    }

    public Store(Integer id, String name, Date packagedTime, Date minimumTime) {
        this.id = id;
        this.name = name;
        this.packagedTime = packagedTime;
        this.minimumTime = minimumTime;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
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

    public Date getPackagedTime() {
        return packagedTime;
    }

    public void setPackagedTime(Date packagedTime) {
        this.packagedTime = packagedTime;
    }

    public Date getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(Date minimumTime) {
        this.minimumTime = minimumTime;
    }

    /*@XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<Mensaje> getMensajeCollection() {
        return mensajeCollection;
    }

    public void setMensajeCollection(Collection<Mensaje> mensajeCollection) {
        this.mensajeCollection = mensajeCollection;
    }

    public Location getLocationid() {
        return locationid;
    }

    public void setLocationid(Location locationid) {
        this.locationid = locationid;
    }

    public User getCreadorUserId() {
        return creadorUserId;
    }

    public void setCreadorUserId(User creadorUserId) {
        this.creadorUserId = creadorUserId;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Store[ id=" + id + " ]";
    }

}
