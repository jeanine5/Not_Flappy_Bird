package GameProgramTests;

import GameProgram.*;
import The_GUI.GameBoardObserver;
import The_GUI.GameManagerObserver;
import org.junit.Test;
import Login.UserAccount;


import java.beans.PropertyChangeEvent;

import static org.junit.Assert.*;

public class GameManagerTest {

    @Test(timeout = 150)
    public void testInitializer() {
        GameManager manager = new GameManager("Jane");
        assert(manager.getScore() == 0);
        assert(manager.getPlayer().getUsername().equals("Jane"));
    }

    @Test(timeout = 2000)
    public void testMoveUp() {
        GameManager manager = new GameManager("Jane");
        manager.moveUp();
        assert(manager.getPlayer().getYCoordinate() == 295);
    }

    @Test(timeout = 50)
    public void testMoveDown() {
        GameManager manager = new GameManager("Jane");
        manager.moveDown();
        assert(manager.getPlayer().getYCoordinate() == 305);
    }

    @Test
    public void testUpdateScore() {
        GameManager manager = new GameManager("Jane");
        manager.startGame();
        assert(manager.getScore() == 0);
        try {
        Thread.sleep(5000); }
        catch(InterruptedException ex)
        {
            ex.printStackTrace(); }
        manager.updateScore();
        assert(manager.getScore() == 5);
    }

    @Test(timeout = 300)
    public void testRunGameTrue() {
        GameManager manager = new GameManager("Jane");
        manager.startGame();
        manager.runGame();
        GameBoard grid = manager.getGameBoard();
        assert(grid.getObstacleList().get(0).getLocation() == 99);
    }

    @Test(timeout = 300)
    public void testRunGameFalse() {
        GameManager manager = new GameManager("Jane");
        manager.getPlayer().setLocation(100, 220);
        manager.startGame();
        assertFalse(manager.runGame());
    }

    @Test
    public void testEndGame() {
        GameManager manager = new GameManager("Jane");
        manager.startGame();
        try {
            Thread.sleep(5000); }
        catch(InterruptedException ex)
        {
            ex.printStackTrace(); }
        manager.endGame();
        assert(manager.getScore() == 5);
    }

    @Test
    public void testGenerateRewardScore() {
        GameManager manager = new GameManager("Jane");
        manager.startGame();
        manager.getGameBoard().addReward(new Reward(79, 299, false));
        manager.runGame();
        try {
            Thread.sleep(5000); }
        catch(InterruptedException ex)
        {
            ex.printStackTrace(); }
        manager.generateRewardScore();
        assert(manager.getScore() == 15);
    }

    @Test
    public void testAddObserver() {
        GameManager manager = new GameManager("Jane");
        GameBoardObserver boardObserver = new GameBoardObserver();
        GameManagerObserver observer = new GameManagerObserver(boardObserver);
        manager.addObserver(observer);
        assertEquals(1, manager.getObserversSize());
    }

    @Test
    public void testNotifyObservers() {
        GameManager manager = new GameManager("Jane");
        GameBoardObserver boardObserver = new GameBoardObserver();
        GameManagerObserver observer = new GameManagerObserver(boardObserver);
        PlayGame play = new PlayGame("Jane", observer, boardObserver, new LeaderboardDisplay());
        observer.savePlayGame(play);
        manager.addObserver(observer);
        assertNull(observer.getGUI());
        play.startGame();
        assertNotNull(observer.getGUI());
    }


}
