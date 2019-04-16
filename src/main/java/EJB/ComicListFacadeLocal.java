/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.ComicList;

/**
 *
 * @author alex
 */
@Local
public interface ComicListFacadeLocal {

    void create(ComicList comicList);

    void edit(ComicList comicList);

    void remove(ComicList comicList);

    ComicList find(Object id);

    List<ComicList> findAll();

    List<ComicList> findRange(int[] range);

    int count();
    
}
