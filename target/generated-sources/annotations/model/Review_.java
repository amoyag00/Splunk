package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Comic;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-16T17:28:38")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, Comic> comic;
    public static volatile SingularAttribute<Review, Integer> reviewId;
    public static volatile SingularAttribute<Review, User> user;
    public static volatile SingularAttribute<Review, Date> writtenDate;
    public static volatile SingularAttribute<Review, String> reviewText;

}