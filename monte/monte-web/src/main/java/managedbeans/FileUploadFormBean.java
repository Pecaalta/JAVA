/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.file.Files;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Lucas
 */
@Named(value = "fileUploadFormBean")
@RequestScoped
public class FileUploadFormBean implements Serializable {

    private Part fileUpload;

    /**
     * Creates a new instance of FileUploadFormBean
     */
    public FileUploadFormBean() {
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }
    
    public void save() {
        String ruta = "C:\\Users\\Lucas\\Documents\\GitHub\\JAVA\\monte\\monte-web\\src\\main\\webapp\\images\\perfil";
        String nombreArchivo = fileUpload.getSubmittedFileName();
        try (InputStream input = fileUpload.getInputStream()) {
            Files.copy(input, new File(ruta, nombreArchivo).toPath());
        }
        catch (IOException e) {
            // Show faces message?
        }
    }
    
    
    String direccion = "/images/perfil/Screenshot_18.png";

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
