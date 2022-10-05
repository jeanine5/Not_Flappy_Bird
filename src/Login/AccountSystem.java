package Login;

import java.util.ArrayList;
import java.util.List;

public class AccountSystem {

    protected static List<UserAccount> allUsers = new ArrayList<>();

    /**
     * Returns the UserAccount object with the given username and password. If the username and/or password do not
     * match any of the existing accounts, returns null.
     *
     * @param username account's username
     * @param password account's password
     * @return account of the user with matching username and password
     */
    public UserAccount logIn(String username, String password) {
        for (UserAccount u : allUsers) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Creates a new UserAccount object and adds it to the list of all accounts in the system as well as a list of all
     * created accounts. Before creating the account, checks whether the password and username meet the requirements.
     *
     * @param newUsername username that needs to be assigned to the new user account
     * @param password    password that needs to be assigned to the new user account
     * @param admin       a boolean that details whether the new account should have admin status
     */
    public void createUser(String newUsername, String password, boolean admin) {
        CreateUserAccount newUser = new CreateUserAccount(newUsername, password, admin);
        UserAccount user = newUser.createUserAccount();
        if (user == null) {
            return;
        }
        allUsers.add(user);
    }

    /**
     * Checks if the username provided belongs to an existing account.
     *
     * @param username username that needs to be checked
     * @return boolean that details whether the username belongs to an existing account
     */
    public boolean checkUsername(String username) {
        for (UserAccount user : allUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given password and username match an existing account.
     *
     * @param username username to be checked
     * @param password password to be checked
     * @return boolean that details whether this password and username match an existing account
     */
    public boolean checkPassword(String username, String password) {
        for (UserAccount user : allUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of all users currently in the system.
     *
     * @return a list of all users
     */
    public static List<UserAccount> getAllUsers() {
        return allUsers;
    }
}