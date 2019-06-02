package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Comic;
import model.Genre;

/**
 *
 * @author splunk
 */
@Local
public interface GenreFacadeLocal {

    void create(Genre genre);

    void edit(Genre genre);

    void remove(Genre genre);

    Genre find(Object id);

    List<Genre> findAll();

    List<Genre> findRange(int[] range);

    int count();

    List<Genre> list(Comic comic);

}
