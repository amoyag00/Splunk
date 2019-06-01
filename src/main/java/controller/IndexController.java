package controller;

import EJB.UserFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.User;

/**
 *
 * @author Splunk
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    @EJB
    private UserFacadeLocal userEJB;

    @Inject
    private User user;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public String checkUser() {
        String direction = "";
        try {
            if (userEJB.exists(user.getNickname())) {
                User u = userEJB.getUser(user);
                if (u == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Error", "La contraseña es incorrecta"));
                } else {
                    if (!u.isBanned()) {
                        String rol = u.getRol().getRol();
                        if (rol.equals("U")) {
                            direction = "/private/user/home.xhtml";
                        } else if (rol.equals("A")) {
                            direction = "/private/admin/comicAdmin.xhtml";
                        }

                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "Error", "Estás baneado " + user.getNickname()));
                    }

                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Error", "No existe el usuario " + user.getNickname()));
            }

        } catch (Exception e) {
        }

        return direction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
