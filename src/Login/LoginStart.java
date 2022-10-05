package Login;

import java.util.*;
import Login.LoginInfo.*;

public class LoginStart {
    private final LoginInfo info;

    /**
     * Constructs a new LoginStart object.
     */
    public LoginStart() {
        this.info = new LoginInfo();
    }

    /**
     * Reads a string representation of a user account, converts it to a UserAccount object and saves it in the system.
     * @param identityList a list of strings, where each string represents a user account
     * @param system a class which stores all UserAccount objects created
     */
    public void startUp(ArrayList<String> identityList, AccountSystem system) {
        for (String identity : identityList) {
            info.menuInputs(info.identitySplitter(identity));
            system.createUser(info.getUsername(), info.getPassword(), info.getAdmin());
        }
    }
}
