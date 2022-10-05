package GameProgram;

import The_GUI.GameManagerObserver;
import The_GUI.GameBoardObserver;

import java.awt.*;
import java.util.List;

public class PlayGame  {

    private GameManager manager;

    private final String username;
    private GameBoard board;

    private boolean running;

    private final GameManagerObserver gameManagerObserver;

    private final GameBoardObserver gameBoardObserver;

    private final LeaderboardDisplay display;

    /**
     * Creates new GameProgram.PlayGame object.
     */
    public PlayGame(String username, GameManagerObserver observer1, GameBoardObserver observer2,
                    LeaderboardDisplay display) {
        this.running = true;
        this.username = username;
        this.gameManagerObserver = observer1;
        this.gameBoardObserver = observer2;
        this.display = display;
    }

    /**
     * Returns whether the program is still running
     * @return boolean
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Stops the program from continuing running
     */
    public void stopRunning() {
        running = false;
    }

    /**
     * Starts a new game.
     */
    public void startGame() {
        this.manager = new GameManager(username);
        this.board = manager.getGameBoard();
        manager.addObserver(gameManagerObserver);
        manager.startGame();
        board.addObserver(gameBoardObserver);
    }

    /**
     * Runs the game until player hits an obstacle or collects a poison apple.
     */
    public void runGame() {
        boolean status = manager.runGame();
        while (status) {
            try {
                Thread.sleep(15); }
            catch(InterruptedException ex)
            { ex.printStackTrace(); }
            status = manager.runGame();
        }
        endGame();
    }


    private void endGame() {
        display.updateLeaderboard(manager.getScore(), username);
    }

    /**
     * Returns the height and width of the game board.
     * @return Dimension of the game board.
     */
    public Dimension getGameBoardDimensions() {
        GameBoard board = new GameBoard();
        return board.getGameBoardDimensions();
    }

    /**
     * Returns a list containing all obstacles currently on the game board.
     * @return List</Obstacle>
     */
    public List<Obstacle> getObstacleList() { return board.getObstacleList(); }

    /**
     * Returns the GameProgram.GameManager object, which is running the current game.
     * @return GameProgram.GameManager
     */
    public GameManager getManager() { return this.manager; }
}
