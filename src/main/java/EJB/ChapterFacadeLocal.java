/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Chapter;
import model.Comic;

/**
 *
 * @author alex
 */
@Local
public interface ChapterFacadeLocal {

    void create(Chapter chapter);

    void edit(Chapter chapter);

    void remove(Chapter chapter);

    Chapter find(Object id);

    List<Chapter> findAll();

    List<Chapter> findRange(int[] range);

    int count();
    
    List<Chapter> list(Comic comic);
    
}
