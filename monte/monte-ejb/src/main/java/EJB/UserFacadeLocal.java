/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mauro
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    User iniciarSesion(User c);

    User findClienteByEmail(String email);

    User iniciarSesionRedes(String email);
    
    User usuarioDeGoogle();
    
    void usuarioDeGoogleCambiarEstado(User c);
    
}
