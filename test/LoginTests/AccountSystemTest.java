package LoginTests;

import Login.AccountSystem;
import Login.CreateUserAccount;
import Login.UserAccount;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountSystemTest {

    @Test(timeout = 50)
    public void testCreateUser() {
        AccountSystem system = new AccountSystem();
        system.createUser("Johnnn", "123456789", false);
        UserAccount user = system.logIn("Johnnn", "123456789");
        assertTrue(system.getAllUsers().contains(user));
    }

    @Test(timeout = 50)
    public void testLogIn() {
        AccountSystem newSystem = new AccountSystem();
        newSystem.createUser("John24", "123456789", false);
        Assert.assertEquals("John24", newSystem.logIn("John24", "123456789").getUsername());
    }

    @Test(timeout = 50)
    public void testCheckUsername() {
        AccountSystem newSystem = new AccountSystem();
        newSystem.createUser("John", "123456789", false);
        assertFalse(newSystem.checkUsername("Jack"));
    }

    @Test(timeout = 50)
    public void testCheckPassword() {
        AccountSystem newSystem = new AccountSystem();
        newSystem.createUser("John", "123456789", false);
        assertFalse(newSystem.checkPassword("John", "987654321"));
    }

}
