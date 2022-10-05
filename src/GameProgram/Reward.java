package GameProgram;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.awt.*;

public class Reward {

    private static final Logger logger = (Logger) LogManager.getLogger(Reward.class);
    private final Point rewardLocation = new Point();
    private final Dimension dimensions = new Dimension(10, 10);

    private final boolean isBad;


    /**
     * Constructs a new Reward object and takes in the initial coordinates.
     * @param start_x initializes x coordinate
     * @param start_y initializes y coordinate
     * @param isBad stating whether the reward is good or bad
     */
    public Reward(int start_x, int start_y, boolean isBad){
        this.rewardLocation.x = start_x;
        this.rewardLocation.y = start_y;
        this.isBad = isBad;
        logger.info("Reward has been initialized");
    }

    /**
     * Returns the boolean value of this reward.
     * @return boolean that details whether the reward is good or bad
     */
    public boolean isBadReward(){
        return this.isBad;
    }

    /**
     * Returns the current x coordinate of this reward.
     * @return int x-coordinate
     */
    public int getXCoordinate() { return rewardLocation.x; }

    /**
     * Returns the current y-coordinate of this reward.
     * @return int y-coordinate
     */
    public int getYCoordinate() { return rewardLocation.y; }

    /**
     * Moves the reward to the left by subtracting one from the x-coordinate value.
     */
    public void moveLeft() {
        rewardLocation.x -= 1;
    }

    /**
     * Returns the width of this reward.
     * @return int width
     */
    public int getWidth() { return dimensions.width; }

    /**
     * Returns the height of this reward.
     * @return int height
     */
    public int getHeight() { return dimensions.height; }
}