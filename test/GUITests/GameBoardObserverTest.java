package GUITests;

import GameProgram.LeaderboardDisplay;
import GameProgram.Obstacle;
import GameProgram.PlayGame;
import GameProgram.Reward;
import The_GUI.GameBoardObserver;
import The_GUI.GameManagerObserver;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameBoardObserverTest {

    @Test
    public void testRemoveReward() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver GMObserver = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Judy", GMObserver, observer1, display);
        GMObserver.savePlayGame(play);
        play.startGame();
        observer1.addReward(new Reward(100, 25, false));
        observer1.addReward(new Reward(150, 50, false));
        assertTrue(observer1.removeReward(new Reward(150, 50, false)));
    }

    @Test
    public void testAddObstacle() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver GMObserver = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Judy", GMObserver, observer1, display);
        GMObserver.savePlayGame(play);
        play.startGame();
        observer1.addObstacle(new Obstacle(100, 100, 100));
        assertTrue(GMObserver.getGUI().removeObstacle());
    }

    @Test
    public void testRemoveFirstGoodReward() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver GMObserver = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Judy", GMObserver, observer1, display);
        GMObserver.savePlayGame(play);
        play.startGame();
        observer1.addReward(new Reward(100, 25, false));
        assertTrue(GMObserver.getGUI().removeFirstGoldenApple());
    }

    @Test
    public void removeFirstBadReward() {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver GMObserver = new GameManagerObserver(observer1);
        LeaderboardDisplay display = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Judy", GMObserver, observer1, display);
        GMObserver.savePlayGame(play);
        play.startGame();
        observer1.addReward(new Reward(100, 25, true));
        assertTrue(GMObserver.getGUI().removeFirstPoisonApple());
    }
}
