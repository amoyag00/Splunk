package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Comic;
import model.User;

/**
 *
 * @author splunk
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public List<User> search(String match, boolean excludePrivate, Order order) {
        List<User> users = new ArrayList<User>();
        String queryStr;

        try {
            queryStr = "FROM User user WHERE LOWER(user.nickname) LIKE CONCAT('%',?1,'%')";
            if (excludePrivate) {
                queryStr += " AND user.isPrivate=0";
            }
            queryStr += " ORDER BY user.nickname ";
            if (order == Order.ASC) {
                queryStr += "ASC";
            } else if (order == Order.DESC) {
                queryStr += "DESC";
            }
            Query query = em.createQuery(queryStr);
            query.setParameter(1, match.toLowerCase());
            users = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }

        return users;
    }

    @Override
    public User getUser(User user) {
        User u = null;
        List<User> userList;
        try {
            String q = "FROM User u WHERE u.nickname=?1 and u.password=?2";
            Query query = em.createQuery(q);
            query.setParameter(1, user.getNickname());
            query.setParameter(2, user.getPassword());
            userList = query.getResultList();
            if (!userList.isEmpty()) {
                u = userList.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error desde el modelo al consultar la base de datos");
        }

        return u;
    }

    @Override
    public boolean exists(String nickname) {
        boolean exists = false;
        String q;
        List<User> userList;

        try {
            q = "FROM User u WHERE u.nickname=?1";
            Query query = em.createQuery(q);
            query.setParameter(1, nickname);
            userList = query.getResultList();
            if (!userList.isEmpty()) {
                exists = true;
            }
        } catch (Exception e) {
            System.out.println("NO hemos podido consultar la base de datos");
        }
        return exists;
    }

}
