package controller;

import EJB.AbstractFacade.Order;
import EJB.ComicEntryFacadeLocal;
import EJB.ComicFacadeLocal;
import EJB.ComicFacadeLocal.Param;
import EJB.UserFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Comic;
import model.ComicEntry;
import model.User;

/**
 *
 * @author splunk
 */
@Named 
@ViewScoped
public class SearcherController implements Serializable{
    @EJB
    private ComicFacadeLocal comicEJB;
    
    @EJB
    private UserFacadeLocal userEJB;
    
    private String comicResults;
    
    private String userResults;
    
    
    //@PostConstruct
    public void init(){
        //TODO
    }
    
    public void dummySearchComic(){
        //TODO delete this
        List<Comic> results=comicEJB.search("on");
        for(Comic comic :results){
            comicResults+=comic.getName()+" "+comic.getImagePath()+"\n";
        }
    }
    
    public void dummySearchComicByName(){
        //TODO delete this
        //Param must be name or score
        //Order must be ASC or DESC or ""
        List<Comic> results=comicEJB.searchBy("",Param.NAME,Order.ASC);
        for(Comic comic :results){
            comicResults+=comic.getName()+" "+comic.getImagePath()+"\n";
        }
    }
    
    public void dummySearchComicByScore(){
        //TODO delete this
        //Param must be name or score
        //Order must be ASC or DESC
        List<Comic> results=comicEJB.searchBy("",Param.SCORE,Order.DESC);
        for(Comic comic :results){
            comicResults+=comic.getName()+" "+comic.getImagePath()+"\n";
        }
    }
    /*public void dummySearchUser(){
        //TODO delete this
        //Order must be ASC or DESC or ""
        List<User> results=userEJB.search("", false, Order.DESC);
        for(User user :results){
            userResults+="Nickname: "+user.getNickname()+" Private profile? "+user.isPrivate()+"\n";
        }
    }*/

    public String getComicResults() {
        return comicResults;
    }

    public void setComicResults(String comicResults) {
        this.comicResults = comicResults;
    }

    public String getUserResults() {
        return userResults;
    }

    public void setUserResults(String userResults) {
        this.userResults = userResults;
    }  
}
