/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Ubicacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sebad
 */
@Stateless
public class UbicacionFacade extends AbstractFacade<Ubicacion> implements UbicacionFacadeLocal {

    @PersistenceContext(unitName = "com.monte_monte-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbicacionFacade() {
        super(Ubicacion.class);
    }
    
    public Ubicacion buscarUbicacionPorNombre(String nom){
        Ubicacion u= null;
        String consulta;
        try {
            consulta = "FROM Ubicacion u WHERE u.nombre = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, nom);
            List<Ubicacion> lista = query.getResultList();
            if(!lista.isEmpty()){
                u = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return u;
    }
    
    public Ubicacion buscarUbicacionPorCoordenadas(String lon, String lat){
        Ubicacion u= null;
        String consulta;
        try {
            consulta = "FROM Ubicacion u WHERE u.lon = ?1 AND u.lat = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, lon);
            query.setParameter(2, lat);
            List<Ubicacion> lista = query.getResultList();
            if(!lista.isEmpty()){
                u = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return u;
    }
    
  /*  public String crearUbicacion(String nom, String lon, String lat){
        String ret= "";
        Ubicacion u= new Ubicacion(, lat, lon, nom);
        return ret;
    }*/
}