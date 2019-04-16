/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Comic;

/**
 *
 * @author alex
 */
@Local
public interface ComicFacadeLocal {

    void create(Comic comic);

    void edit(Comic comic);

    void remove(Comic comic);

    Comic find(Object id);

    List<Comic> findAll();

    List<Comic> findRange(int[] range);

    int count();
    
}
