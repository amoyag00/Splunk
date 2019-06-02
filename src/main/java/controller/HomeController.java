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
import EJB.UserFacadeLocal;
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
public class HomeController implements Serializable {
    @EJB
    private UserFacadeLocal userEJB;
    
    @EJB
    private ComicEntryFacadeLocal comicListEJB;

    @Inject
    private SearcherController searchController;

    private List<ComicEntry> comicList;
    private String statusComic[] = new String[4];
    private String prettyStatusComic[] = new String[4];
    private boolean editable = true;

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
    public void init() {

        usuario = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

        if (searchController != null) {
            if (searchController.getUserSelected() != null) {
                if (!usuario.equals(searchController.getUserSelected())) {

                    editable = false;
                    usuario = searchController.getUserSelected();
                    searchController.setUserSelected(null);
                }
            }
        }

        comicList = comicListEJB.getListOf(usuario);
        statusComic[0] = "L";
        statusComic[1] = "R";
        statusComic[2] = "P";
        statusComic[3] = "";
        prettyStatusComic[0] = "Leido";
        prettyStatusComic[1] = "Leyendo";
        prettyStatusComic[2] = "Pendiente";
        prettyStatusComic[3] = "";
        createPieModel1();
    }

    public String[] getPrettyStatusComic() {
        return prettyStatusComic;
    }

    public void setPrettyStatusComic(String[] prettyStatusComic) {
        this.prettyStatusComic = prettyStatusComic;
    }

    public String[] getStatusComic() {
        return statusComic;
    }

    public void setStatusComic(String[] statusComic) {
        this.statusComic = statusComic;
    }
    
    public void updateSettings(){
        if(isStrong(usuario.getPassword())){
            userEJB.edit(usuario);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Ajustes guardados"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Las contraseña ha de tener más de 8 caracteres, un número y una mayúscula"));
        }
        
        
    }
    
    public boolean isStrong(String password) {
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean isStrong = false;

        for (int i = 0; i < password.length(); i++) {

            if (Character.isDigit(password.charAt(i))) {
                hasNumber = true;
            }

            if (Character.isUpperCase(password.charAt(i))) {
                hasUpperCase = true;
            }

        }

        if (password.length() >= 8 && hasNumber && hasUpperCase) {
            isStrong = true;
        }

        return isStrong;
    }

    public void deleteEntry(int row) {
        comicListEJB.remove(comicList.get(row));
        comicList.remove(row);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Entrada eliminada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        createPieModel1();
    }

    public String prettyStatus(String status) {
        String prettyStatus = "";
        if (status == null) {
            status = "";
        }
        switch (status) {
            case "L":
                prettyStatus = "Leyendo";
                break;
            case "R":
                prettyStatus = "Leido";
                break;
            case "P":
                prettyStatus = "Pendiente";
                break;
            default:
                prettyStatus = "Sin especificar";
        }
        return prettyStatus;
    }

    public void update(List<ComicEntry> comicList) {
        comicListEJB.update(comicList);

    }

    public void createPieModel1() {
        pieModel1 = new PieChartModel();

        int rding = 0;
        int r = 0;
        int ptr = 0;
        int ns = 0;

        for (int i = 0; i < comicList.size(); i++) {
            String status = comicList.get(i).getComicStatus();
            if (status == null) {
                status = "";
            }
            switch (status) {
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
        comicList = comicListEJB.getListOf(user);
        createPieModel1();

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Entrada actualizada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }
}
