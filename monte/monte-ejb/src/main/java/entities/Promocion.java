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
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
        name = "Promocion",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Promocion")
    private Long id;
    private float porcentaje;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date start;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finish;
    private String titulo;
    private float precio;
    private int repeticion;
    private String descripcion;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Producto productoIdPromocion;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tienda storeIdPromocion;

    public Promocion() {
    }

    public Promocion(Long id) {
        this.id = id;
    }

    public Promocion(Long id, float porcentaje, Date start, String titulo, float precio, int repeticion, String descripcion) {
        this.id = id;
        this.porcentaje = porcentaje;
        this.start = start;
        this.titulo = titulo;
        this.precio = precio;
        this.repeticion = repeticion;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(int repeticion) {
        this.repeticion = repeticion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getProductoIdPromocion() {
        return productoIdPromocion;
    }

    public void setProductoIdPromocion(Producto productoIdPromocion) {
        this.productoIdPromocion = productoIdPromocion;
    }

    public Tienda getStoreIdPromocion() {
        return storeIdPromocion;
    }

    public void setStoreIdPromocion(Tienda storeIdPromocion) {
        this.storeIdPromocion = storeIdPromocion;
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
        if (!(object instanceof Promocion)) {
            return false;
        }
        Promocion other = (Promocion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Promocion[ id=" + id + " ]";
    }
    
}
