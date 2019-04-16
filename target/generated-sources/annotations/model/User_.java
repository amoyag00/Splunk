package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-16T17:28:38")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> nickname;
    public static volatile SingularAttribute<User, String> userType;
    public static volatile SingularAttribute<User, Boolean> isPrivate;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, Date> expirationDate;

}