package EJB;

import EJB.AbstractFacade.Order;
import java.util.List;
import javax.ejb.Local;
import model.Comic;
import model.User;

/**
 *
 * @author splunk
 */
@Local
public interface ComicFacadeLocal {
    
    public enum Param{
        NAME, SCORE
    }
    
   

    void create(Comic comic);

    void edit(Comic comic);

    void remove(Comic comic);

    Comic find(Object id);

    List<Comic> findAll();

    List<Comic> findRange(int[] range);

    int count();
    
    List<Comic> search(String match);
    
    List<Comic> searchOrder(String match, Order order);
    
    List<Comic> searchBy(String match, Param param, Order order);
    
    boolean isAdded(Comic comic, User user);    
}
