package GameProgramTests;

import GameProgram.LeaderboardDisplay;
import GameProgram.PlayGame;
import Login.UserAccount;
import The_GUI.GameBoardObserver;
import The_GUI.GameManagerObserver;
import org.junit.Test;

import java.awt.*;
import java.util.Optional;

import static org.junit.Assert.*;

public class PlayGameTest {

    @Test
    public void testIsRunning() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Jane", observer2, observer1, display);
        assertTrue(play.isRunning());
    }

    @Test
    public void testStopRunning() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Jane", observer2, observer1, display);
        play.stopRunning();
        assertFalse(play.isRunning());
    }

    @Test
    public void testStartGame() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Jane", observer2, observer1, display);
        observer2.savePlayGame(play);
        play.startGame();
        assert(play.getManager().getObserversSize() == 1);
    }

    @Test
    public void testRunGame() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Jane", observer2, observer1, display);
        observer2.savePlayGame(play);
        play.startGame();
        play.runGame();
        assertNotEquals(0, play.getManager().getScore());
        int expected;
        if (play.getManager().getScore() > display.getLeaderboard().getScoreMap().get("Jane")) {
            expected = play.getManager().getScore();
        } else {
            expected = display.getLeaderboard().getScoreMap().get("Jane");
        }
        assertEquals(expected, (int) display.getLeaderboard().getScoreMap().get("Jane"));
    }

    @Test
    public void testGetGameBoardDimensions() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Jane", observer2, observer1, display);
        observer2.savePlayGame(play);
        Dimension size = play.getGameBoardDimensions();
        play.startGame();
        assertEquals(play.getManager().getGameBoard().getGameBoardDimensions().height, size.height);
        assertEquals(play.getManager().getGameBoard().getGameBoardDimensions().width, size.width);
    }

    @Test
    public void testGetGameManager() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Jane", observer2, observer1, display);
        observer2.savePlayGame(play);
        assertNull(play.getManager());
        play.startGame();
        assertNotNull(play.getManager());
    }


}
