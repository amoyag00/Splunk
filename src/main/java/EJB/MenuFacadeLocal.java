package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Menu;
import model.User;

/**
 *
 * @author splunk
 */
@Local
public interface MenuFacadeLocal {

    void create(Menu menu);

    void edit(Menu menu);

    void remove(Menu menu);

    Menu find(Object id);

    List<Menu> findAll();

    List<Menu> findRange(int[] range);

    int count();
    
    List<Menu> getMenus(User user);
    
}
