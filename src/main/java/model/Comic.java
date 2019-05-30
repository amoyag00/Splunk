package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author splunk
 */
@Entity
@Table(name="COMICS")
public class Comic implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int comicId;
    
    @Column(name="name")
    private String name;
    
    @Column(name="numberChapters")
    private int numChapters;
    
    @Column(name="statusComic")
    private String statusComic;
    
    @Column(name="visible")
    private boolean visible;
    
    @Column(name="imagePath")
    private String imagePath;
    
    @Column(name="publishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    
    @Transient 
    private double globalScore;

    public int getComicId() {
        return comicId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumChapters() {
        return numChapters;
    }

    public void setNumChapters(int numChapters) {
        this.numChapters = numChapters;
    }

    public String getStatusComic() {
        return statusComic;
    }

    public void setStatusComic(String statusComic) {
        this.statusComic = statusComic;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(double globalScore) {
        this.globalScore = globalScore;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Comic{" + "comicId=" + comicId + ", name=" + name + ", numChapters=" + numChapters + ", statusComic=" + statusComic + ", publishDate=" + publishDate + '}';
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
        final Comic other = (Comic) obj;
        if (this.comicId != other.comicId) {
            return false;
        }
        return true;
    }
    
    
}
