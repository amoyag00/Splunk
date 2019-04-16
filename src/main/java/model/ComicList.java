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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author splunk
 */

@Entity
@Table(name="COMICS")
public class ComicList implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int listId;
    
    //What this class really contains are list entries, so many entries (comics added) can belong to a single User
    @JoinColumn(name="userId")
    @ManyToOne 
    private User user;
    
    @JoinColumn(name="comicID")
    @ManyToOne
    private Comic comic;
    
    @Column(name="comicStatus")
    private String comicStatus;
    
    
    @Column(name="score")
    private int score;
    
    @Column(name="progress")
    private int progress;
    
    @Column(name="addedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public String getComicStatus() {
        return comicStatus;
    }

    public void setComicStatus(String comicStatus) {
        this.comicStatus = comicStatus;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
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
        final ComicList other = (ComicList) obj;
        if (this.listId != other.listId) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return "ComicList{" + "listId=" + listId + ", user=" + user + ", comic=" + comic + ", comicStatus=" + comicStatus + ", score=" + score + ", progress=" + progress + ", addedDate=" + addedDate + '}';
    }
    
    
    
}
