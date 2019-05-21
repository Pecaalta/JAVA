/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Ubicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sebad
 */
@Local
public interface UbicacionFacadeLocal {

    void create(Ubicacion ubicacion);

    void edit(Ubicacion ubicacion);

    void remove(Ubicacion ubicacion);

    Ubicacion find(Object id);

    List<Ubicacion> findAll();

    List<Ubicacion> findRange(int[] range);

    int count();
    
}
