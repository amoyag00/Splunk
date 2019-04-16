package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.ComicEntry;
import model.User;

/**
 *
 * @author splunk
 */
@Stateless
public class ComicEntryFacade extends AbstractFacade<ComicEntry> implements ComicEntryFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComicEntryFacade() {
        super(ComicEntry.class);
    }
    
    @Override
    public List<ComicEntry> getListOf(User user){
        List<ComicEntry> comicList = new ArrayList<ComicEntry>();
        String queryText="";
        try{
            queryText="FROM ComicEntry cList WHERE cList.user.userId=?1";
            Query query = em.createQuery(queryText);
            query.setParameter(1, user.getUserId());
            comicList=query.getResultList();
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Database query could not be made");
        }
        
        return comicList;
    }
    
    @Override
    public void update(List<ComicEntry> comicList){
       for(ComicEntry entry : comicList){
           edit(entry);
       } 
    }
    
}
