package GameProgram;


import java.awt.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


public class    GamePlayer {
    private final String username;
    private final Point location;

    private final Dimension dimensions = new Dimension(20, 20);

    private static final Logger logger = (Logger) LogManager.getLogger(GamePlayer.class);

    /**
     * Constructs a new GamePlayer object.
     * @param name username of the user playing the game
     */
    public GamePlayer(String name){
        username = name;
        // this.name = username from account
        location = new Point(80, 300);
        logger.info("Game Player created");
    }

    /**
     * Returns the username of the user playing the game.
     * @return String username
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Sets the location of this GamePlayer on the backend grid.
     * @param xPos new x-coordinate
     * @param yPos new y-coordinate
     */
    public void setLocation(double xPos, double yPos){
        this.location.setLocation(xPos, yPos);
    }

    /**
     * Returns the X-coordinate of this GamePlayer.
     * @return int x-coordinate
     */
    public int getXCoordinate() { return location.x; }

    /**
     * Returns the Y-coordinate of this GamePlayer.
     * @return int y-coordinate
     */
    public int getYCoordinate() { return location.y; }

    /**
     * Returns the width of this GamePlayer in the backend.
     * @return int width
     */
    public int getWidth() { return dimensions.width; }

    /**
     * Returns the height of this GamePlayer in the backend.
     * @return int height
     */
    public int getHeight() { return dimensions.height; }

}
