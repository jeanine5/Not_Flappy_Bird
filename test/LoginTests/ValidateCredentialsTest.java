package LoginTests;

import Login.ValidateCredentials;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateCredentialsTest extends ValidateCredentials {
    @Test(timeout = 50)
    public void testisValidPasswordLength() {
        String password = "1234567891234";
        assertTrue(isValidPasswordLength(password));
    }

    @Test(timeout = 50)
    public void testisValidUserNameLength() {
        String userName = "kiddie";
        assertTrue(isValidUsernameLength(userName));
    }

    @Test(timeout = 50)
    public void testisValidSequenceCharInvalid() {
        String password = "12@45!";
        assertFalse(isValidSequenceChar(password));
    }

    @Test(timeout = 50)
    public void testisValidSequenceChar() {
        String password = "q1w2e3";
        assertTrue(isValidSequenceChar(password));
    }

    @Test(timeout = 50)
    public void testIsValidSequenceCharInvalid() {
        String username = "t@newme";
        assertFalse(isValidSequenceChar(username));
    }

    @Test(timeout = 50)
    public void testIsValidUsernameChar() {
        String username = "4Mary5";
        assertTrue(isValidSequenceChar(username));
    }
}
