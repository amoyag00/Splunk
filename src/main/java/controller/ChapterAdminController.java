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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Chapter;
import model.Comic;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author splunk
 */
@Named
@ViewScoped
public class ChapterAdminController implements Serializable {

    @EJB
    private ComicFacadeLocal comicEJB;

    @EJB
    private ChapterFacadeLocal chapterEJB;

    private Comic comic;

    private Chapter chapterCreated;

    private Chapter chapterSelected;

    private List<Chapter> chapterResults;

    @Inject
    private ComicAdminController adminController;

    public ComicAdminController getAdminController() {
        return adminController;
    }

    public void setAdminController(ComicAdminController adminController) {
        this.adminController = adminController;
    }

    @PostConstruct
    public void init() {
        comic = adminController.getComicSeleccionado();

        chapterCreated = new Chapter();
        chapterResults = chapterEJB.list(comic, false);

    }

    public void selectChapter(Chapter chapter) {
        chapterSelected = chapter;
    }

    public void create() {
        chapterCreated.setComic(comic);
        chapterEJB.create(chapterCreated);
        this.chapterResults.add(chapterCreated);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Nuevo capítulo creado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void edit() {
        chapterEJB.edit(chapterSelected);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Capítulo editado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es", "ES"));
        return formatter.format(date);
    }

    public Chapter getChapterSelected() {
        return chapterSelected;
    }

    public void setChapterSelected(Chapter chapterSelected) {
        this.chapterSelected = chapterSelected;
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
