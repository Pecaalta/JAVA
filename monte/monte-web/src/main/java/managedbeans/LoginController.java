/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import EJB.ClienteFacadeLocal;
import entities.Cliente;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Lucas
 */
@Named(value = "LoginController")
@SessionScoped
public class LoginController implements Serializable {

    /**
     * Creates a new instance of LoginController
     */
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    
    private Cliente cliente;
    
    @PostConstruct
    public void init() {
        cliente = new Cliente();
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
                redireccion = "/protegido/report?faces-redirect=true";
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
                redireccion = "/protegido/report?faces-redirect=true";
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
    
}
