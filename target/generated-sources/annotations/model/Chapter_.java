package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Comic;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-16T17:28:38")
@StaticMetamodel(Chapter.class)
public class Chapter_ { 

    public static volatile SingularAttribute<Chapter, String> contentPath;
    public static volatile SingularAttribute<Chapter, Date> addedDate;
    public static volatile SingularAttribute<Chapter, Integer> chapterId;
    public static volatile SingularAttribute<Chapter, Integer> chapterNumber;
    public static volatile SingularAttribute<Chapter, Comic> comic;

}