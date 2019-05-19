/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author sebad
 */
@Entity
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
        name = "Imagen",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Imagen")
    private Long id;
    private int tabla;
    private String url;
    private int idref;

    public Imagen() {
    }

    public Imagen(Long id) {
        this.id = id;
    }

    public Imagen(Long id, int tabla, String url, int idref) {
        this.id = id;
        this.tabla = tabla;
        this.url = url;
        this.idref = idref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTabla() {
        return tabla;
    }

    public void setTabla(int tabla) {
        this.tabla = tabla;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdref() {
        return idref;
    }

    public void setIdref(int idref) {
        this.idref = idref;
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
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Imagen[ id=" + id + " ]";
    }
    
}
