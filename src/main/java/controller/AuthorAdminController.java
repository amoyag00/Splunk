package controller;

import EJB.AuthorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Author;
import model.Comic;

/**
 *
 * @author splunk
 */
@Named
@ViewScoped
public class AuthorAdminController implements Serializable {

    @EJB
    AuthorFacadeLocal authorEJB;

    @Inject
    private ComicAdminController comicAdminController;

    private Comic comic;

    private List<Author> authors;

    private List<Author> allAuthors;

    private Author newAuthor;

    private Author selectedAuthor;

    @PostConstruct
    public void init() {
        this.comic = comicAdminController.getComicSeleccionado();
        this.authors = comicAdminController.getAuthorList();
        this.allAuthors = authorEJB.findAll();
        this.newAuthor = new Author();

    }

    public void delete(Author author) {
        authorEJB.remove(author);
        authors.remove(author);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Autor eliminado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void edit() {
        authorEJB.edit(selectedAuthor);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Autor editado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void create() {
        newAuthor.setComic(comic);
        authorEJB.create(newAuthor);
        authors.add(newAuthor);
        newAuthor = new Author();

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Autor creado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Author getSelectedAuthor() {
        return selectedAuthor;
    }

    public void setSelectedAuthor(Author selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }

    public Author getNewAuthor() {
        return newAuthor;
    }

    public void setNewAuthor(Author newAuthor) {
        this.newAuthor = newAuthor;
    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public void setAllAuthors(List<Author> allAuthors) {
        this.allAuthors = allAuthors;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

}
