package GameProgramTests;

import GameProgram.Stopwatch;
import org.junit.Test;

public class StopwatchTest {

    @Test(timeout = 50)
    public void testStartTimer() {
        Stopwatch stopwatch1 = new Stopwatch();
        stopwatch1.start();
        assert(stopwatch1.isStopWatchRunning());
    }

    @Test(timeout = 50)
    public void testEndTimer() {
        Stopwatch stopwatch1 = new Stopwatch();
        stopwatch1.start();
        stopwatch1.stop();
        assert(!stopwatch1.isStopWatchRunning());
    }
}
