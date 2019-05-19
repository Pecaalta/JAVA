/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Visita;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sebad
 */
@Stateless
public class VisitaFacade extends AbstractFacade<Visita> implements VisitaFacadeLocal {

    @PersistenceContext(unitName = "com.monte_monte-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VisitaFacade() {
        super(Visita.class);
    }
    
}
