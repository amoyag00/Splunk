
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
 * @author splunk
 */
@Entity
@Table(name="GENRES")
public class Genre implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int genreId;
    
    @JoinColumn(name="comicId")
    @ManyToOne
    private Comic comic;
    
    @Column(name="name")
    private String name;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        final Genre other = (Genre) obj;
        if (this.genreId != other.genreId) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return "Genre{" + "genreId=" + genreId + ", comic=" + comic + ", name=" + name + '}';
    }
    
    
    
}
