package GameProgram;

import java.awt.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


public class
Obstacle {
    private final Dimension topObstacle;
    private final Dimension bottomObstacle;

    private int xCoordinate;

    private static final Logger logger = (Logger) LogManager.getLogger(Obstacle.class);


    /**
     * Constructs a new Obstacle object.
     * @param topHeight the height of the upper obstacle
     * @param bottomHeight the height of the lower obstacle
     * @param x the coordinate of the location of the upper and lower obstacles
     */
    public Obstacle(int topHeight, int bottomHeight, int x) {
        topObstacle = new Dimension(30, topHeight);
        bottomObstacle = new Dimension(30, bottomHeight);
        xCoordinate = x;
        logger.info("New instance of obstacle created in the back end.");
    }

    /**
     * Sets this obstacle's upper and lower height and x-coordinate location to the given values.
     * @param topHeight the initialized upper height
     * @param bottomHeight the initialized lower height
     * @param width the initialized x location
     */
    public void setObstacleSize(int topHeight, int bottomHeight, int width){
        topObstacle.setSize(width, topHeight);
        bottomObstacle.setSize(width, bottomHeight);
    }

    /**
     * Returns the height of the upper part of this obstacle.
     * @return int top obstacle height
     */
    public int getTopObstacleHeight(){
        return topObstacle.height;
    }

    /**
     * Returns the height of the lower part of this obstacle.
     * @return int bottom obstacle height
     */
    public int getBottomObstacleHeight(){
        return bottomObstacle.height;
    }

    /**
     * Returns the width of this obstacle. Since the width is equal for both upper and lower obstacles,
     * it only returns bottom obstacle width.
     * @return int width of the obstacle
     */
    public int getWidth() { return bottomObstacle.width; }

    /**
     * Returns the current location of this obstacle.
     * @return int x-coordinate
     */
    public int getLocation() {
        return xCoordinate;
    }

    /**
     * Moves this obstacle to the left by changing the x coordinate.
     */
    public void moveLeft() {
        xCoordinate -= 1;
    }

    /**
     * Sets the location of this obstacle.
     * @param x new x-coordinate
     */
    public void setLocation(int x) { this.xCoordinate = x; }
}
