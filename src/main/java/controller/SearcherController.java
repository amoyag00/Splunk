package controller;

import EJB.AbstractFacade.Order;
import EJB.ComicEntryFacadeLocal;
import EJB.ComicFacadeLocal;
import EJB.ComicFacadeLocal.Param;
import EJB.UserFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
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
@SessionScoped
public class SearcherController implements Serializable {

    @EJB
    private ComicFacadeLocal comicEJB;

    @EJB
    private UserFacadeLocal userEJB;

    @EJB
    private ComicEntryFacadeLocal entryEJB;

    private String comicResults;

    private String userResults;

    private String textSearch;

    private String searchType;

    private String orderType;

    private List<Comic> resultComics;

    private List<User> resultUsers;

    private List<ComicEntry> resultEntry;

    private Comic comicSelected;

    private User userSelected;

    public User getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(User userSelected) {
        this.userSelected = userSelected;
    }

    private boolean flagComic;
    private boolean flagUser;

    @PostConstruct
    public void init() {
        flagUser = false;
        flagComic = true;
        //TODO
        /*System.out.println("entra search");
        resultComics = comicEJB.searchOrder("dragon ball", Order.ASC);**/
    }

    public void changeResults() {
        if (searchType.equals("comicSearch")) {
            flagComic = true;
            flagUser = false;

        } else if (searchType.equals("userSearch")) {
            flagUser = true;
            flagComic = false;

        }
    }

    public void search() {
        if (searchType.equals("comicSearch")) {
            if (orderType.equals("asc")) {
                resultComics = comicEJB.searchOrder(textSearch, true, Order.ASC);
            } else {
                resultComics = comicEJB.searchOrder(textSearch, true, Order.DESC);
            }

        } else if (searchType.equals("userSearch")) {

            if (orderType.equals("asc")) {
                resultUsers = userEJB.search(textSearch, true, Order.ASC);
            } else {
                resultUsers = userEJB.search(textSearch, true, Order.DESC);
            }
        } else {
            if (orderType.equals("asc")) {
                resultEntry = entryEJB.searchOrder(textSearch, true, Order.ASC);
                resultComics = new ArrayList<Comic>();

                for (int i = 0; i < resultEntry.size(); i++) {
                    if (!resultComics.contains(resultEntry.get(i).getComic())) {
                        resultComics.add(resultEntry.get(i).getComic());
                    }
                }
            } else {
                resultEntry = entryEJB.searchOrder(textSearch, true, Order.DESC);

                resultComics = new ArrayList<Comic>();

                for (int i = 0; i < resultEntry.size(); i++) {
                    resultComics.add(resultEntry.get(i).getComic());
                }
            }
        }
    }

    public void viewComic(Comic comic) {
        comicSelected = comic;
    }

    public void dummySearchComic() {
        //TODO delete this
        List<Comic> results = comicEJB.search("on");
        for (Comic comic : results) {
            comicResults += comic.getName() + " " + comic.getImagePath() + "\n";
        }
    }

    public void dummySearchComicByName() {
        //TODO delete this
        //Param must be name or score
        //Order must be ASC or DESC or ""
        List<Comic> results = comicEJB.searchBy("", Param.NAME, Order.ASC);
        for (Comic comic : results) {
            comicResults += comic.getName() + " " + comic.getImagePath() + "\n";
        }
    }

    public void dummySearchComicByScore() {
        //TODO delete this
        //Param must be name or score
        //Order must be ASC or DESC
        List<Comic> results = comicEJB.searchBy("", Param.SCORE, Order.DESC);
        for (Comic comic : results) {
            comicResults += comic.getName() + " " + comic.getImagePath() + "\n";
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

    public List<ComicEntry> getResultEntry() {
        return resultEntry;
    }

    public void setResultEntry(List<ComicEntry> resultEntry) {
        this.resultEntry = resultEntry;
    }

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

    public Comic getComicSelected() {
        return comicSelected;
    }

    public void setComicSelected(Comic comicSelected) {
        this.comicSelected = comicSelected;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<Comic> getResultComics() {
        return resultComics;
    }

    public void setResultComics(List<Comic> resultComics) {
        this.resultComics = resultComics;
    }

    public List<User> getResultUsers() {
        return resultUsers;
    }

    public void setResultUsers(List<User> resultUsers) {
        this.resultUsers = resultUsers;
    }

    public boolean isFlagComic() {
        return flagComic;
    }

    public void setFlagComic(boolean flagComic) {
        this.flagComic = flagComic;
    }

    public boolean isFlagUser() {
        return flagUser;
    }

    public void setFlagUser(boolean flagUser) {
        this.flagUser = flagUser;
    }

}
