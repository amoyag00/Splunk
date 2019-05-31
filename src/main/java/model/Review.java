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
@Table(name="REVIEWS")
public class Review implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int reviewId;
    
    @JoinColumn(name="userId")
    @ManyToOne
    private User user;
    
    @JoinColumn(name="comicId")
    @ManyToOne
    private Comic comic;
    
    @Column(name="writtenDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date writtenDate;
    
    @Column(name="reviewText")
    private String reviewText;
    
    @Column(name = "visible")
    private boolean visible;


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public Date getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(Date writtenDate) {
        this.writtenDate = writtenDate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    

    @Override
    public String toString() {
        return "Review{" + "reviewId=" + reviewId + ", userId=" + user.toString() + ", comicId=" + comic.toString() + ", writtenDate=" + writtenDate + ", reviewText=" + reviewText + '}';
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
        final Review other = (Review) obj;
        if (this.reviewId != other.reviewId) {
            return false;
        }
        return true;
    }
    
    
    
}
