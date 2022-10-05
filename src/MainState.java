public class MainState {

    private boolean running;

    public MainState() {
        this.running = true;
    }

    public void stopRunning() {running = false;}
    public boolean isRunning() {return running;}
}
