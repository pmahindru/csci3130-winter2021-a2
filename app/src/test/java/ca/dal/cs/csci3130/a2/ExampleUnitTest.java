package ca.dal.cs.csci3130.a2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import android.util.Patterns;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    static MainActivity mainActivity;

    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
    }

    /*** AT-II**/
    @Test
    public void checkIfUserNameIsEmpty() {
        assertTrue(mainActivity.isEmptyUserName(""));
        assertFalse(mainActivity.isEmptyUserName("xyz$56"));
    }

    /*** AT-III**/
    @Test
    public void checkIfUserNameIsAlphaNumeric(){
        assertTrue(mainActivity.isAlphanumericUserName("xyz123"));
    }

    /*** AT-III**/
    @Test
    public void checkIfUserNameIsNotAlphaNumeric(){
        assertFalse(mainActivity.isAlphanumericUserName(""));
        assertFalse(mainActivity.isAlphanumericUserName("&45abc^!"));
    }

    /*** AT-IV**/
    @Test
    public void checkIfEmailIsValid(){
        assertTrue(mainActivity.isValidEmailAddress("abc123@dal.ca"));
    }

    /*** AT-IV**/
    @Test
    public void checkIfEmailIsInvalid(){
        assertFalse(mainActivity.isValidEmailAddress("abc.123dal.ca"));
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}