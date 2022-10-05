package Login.LoginInfo;

import Login.LoginReader;

import java.util.ArrayList;
import java.util.Collections;

public class LoginInfo {
    private final LoginReader reader = new LoginReader();
    private String username;
    private String password;
    private boolean admin;
    private String identity;

    /**
     * Constructs a new instance of LoginInfo class.
     */
    public LoginInfo() {
        this.username = null;
        this.password = null;
        this.admin = false;
        this.identity = null;
    }

    /**
     * Splits a string into multiple parts and adds it to an ArrayList.
     * Splits using the delimiter "."
     * @param id a String
     * @return ArrayList<String>
     */
    public ArrayList<String> identitySplitter(String id) {
        identity = id;
        ArrayList<String> stringList = new ArrayList<>();
        String[] arrOfStr = id.split("[.]", 0);
        Collections.addAll(stringList, arrOfStr);
        return stringList;
    }

    /**
     * Sets variables - username, password, and admin - using elements of an ArrayList<String>
     * username: index 0, password: index 1, admin: decided using index 2
     * @param infoList an ArrayList with at least 2 String elements
     */
    public void menuInputs(ArrayList<String> infoList) {
        username = infoList.get(0);
        password = infoList.get(1);
        if (infoList.size() > 2) {
            admin = infoList.get(2).equals("ADMIN");
        }
    }

    /**
     * Calls reader's method "writeTo" and feeds it a String
     * @param identity a String
     */
    public void writeToFile(String identity) {
        reader.writeTo(identity);
    }

    /**
     * Returns "identity"
     * @return String
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Returns "username"
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns "password"
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns "admin"
     * @return boolean
     */
    public boolean getAdmin() {
        return admin;
    }
}
