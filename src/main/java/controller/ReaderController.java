/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Chapter;

/**
 *
 * @author Splunk
 */

@Named 
@ViewScoped
public class ReaderController implements Serializable{
    
    @Inject
    private ComicController comicController;

    private Chapter chapter;
    
    @PostConstruct
    public void init(){
        chapter = comicController.getSelectedChapter();
        //chapter.setContentPath("../../resources/chapters/"+chapter.getContentPath());
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
    
    public ComicController getComicController() {
        return comicController;
    }

    public void setComicController(ComicController comicController) {
        this.comicController = comicController;
    }

    
}
