/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.ComicEntry;
import model.User;
import EJB.ComicEntryFacadeLocal;

/**
 *
 * @author splunk
 */

@Named 
@ViewScoped
public class homeController implements Serializable{
    @EJB
    private ComicEntryFacadeLocal comicListEJB;
    
    private String list;
    
    
    @PostConstruct
    public void init(){
        //TODO 
       
               
    }
    
    public void dummyEditList(){
        //TODO delete this
        list="";
        User user = new User();
        user.setUserId(1);
        List<ComicEntry> comicList=comicListEJB.getListOf(user);
        comicList.get(0).setComicStatus("P");
        comicList.get(0).setScore(9);
        
        comicList.get(1).setComicStatus("R");
        comicList.get(1).setScore(87);
        comicList.get(1).setProgress(87);
        update(comicList);

    }
    
    public void dummyDeleteEntry(){
        //TODO delete this
        User user = new User();
        user.setUserId(1);
        List<ComicEntry> comicList=comicListEJB.getListOf(user);
        comicListEJB.remove(comicList.get(comicList.size()-1));
    }
    
    
    public void dummyPrintList(){
        //TODO delete this
        list="";
        User user = new User();
        user.setUserId(1);
        List<ComicEntry> comicList=comicListEJB.getListOf(user);
        for(ComicEntry entry: comicList){
            String strEntry=entry.getComic().getName() + "       "
                    +entry.getScore()+"/100       "
                    +prettyStatus(entry.getComicStatus())+ "       "
                    +entry.getProgress()+" read of "+entry.getComic().getNumChapters()+"      "
                    +"\n";
            list+=strEntry;
        }

    }
    
    public String prettyStatus(String status){
        String prettyStatus="";
        switch(status){
            case "L":
                prettyStatus="Reading";
                break;
            case "R":
                prettyStatus="Read";
                break;
            case "P":
                prettyStatus="Plan to read";
                break;
            default:
                prettyStatus="No specified";
        }
        return prettyStatus;
    }
    
    public void update(List<ComicEntry> comicList){
        comicListEJB.update(comicList);
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
    
    
}
