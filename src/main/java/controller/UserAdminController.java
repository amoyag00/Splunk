package controller;

import EJB.ComicFacadeLocal;
import EJB.UserFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.User;

/**
 *
 * @author splunk
 */
@Named
@RequestScoped
public class UserAdminController implements Serializable{
    private List<User> userResults;
    
    private User selectedUser;
    
    @EJB
    private UserFacadeLocal userEJB;
    
    @PostConstruct
    public void init() {
        userResults=userEJB.findAll();
    }

    public List<User> getUserResults() {
        return userResults;
    }

    public void setUserResults(List<User> userResults) {
        this.userResults = userResults;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }  
    
    
    public void edit(){
        userEJB.edit(selectedUser);
    }
}
