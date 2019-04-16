package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Comic;

/**
 *
 * @author alex
 */
@Local
public interface ComicFacadeLocal {

    void create(Comic comic);

    void edit(Comic comic);

    void remove(Comic comic);

    Comic find(Object id);

    List<Comic> findAll();

    List<Comic> findRange(int[] range);

    int count();
    
}
