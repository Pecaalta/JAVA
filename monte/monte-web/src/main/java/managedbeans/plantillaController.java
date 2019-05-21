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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author Lucas
 */
@Named(value = "plantillaController")
@ViewScoped
public class plantillaController implements Serializable{
    
    /**
     * Creates a new instance of plantillaController
     */
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    private Cliente cliente;
    public void verificarSesion(){
        
//        Cliente clGoogle;
//        clGoogle = clienteEJB.usuarioDeGoogle();
//        if(clGoogle != null){
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", clGoogle);
//            clGoogle.setGoogleActivo("");
//            clienteEJB.edit(clGoogle);
//        }
        
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            cliente = (Cliente) context.getExternalContext().getSessionMap().get("usuario");
            if(cliente == null){
               context.getExternalContext().redirect("../permisos.xhtml");
            } else {
                
            }
        } catch (Exception e) {
            //log para guardar registro de inicio de sesion
        }
    }
    
    public void printChart() {
         
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
