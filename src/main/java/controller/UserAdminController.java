package controller;

import java.util.List;
import javax.annotation.PostConstruct;
import model.User;

/**
 *
 * @author splunk
 */
public class UserAdminController {
    private List<User> userResults;
    
    private User userSelected;
    
    @PostConstruct
    public void init() {
        
    }

    public List<User> getUserResults() {
        return userResults;
    }

    public void setUserResults(List<User> userResults) {
        this.userResults = userResults;
    }

    public User getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(User userSelected) {
        this.userSelected = userSelected;
    }
    
    
    
    
}
