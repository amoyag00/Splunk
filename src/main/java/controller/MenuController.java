/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.MenuFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Menu;
import model.User;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Splunk
 */
@Named 
@SessionScoped
public class MenuController implements Serializable {
    
    private MenuModel modelo;
    
    @EJB
    private MenuFacadeLocal menuFacadeLocal;
    
    public void init(){
        
    }
    
    public void loadMenu(){
        modelo = new DefaultMenuModel();
        String contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        List<Menu> listaMenus;
        User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        listaMenus = menuFacadeLocal.getMenus(user);
        
        for(Menu menu:listaMenus){
            if(menu.getTipo().equals("S")){
                DefaultSubMenu firstSubMenu = new DefaultSubMenu(menu.getNombre());
                for(Menu submenu: listaMenus){
                    if(submenu.getMenu_menuId()!=null){
                        if(menu.getMenuId()==submenu.getMenu_menuId().getMenuId() && submenu.getTipo().equals("I")){
                            DefaultMenuItem item = new DefaultMenuItem(submenu.getNombre());
                            item.setUrl(submenu.getUrl());//le quitamos el conexto
                            firstSubMenu.addElement(item);
                        }
                    }
                }
                modelo.addElement(firstSubMenu);
            }else{
                if(menu.getMenu_menuId()==null){
                    DefaultMenuItem item = new DefaultMenuItem(menu.getNombre());
                    item.setUrl(menu.getUrl());//le quitamos contexto
                    modelo.addElement(item);
                }
            }
        }
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }
    
    public void cerrarSesion(){
        String contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(contexto + "/index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
