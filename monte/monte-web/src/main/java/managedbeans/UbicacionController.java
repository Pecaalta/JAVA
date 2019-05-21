/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import EJB.UbicacionFacadeLocal;
import entities.Ubicacion;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Mariano
 */
@Named(value = "ubicacionController")
@ViewScoped
public class UbicacionController {

    @EJB
    private UbicacionFacadeLocal ubicacionEJB;
    
    private Ubicacion ubicacion;
    
    @PostConstruct
    public void init() {
        ubicacion = new Ubicacion();
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
