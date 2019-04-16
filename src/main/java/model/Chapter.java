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
@Table(name="CHAPTERS")
public class Chapter implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int chapterId;
    
    @JoinColumn(name="comicId")
    @ManyToOne
    private Comic comic;
    
    @Column(name="chapterNumber")
    private int chapterNumber;
    
    @Column(name="chapterName")
    private String chapterName;
    
    @Column(name="addedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    
    @Column(name="contentPath")
    private String contentPath;

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
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
        final Chapter other = (Chapter) obj;
        if (this.chapterId != other.chapterId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Chapter{" + "chapterId=" + chapterId + ", comic=" + comic + ", chapterNumber=" + chapterNumber + ", chapterName=" + chapterName + ", addedDate=" + addedDate + ", contentPath=" + contentPath + '}';
    }

    
    
    
    
}
