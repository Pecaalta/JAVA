package managedbeans;

import EJB.ClienteFacadeLocal;
import entities.Cliente;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import utilidades.Correo;
import utilidades.RandomUtilidad;

@Named(value= "UsuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {


    private static final long serialVersionUID = 3658300628580536494L;
	
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
    
    //registro
    
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
    
    
    //login
    
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
