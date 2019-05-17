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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utilidades.Correo;
import utilidades.RandomUtilidad;

/**
 *
 * @author Lucas
 */
@Named(value = "RegistroController")
@ViewScoped
public class RegistroController implements Serializable {

    /**
     * Creates a new instance of ClienteController
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
    
    public void registrar(){
        try {
            
            Cliente prueba = new Cliente();
            prueba.setApellido(cliente.getApellido());
            prueba.setNombre(cliente.getNombre());
            prueba.setEmail(cliente.getEmail());
            prueba.setPassword(cliente.getPassword());
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
