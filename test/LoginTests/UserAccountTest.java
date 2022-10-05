package LoginTests;

import Login.UserAccount;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UserAccountTest {

    @Test(timeout = 50)
    public void testUserAccountConstructor() {
        UserAccount firstUser = new UserAccount("newUser", "123456789", false);
        Assert.assertEquals("newUser", firstUser.getUsername());
    }

    @Test(timeout = 1000)
    public void testLoginHistory() {
        UserAccount user = new UserAccount("Mary17", "123456789", false);
        user.loginHistory(LocalDateTime.of(2021, 8, 29, 12, 7, 30));
        user.loginHistory(LocalDateTime.now());
        Assert.assertEquals(2, user.getLoginHistory().size());
    }

    @Test(timeout = 50)
    public void testGetPassword() {
        UserAccount firstUser = new UserAccount("newUser", "123456789", false);
        Assert.assertEquals("123456789", firstUser.getPassword());
    }

    @Test(timeout = 50)
    public void testGetStatus() {
        UserAccount firstUser = new UserAccount("newUser", "123456789", false);
        assertFalse(firstUser.getStatus());
    }

    @Test(timeout = 50)
    public void testGetUsername() {
        UserAccount firstUser = new UserAccount("newUser", "123456789", false);
        Assert.assertEquals("newUser", firstUser.getUsername());
    }
}
