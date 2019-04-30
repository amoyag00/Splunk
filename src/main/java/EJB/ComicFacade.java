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
public class ComicFacade extends AbstractFacade<Comic> implements ComicFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComicFacade() {
        super(Comic.class);
    }
    
    @Override
    public List<Comic> search(String match){
        List<Comic> comics= new ArrayList<Comic>();
        String queryStr;
        
        try{
            queryStr="FROM Comic comic WHERE LOWER(comic.name) LIKE CONCAT('%',?1,'%')";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, match.toLowerCase());
            comics=query.getResultList();      
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }
        
        return comics;
    }
    
    @Override
    public List<Comic> searchOrder(String match, Order order){
        List<Comic> comics= new ArrayList<Comic>();
        String queryStr;
        
        try{
            queryStr="FROM Comic comic WHERE LOWER(comic.name) LIKE CONCAT('%',?1,'%')";
            queryStr+=" ORDER BY comic.name ";
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
    
    @Override
    public List<Comic> searchBy(String match, Param param, Order order){
        List<Comic> comics= new ArrayList<Comic>();
        String queryStr="";
        
        try{
            if(param==Param.NAME){ 
                queryStr= "FROM Comic comic WHERE comic.name LIKE CONCAT('%',?1,'%') ORDER BY comic.name ";
            }else if(param == Param.SCORE){
                queryStr+="SELECT etr.comic FROM ComicEntry etr WHERE LOWER(etr.comic.name) LIKE CONCAT('%',?1,'%') GROUP BY etr.comic.comicId ORDER BY AVG(etr.score) ";
            }
            
            if(order == order.ASC){
                queryStr+="ASC";
            }else if(order == order.DESC){
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
