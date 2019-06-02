package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Comic;
import model.Genre;

/**
 *
 * @author splunk
 */
@Stateless
public class GenreFacade extends AbstractFacade<Genre> implements GenreFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenreFacade() {
        super(Genre.class);
    }

    @Override
    public List<Genre> list(Comic comic) {
        List<Genre> genres = new ArrayList<Genre>();

        String queryStr;

        try {
            queryStr = "FROM Genre genre WHERE genre.comic.comicId=?1";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, comic.getComicId());
            genres = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }

        return genres;
    }

}
