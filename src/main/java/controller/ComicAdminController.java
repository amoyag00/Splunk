/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.AuthorFacadeLocal;
import EJB.ComicFacadeLocal;
import EJB.GenreFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Author;
import model.Comic;
import model.Genre;

/**
 *
 * @author Samuel
 */
@Named 
@SessionScoped
public class ComicAdminController implements Serializable{
    
    @EJB
    private ComicFacadeLocal comicEJB;

    @EJB
    private GenreFacadeLocal genreEJB;
    
    @EJB
    private AuthorFacadeLocal authorEJB;
    
    private List<Comic>  comicList;
    private List<Author> authorList; 
    private List<Genre> genreList;
    
    private Comic newComic;
    private int day;
    private int month;
    private String newComicAuthor;
    private String newComicGenre;

    private ArrayList<String> newComicAuthorList;
    private ArrayList<String> newComicGenreList;

    private Comic comicSeleccionado;

    public Comic getComicSeleccionado() {
        return comicSeleccionado;
    }

    public void setComicSeleccionado(Comic comicSeleccionado) {
        this.comicSeleccionado = comicSeleccionado;
    }
    
    
    
    public ArrayList<String> getNewComicAuthorList() {
        return newComicAuthorList;
    }

    public void setNewComicAuthorList(ArrayList<String> newComicAuthorList) {
        this.newComicAuthorList = newComicAuthorList;
    }

    public ArrayList<String> getNewComicGenreList() {
        return newComicGenreList;
    }

    public void setNewComicGenreList(ArrayList<String> newComicGenreList) {
        this.newComicGenreList = newComicGenreList;
    }

    public String getNewComicAuthor() {
        return newComicAuthor;
    }

    public void setNewComicAuthor(String newComicAuthors) {
        this.newComicAuthor = newComicAuthors;
    }

    public String getNewComicGenre() {
        return newComicGenre;
    }

    public void setNewComicGenre(String newComicGenre) {
        this.newComicGenre = newComicGenre;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    private int year;

    public Comic getNewComic() {
        return newComic;
    }

    public void setNewComic(Comic newComic) {
        this.newComic = newComic;
    }
    
    public List<Comic> getComicList() {
        return comicList;
    }

    public void setComicList(List<Comic> comicList) {
        this.comicList = comicList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
    
    @PostConstruct
    public void init(){
        //TODO
        comicList=comicEJB.findAll();
        newComic = new Comic();
        comicSeleccionado = comicList.get(0);

    }
    public void update(List<Comic> comicList){
        comicEJB.update(comicList);
    }
    
    public List<Author> listAuthorsComic(Comic comic){
    
        return authorEJB.list(comic);
        
    }
    
    public List<Genre> listGenreComic(Comic comic){
    
        return genreEJB.list(comic);
        
    }
    
    public void setComicToChapter(int row){
    
        comicSeleccionado = comicList.get(row);
        System.out.println(comicSeleccionado.getName());
    }
    
    /*public void addAuthor(){
    
        //newComicAuthorList.add(newComicAuthor);
        System.out.println("PATATA");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion","Nuevo autor");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addGenre(){
    
        newComicGenreList.add(newComicGenre);

    }*/
    
    public void delete(int row){
    
        comicEJB.remove(comicList.get(row));
        
    }
    
    public void save(){
        newComic.setPublishDate(new Date(year,month,day));
        comicEJB.create(newComic);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion","Nuevo comic creado");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
       
        Author autor = new Author();
        autor.setName(newComicAuthor);
        autor.setCategory("Art & Script");
        autor.setComic(newComic);
        authorEJB.create(autor);

        Genre genero = new Genre();
        genero.setName(newComicGenre);
        genero.setComic(newComic);
        genreEJB.create(genero);
        
    }
    
    public String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es","ES"));
        return formatter.format(date);
    }
    
    public void onCellEdit() {


            System.out.println("////////////////////////");
            System.out.println(comicList.get(0).getName()+"  "+comicList.get(0).isVisible());
            
            update(comicList);
            comicList=comicEJB.findAll();


            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion","Comic Updated");  
            FacesContext.getCurrentInstance().addMessage(null, msg);


        }

}