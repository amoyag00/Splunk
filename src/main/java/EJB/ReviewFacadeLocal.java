/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Comic;
import model.Review;
import model.User;

/**
 *
 * @author alex
 */
@Local
public interface ReviewFacadeLocal {

    void create(Review review);

    void edit(Review review);

    void remove(Review review);

    Review find(Object id);

    List<Review> findAll();

    List<Review> findRange(int[] range);

    int count();
    
    List<Review> list(Comic comic, boolean visibleOnly);
    
    List<Review> list (User user, boolean visibleOnly);
    
}
