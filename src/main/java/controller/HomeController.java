package controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.ComicEntry;
import model.User;
import EJB.ComicEntryFacadeLocal;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author splunk
 */

@Named 
@ViewScoped
public class HomeController implements Serializable{
    @EJB
    private ComicEntryFacadeLocal comicListEJB;
    
    private List<ComicEntry>  comicList;

    public List<ComicEntry> getComicList() {
        return comicList;
    }

    public void setComicList(List<ComicEntry> comicList) {
        this.comicList = comicList;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
    private PieChartModel pieModel1;
    
    @PostConstruct
    public void init(){
        //TODO 
       createPieModel1();
       User user = new User(); 
       user.setUserId(1); // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user")
       comicList=comicListEJB.getListOf(user);        
    }
    
    public void editList(){
        //TODO delete this
   
        comicList.get(0).setComicStatus("P");
        comicList.get(0).setScore(9);
        
        comicList.get(1).setComicStatus("R");
        comicList.get(1).setScore(87);
        comicList.get(1).setProgress(87);
        update(comicList);

    }
    
    public void dummyDeleteEntry(){
        //TODO delete this
        
        comicListEJB.remove(comicList.get(comicList.size()-1));
    }
    
    public void deleteEntry(int row){
        //TODO delete this
        comicListEJB.remove(comicList.get(row));
    }
    
    /*public void dummyPrintList(){
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

    }*/
    
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

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
 
        pieModel1.set("Reading", 540);
        pieModel1.set("Read", 325);
        pieModel1.set("Plan to read", 702);
        pieModel1.set("No specified", 421);
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(true);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
