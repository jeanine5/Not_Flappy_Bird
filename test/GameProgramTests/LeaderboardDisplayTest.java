package GameProgramTests;

import GameProgram.LeaderboardDisplay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeaderboardDisplayTest {

    @Test
    public void testUpdateLeaderboardNewUser() {
        LeaderboardDisplay display = new LeaderboardDisplay();
        display.updateLeaderboard(12, "Mary");
        assertTrue(display.getLeaderboard().getScoreMap().containsKey("Mary"));
    }

    @Test
    public void testUpdateLeaderboardExistingUser() {
        LeaderboardDisplay display = new LeaderboardDisplay();
        display.updateLeaderboard(35, "Mary");
        assertEquals(35, (int) display.getLeaderboard().getScoreMap().get("Mary"));
    }
}
