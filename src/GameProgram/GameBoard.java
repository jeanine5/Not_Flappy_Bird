package GameProgram;


import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class GameBoard {

    private final int width = 300;

    private final int height = 600;

    private final List<Reward> rewardsList = new ArrayList<>();

    private final List<Obstacle> obstacleList = new ArrayList<>();

    private final PropertyChangeSupport observable;

    private static final Logger logger = (Logger) LogManager.getLogger(GameBoard.class);

    /**
     * Creates new GameProgram.GameBoard object.
     */
    public GameBoard() {
        logger.info("Initializing GameBoard");
        this.obstacleList.add(new Obstacle(240, 240, 100));
        this.obstacleList.add(randomizeObstacle());
        obstacleList.get(1).setLocation(220);
        this.observable = new PropertyChangeSupport(this);
        logger.info("GameBoard instance created");
    }

    /**
     * Adds a new observer class to the list of this GameBoard's observers.
     * @param observer instance of the observer class
     */
    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("move", observer);
        logger.info("Observer added");
    }

    /**
     * Notifies all the observers of this GameBoard of a change that has occurred.
     * @param event PropertyChangeEvent that stores information about the new value after the change
     */
    public void notifyObservers(PropertyChangeEvent event) {
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()) {
            observer.propertyChange(event);
            logger.info("Observer Notified");
        }
    }

    /**
     * Returns the number of observers this GameBoard has.
     * @return int
     */
    public int getObserversSize() {
        List<PropertyChangeListener> list = List.of(observable.getPropertyChangeListeners());
        return list.size();
    }

    /**
     * Checks if player has hit an obstacle.
     * @param player the player in the current game
     * @return boolean
     */
    public boolean isTouchingObstacle(GamePlayer player) {
        for (Obstacle o : obstacleList) {
            int oLocation = o.getLocation();
            int blank = o.getTopObstacleHeight();
            int blankSize = height - o.getBottomObstacleHeight() - o.getTopObstacleHeight();
            int height = player.getHeight();
            int xPos = player.getXCoordinate() + player.getWidth();
            int yPos = player.getYCoordinate() + player.getHeight();
            if (xPos > oLocation && oLocation + o.getWidth() > xPos) {
                logger.info("GamePlayer is touching an obstacle in the back end");
                return (yPos - height < blank | yPos > blank + blankSize);
            }
        }
        return false;
    }

    /**
     * Checks if player has hit the bottom of the game board.
     * @param player the player in the current game
     * @return boolean
     */
    public boolean isTouchingBottom(GamePlayer player) {
        logger.info("GamePlayer is touching the bottom in the backend" );
        return player.getYCoordinate() >= this.height - 140 | player.getYCoordinate() <= -15;
    }

    /**
     * Checks if player has collected a reward.
     * @param player the player in the current game
     * @return boolean
     */
    public Reward isTouchingReward(GamePlayer player) {
        logger.info("gamePlayer is touching reward in the backend");
        int xPos = player.getXCoordinate() + player.getWidth();
        int yPos = player.getYCoordinate() + player.getHeight();
        List<Reward> remove = new ArrayList<>();
        for (Reward r : rewardsList) {
            int xReward = r.getXCoordinate();
            int yReward = r.getYCoordinate() + r.getHeight();
            if (isTouchingChecker(xReward, yReward, xPos, yPos, player, r)) {
                    remove.add(r);
                }
            }
        return removeReward(remove);
    }

    private boolean isTouchingChecker(int xReward, int yReward, int xPos, int yPos, GamePlayer player, Reward r) {
        logger.info("Checking to see if GamePlayer is touching a reward");
        if ((xPos > xReward && xPos < xReward + r.getWidth()) | (xPos > xReward + r.getWidth() && xPos -
                player.getWidth() < xReward) | (xPos - player.getWidth() < xReward + r.getWidth() && xPos -
                player.getWidth() > xReward)) {
            return (yReward >= yPos && yPos >= yReward - r.getHeight()) |
                    (yReward >= yPos - player.getHeight() && yPos - player.getHeight() >= yReward - r.getHeight())
                    | (yPos > yReward && yPos - player.getHeight() < yReward - r.getHeight());
        }
        return false;
    }


    /**
     * Removes the reward from the rewards list.
     * @param remove the list where a Reward will be removed from
     * @return Reward
     */
    private Reward removeReward(List<Reward> remove) {
        logger.info("A reward is in the process if being removed in the backend, observers will be notified");
        if (!remove.isEmpty()) {
            rewardsList.remove(remove.get(0));
            PropertyChangeEvent event = new PropertyChangeEvent(this, "reward", remove.get(0),
                    "collected");
            notifyObservers(event);
            logger.info("Observers should have been notified that a 'remove reward' event has occurred");
            return remove.get(0);
        }
        return null;
    }

    /**
     * Adds new obstacles to the game board and removes obstacles when they have reached the end of the game board.
     */
    public void updateObstacles() {
        logger.info("Obstacles in the process of being updated in the backend");
        if (obstacleList.get(0).getLocation() == 60) {
            Obstacle ob = randomizeObstacle();
            obstacleList.add(ob);
            PropertyChangeEvent event = new PropertyChangeEvent(this, "obstacle", null, ob);
            notifyObservers(event);
            logger.info("Observers have been notified of an 'update Obstacles event' ");
        } else if (obstacleList.get(0).getLocation() + obstacleList.get(0).getWidth() == 0) {
            obstacleList.remove(0);
            PropertyChangeEvent event = new PropertyChangeEvent(this, "obstacle", "Obstacle",
                    "null");
            notifyObservers(event);
            logger.info("Observers have been notified of an 'update Obstacles event' ");
        }
    }

    /**
     * Adds new reward to the game board. Removes reward when it has reached the end of the game board.
     */
    public void updateRewards() {
        Obstacle last = obstacleList.get(obstacleList.size() - 1);
        logger.info("Rewards in the process of being updated in the backend");
        if (last.getLocation() == 300) {
            Reward r = randomizeReward();
            rewardsList.add(r);
            PropertyChangeEvent event = new PropertyChangeEvent(this, "reward", null,
                    r);
            notifyObservers(event);
            logger.info("Observers have been notified of an 'update Rewards event' ");
        }
        if (!rewardsList.isEmpty()) {
            if (rewardsList.get(0).getXCoordinate() + rewardsList.get(0).getWidth() == 0) {
                PropertyChangeEvent event = new PropertyChangeEvent(this, "reward",
                        rewardsList.get(0), "null");
                notifyObservers(event);
                logger.info("Observers have been notified of an 'update Rewards event' ");
                rewardsList.remove(0);
            }
        }
    }

    /**
     * Creates a new randomized obstacle.
     * @return GameProgram.Obstacle
     */
    public Obstacle randomizeObstacle() {
        logger.info("randomize obstacles initiated");
        List<Obstacle> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(320, 160, this.width));
        obstacleList.add(new Obstacle(160, 320, this.width));
        obstacleList.add(new Obstacle((this.height - 120) / 2, (this.height - 120) / 2, this.width));
        Random rand = new Random();
        int index = rand.nextInt(obstacleList.size());
        logger.info("randomize obstacles completed");
        return obstacleList.get(index);
    }

    /**
     * Creates a new randomized reward.
     * @return Rewards
     */
    public Reward randomizeReward() {
        logger.info("randomize rewards initiated");
        List<Reward> reward = new ArrayList<>();
        Random rand = new Random();
        reward.add(new Reward(this.width + 70, rand.nextInt(this.height - 160) + 5, true));
        reward.add(new Reward(this.width + 70, rand.nextInt(this.height - 200) + 45, false));
        reward.add(new Reward(this.width + 70, rand.nextInt(this.height - 200) + 45, false));
        int index = rand.nextInt(reward.size());
        logger.info("randomize rewards completed");
        return reward.get(index);
    }

    /**
     * Moves all objects on the game board (excluding player) left by 1 square.
     */
    public void moveObjects() {
        for (Obstacle o : obstacleList) {
            o.moveLeft();
        }
        for (Reward r : rewardsList) {
            r.moveLeft();
        }
        logger.info("objects have been moved left in the backend");
        PropertyChangeEvent event = new PropertyChangeEvent(this, "move", null,
                -1);
        notifyObservers(event);
        logger.info("observers have been notified that objects have moved left in the backend");
    }

    /**
     * Returns the current list of obstacles.
     * @return List</Obstacle>
     */
    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    /**
     * Adds a reward to the list of rewards.
     * @param r the reward to be added to the list of rewards
     */
    public void addReward(Reward r) {
        this.rewardsList.add(r);
    }

    /**
     *  Returns the list of rewards.
     * @return List</Reward>
     */
    public List<Reward> getRewardsList() { return this.rewardsList; }

    /**
     * Returns the dimensions of the game board.
     * @return Dimension
     */
    public Dimension getGameBoardDimensions() { return new Dimension(width, height); }
}







