package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-16T17:28:38")
@StaticMetamodel(Comic.class)
public class Comic_ { 

    public static volatile SingularAttribute<Comic, Integer> comicId;
    public static volatile SingularAttribute<Comic, String> name;
    public static volatile SingularAttribute<Comic, Date> publishDate;
    public static volatile SingularAttribute<Comic, Integer> numChapters;
    public static volatile SingularAttribute<Comic, String> statusComic;

}