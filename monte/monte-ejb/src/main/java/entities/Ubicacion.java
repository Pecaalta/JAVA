/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author sebad
 */
@Entity
public class Ubicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
        name = "Ubicacion",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Ubicacion")
    private Long id;
    private float ubicacion;
    @OneToMany(mappedBy = "ubicacionidCliente")
    private Collection<Cliente> clienteCol;
    @OneToMany(mappedBy = "ubicacionidCompra")
    private Collection<Compra> compraCol;
    @OneToMany(mappedBy = "ubicacionidProducto")
    private Collection<Producto> productoCol;
    @OneToMany(mappedBy = "ubicacionidTienda")
    private Collection<Tienda> tiendaCol;

    public Ubicacion() {
    }

    public Ubicacion(Long id) {
        this.id = id;
    }

    public Ubicacion(Long id, float ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(float ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Collection<Cliente> getClienteCol() {
        return clienteCol;
    }

    public void setClienteCol(Collection<Cliente> clienteCol) {
        this.clienteCol = clienteCol;
    }

    public Collection<Compra> getCompraCol() {
        return compraCol;
    }

    public void setCompraCol(Collection<Compra> compraCol) {
        this.compraCol = compraCol;
    }

    public Collection<Producto> getProductoCol() {
        return productoCol;
    }

    public void setProductoCol(Collection<Producto> productoCol) {
        this.productoCol = productoCol;
    }

    public Collection<Tienda> getTiendaCol() {
        return tiendaCol;
    }

    public void setTiendaCol(Collection<Tienda> tiendaCol) {
        this.tiendaCol = tiendaCol;
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
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ubicacion[ id=" + id + " ]";
    }
    
}
