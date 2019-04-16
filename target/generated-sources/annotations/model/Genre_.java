package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Comic;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-16T17:28:38")
@StaticMetamodel(Genre.class)
public class Genre_ { 

    public static volatile SingularAttribute<Genre, Integer> genreId;
    public static volatile SingularAttribute<Genre, String> name;
    public static volatile SingularAttribute<Genre, Comic> comic;

}