package controller;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.User;

/**
 *
 * @author Splunk
 */
@Named
@SessionScoped
public class SecurityController implements Serializable {

    public void checkAdminPrivileges() {
        checkPrivileges("A");
    }

    public void checkUserPrivileges() {
        checkPrivileges("U");
    }

    private void checkPrivileges(String userType) {
        User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

        if (!us.getRol().getRol().equals(userType)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/public/error.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                System.out.println("Error al redireccionar");
            }
        }
    }
}
