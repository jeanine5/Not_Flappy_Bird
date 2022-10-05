package GUITests;

import GameProgram.LeaderboardDisplay;
import GameProgram.PlayGame;
import The_GUI.GUI;
import The_GUI.GameBoardObserver;
import The_GUI.GameManagerObserver;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerObserverTest {

    @Test
    public void testStartGUI() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver GMObserver = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Judy", GMObserver, observer1, display);
        GMObserver.savePlayGame(play);
        assertNull(GMObserver.getGUI());
        play.startGame();
        assertNotNull(GMObserver.getGUI());
    }

    @Test
    public void testMovePlayer() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver GMObserver = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Judy", GMObserver, observer1, display);
        GMObserver.savePlayGame(play);
        play.startGame();
        play.getManager().moveUp();
        assertEquals(295, GMObserver.getGUI().moveGamePlayer(0).y);
    }
}
