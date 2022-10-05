package Login;

public class ValidateCredentials {

    /**
     * Checks if a given password is between 8 and 128 characters in length.
     * @param password password to be checked
     * @return boolean that details whether the password satisfies the length requirement
     */
    public boolean isValidPasswordLength(String password) {
        return password.length() >= 8 && password.length() <= 128;
    }

    /**
     * Checks if a given username is between 5 and 12 characters in length.
     * @param username username to be checked
     * @return boolean that details whether the username satisfies the length requirement
     */
    public boolean isValidUsernameLength(String username) {
        return username.length() >= 5 && username.length() <= 12;
    }

    /**
     * Checks if a given username contains any special characters.
     * @param sequence username to be checked
     * @return boolean that details whether the given username contains any special characters
     */
    public boolean isValidSequenceChar(String sequence) {
        char[] charList = sequence.toCharArray();
        for (char u : charList) {
            if (!Character.isLetter(u)) {
                if (!Character.isDigit(u)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a given username satisfies the length requirement and does not contain any special characters.
     * @param username username to be checked
     * @return a boolean that details whether the given username satisfies the requirements
     */
    public boolean isValidUsername(String username) {
        CreateUserAccount obj = new CreateUserAccount(username, "None", false);
        return isValidUsernameLength(username) && isValidSequenceChar(username) && obj.uniqueUsername(username);
    }
}
