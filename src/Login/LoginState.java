package Login;

public class LoginState {

    private boolean running;

    /**
     * Constructs a new LoginState object.
     */
    public LoginState() {
        this.running = true;
    }

    /**
     * Stops the login from continuing running.
     */
    public void stopRunning() {
        this.running = false;
    }

    /**
     * Returns whether the login is still running.
     * @return boolean
     */
    public boolean isRunning() {
        return running;
    }

}

