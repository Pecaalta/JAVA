/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Message;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Mauro
 */
@Local
public interface MessageManagerLocal {
    
    void sendMessage(Message msg);
    Message getFirstAfter(Date after);
}
