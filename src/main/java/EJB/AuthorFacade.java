package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Author;
import model.Comic;

/**
 *
 * @author splunk
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> implements AuthorFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    @Override
    public List<Author> list(Comic comic){
        List<Author> authors= new ArrayList<Author>();
        String queryStr;
        
        try{
            queryStr="FROM Author author WHERE author.comic.comicId=?1";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, comic.getComicId());
            authors=query.getResultList();      
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }
        
        return authors;
    }
    
}
