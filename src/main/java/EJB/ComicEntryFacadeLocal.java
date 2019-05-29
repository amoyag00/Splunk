/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import EJB.AbstractFacade.Order;
import java.util.List;
import javax.ejb.Local;
import model.Comic;
import model.ComicEntry;
import model.User;

/**
 *
 * @author splunk
 */
@Local
public interface ComicEntryFacadeLocal {

    void create(ComicEntry comicEntry);

    void edit(ComicEntry comicEntry);

    void remove(ComicEntry comicEntry);

    ComicEntry find(Object id);

    List<ComicEntry> findAll();

    List<ComicEntry> findRange(int[] range);

    int count();
    
    List<ComicEntry> getListOf(User user);
    
    void update(List<ComicEntry> comicList);
    
    boolean exists(ComicEntry entry);
    
    double getGlobalScore(Comic comic);
    
    List<ComicEntry> searchOrder(String match, Order order);
}
