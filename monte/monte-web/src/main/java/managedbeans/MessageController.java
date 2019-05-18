/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

//import EJB.MessageManagerLocal;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
//import org.primefaces.context.RequestContext;
import entities.Cliente;
//import entities.Message;

 
@Named(value = "MessageController")
@ViewScoped
public class MessageController implements Serializable {

    /**
     * Creates a new instance of LoginController
     */
    
//    @EJB
//    private MessageManagerLocal mmEJB;
//    
//    private List messages;
//    private Date lastUpdate;
//    public Message message;
// 
//    /**
//     * Creates a new instance of MessageBean
//     */
//    public  MessageController() {
//        messages = Collections.synchronizedList(new LinkedList());
//        lastUpdate = new Date(0);
//        message = new Message();
//    }
// 
//    public Date getLastUpdate() {
//        return lastUpdate;
//    }
// 
//    public void setLastUpdate(Date lastUpdate) {
//        this.lastUpdate = lastUpdate;
//    }
// 
//    public Message getMessage() {
//        return message;
//    }
// 
//    public void setMessage(Message message) {
//        this.message = message;
//    }
// 
//    public void sendMessage(ActionEvent evt) {
//        mmEJB.sendMessage(message);
//    }
// 
//    public void firstUnreadMessage(ActionEvent evt) {
//       RequestContext ctx = RequestContext.getCurrentInstance();
// 
//       Message m = mmEJB.getFirstAfter(lastUpdate);
// 
//       ctx.addCallbackParam("ok", m!=null);
//       if(m==null)
//           return;
// 
//       lastUpdate = m.getDateSent();
// 
//       ctx.addCallbackParam("user", m.getUser());
//       ctx.addCallbackParam("dateSent", m.getDateSent().toString());
//       ctx.addCallbackParam("text", m.getMessage());
// 
//    }
 
    
}
