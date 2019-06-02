package controller;

import EJB.RolFacadeLocal;
import EJB.UserFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Rol;
import model.User;

/**
 *
 * @author Splunk
 */
@Named
@ViewScoped
public class CreateAccountController implements Serializable {

    @EJB
    private UserFacadeLocal usuarioEJB;

    @EJB
    private RolFacadeLocal rolEJB;

    private User user;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void createUser() {
        try {

            if (user.getPassword().equals(user.getPassword2())) {
                if (isStrong(user.getPassword())) {
                    if (!usuarioEJB.exists(user.getNickname())) {

                        Rol userRol = null;
                        List<Rol> rols = rolEJB.findAll();
                        for (Rol r : rols) {
                            if (r.getRol().equals("U")) {
                                userRol = r;
                            }
                        }
                        user.setRol(userRol);
                        user.setExpirationDate(new Date());
                        user.setBanned(false);
                        usuarioEJB.create(user);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Usuario registrado"));

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El usuario ya existe"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Las contraseña ha de tener más de 8 caracteres, un número y una mayúscula"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Las contraseñas han de ser iguales"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Informacion", "Error al dar de alta el usuario"));
        }
    }

    public boolean isStrong(String password) {
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean isStrong = false;

        for (int i = 0; i < password.length(); i++) {

            if (Character.isDigit(password.charAt(i))) {
                hasNumber = true;
            }

            if (Character.isUpperCase(password.charAt(i))) {
                hasUpperCase = true;
            }

        }

        if (password.length() >= 8 && hasNumber && hasUpperCase) {
            isStrong = true;
        }

        return isStrong;
    }

}
