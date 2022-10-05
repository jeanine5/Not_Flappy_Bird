package Login;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminManager extends AccountSystem {

    private final static Map<UserAccount, LocalDateTime> bannedAccounts = new HashMap<>();

    /**
     * Promotes a regular user account to an admin account.
     * @param username username of the user to be promoted
     */
    public void promoteAdminUser(String username) {
        AccountSystem system = new AccountSystem();
        for (UserAccount user: AccountSystem.allUsers) {
            if(user.getUsername().equals(username)) {
                String password = user.getPassword();
                deleteUser(username);
                system.createUser(username, password,true);
                return;
            }
        }
    }

    /**
     * Bans a user for a specified amount of time.
     * @param username username of the user to be banned
     * @param banLength date and time when the ban will end
     */
    public void temporaryBan(String username, LocalDateTime banLength) {
        List<UserAccount> userToBan = new ArrayList<>();
        for (UserAccount user: AccountSystem.allUsers) {
            if (user.getUsername().equals(username)) {
                userToBan.add(user);
                bannedAccounts.put(user, banLength);
            }
        }
        if (userToBan.size() == 0) {
            return;
        }
        AccountSystem.allUsers.remove(userToBan.get(0));
    }

    /**
     * Ends the ban of the user.
     * @param username username of the sure to unban
     * @param banLength date and time when the ban was supposed to end
     */
    public void unbanAccount(String username, LocalDateTime banLength) {
        List<UserAccount> userUnban = new ArrayList<>();
        for (UserAccount user: bannedAccounts.keySet()) {
            if (user.getUsername().equals(username) && LocalDateTime.now().isAfter(banLength)) {
                userUnban.add(user);
                AccountSystem.allUsers.add(user);
            }
        }
        if (userUnban.size() == 0) {
            return;
        }
        bannedAccounts.remove(userUnban.get(0));
    }

    /**
     * Deletes a user account.
     * @param username username of the account to be deleted
     */
    public void deleteUser(String username){
        AccountSystem.allUsers.removeIf(user -> user.getUsername().equals(username));
        CreateUserAccount.allCreatedUsers.removeIf((user -> user.getUsername().equals(username)));
    }

    /**
     * Create a new admin account.
     * @param username username of the new account
     * @param password password of the new account
     */
    public void createNewAdmin(String username, String password){
        AccountSystem system = new AccountSystem();
        system.createUser(username, password, true);
    }

}
