package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Chapter;
import model.Comic;
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
    
    @Override
    public boolean exists(ComicEntry entry){
        boolean exists= false;
        String queryStr;
        List<ComicEntry> comicList;
        int userId=entry.getUser().getUserId();
        int comicId=entry.getComic().getComicId();
        
        try{
            queryStr="FROM ComicEntry etr WHERE etr.user.userId=?1 AND etr.comic.comicId=?2";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, userId);
            query.setParameter(2, comicId);
            query.setMaxResults(1);
            comicList=query.getResultList();      
            if(!comicList.isEmpty()){
                exists=true;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }
        return exists;
    }
    @Override
    public double getGlobalScore(Comic comic){
        double score=0.0d;
        String queryStr;
        
        try{
            queryStr="SELECT AVG(etr.score) FROM ComicEntry etr WHERE etr.comic.comicId=?1";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, comic.getComicId());
            score=(double)query.getSingleResult();      
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }
        return score;
    }

    @Override
    public List<ComicEntry> searchOrder(String match, Order order) {
        List<ComicEntry> comics= new ArrayList<ComicEntry>();
        String queryStr;
        
        try{
            queryStr="FROM ComicEntry ent WHERE LOWER(ent.comic.name) LIKE CONCAT('%',?1,'%')";
            queryStr+=" ORDER BY ent.score ";
            if(order == Order.ASC){
                queryStr+="ASC";
            }else if(order == Order.DESC){
                queryStr+="DESC";
            }
            Query query = em.createQuery(queryStr);
            query.setParameter(1, match.toLowerCase());
            comics=query.getResultList();      
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }
        
        return comics;
    }
    
}
