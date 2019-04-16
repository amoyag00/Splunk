package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Comic;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-16T17:28:38")
@StaticMetamodel(ComicEntry.class)
public class ComicEntry_ { 

    public static volatile SingularAttribute<ComicEntry, Integer> listId;
    public static volatile SingularAttribute<ComicEntry, Integer> score;
    public static volatile SingularAttribute<ComicEntry, Date> addedDate;
    public static volatile SingularAttribute<ComicEntry, Integer> progress;
    public static volatile SingularAttribute<ComicEntry, Comic> comic;
    public static volatile SingularAttribute<ComicEntry, User> user;
    public static volatile SingularAttribute<ComicEntry, String> comicStatus;

}