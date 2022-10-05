package The_GUI;

import GameProgram.LeaderboardDisplay;
import GameProgram.PlayGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JPanel {

    private final Rectangle player;

    private final List<Rectangle> obstacles = new ArrayList<>();

    private final List<Rectangle> goldenApples = new ArrayList<>();

    private final List<Rectangle> poisonApples = new ArrayList<>();

    private final int width;

    private final int height;

    public JFrame frame = new JFrame();

    //source to help make initial GUI frame: https://www.guru99.com/java-swing-gui.html#4

    /**
     * Creates a new GUI object and stores in it an instance of a player, width and height of the game board
     *
     * @param player     an instance of a player that should be put on a game board
     * @param myListener an instance of a class that gets user input from the keyboard
     * @param width      game board width
     * @param height     game board height
     */
    public GUI(Rectangle player, Listener myListener, int width, int height) {
        addKeyListener(myListener);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow(true);
        this.player = player;
        this.width = width;
        this.height = height;
    }

    /**
     * Assigns name and size to a frame and opens it in a new window.
     */
    public void frameSetup() {
        frame.setTitle("Not Flappy Bird");
        frame.setSize(width, height);
        frame.setLayout(new BorderLayout());
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Closes the game window after the game ends.
     */
    public void endGame() {
        setFocusable(false);
        setVisible(false);
    }

    /**
     * Moves the player visual by y squares.
     *
     * @param y number of squares the player needs to be moved by.
     */
    public Rectangle moveGamePlayer(int y) {
        player.setLocation(player.x, player.y + y);
        return player;
    }

    /**
     * Paints the background and all objects in the frame.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        requestFocus(true);
        super.paintComponent(g);

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.orange);
        g.fillRect(0, height - 120, width, 120);

        g.setColor(Color.green);
        g.fillRect(0, height - 120, width, 20);

        g.setColor(Color.RED);
        g.fillRect(player.x, player.y, player.width, player.height);

        for (int i = 0; i < obstacles.size(); i++) {
            paintObstacle(g, obstacles.get(i));
        }

        for (Rectangle poison : poisonApples) {
            paintPoisonApple(g, poison);
        }

        for (Rectangle golden : goldenApples) {
            paintGoldenApple(g, golden);
        }
    }

    /**
     * Paints a given obstacle in the frame.
     *
     * @param g      the <code>Graphics</code> object to protect
     * @param column an object of type Rectangle that represents a top or bottom part of the obstacle
     */
    public void paintObstacle(Graphics g, Rectangle column) {
        g.setColor(Color.BLACK);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    /**
     * Paints a given poison apple in the frame.
     *
     * @param g      the <code>Graphics</code> object to protect
     * @param column an object of type Rectangle that represents a poison apple
     */
    public void paintPoisonApple(Graphics g, Rectangle column) {
        g.setColor(Color.BLACK);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    /**
     * Paints a given golden apple in the frame.
     *
     * @param g      the <code>Graphics</code> object to protect
     * @param column an object of type Rectangle that represents a golden apple
     */
    public void paintGoldenApple(Graphics g, Rectangle column) {
        g.setColor(Color.ORANGE);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    /**
     * Moves all objects on the GUI (excluding player) left by 1 square.
     */
    public void moveAllLeft() {
        for (Rectangle i : obstacles) {
            i.setLocation(i.x - 1, i.y);
        }

        for (Rectangle i : goldenApples) {
            i.setLocation(i.x - 1, i.y);
        }

        for (Rectangle i : poisonApples) {
            i.setLocation(i.x - 1, i.y);
        }
        repaint();
    }

    /**
     * Removes the first golden apple in the list of golden apples currently displayed.
     */
    public boolean removeFirstGoldenApple() {
        if (goldenApples.size() != 0) {
            goldenApples.remove(0);
            return true;
        }
        return false;
    }

    /**
     * Removes the first poison apple in the list of poison apples currently displayed.
     */
    public boolean removeFirstPoisonApple() {
        if (poisonApples.size() != 0) {
            poisonApples.remove(0);
            return true;
        }
        return false;
    }

    /**
     * Removes a golden apple with the given coordinates.
     *
     * @param x x-coordinate of the apple
     * @param y y-coordinate of the apple
     */
    public boolean removeGoldenApple(int x, int y) {
        return goldenApples.removeIf(golden -> golden.x == x && golden.y == y);
    }

    /**
     * Creates a new poison apple with the given coordinates, width and height. Adds it to the list of
     * all poison apples to be displayed in the GUI.
     *
     * @param x      x-coordinate of the apple
     * @param y      y-coordinate of the apple
     * @param width  width of the apple
     * @param height height of the apple
     */
    public void addPoisonApple(int x, int y, int width, int height) {
        poisonApples.add(new Rectangle(x, y, width, height));
    }

    /**
     * Creates a new golden apple with the given coordinates, width and height. Adds it to the list of
     * all golden apples to be displayed in the GUI.
     *
     * @param x      x-coordinate of the apple
     * @param y      y-coordinate of the apple
     * @param width  width of the apple
     * @param height height of the apple
     */
    public void addGoldenApple(int x, int y, int width, int height) {
        goldenApples.add(new Rectangle(x, y, width, height));
    }

    /**
     * Creates a new obstacle with the given coordinates, width and height. Adds it to the list of
     * all obstacles to be displayed in the GUI.
     *
     * @param x            x-coordinate of the obstacle
     * @param topHeight    height of the top part of the obstacle
     * @param bottomHeight height of the bottom part of the obstacle
     * @param width        width of the obstacle
     */
    public void addObstacle(int x, int topHeight, int bottomHeight, int width) {
        obstacles.add(new Rectangle(x, height - bottomHeight, width, bottomHeight));
        obstacles.add(new Rectangle(x, 0, width, topHeight));
    }

    /**
     * Removes the first obstacle in the list of all obstacles currently in the game.
     */
    public boolean removeObstacle() {
        if (obstacles.size() != 0) {
            obstacles.remove(0);
            obstacles.remove(0);
            return true;
        }
        return false;
    }

    /**
     * Displays a pop-up window with a final score.
     * @param score score of the game
     */
    public void displayScore(int score) {
        JOptionPane.showMessageDialog(frame, "YOU LOST!\nYour score: " + score);
        frame.setVisible(false);
    }

    public static void main(String[] args) {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        LeaderboardDisplay leaderboardDisplay = new LeaderboardDisplay();
        PlayGame play = new PlayGame("Jane", observer2, observer1, leaderboardDisplay);
        observer2.savePlayGame(play);
        play.startGame();
        play.runGame();
    }
}