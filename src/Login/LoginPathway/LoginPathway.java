package Login.LoginPathway;

import Login.AccountSystem;
import Login.LoginInfo.LoginInfo;

public interface LoginPathway {

    /**
     *Either creates a new account or logs in to an existing account based on the pathway instance
     * @param mainSystem the AccountSystem used
     * @param mainInfo the LoginInfo used to store login info
     */
    void processInfo(AccountSystem mainSystem, LoginInfo mainInfo);
    /**
     * Checks if the info is correct based on the pathway instance
     * @param mainSystem the AccountSystem used
     * @param mainInfo the LoginInfo used to store login info
     * @return boolean
     */
    boolean canProcess(AccountSystem mainSystem, LoginInfo mainInfo);
    /**
     * Outputs the relevant UI
     * @return String
     */
    String outputUI();
    /**
     * Changes current enum to the next enum
     */
    void nextLine();
    /**
     * Checks if the current line is the last line
     * @return boolean
     */
    boolean checkFinish();

}
