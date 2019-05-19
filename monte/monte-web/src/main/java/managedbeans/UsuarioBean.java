package managedbeans;

import EJB.UserFacadeLocal;
import entities.User;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import utilidades.Correo;
import utilidades.RandomUtilidad;

@Named(value = "UsuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 3658300628580536494L;

        @EJB
        private UserFacadeLocal clienteEJB;
        private User user;

	
	private SocialAuthManager socialManager;
	private Profile profile;

	private final String mainURL = "http://localhost:8080/monte-web/home.xhtml";
	private final String redirectURL = "http://localhost:8080/monte-web/redirectHome.xhtml";
	//private final String redirectURL = "http://www.codewebpro.com/blog";
	private final String provider = "facebook";

        @PostConstruct
        public void init() {
            user = new User();
        }

	public void conectar() {
		Properties prop = System.getProperties();
		prop.put("graph.facebook.com.consumer_key", "329112907768865");
		prop.put("graph.facebook.com.consumer_secret", "27f824a9a4cdcd93a7afea7b4f68077d");
		prop.put("graph.facebook.com.custom_permissions", "public_profile,email");

		SocialAuthConfig socialConfig = SocialAuthConfig.getDefault();
		try {
			socialConfig.load(prop);
			socialManager = new SocialAuthManager();
			socialManager.setSocialAuthConfig(socialConfig);
			String URLRetorno = socialManager.getAuthenticationUrl(provider, redirectURL);
			FacesContext.getCurrentInstance().getExternalContext().redirect(URLRetorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPerfilUsuario() throws Exception {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Error"));
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ex.getRequest();

		Map<String, String> parametros = SocialAuthUtil.getRequestParametersMap(request);

		if (socialManager != null) {
			AuthProvider provider = socialManager.connect(parametros);
			this.setProfile(provider.getUserProfile());

		}
                
		FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
	}
    
        public String iniciarSesion(){
            String redireccion = null;
            try {
                User cl;
                cl = clienteEJB.iniciarSesion(user);
                if(cl != null){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", cl);
                    redireccion = "/protegido/report?faces-redirect=true";
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Cagamos dijo ramos"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Error"));
            }
            return redireccion;
        }

        public String iniciarSesionGoogle(){
            String redireccion = null;
            try {
                User cl;
                cl = clienteEJB.findClienteByEmail(user.getEmail());
                if(cl != null){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", cl);
                    redireccion = "/protegido/report?faces-redirect=true";
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Cagamos dijo ramos"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Error"));
            }
            return redireccion;
        }

        public String cerrarSecion(){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
            return "/?faces-redirect=true";
        }

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
        
         
        public void registrar(){
            try {
                User prueba = new User();
                prueba.setSurname(user.getSurname());
                prueba.setName(user.getName());
                prueba.setEmail(user.getEmail());
                prueba.setPass(user.getPass());
                if(clienteEJB.findClienteByEmail(prueba.getEmail()) == null){
                    user.setEmailVerificado(RandomUtilidad.randomString(10));
                    prueba.setEmailVerificado(user.getEmailVerificado());
                    clienteEJB.create(prueba);
                    Correo.verificacionCorreoCliente(prueba);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se registro el usuario"));
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso","Ya existe un usuario con ese email."));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","Error"));
            }
        }
    
        public User getCliente() {
            return user;
        }

        public void setCliente(User cliente) {
            this.user = cliente;
        }
}
