package The_GUI;

import GameProgram.GamePlayer;
import GameProgram.Obstacle;
import GameProgram.PlayGame;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class GameManagerObserver implements PropertyChangeListener {

    private GUI userInter;
    private PlayGame play;

    private final GameBoardObserver gameBoardObserver;

    private int width;

    private int height;

    /**
     * Constructs a new GameManagerObserver object.
     * @param observer instance of The_GUI.GameBoardObserver class
     */
    public GameManagerObserver(GameBoardObserver observer) {
        this.gameBoardObserver = observer;
    }

    /**
     * Saves an instances of GameProgram.PlayGame class to an instance variable.
     * @param play instance of the PlayGame class
     */
    public void savePlayGame(PlayGame play) {
        this.play = play;
        this.width = play.getGameBoardDimensions().width;
        this.height = play.getGameBoardDimensions().height;
    }


    /**
     * Gets called when one of the observed properties has changed. Calls other methods to reflect the change on the GUI.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("gameEnd")) {
            if (evt.getNewValue().equals("run")) {
                startGUI();
            } else if (evt.getOldValue().equals("run")) {
                int score = Integer.parseInt(evt.getNewValue().toString());
                userInter.displayScore(score);
                userInter.endGame();
            }
        } else if (evt.getPropertyName().equals("player")) {
            movePlayer(evt.getNewValue(), evt.getOldValue());
        }
    }

    /**
     * Constructs and sets up a new GUI object to display the game. Adds to the GUI two obstacles and a player.
     */
    public void startGUI() {
        GamePlayer p = play.getManager().getPlayer();
        Rectangle player = new Rectangle(p.getXCoordinate(), p.getYCoordinate(), p.getWidth(), p.getHeight());
        Listener myListener = new Listener(play.getManager());
        this.userInter = new GUI(player, myListener, width, height);
        gameBoardObserver.saveGUI(userInter);
        userInter.frame.add(userInter);
        userInter.frameSetup();
        List<Obstacle> obstacles = play.getObstacleList();
        for (Obstacle obs : obstacles) {
            userInter.addObstacle(obs.getLocation(), obs.getTopObstacleHeight(), obs.getBottomObstacleHeight(),
                    obs.getWidth());
        }
    }

    /**
     * Moves player on the graphical user interface.
     * @param newValue new y-coordinate of the player
     * @param oldValue previous y-coordinate of the player
     */
    public void movePlayer(Object newValue, Object oldValue) {
        int newV = Integer.parseInt(newValue.toString());
        int oldV = Integer.parseInt(oldValue.toString());
        userInter.moveGamePlayer(newV - oldV);
    }

    /**
     * Returns the instance of GUI displaying the current game.
     * @return GUI that is displaying current game.
     */
    public GUI getGUI() { return userInter; }
}
