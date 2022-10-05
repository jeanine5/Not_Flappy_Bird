package LoginTests;

import Login.*;
import org.junit.Assert;
import org.junit.Test;

public class LoginCommanderTest {

    @Test(timeout = 50)
    public void testInputStore() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        String identity = "isIdentitySavingProperly";
        commander.inputStore(identity);
        Assert.assertEquals(identity, commander.getIdentity());
    }

    @Test
    public void testNextOld() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        commander.start("OLD"); //This is what creates "pathway"
        Assert.assertEquals("Enter Username", commander.getPathway().outputUI());
    }

    @Test(timeout = 50)
    public void testNextCreate() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        commander.start("NEW"); //This is what creates "pathway"
        commander.next();
    }

    /*
     * For testProcessInfoSetCreate the first inputStore call has to be changed (unique). LoginReader will save this
     * user to userdata.txt and therefore this test will only work if user is not already in userdata.txt.
     * This happens since process is at a higher level, meaning it uses almost all of the Login package.
     */
    @Test(timeout = 50)
    public void testProcessInfoSetCreate() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        commander.start("NEW");
        commander.inputStore("username");
        commander.inputStore("password");
        commander.inputStore("NO");
        commander.infoSet(commander.getIdentity());
        commander.process();
        Assert.assertTrue(system.checkUsername("username"));
    }

    @Test(timeout = 100)
    public void testProcessInfoSetOld() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        commander.start("OLD");
        commander.inputStore("username");
        commander.inputStore("password");
        commander.infoSet(commander.getIdentity());
        commander.process();
    }

    @Test (timeout = 50)
    public void testState() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        Assert.assertTrue(commander.getState());
        commander.start("EXIT");
        Assert.assertFalse(commander.getState());
    }
    @Test (timeout = 50)
    public void testProcessFailure() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        commander.start("OLD");
        commander.inputStore("username");
        commander.inputStore("wrongpassword");
        commander.infoSet(commander.getIdentity());
        commander.process();
    }

    @Test (timeout = 50)
    public void testOutput() {
        AccountSystem system = new AccountSystem();
        LoginCommander commander = new LoginCommander(system);
        commander.start("OLD");
        Assert.assertEquals("Enter Username", commander.output());
    }
}
