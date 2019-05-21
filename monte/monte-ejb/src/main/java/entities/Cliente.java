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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucas
 */
@Entity
//@XmlRootElement
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
        name = "Cliente",
        allocationSize = 1,
        initialValue = 1)
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator="Cliente")
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique=true)
    private String email;
    private String emailVerificado;
    private String sexo;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date fecNac;
    private int tipo;
    @OneToOne(mappedBy = "creadorUserId")
    private Tienda storeCollection;
    @OneToMany (mappedBy = "UseridCompra")
    private Collection<Compra> compraCol;
    @OneToMany(mappedBy = "UseridVisita")
    private Collection<Visita> visitedCol;
    @OneToMany(mappedBy = "UseridSusc")
    private Collection<Suscripcion> suscripcionCol;
    @OneToMany(mappedBy = "UseridMensaje")
    private Collection<Mensaje> mensajeCol;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ubicacion ubicacionidCliente;
    
    

    public Cliente() {
    }

    public Cliente(Long id) {
        this.id = id;
    }
    
    public Cliente(Long id, String nombre, String apellido, String email, String emailVerificado, String sexo, String password, Date fecNac) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.emailVerificado = emailVerificado;
        this.sexo = sexo;
        this.password = password;
        this.fecNac = fecNac;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerificado() {
        return emailVerificado;
    }

    public void setEmailVerificado(String emailVerificado) {
        this.emailVerificado = emailVerificado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public Tienda getStoreCollection() {
        return storeCollection;
    }

    public void setStoreCollection(Tienda storeCollection) {
        this.storeCollection = storeCollection;
    }

    public Collection<Compra> getCompraCol() {
        return compraCol;
    }

    public void setCompraCol(Collection<Compra> compraCol) {
        this.compraCol = compraCol;
    }

    public Collection<Visita> getVisitedCol() {
        return visitedCol;
    }

    public void setVisitedCol(Collection<Visita> visitedCol) {
        this.visitedCol = visitedCol;
    }

    public Collection<Suscripcion> getSuscripcionCol() {
        return suscripcionCol;
    }

    public void setSuscripcionCol(Collection<Suscripcion> suscripcionCol) {
        this.suscripcionCol = suscripcionCol;
    }

    public Collection<Mensaje> getMensajeCol() {
        return mensajeCol;
    }

    public void setMensajeCol(Collection<Mensaje> mensajeCol) {
        this.mensajeCol = mensajeCol;
    }

    public Ubicacion getUbicacionidCliente() {
        return ubicacionidCliente;
    }

    public void setUbicacionidCliente(Ubicacion ubicacionidCliente) {
        this.ubicacionidCliente = ubicacionidCliente;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cliente[ id=" + id + " ]";
    }
    
}
