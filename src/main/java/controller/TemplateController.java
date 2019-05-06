package controller;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import model.User;

/**
 *
 * @author Splunk
 */
@Named 
@ViewScoped
public class TemplateController implements Serializable{
    
    @Inject
    private MenuController menus;
    
    @PostConstruct
    public void init() {
        //MenuController menus = new MenuController();
        menus.loadMenu();
    }
    
    public void checkPermissions(){
       User us =(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
       //TODO check if this url has rol user.gerRol().getRolId()
      // String path = request.getRequestURI().substring(request.getContextPath().length());
      // System.out.println("URL: "+path);
        
       if(us==null){
           try {
               FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/public/error.xhtml?faces-redirect=true");
           } catch (IOException ex) {
               System.out.println("Error al redireccionar");
           }
       }
    }
}
