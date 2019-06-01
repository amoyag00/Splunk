package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Chapter;
import model.Comic;

/**
 *
 * @author splunk
 */
@Stateless
public class ChapterFacade extends AbstractFacade<Chapter> implements ChapterFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChapterFacade() {
        super(Chapter.class);
    }

    @Override
    public List<Chapter> list(Comic comic, boolean visibleOnly) {
        List<Chapter> chapters = new ArrayList<Chapter>();

        String queryStr;

        try {
            queryStr = "FROM Chapter c WHERE c.comic.comicId=?1";
            if (visibleOnly) {
                queryStr += " AND c.visible=1";
            }
            queryStr += " ORDER BY c.chapterNumber ASC";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, comic.getComicId());
            chapters = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }

        return chapters;
    }

}
