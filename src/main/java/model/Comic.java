/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    @Column(name="publishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;

    public int getComicId() {
        return comicId;
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
