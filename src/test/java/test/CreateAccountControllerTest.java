package test;

import controller.CreateAccountController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Splunk
 */
public class CreateAccountControllerTest {
    private CreateAccountController c;
    
    @Before
    public void setup(){
        c = new CreateAccountController();
    }
   
    @Test
    public void shortPasswordTest() {
        assertFalse(c.isStrong("hola"));
    }
    
    @Test
    public void noNumberTest() {
        assertFalse(c.isStrong("somepassword"));
    }
    
    @Test
    public void HasNumberNoUpperCaseTest() {
        assertFalse(c.isStrong("somepassword2"));
    }
    
    @Test
    public void HasNumberAndUpperCaseTest() {
        assertTrue(c.isStrong("somePassword2"));
    }
    
    @Test
    public void EmptyPasswordTest() {
        assertFalse(c.isStrong(""));
    }
}
