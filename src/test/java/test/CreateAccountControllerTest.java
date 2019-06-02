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

    //White box testing
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

    //Black box testing
    //Equivalency classes
    //Case 1
    @Test
    public void hasNumberHasUpperCaseLongTest2() {
        assertTrue(c.isStrong("P4ssword"));
    }

    //Case 2
    @Test
    public void hasNumberHasUpperCaseShortTest() {
        assertFalse(c.isStrong("B2"));
    }

    //Case 3
    @Test
    public void noNumberHasUpperCaseLongTest() {
        assertFalse(c.isStrong("Ppassword"));
    }

    //Case 4
    @Test
    public void hasNumberNoUpperCaseLongTest3() {
        assertFalse(c.isStrong("passwordd2"));
    }

    //AVL
    //Case 1
    @Test
    public void hasNumberHasUpperCaseLongTest4() {
        assertTrue(c.isStrong("P4ssword"));
    }

    //Case 2
    @Test
    public void hasNumberHasUpperCaseLongTest5() {
        assertTrue(c.isStrong("P4sswordd"));
    }

    //Case 3
    @Test
    public void hasNumberHasUpperCaseShortTest2() {
        assertFalse(c.isStrong("P4sswor"));
    }

    //Case 4
    @Test
    public void hasNumberHasUpperCaseLongTest6() {
        assertTrue(c.isStrong("helloB0y"));
    }

    //Case 5
    @Test
    public void hasNumberHasUpperCaseLongTest7() {
        assertTrue(c.isStrong("h3lloB0y"));
    }

    //Case 6
    @Test
    public void noNumberHasUpperCaseLongTest6() {
        assertFalse(c.isStrong("helloBoy"));
    }

    //Case 7
    @Test
    public void hasNumberHasUpperCaseLongTest8() {
        assertTrue(c.isStrong("Willyr3x"));
    }

    //Case 8
    @Test
    public void hasNumberHasUpperCaseLongTest9() {
        assertTrue(c.isStrong("W1llyr3x"));
    }

    //Case 9
    @Test
    public void hasNumberHasUpperCaseShortTest3() {
        assertFalse(c.isStrong("willyr3x"));
    }

}
