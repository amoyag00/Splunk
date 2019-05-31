package controller;

import EJB.AuthorFacadeLocal;
import EJB.ChapterFacadeLocal;
import EJB.ComicEntryFacadeLocal;
import EJB.ComicFacadeLocal;
import EJB.GenreFacadeLocal;
import EJB.ReviewFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Author;
import model.Chapter;
import model.Comic;
import model.ComicEntry;
import model.Genre;
import model.Review;
import model.User;

/**
 *
 * @author splunk
 */
@Named 
@RequestScoped
public class ComicController implements Serializable{
    @EJB
    private ComicEntryFacadeLocal comicListEJB;
    
    @EJB
    private ComicFacadeLocal comicEJB;
    
    @EJB
    private ChapterFacadeLocal chapterEJB;
    
    @EJB
    private ReviewFacadeLocal reviewEJB;
    
    @EJB
    private GenreFacadeLocal genreEJB;
    
    @EJB
    private AuthorFacadeLocal authorEJB;
    
    @EJB
    private ComicEntryFacadeLocal entryEJB;
    
    @Inject
    private SearcherController searcher;
    
    private Comic comic;
    
    private Chapter selectedChapter;

    private ComicEntry comicEntry;
    
    private Review review;
    
    private List<ComicEntry> entry;
    
    private List<Review> reviewResults;
    
    private List<Author> authorResults;
    
    private List<Genre> genreResults;
    
    private List<Chapter> chapterResults;
    
    private boolean isAdded;
            
    private String genres;
    
    private String authors;
    
    private String globalScore;
    
    private String reviewText;
    
    @PostConstruct
    public void init(){
        comic = searcher.getComicSelected();
        reviewResults = reviewEJB.list(comic, true);
        authorResults = authorEJB.list((comic));
        genreResults = genreEJB.list(comic);
        chapterResults = chapterEJB.list(comic, true);
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        isAdded = comicEJB.isAdded(comic, user);
        globalScore = Double.toString(entryEJB.getGlobalScore(comic));
        
        List<Author> authors=authorEJB.list(comic);
        this.authors="";
        for(Author author: authors){
            this.authors+= author.getName()+", "+ author.getCategory()+"\n";
        }
        
        List<Genre> genres=genreEJB.list(comic);
        this.genres="";
        for(Genre genre: genres){
            this.genres+= genre.getName()+" ";
        }
    }
    
    public void addReview() {
        review = new Review();
        review.setComic(comic);
        review.setReviewText(reviewText);
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        review.setUserId(user);
        review.setVisible(true);
        review.setWrittenDate(new Date());
        reviewEJB.create(review);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Reseña añadida con éxito "));
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    
    public void setSelectedChapter(Chapter chapter){
        this.selectedChapter = chapter;
    }
    
    public Chapter getSelectedChapter(){
        return this.selectedChapter;
    }
    
    public String checkPremium(){
        String redirect="";
        if(this.chapterResults.get(0).equals(this.selectedChapter)
            || this.chapterResults.get(chapterResults.size()-1).equals(this.selectedChapter)){
            redirect = "reader.xhtml";//Change for chapter xhtml
        }else{
            User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
            Date expirationDate = user.getExpirationDate();
            long diff = expirationDate.getTime() - new Date().getTime();
            
            
            
            if(diff>0){//User suscription has not expired
                redirect="reader.xhtml"; //Change for chapter XHTML
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Su suscripción caducó el "+ dateToString(expirationDate))); 
            }
        }
        return redirect;
    }
    
    public String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es","ES"));
        return formatter.format(date);
    }

    public List<ComicEntry> getEntry() {
        return entry;
    }
    
    public void addComic() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        comicEntry = new ComicEntry();
        comicEntry.setAddedDate(new Date());
        comicEntry.setComic(comic);
        comicEntry.setComicStatus("P");
        comicEntry.setProgress(0);
        comicEntry.setUser(user);
        entryEJB.create(comicEntry);
        isAdded=true;
    }

    public ComicEntry getComicEntry() {
        return comicEntry;
    }

    public void setComicEntry(ComicEntry comicEntry) {
        this.comicEntry = comicEntry;
    }

    public void setEntry(List<ComicEntry> entry) {
        this.entry = entry;
    }

    public boolean isIsAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public SearcherController getComicSelected() {
        return searcher;
    }

    public void setComicSelected(SearcherController searcher) {
        this.searcher = searcher;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public List<Chapter> getChapterResults() {
        return chapterResults;
    }

    public void setChapterResults(List<Chapter> chapterResults) {
        this.chapterResults = chapterResults;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(String globalScore) {
        this.globalScore = globalScore;
    }

    public List<Review> getReviewResults() {
        return reviewResults;
    }

    public void setReviewResults(List<Review> reviewResults) {
        this.reviewResults = reviewResults;
    }

    public List<Author> getAuthorResults() {
        return authorResults;
    }

    public void setAuthorResults(List<Author> authorResults) {
        this.authorResults = authorResults;
    }

    public List<Genre> getGenreResults() {
        return genreResults;
    }

    public void setGenreResults(List<Genre> genreResults) {
        this.genreResults = genreResults;
    } 
}
