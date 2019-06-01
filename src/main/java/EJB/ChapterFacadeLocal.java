package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Chapter;
import model.Comic;

/**
 *
 * @author splunk
 */
@Local
public interface ChapterFacadeLocal {

    void create(Chapter chapter);

    void edit(Chapter chapter);

    void remove(Chapter chapter);

    Chapter find(Object id);

    List<Chapter> findAll();

    List<Chapter> findRange(int[] range);

    int count();

    List<Chapter> list(Comic comic, boolean visibleOnly);

}
