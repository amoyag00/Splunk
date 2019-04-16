/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author alex
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
    public List<Review> list(Comic comic){
        List<Review> reviews= new ArrayList<Review>();
        
        String queryStr;
        
        try{
            queryStr="FROM Review review WHERE review.comic.comicId=?1";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, comic.getComicId());
            reviews=query.getResultList();      
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not access to the database");
        }
        
        return reviews; 
        
    }
    
}
