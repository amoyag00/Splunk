package EJB;

import EJB.AbstractFacade.Order;
import java.util.List;
import javax.ejb.Local;
import model.User;

/**
 *
 * @author splunk
 */
@Local
public interface UserFacadeLocal {
    
    

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    List<User> search(String nickname, boolean includePrivate/*, Order order*/);
}
