package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Author;
import model.Comic;

/**
 *
 * @author splunk
 */
@Local
public interface AuthorFacadeLocal {

    void create(Author author);

    void edit(Author author);

    void remove(Author author);

    Author find(Object id);

    List<Author> findAll();

    List<Author> findRange(int[] range);

    int count();

    List<Author> list(Comic comic);

}
