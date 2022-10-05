package GameProgramTests;

import GameProgram.Reward;
import org.junit.Test;

public class RewardTest {

    @Test(timeout = 1000)
    public void testXPosition() {
        Reward apple = new Reward(3,4, true);
        assert(apple.getXCoordinate()==3);
    }

    @Test(timeout = 100)
    public void testWidth() {
        Reward apple = new Reward(3,4, false);
        assert(apple.getWidth()==10);
    }

    @Test(timeout = 100)
    public void testHeight() {
        Reward apple = new Reward(3,4, false);
        assert(apple.getHeight()==10);
    }
}
