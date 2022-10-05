package LoginTests;

import Login.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class LoginStartTest {

    @Test (timeout = 50)
    public void testStartUp() {
        AccountSystem system = new AccountSystem();
        LoginStart start = new LoginStart();
        ArrayList<String> identityList = new ArrayList<>();
        identityList.add("hehehehe0.hehehehe.NO");
        identityList.add("heheheha1.hehehehe.NO");
        identityList.add("hehehehe2.hehehehe.NO");
        start.startUp(identityList, system);
        Assert.assertTrue(system.checkUsername("hehehehe0"));
        Assert.assertTrue(system.checkUsername("heheheha1"));
        Assert.assertTrue(system.checkUsername("hehehehe2"));
    }
}
