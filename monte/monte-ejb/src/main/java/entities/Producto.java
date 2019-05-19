/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private String descripcion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date publicado;
    private String titulo;
    private short destacado;
    private int disponible;
    private float precio;
    @ManyToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tienda storeIdProducto;
    @OneToMany(mappedBy = "productoidCompra")
    private Collection<Compra> compraCol;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ubicacion ubicacionidProducto;
    @OneToMany(mappedBy = "productoidVisita")
    private Collection<Visita> visitaCol;
    @OneToMany(mappedBy = "productoidSusc")
    private Collection<Suscripcion> suscripcionCol;
    @OneToMany(mappedBy = "productoIdPromocion")
    private Collection<Promocion> promocionCol;
    

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String descripcion, Date publicado, String titulo, short destacado, int disponible) {
        this.id = id;
        this.descripcion = descripcion;
        this.publicado = publicado;
        this.titulo = titulo;
        this.destacado = destacado;
        this.disponible = disponible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getPublicado() {
        return publicado;
    }

    public void setPublicado(Date publicado) {
        this.publicado = publicado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public short getDestacado() {
        return destacado;
    }

    public void setDestacado(short destacado) {
        this.destacado = destacado;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public Tienda getStoreIdProducto() {
        return storeIdProducto;
    }

    public void setStoreIdProducto(Tienda storeIdProducto) {
        this.storeIdProducto = storeIdProducto;
    }

    public Collection<Compra> getCompraCol() {
        return compraCol;
    }

    public void setCompraCol(Collection<Compra> compraCol) {
        this.compraCol = compraCol;
    }

    public Ubicacion getUbicacionidProducto() {
        return ubicacionidProducto;
    }

    public void setUbicacionidProducto(Ubicacion ubicacionidProducto) {
        this.ubicacionidProducto = ubicacionidProducto;
    }

    public Collection<Visita> getVisitaCol() {
        return visitaCol;
    }

    public void setVisitaCol(Collection<Visita> visitaCol) {
        this.visitaCol = visitaCol;
    }

    public Collection<Suscripcion> getSuscripcionCol() {
        return suscripcionCol;
    }

    public void setSuscripcionCol(Collection<Suscripcion> suscripcionCol) {
        this.suscripcionCol = suscripcionCol;
    }

    public Collection<Promocion> getPromocionCol() {
        return promocionCol;
    }

    public void setPromocionCol(Collection<Promocion> promocionCol) {
        this.promocionCol = promocionCol;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
