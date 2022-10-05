package Login.LoginInfo;

import Login.AccountSystem;
import Login.ValidateCredentials;

public class CheckInfo {
    private final ValidateCredentials cred;
    private final AccountSystem system;

    /**
     * Constructs a new instance of CheckInfo object.
     * @param mainSystem a class storing all the user accounts created
     */
    public CheckInfo(AccountSystem mainSystem) {
        this.cred = new ValidateCredentials();
        this.system = mainSystem;
    }

    /**
     * Checks if given username and password is a valid and unique combination.
     * @param username username to be checked
     * @param password password to be checked
     * @return boolean
     */
    public boolean check(String username, String password) {
        if (checkValidUsername(username, password)) {
            return !checkUsed(username);
        } return false;
    }

    /**
     * Checks if given username and password are valid.
     * @param username username to be checked
     * @param password password to be checked
     * @return boolean
     */
    public boolean checkValidUsername(String username, String password) {
        if (cred.isValidUsername(username)) {
            return cred.isValidPasswordLength(password);
        } return false;
    }

    /**
     * Checks if a given username is unique.
     * @param username username to be checked
     * @return boolean
     */
    public boolean checkUsed(String username) {
        return system.checkUsername(username);
    }

}
