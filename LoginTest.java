
package chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hlogonofatso the great
 */
public class LoginTest {
    
   // Tests for valid and invalid username formats
    @Test
    public void testValidUsernameFormat() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976" );
        assertTrue("Username successfully captured.", user.checkUserName());
        
    }
    @Test public void testInvalidUsernameFormat(){
        Login user = new Login("kyle!!!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse("Username incorrectly formated. Ensure it has an underscore and doesnt have more than 5 charecters", user.checkUserName());
        
    }
    
    //tests for valid and invalid password formats
     @Test
    public void testValidPasswordFormat() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976" );
        assertTrue("Password successfully captured.", user.checkPasswordComplexity());
        
    }
    @Test public void testInvalidPasswordFormat(){
        Login user = new Login("kyl_1!", "password", "+27838968976");
        assertFalse("Password incorrectly formatted. Ensure it has at least 8+ charecters, a capital letter, a number and a special charecter", user.checkPasswordComplexity());
        
    }
    
    //Tests for valid and invlid cellphone number formats
     @Test
    public void testValidCellNumberFormat() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976" );
        assertTrue("Cell phone number successfully added.", user.checkCellPhoneNumber());
        
    }
    @Test public void testInvalidCellNumberFormat(){
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse("Cell phone number incorrectly formatted ir doesnt contain the international code. Ensure that the cell number starts with +27 and is followed by 9 numbers.", user.checkCellPhoneNumber());
        
    }
    
    //tests for successful and unsuccesful logins
     @Test
    public void testSuccessfulLogin() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976" );
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Lionsin Hlogonofatso Mphahlele, it is great to see you again.", user.returnLoginStatus(true, "Lionsin", "Escanor"));
    }
    @Test public void testUnsuccessfulLogin(){
        Login user = new Login("kyle!!!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("Wron Username", "wrong Password"));
        assertEquals("Username or password incorrect. Try again.", user.returnLoginStatus(false, "Lionsin", "Escanor"));
    }
}
