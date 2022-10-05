package GameProgramTests;

import GameProgram.*;
import The_GUI.GameBoardObserver;
import org.junit.Test;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import static org.junit.Assert.*;

public class GameBoardTest {

    @Test(timeout = 5000)
    public void testInitializer() {
        GameBoard board = new GameBoard();
        assert(board.getObstacleList().get(0).getTopObstacleHeight()==240);
        assert(board.getObstacleList().get(0).getBottomObstacleHeight()==240);
        assert(board.getObstacleList().get(0).getWidth()==30);
    }

    @Test(timeout = 50)
    public void testIsTouchingObstacleTrue() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(101, 100);
        assertTrue(grid.isTouchingObstacle(player));
    }

    @Test
    public void testIsTouchingObstacleFalse() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(110, 125);
        assertFalse(grid.isTouchingObstacle(player));
    }

    @Test(timeout = 50)
    public void testIsTouchingBottomFalse() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(150, 0);
        assertFalse(grid.isTouchingBottom(player));
    }

    @Test(timeout = 1000)
    public void testIsTouchingBottomTrue() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(160, -15);
        assertTrue(grid.isTouchingBottom(player));
    }

    @Test(timeout = 50)
    public void testIsTouchingRewardNoRewards() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(160, 125);
        assertNull(grid.isTouchingReward(player));
    }

    @Test(timeout = 50)
    public void testIsTouchingRewardFalse() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(160, 100);
        grid.addReward(new Reward(160, 100, true));
        assertNull(grid.isTouchingReward(player));
    }

    @Test(timeout = 50)
    public void testIsTouchingRewardTrue() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(161, 100);
        grid.addReward(new Reward(160, 100, true));
        Reward reward = grid.isTouchingReward(player);
        assert(reward.isBadReward());
        assert(grid.getRewardsList().isEmpty());
    }

    @Test(timeout = 1000)
    public void isTouchingRewardTrue2() {
        GameBoard grid = new GameBoard();
        GamePlayer player = new GamePlayer("bird");
        player.setLocation(161, 100);
        grid.addReward(new Reward(160, 100, true));
        grid.addReward(new Reward(50, 50, false));
        Reward reward = grid.isTouchingReward(player);
        assert(reward.isBadReward());
        assert(grid.getRewardsList().size() == 1);
        assert(!grid.getRewardsList().get(0).isBadReward());
    }

    @Test(timeout = 50)
    public void testRandomizeObstacle() {
        GameBoard grid = new GameBoard();
        Obstacle ob = grid.randomizeObstacle();
        assert(ob.getLocation() == 300);
        assert(ob.getBottomObstacleHeight() == 160 | ob.getBottomObstacleHeight() == 240 |
                ob.getBottomObstacleHeight() == 320);
    }

    @Test(timeout = 50)
    public void testRandomizeReward() {
        GameBoard grid = new GameBoard();
        Reward apple = grid.randomizeReward();
        assert(apple.getXCoordinate() == 370);
        int y = apple.getYCoordinate();
        assert(5 <= y && 585 >= y);
    }

    @Test(timeout = 50)
    public void testMoveObjects() {
        GameBoard grid = new GameBoard();
        grid.addReward(new Reward(160, 100, false));
        grid.moveObjects();
        List<Obstacle> obstacles = grid.getObstacleList();
        assert(obstacles.get(0).getLocation() == 99);
        assert(obstacles.get(1).getLocation() == 219);
        assert (grid.getRewardsList().get(0).getXCoordinate() == 159);
    }

    @Test(timeout = 50)
    public void testUpdateObstacles() {
        GameBoard grid = new GameBoard();
        Obstacle ob = grid.getObstacleList().get(0);
        while (ob.getLocation() != 60) {
            grid.moveObjects();
        }
        grid.updateObstacles();
        assert(grid.getObstacleList().size() == 3);
        assert(grid.getObstacleList().get(2).getLocation() == 300);
        while (ob.getLocation() != -ob.getWidth()) {
            grid.moveObjects();
        }
        grid.updateObstacles();
        assert(grid.getObstacleList().size() == 2);
    }

    @Test(timeout = 50)
    public void testUpdateRewards() {
        GameBoard grid = new GameBoard();
        Obstacle ob = grid.getObstacleList().get(0);
        while (ob.getLocation() != 60) {
            grid.moveObjects();
        }
        grid.updateObstacles();
        grid.updateRewards();
        assert(grid.getRewardsList().size() == 1);
    }

    @Test
    public void testGetGameBoardDimensions(){
        GameBoard grid = new GameBoard();
        Dimension dim = new Dimension(300, 600);
        assert(grid.getGameBoardDimensions().getWidth() == dim.getWidth());
        assert(grid.getGameBoardDimensions().getHeight() == dim.getHeight());
    }

    @Test
    public void testGetObserversSize() {
        GameBoard grid = new GameBoard();
        assertEquals(0, grid.getObserversSize());
    }
    @Test
    public void testAddObserver() {
        GameBoard grid = new GameBoard();
        GameBoardObserver observer = new GameBoardObserver();
        grid.addObserver(observer);
        assertEquals(1, grid.getObserversSize());
    }
}
