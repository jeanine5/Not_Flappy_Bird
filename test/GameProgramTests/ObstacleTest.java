package GameProgramTests;

import GameProgram.Obstacle;
import org.junit.Test;

public class ObstacleTest {

    @Test(timeout = 1000)
    public void testInitializer() {
        Obstacle block = new Obstacle(20, 30, 40);
        assert (block.getTopObstacleHeight() == 20);
        assert (block.getBottomObstacleHeight() == 30);
        assert (block.getLocation() == 40);
        assert (block.getWidth() == 30);
    }

    @Test(timeout = 100)
    public void testSetter() {
        Obstacle block = new Obstacle(20, 30, 40);
        block.setObstacleSize(25,35,45);
        assert (block.getTopObstacleHeight() == 25);
        assert (block.getBottomObstacleHeight() == 35);
        assert (block.getWidth() == 45);
    }

    @Test(timeout = 50)
    public void testMoveLeft() {
        Obstacle block = new Obstacle(100, 100, 40);
        block.moveLeft();
        assert (block.getLocation() == 39);
    }
}
