/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Cliente;
import entities.Producto;
import entities.Tienda;
import entities.Visita;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sebad
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

    @PersistenceContext(unitName = "com.monte_monte-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    @Override
    public List<Producto> listar(){
       
        FacesContext context = FacesContext.getCurrentInstance();
        Cliente c = (Cliente) context.getExternalContext().getSessionMap().get("usuario");
        Tienda tienda = c.getStoreCollection();
        List<Producto> ct =  (List<Producto>) tienda.getProductCol();
        
        return ct;
    }
    
    @Override
    public List<Visita> visitas(){
        Query q = em.createNativeQuery("SELECT id, date, useridvisita_id, productoidvisita_id\n" +
"	FROM public.visita;", Visita.class);
        
        List<Visita> lst = q.getResultList();
        return lst;
        
    }

}
