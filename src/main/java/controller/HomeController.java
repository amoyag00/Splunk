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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.CellEditEvent;
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
    
    @Inject
    private SearcherController searchController;
    
    private List<ComicEntry>  comicList;
    private String statusComic[] = new String[4];
    private boolean editable=true;
    
    private User usuario;

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
    
    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
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
       
       usuario = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
       
       if(searchController!=null){
           if(searchController.getUserSelected()!=null){
                if(!usuario.equals(searchController.getUserSelected())){

                    editable = false;
                    usuario=searchController.getUserSelected();
                    searchController.setUserSelected(null);
                }
           }
       }
       
       comicList=comicListEJB.getListOf(usuario);
       statusComic[0] = "L";
       statusComic[1] = "R";
       statusComic[2] = "P";
       statusComic[3] = "";
       createPieModel1();        
    }

    public String[] getStatusComic() {
        return statusComic;
    }

    public void setStatusComic(String[] statusComic) {
        this.statusComic = statusComic;
    }
    
    public void deleteEntry(int row){
        //TODO delete this
        comicListEJB.remove(comicList.get(row));
        comicList.remove(row);
        createPieModel1();
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
    
    
    
    public String prettyStatus(String status){
        String prettyStatus="";
        if (status==null){
                status="";
        }
        switch(status){
            case "L":
                prettyStatus="Leyendo";
                break;
            case "R":
                prettyStatus="Leido";
                break;
            case "P":
                prettyStatus="Pendiente";
                break;
            default:
                prettyStatus="Sin especificar";
        }
        return prettyStatus;
    }
    
    public void update(List<ComicEntry> comicList){
        comicListEJB.update(comicList);
    }

    public void createPieModel1() {
        pieModel1 = new PieChartModel();
 
        int rding=0;
        int r=0;
        int ptr=0;
        int ns=0;
        
        for(int i=0;i<comicList.size();i++){
            String status = comicList.get(i).getComicStatus();
            if (status==null){
                status="";
            }
            switch(status){
                case "L":
                    rding++;
                    break;
                case "R":
                    r++;
                    break;
                case "P":
                    ptr++;
                    break;
                default:
                    ns++;
            }
        
        }
        
        pieModel1.set("Leyendo", rding);
        pieModel1.set("Leidos", r);
        pieModel1.set("Pendientes", ptr);
        pieModel1.set("Sin esecificar", ns);
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(true);
    }
    
    public void onCellEdit() {
          
          
       
        
        update(comicList);
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        comicList=comicListEJB.getListOf(user);
        createPieModel1();
                
        

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated","");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
    }
}
