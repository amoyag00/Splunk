package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Splunk
 */
@Entity
@Table(name = "MENUS")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;

    @Column(name = "tipo")
    private String tipo;

    @JoinColumn(name = "rolId")
    @ManyToOne
    private Rol rolId;

    @JoinColumn(name = "menu_menuId")
    @ManyToOne
    private Menu menu_menuId;

    @Column(name = "url")
    private String url;

    @Column(name = "nombre")
    private String nombre;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public Menu getMenu_menuId() {
        return menu_menuId;
    }

    public void setMenu_menuId(Menu menu_menuId) {
        this.menu_menuId = menu_menuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.menuId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (this.menuId != other.menuId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Menu{" + "menuId=" + menuId + ", tipo=" + tipo + ", rolId=" + rolId + ", menu_menuId=" + menu_menuId + ", url=" + url + ", nombre=" + nombre + '}';
    }
}
