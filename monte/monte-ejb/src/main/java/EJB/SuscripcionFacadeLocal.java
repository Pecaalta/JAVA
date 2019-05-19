/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Suscripcion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sebad
 */
@Local
public interface SuscripcionFacadeLocal {

    void create(Suscripcion suscripcion);

    void edit(Suscripcion suscripcion);

    void remove(Suscripcion suscripcion);

    Suscripcion find(Object id);

    List<Suscripcion> findAll();

    List<Suscripcion> findRange(int[] range);

    int count();
    
}
