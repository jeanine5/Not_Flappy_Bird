package Login;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    private final String username;
    private final String password;

    private final boolean admin;

    private final List<LocalDateTime> loginTimes = new ArrayList<>();

    /**
     * Constructs a new UserAccount object.
     * @param username username of the new account
     * @param password password of the new account
     * @param admin boolean that details whether the new account is an admin account
     */
    public UserAccount(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    /**
     * Returns the username of this account.
     * @return username currently assigned to this account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of this account.
     * @return password that is currently assigned to this account
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns a boolean that details whether this account is an admin account.
     * @return boolean
     */
    public boolean getStatus() { return this.admin; }

    /**
     * Updates the log in history by adding a new log in time.
     * @param time date and time of the new log in
     * @return the list of all previous log in times
     */
    public List<LocalDateTime> loginHistory(LocalDateTime time) {
        loginTimes.add(time);
        return loginTimes;
    }

    /**
     * Returns a list with all the previous log in dates and times for this account.
     * @return list of all the previous log ins
     */
    public List<LocalDateTime> getLoginHistory() { return loginTimes; }

    /**
     * Returns a String representation of this user account.
     * @return String representation of this account
     */
    public String toString(){
        return "Welcome to your account " + username;
    }
}
