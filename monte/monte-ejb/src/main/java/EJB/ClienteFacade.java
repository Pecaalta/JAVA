/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lucas
 */
@Named(value = "ClienteFacade")
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "com.monte_monte-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public Cliente iniciarSesion(Cliente c) {
        Cliente cliente = null;
        String consulta;
        try {
            consulta = "FROM Cliente c WHERE c.email = ?1 and c.password = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, c.getEmail());
            query.setParameter(2, c.getPassword());
            List<Cliente> lista = query.getResultList();
            if(!lista.isEmpty()){
                cliente = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
    
    @Override
    public Cliente iniciarSesionRedes(String email){
        Cliente cliente = null;
        String consulta;
        try {
            consulta = "FROM Cliente c WHERE c.email = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, email);
            List<Cliente> lista = query.getResultList();
            if(!lista.isEmpty()){
                cliente = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }

    @Override
    public Cliente findClienteByEmail(String email) {
        Cliente cliente = null;
        String consulta = "FROM Cliente c WHERE c.email = ?1";
        try{
            Query query = em.createQuery(consulta);
            query.setParameter(1, email);
            List<Cliente> lista = query.getResultList();
                if(!lista.isEmpty()){
                    cliente = lista.get(0);
                }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }

    @Override
    public Cliente usuarioDeGoogle() {
        Cliente cliente = null;
        String consulta = "FROM Cliente c WHERE c.googleActivo = ?1";
        try{
            Query query = em.createQuery(consulta);
            query.setParameter(1, "activo");
            List<Cliente> lista = query.getResultList();
                if(!lista.isEmpty()){
                    cliente = lista.get(0);
                }
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }
    
    @Override
    public void usuarioDeGoogleCambiarEstado(Cliente c){
        

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

    @Override
    public List<Cliente> listadoUsuariosSistema() {
        
        Cliente cliente = null;
        String consulta = "SELECT * FROM Cliente";
        try{
            Query query = em.createQuery(consulta);
            List<Cliente> lista = query.getResultList();
                if(!lista.isEmpty()){
                    return lista;
                }
        } catch (Exception e) {
            throw e;
        }
        
        return null;
    }
    
    
    
}
