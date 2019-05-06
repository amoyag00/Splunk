/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Menu;
import model.User;

/**
 *
 * @author jack
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "SplunkPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    
    @Override
    public List<Menu> getMenus(User user){
        List<Menu> listaMenus=null;
        System.out.println(user);
        try{
            String consulta="FROM Menu m WHERE m.rolId.rolId =?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1,user.getRol().getRolId());
            listaMenus = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();       
        }
        return listaMenus;
    }
}
