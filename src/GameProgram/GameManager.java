package GameProgram;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class GameManager{
    private final Stopwatch timer;

    private int totalGoldenApples;

    private final GamePlayer player;

    private final GameBoard grid;

    private int score;

    private final PropertyChangeSupport observable;

    private static final Logger logger = (Logger) LogManager.getLogger(GameManager.class);

    /**
     * Constructs a new GameManager object. Stores a new GamePlayer object in an instance variable
     * and assigns to it the username of the current user.
     */
    public GameManager(String username) {
        logger.info("New instance of GameManager is being initialized");
        player = new GamePlayer(username);
        score = 0;
        timer = new Stopwatch();
        observable = new PropertyChangeSupport(this);
        grid = new GameBoard();
        totalGoldenApples = 0;
        logger.info("New instance of Game Manager created");
    }

    /**
     * Adds a new instance of the observer class to the list of this GameManager observers.
     * @param observer an observer of this GameManager
     */
    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("obstacles", observer);
        logger.info("Observer added to observable with the property name " +
                "'obstacles' with the Property change listener " + observer );
    }

    /**
     * Notifies all the observers of this GameManager of a change that has occurred.
     * @param event PropertyChangeEvent that stores information about the new value after the change
     */
    public void notifyObservers(PropertyChangeEvent event) {
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()) {
            observer.propertyChange(event);
        }
        logger.info("Observers of this Game Manager notified of" + event);
    }

    /**
     * Returns the number of observers this GameManager has.
     * @return int
     */
    public int getObserversSize() {
        List<PropertyChangeListener> list = List.of(observable.getPropertyChangeListeners());
        return list.size();
    }

    /**
     * Moves the player upwards by 1 square.
     */
    public void moveUp(){
        int xCoordinate = player.getXCoordinate();
        int yCoordinate = player.getYCoordinate();
        int newY = yCoordinate - 5;
        player.setLocation(xCoordinate, newY);
        logger.info("GamePlayer moved up in the backend.");
        PropertyChangeEvent event = new PropertyChangeEvent(this, "player", yCoordinate, newY);
        logger.info("New event created from GameManager with property name: player");
        notifyObservers(event);
    }

    /**
     * Moves the player downwards by 1 square.
     */
    public void moveDown(){
        int xCoordinate = player.getXCoordinate();
        int yCoordinate = player.getYCoordinate();
        int newY = yCoordinate + 5;
        player.setLocation(xCoordinate, newY);
        logger.info("GamePlayer moved down in the backend.");
        PropertyChangeEvent event = new PropertyChangeEvent(this, "player", yCoordinate, newY);
        logger.info("New event created from GameManager with property name: player");
        notifyObservers(event);
    }

    /**
     * Starts a new game.
     */
    public void startGame() {
        timer.start();
        logger.info("timer backend has started");
        PropertyChangeEvent event = new PropertyChangeEvent(this, "gameEnd", "stop",
                "run");
        notifyObservers(event);
        logger.info("Front-end notified that game has begun in the backend.");
    }

    /**
     * Runs the game and ends it when the player has hit an obstacle or the bottom of the game board. Calculates
     * the score and updates the leaderboard after the game ended.
     * @return boolean
     */
    public boolean runGame() {
        grid.moveObjects();
        grid.updateObstacles();
        grid.updateRewards();
        return !isTouching();
    }

    /**
     * Checks whether the player object comes in contact with the bottom of the game board, an obstacle or if it's
     * touching a reward and what its consequences will be.
     * @return boolean
     */
    private boolean isTouching() {
        Reward re = grid.isTouchingReward(this.player);
        if(grid.isTouchingBottom(this.player) || grid.isTouchingObstacle(this.player)){
            endGame();
            return true;
        } else if (re != null) {
            if (re.isBadReward()) {
                endGame();
                return true;
            } else {
                totalGoldenApples += 1;
            }
        }
        return false;
    }

    /**
     * Ends the current game, calculates the score of the game and updates the leaderboard, if required.
     */
    public void endGame() {
        timer.stop();
        logger.info("game timer has ended in the backend");
        generateRewardScore();
        PropertyChangeEvent event = new PropertyChangeEvent(this, "gameEnd", "run",
                this.score);
        notifyObservers(event);
        logger.info("GUI has been notified that the game has ended in the back end.");
    }

    /**
     * Updates the score variable while the game is running based on the time elapsed.
     */
    public void updateScore() {
        score = (int)timer.getElapsedSeconds();
        logger.info("Score updated.");
    }

    /**
     * Calculates the score for the current game based on the time elapsed and the number of
     * Golden Apples collected. Stores it in an instance variable.
     */
    public void generateRewardScore(){
        score = (int)timer.getElapsedSeconds() + totalGoldenApples * 10;
        logger.info("Reward score generated.");
    }

    /**
     * Returns the score of the current game.
     * @return int score of the current game
     */
    public int getScore(){
        return score;
    }

    /**
     * Returns the player of the current game.
     * @return GameProgram.GamePlayer player of the current game
     */
    public GamePlayer getPlayer() { return player; }

    /**
     * Returns the game board that the current game is played on.
     * @return GameProgram.GameBoard game board of the current game
     */
    public GameBoard getGameBoard() { return this.grid; }
}

