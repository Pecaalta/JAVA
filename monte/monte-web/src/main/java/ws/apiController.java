/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import EJB.ClienteFacadeLocal;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import entities.Cliente;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Lucas
 */
@ApplicationPath("/api")
@Path("/hola")
public class apiController extends Application {
    
    @EJB
    ClienteFacadeLocal cl;
    
    @GET
    @Path("/milagro")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String registrarAlgo(String milagro){
        String respuesta = "esperemos que esto funcione";
        Cliente c = new Cliente();
        c.setNombre(milagro);
        cl.create(c);
        return respuesta;
    }
    
    @GET
    @Path("/sd")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(){
        return "TENGO";
    }
    
    @POST
    @Path("/sesionGoogle")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String sesionGoogle(MultivaluedMap<String, String> parametros){
        
        String apellido = parametros.getFirst("apellido");
        String nombre = parametros.getFirst("nombre");
        String email = parametros.getFirst("email");
        
        if(cl.findClienteByEmail(email) == null){
            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setEmail(email);
            c.setEmailVerificado("Si");
            c.setTipo(2);
            //c.setGoogleActivo("activo");
            cl.create(c);
        }
//        else{
//            Cliente c = cl.findClienteByEmail(email);
//            c.setApellido(apellido);
//            c.setNombre(nombre);
//            cl.edit(c);
//        }
        
        return email;
    }
}
