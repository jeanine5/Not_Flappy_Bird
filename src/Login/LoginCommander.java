package Login;

import Login.LoginInfo.*;
import Login.LoginPathway.*;

public class LoginCommander {
    private final AccountSystem system;
    private String identity;
    private final LoginInfo info;
    private LoginPathway pathway;
    private final LoginState state;

    /**
     * Constructs a new instance of LoginCommander class.
     * @param mainSystem a class storing all the user account created
     */
    public LoginCommander(AccountSystem mainSystem) {
        this.system = mainSystem;
        this.identity = null;
        this.state = new LoginState();
        this.info = new LoginInfo();
    }

    /**
     * Takes in a string and then attaches it to the end of "identity"
     * @param userInput any string that does not include a period
     */
    public void inputStore(String userInput) {
        //takes in a string and adds it to an already existing string "identity".
        if (identity == null) {
            identity = userInput;}
        else{identity += ("." + userInput);}
     }

    /**
     * Takes in a string input of either "NEW" or "OLD". If neither, the program closes.
     * Sets the pathway to either Create or Old login based on user choice
     * @param userInput string that is either "NEW" or "OLD"
     */
    public void start(String userInput) {
        //Takes in the first input from the user, in order to determine the pathway that the user chooses
        if (userInput.equals("NEW")) {
            pathway = new LoginCreate();
        } else if (userInput.equals("OLD")) {
            pathway = new LoginOld();
        } else {
            state.stopRunning();
        }
    }


    /**
     * If the pathway.checkFinish is false, calls pathway.nextLine.
     * Otherwise, calls the process method.
     */
    public void next() {
        if (pathway.checkFinish()) {
            infoSet(identity);
            process();
        } else {
            pathway.nextLine();
        }
    }

    /**
     * Calls pathway's processInfo method if pathway's canProcess method returns true.
     * If canProcess is false: outputs faulty sign in message.
     * Calls resetIdentity always.
     * Uses this commander's AccountSystem and LoginInfo
     */
    public void process() {
        if (pathway.canProcess(system, info)) {
            pathway.processInfo(system, info);
            pathway = new LoginOld();
        } else {
            pathway.nextLine();
            output();
            pathway.nextLine();
        }
        resetIdentity();
    }

    /**
     * Gives info (the instance of LoginInfo in this commander) a string.
     * The string is then appropriately processed in info and then stored as separate variables.
     * @param someString a string that is to be split. Must have at least 1 period.
     */
    public void infoSet(String someString) {
        info.menuInputs(info.identitySplitter(someString));
    }

    /**
     * returns the state of the program
     */
    public boolean getState() {
        return state.isRunning();
    }

    /**
     * Using the pathway decided by LoginCommander, outputs a string to the terminal
     */
    public String output() {
        return pathway.outputUI();
    }

    /**
     * Resets "identity" to null
     */
    public void resetIdentity() {
        identity = null;
    }

    /**
     * Used solely for LoginCommanderTest.java.
     * Returns "identity"
     * @return String
     */
    public String getIdentity() {return identity;}

    /**
     * Used solely for LoginCommanderTest.java.
     * Returns "pathway"
     * @return LoginPathway
     */
    public LoginPathway getPathway() {return pathway;}
}


