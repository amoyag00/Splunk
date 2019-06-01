/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.GenreFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Comic;
import model.Genre;

/**
 *
 * @author splunk
 */
@Named
@ViewScoped
public class GenresAdminController implements Serializable{
    
    @EJB
    private GenreFacadeLocal genreEJB;
    
    @Inject
    private ComicAdminController comicAdminController;
    
    private Comic comic;
    
    private Genre genreAdded;
    
    private Genre genreSelected;
    
    private List<Genre> genresResults;
    
    @PostConstruct
    public void init() {
        comic = comicAdminController.getSelected();
        genresResults = genreEJB.list(comic);
        genreAdded = new Genre();
    }
    
    public void create() {
        genreAdded.setComic(comic);
        genreEJB.create(genreAdded);
    }
    
    public void edit() {
        genreEJB.edit(genreSelected);
    }
    
    public void remove(Genre genre) {
        genreEJB.remove(genre);
    }

    public GenreFacadeLocal getGenreEJB() {
        return genreEJB;
    }

    public void setGenreEJB(GenreFacadeLocal genreEJB) {
        this.genreEJB = genreEJB;
    }

    public Genre getGenreSelected() {
        return genreSelected;
    }

    public void setGenreSelected(Genre genreSelected) {
        this.genreSelected = genreSelected;
    }

    public ComicAdminController getComicAdminController() {
        return comicAdminController;
    }

    public void setComicAdminController(ComicAdminController comicAdminController) {
        this.comicAdminController = comicAdminController;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public Genre getGenreAdded() {
        return genreAdded;
    }

    public void setGenreAdded(Genre genreAdded) {
        this.genreAdded = genreAdded;
    }

    public List<Genre> getGenresResults() {
        return genresResults;
    }

    public void setGenresResults(List<Genre> genresResults) {
        this.genresResults = genresResults;
    }
}
