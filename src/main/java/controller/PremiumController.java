/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UserFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Comic;
import model.User;

/**   
    
 *
 * @author Samuel
 */

@Named 
@ViewScoped
public class PremiumController implements Serializable{
   @EJB
   private UserFacadeLocal userEJB;
   
   private User usuario;

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
   
   @PostConstruct
   public void init(){
       usuario = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
       
   }
    
   public void save(){
          
       Date expirationDate = new Date();
       
       int newMonth = (expirationDate.getMonth()+ 1)%12;
       expirationDate.setMonth(newMonth);
       
       usuario.setExpirationDate(expirationDate);
              userEJB.edit(usuario);

       System.out.println(expirationDate.toString());
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion",expirationDate.toString()+"");  
       FacesContext.getCurrentInstance().addMessage(null, msg);
   
   }
}
