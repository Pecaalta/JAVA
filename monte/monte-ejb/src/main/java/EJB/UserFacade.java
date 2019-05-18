/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mauro
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "com.monte_monte-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    @Override
    public User iniciarSesion(User c) {
        User cliente = null;
        String consulta;
        try {
            consulta = "FROM Cliente c WHERE c.email = ?1 and c.password = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, c.getEmail());
            query.setParameter(2, c.getPass());
            List<User> lista = query.getResultList();
            if(!lista.isEmpty()){
                cliente = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
    
    @Override
    public User iniciarSesionRedes(String email){
        User cliente = null;
        String consulta;
        try {
            consulta = "FROM Cliente c WHERE c.email = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, email);
            List<User> lista = query.getResultList();
            if(!lista.isEmpty()){
                cliente = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }

    @Override
    public User findClienteByEmail(String email) {
        User cliente = null;
        String consulta = "FROM Cliente c WHERE c.email = ?1";
        try{
            Query query = em.createQuery(consulta);
            query.setParameter(1, email);
            List<User> lista = query.getResultList();
                if(!lista.isEmpty()){
                    cliente = lista.get(0);
                }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }

    @Override
    public User usuarioDeGoogle() {
        User cliente = null;
        String consulta = "FROM Cliente c WHERE c.googleActivo = ?1";
        try{
            Query query = em.createQuery(consulta);
            query.setParameter(1, "activo");
            List<User> lista = query.getResultList();
                if(!lista.isEmpty()){
                    cliente = lista.get(0);
                }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
    
    @Override
    public void usuarioDeGoogleCambiarEstado(User c){
        

        String consulta = "UPDATE Cliente SET apellido = 'alamierda' "
                + "WHERE apellido = ?1";
        try{
            Query query = em.createQuery(consulta);
            query.setParameter(1, "Montelongo");
            int update = query.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    
}
