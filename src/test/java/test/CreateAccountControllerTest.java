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
    public void setup() {
        c = new CreateAccountController();
    }

    @Test
    public void emptyPasswordTest() {
        assertFalse(c.isStrong(""));
    }

    @Test
    public void noNumberNoUpperCaseShortTest() {
        assertFalse(c.isStrong("hello"));
    }

    @Test
    public void hasNumberNoUpperCaseShortTest() {
        assertFalse(c.isStrong("3"));
    }

    @Test
    public void hasUpperCaseNoNumberShortTest() {
        assertFalse(c.isStrong("A"));
    }

    @Test
    public void noNumberNoUpperCaseLongTest() {
        assertFalse(c.isStrong("thisisalongpassword"));
    }

    @Test
    public void hasNumberNoUpperCaseLongTest() {
        assertFalse(c.isStrong("thisisalongpassword2"));
    }

    @Test
    public void hasNumberHasUpperCaseLongTest() {
        assertTrue(c.isStrong("Thisisalongpassword2"));
    }

}
