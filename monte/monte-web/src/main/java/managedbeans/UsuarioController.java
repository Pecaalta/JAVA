/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import EJB.ClienteFacadeLocal;
import EJB.TiendaFacadeLocal;
import entities.Cliente;
import entities.Pago;
import entities.Tienda;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utilidades.Correo;
import utilidades.RandomUtilidad;

/**
 *
 * @author Lucas
 */
@Named(value = "UsuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    @EJB
    private TiendaFacadeLocal tiendaEJB;
    
    private Cliente cliente;
    private Tienda tienda;    

    
    @PostConstruct
    public void init() {
        cliente = new Cliente();
        tienda = new Tienda();
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String iniciarSesion(){
        String redireccion = null;
        try {
            Cliente cl;
            cl = clienteEJB.iniciarSesion(cliente);
            if(cl != null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", cl);
                redireccion = "/protegido/CantidadProductos?faces-redirect=true";
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Cagamos dijo ramos"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Error"));
        }
        return redireccion;
    }
    
    public String iniciarSesionGoogle(){
        String redireccion = null;
        try {
            Cliente cl;
            cl = clienteEJB.findClienteByEmail(cliente.getEmail());
            if(cl != null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", cl);
                redireccion = "/protegido/principal?faces-redirect=true";
                cliente = cl;
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Cagamos dijo ramos"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Error"));
        }
        return redireccion;
    }
    
    public String cerrarSecion(){
       
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        return "/?faces-redirect=true";
    }
    
    
    
    
    
    public void registrarTienda(){
        try {
            Cliente prueba = new Cliente();
            prueba.setApellido(cliente.getApellido());
            prueba.setNombre(cliente.getNombre());            
            prueba.setTipo(1);
            prueba.setEmail(cliente.getEmail());
            prueba.setPassword(cliente.getPassword());
            
            Tienda pruebaTienda = new Tienda();
            pruebaTienda.setNombre(tienda.getNombre());
            pruebaTienda.setPackegedTime(tienda.getPackegedTime());            
            pruebaTienda.setMinimumTime(tienda.getMinimumTime());
            
            
            
            if(clienteEJB.findClienteByEmail(prueba.getEmail()) == null){
                cliente.setEmailVerificado(RandomUtilidad.randomString(10));
                prueba.setEmailVerificado(cliente.getEmailVerificado());
                tiendaEJB.create(pruebaTienda);
                prueba.setStoreCollection(pruebaTienda);
                clienteEJB.create(prueba);
                Correo.verificacionCorreoCliente(prueba);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se registro el usuario"));
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Ya existe un usuario con ese email."));
            }

            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","Error"));
        }
    }
    
    
    public void registrar(){
        try {
            Cliente prueba = new Cliente();
            prueba.setApellido(cliente.getApellido());
            prueba.setNombre(cliente.getNombre());            
            prueba.setTipo(2);
            prueba.setEmail(cliente.getEmail());
            prueba.setPassword(cliente.getPassword());
            prueba.setTipo(2);//0 admin monte, 1 admin tienda, 2 cliente normal
            if(clienteEJB.findClienteByEmail(prueba.getEmail()) == null){
                cliente.setEmailVerificado(RandomUtilidad.randomString(10));
                prueba.setEmailVerificado(cliente.getEmailVerificado());
                clienteEJB.create(prueba);
                Correo.verificacionCorreoCliente(prueba);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se registro el usuario"));
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Ya existe un usuario con ese email."));
            }

            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","Error"));
        }
    }
    
    
    
    
}
