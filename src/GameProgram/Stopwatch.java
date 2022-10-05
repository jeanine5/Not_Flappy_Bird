package GameProgram;

public class Stopwatch {
    private long stopWatchStartTime = 0;
    private long stopWatchStopTime = 0;
    private boolean stopWatchRunning;

    /**
     * Creates Stopwatch object
     */
    public Stopwatch(){
        this.stopWatchRunning = false;
    }

    /**
     * Returns whether the stopwatch is running
     * @return boolean
     */
    public boolean isStopWatchRunning(){
        return stopWatchRunning;
    }

    /**
     * Starts the stopwatch in nanoseconds and sets the boolean variable stopWatchRunning to true
     */
    public void start() {
        this.stopWatchStartTime = System.nanoTime();
        this.stopWatchRunning = true;
    }

    /**
     * Stop the stopwatch and sets the boolean variable stopWatchRunning to false
     */
    public void stop() {
        this.stopWatchStopTime = System.nanoTime();
        this.stopWatchRunning = false;
    }

    /**
     * Converts the total time passed from nanoseconds to seconds and returns the time passed in seconds.
     * @return long
     */
    public long getElapsedSeconds() {
        long elapsedTime;

        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - stopWatchStartTime);
        else
            elapsedTime = (stopWatchStopTime - stopWatchStartTime);

        int nanoSecondsPerSecond = 1000000000;
        return elapsedTime / nanoSecondsPerSecond;
    }
}
