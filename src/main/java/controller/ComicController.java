package controller;

import EJB.AuthorFacadeLocal;
import EJB.ChapterFacadeLocal;
import EJB.ComicEntryFacadeLocal;
import EJB.ComicFacadeLocal;
import EJB.GenreFacadeLocal;
import EJB.ReviewFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
@ViewScoped
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
    
    private ComicEntry comicEntry;
    
    private List<ComicEntry> entry;
    
    private List<Review> reviewResults;
    
    private List<Author> authorResults;
    
    private List<Genre> genreResults;
    
    private List<Chapter> chapterResults;
    
    private boolean isAdded;
            
    private String genres;//TODO deldete
    
    private String authors;//TODO deldete
    
    private String globalScore;//TODO deldete
    
    @PostConstruct
    public void init(){
        comic = searcher.getComicSelected();
        reviewResults = reviewEJB.list(comic);
        authorResults = authorEJB.list((comic));
        genreResults = genreEJB.list(comic);
        chapterResults = chapterEJB.list(comic);
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
    }

    public ComicEntry getComicEntry() {
        return comicEntry;
    }

    public void setComicEntry(ComicEntry comicEntry) {
        this.comicEntry = comicEntry;
    }

    /*public void search(){
    List<Comic> tmp;
    tmp = comicEJB.search("dragon ball");
    comic = tmp.get(0);
    <<<<<<< HEAD
    <<<<<<< HEAD
    =======
    =======
    >>>>>>> origin/menus
    reviewResults = reviewEJB.list(comic);
    System.out.println(reviewResults);
    authorResults = authorEJB.list((comic));
    genreResults = genreEJB.list(comic);
    chapterResults = chapterEJB.list(comic);
    List<Author> authors=authorEJB.list(comic);
    this.authors="";
    for(Author author: authors){
    this.authors+= author.getName()+", "+ author.getCategory()+"\n";
    }
    List<Genre> genres=genreEJB.list(comic);    
    chapterList="";
    this.genres="";
    for(Genre genre: genres){
    this.genres+= genre.getName()+" ";
    }
    <<<<<<< HEAD
    >>>>>>> origin/comic
    =======
    >>>>>>> origin/menus
    }
    public void dummyAddEntry(){
    //TODO delete this
    User user = new User();
    user.setUserId(1);
    Comic comic = comicEJB.find(3);
    ComicEntry entry= new ComicEntry();
    entry.setUser(user);
    entry.setComic(comic);
    entry.setComicStatus("P");
    entry.setScore(50);
    entry.setProgress(40);
    entry.setListId(1);
    entry.setAddedDate(new Date());
    comicListEJB.create(entry);
    }
    public void dummyIsAdded(){
    //TODO delete this
    User user = new User();
    user.setUserId(1);
    Comic comic = comicEJB.find(3);
    ComicEntry entry= new ComicEntry();
    entry.setUser(user);
    entry.setComic(comic);
    entry.setComicStatus("P");
    entry.setScore(50);
    entry.setProgress(40);
    entry.setListId(1);
    entry.setAddedDate(new Date());
    this.isAdded=comicListEJB.exists(entry);
    }
    public void dummyListChapters(){
    //TODO delete this
    comic=comicEJB.find(3);
    List<Chapter> chapters=chapterEJB.list(comic);
    chapterList="";
    for(Chapter chapter : chapters){
    chapterList+= "Chapter "+chapter.getChapterNumber()+"      "
    +"Chapter name:  "+chapter.getChapterName()+ "      "
    +" Pdf Path: "+chapter.getContentPath()+ "      "
    +"date Added: "+chapter.getAddedDate()+"\n";
    }
    }    
    public void dummyListReviews(){
    //TODO delete this
    comic=comicEJB.find(3);
    List<Review> reviews=reviewEJB.list(comic);
    chapterList="";
    for(Review review : reviews){
    reviewList+= "User "+review.getUser().getNickname()+"      "
    +"Text:  "+review.getReviewText()+"\n";
    }
    }
    public void dummyListGenres(){
    //TODO delete this
    comic=comicEJB.find(3);
    List<Genre> genres=genreEJB.list(comic);
    chapterList="";
    this.genres="";
    for(Genre genre: genres){
    this.genres+= genre.getName()+" ";
    }
    }
    public void dummyListAuthors(){
    //TODO delete this
    comic=comicEJB.find(3);
    List<Author> authors=authorEJB.list(comic);
    this.authors="";
    for(Author author: authors){
    this.authors+= author.getName()+", "+ author.getCategory()+"\n";
    }
    }    
    public void dummyGetGlobalSocre(){
    //TODO delete this
    comic=comicEJB.find(3);
    this.globalScore=String.valueOf(comicListEJB.getGlobalScore(comic));
    }*/
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
