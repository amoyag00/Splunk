/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ChapterFacadeLocal;
import EJB.ComicFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Chapter;
import model.Comic;

/**
 *
 * @author jack
 */

@Named
@ViewScoped
public class ChapterAdminController implements Serializable{
    
    @EJB
    private ComicFacadeLocal comicEJB;
    
    @EJB
    private ChapterFacadeLocal chapterEJB;
    
    private Comic comic;
    
    private Chapter chapterCreated;
    
    private List<Chapter> chapterResults;
    
    @PostConstruct
    public void init() {
        prueba();
    }
    
    public void prueba() {
        List<Comic> tmp;
        tmp = comicEJB.search("dragon ball");
        comic = tmp.get(0);
        chapterResults = chapterEJB.list(comic);
    }
    
    public String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es","ES"));
        return formatter.format(date);
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public List<Chapter> getChapterResults() {
        return chapterResults;
    }

    public void setChapterResults(List<Chapter> chapterResults) {
        this.chapterResults = chapterResults;
    }

    public Chapter getChapterCreated() {
        return chapterCreated;
    }

    public void setChapterCreated(Chapter chapterCreated) {
        this.chapterCreated = chapterCreated;
    }
}
