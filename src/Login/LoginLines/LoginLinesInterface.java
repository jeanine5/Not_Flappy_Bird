package Login.LoginLines;

public interface LoginLinesInterface {

    /**
     * Returns the String related to a certain enum
     * @return String
     */
    String printUI();

    /**
     * Returns the next enum in the sequence of enums
     * @return LoginLinesInterface
     */
    LoginLinesInterface next();
}
