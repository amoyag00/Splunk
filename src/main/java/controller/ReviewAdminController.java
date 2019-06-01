package controller;

import EJB.ReviewFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Review;
import model.User;

/**
 *
 * @author splunk
 */
@Named
@ViewScoped
public class ReviewAdminController implements Serializable {

    @EJB
    private ReviewFacadeLocal reviewEJB;

    private List<Review> reviews;

    @Inject
    private UserAdminController userController;

    private String nickname;

    private Review selectedReview;

    @PostConstruct
    public void init() {
        User user = userController.getSelectedUser();
        this.reviews = reviewEJB.list(user, false);
        this.nickname = user.getNickname();

    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es", "ES"));
        return formatter.format(date);
    }

    public void edit() {
        this.reviewEJB.edit(this.selectedReview);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Reseña editada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public UserAdminController getUserController() {
        return userController;
    }

    public void setUserController(UserAdminController userController) {
        this.userController = userController;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Review getSelectedReview() {
        return selectedReview;
    }

    public void setSelectedReview(Review selectedReview) {
        this.selectedReview = selectedReview;
    }

}
