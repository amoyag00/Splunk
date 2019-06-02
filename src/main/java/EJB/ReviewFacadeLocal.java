package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Comic;
import model.Review;
import model.User;

/**
 *
 * @author splunk
 */
@Local
public interface ReviewFacadeLocal {

    void create(Review review);

    void edit(Review review);

    void remove(Review review);

    Review find(Object id);

    List<Review> findAll();

    List<Review> findRange(int[] range);

    int count();

    List<Review> list(Comic comic, boolean visibleOnly);

    List<Review> list(User user, boolean visibleOnly);

}
