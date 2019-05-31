package controller;

import EJB.UserFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Comic;
import model.User;
import org.primefaces.util.DateUtils;

/**  *
 *
 * @author splunk
 */
@Named
@ViewScoped
public class PremiumController implements Serializable {

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
    public void init() {
        usuario = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

    }

    public void save() {
        Date oldExpirationDate = usuario.getExpirationDate();
        Date today = new Date();
        Date newExpirationDate = null;
        Calendar cal = Calendar.getInstance();

        long difference = today.getTime() - oldExpirationDate.getTime();

        if (difference > 0) {//Already expired, the new expiration date will be in 1 month counting from today
            cal.setTime(today);
            cal.add(Calendar.MONTH, 1);
            newExpirationDate = cal.getTime();

        } else {//is not expired yet, the new expirationDate will be the old one plus 1 month
            cal.setTime(oldExpirationDate);
            cal.add(Calendar.MONTH, 1);
            newExpirationDate = cal.getTime();
        }

        usuario.setExpirationDate(newExpirationDate);
        userEJB.edit(usuario);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion","Su suscripción premium caduca el "+
                dateToString(newExpirationDate));
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }
    
    public String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es","ES"));
        return formatter.format(date);
    }
}
