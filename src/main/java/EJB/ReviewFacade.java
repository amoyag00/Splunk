package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Chapter;
import model.Comic;
import model.Review;
import model.User;

/**
 *
 * @author splunk
 */
@Stateless
public class ReviewFacade extends AbstractFacade<Review> implements ReviewFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReviewFacade() {
        super(Review.class);
    }

    @Override
    public List<Review> list(Comic comic, boolean visibleOnly) {
        List<Review> reviews = new ArrayList<Review>();

        String queryStr;

        try {
            queryStr = "FROM Review review WHERE review.comic.comicId=?1";
            if (visibleOnly) {
                queryStr += " AND review.visible=1";
            }
            Query query = em.createQuery(queryStr);
            query.setParameter(1, comic.getComicId());
            reviews = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }

        return reviews;

    }

    @Override
    public List<Review> list(User user, boolean visibleOnly) {
        List<Review> reviews = new ArrayList<Review>();

        String queryStr;

        try {
            queryStr = "FROM Review review WHERE review.user.userId=?1";
            if (visibleOnly) {
                queryStr += " AND review.visible=1";
            }
            Query query = em.createQuery(queryStr);
            query.setParameter(1, user.getUserId());
            reviews = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }

        return reviews;

    }

}
