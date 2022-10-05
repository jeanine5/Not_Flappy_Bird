package UnfinishedMenuGUI;

import javax.swing.*;
import java.awt.*;

public class MenuObserver extends JPanel {

    private final int width;
    private final int height;

    public JFrame frame = new JFrame();

    /**
     * Creates a new GUI object and stores in it an instance of a player, width and height of the game board.
     * @param width game board width
     * @param height game board height
     */
    public MenuObserver(MenuListener listener, int width, int height){
        addKeyListener(listener);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocus();
        frameSetup();
        frameVisible();
        this.width = width;
        this.height = height;
    }

    /**
     * Assigns name and size to a frame.
     */
    public void frameSetup(){
        frame.setTitle("Not Flappy Bird");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Opens the frame in a new window.
     */
    public void frameVisible(){
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocus();

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.lightGray);
        g.fillRect(100, 100, 100, 30);
        g.fillRect(100, 150, 100, 30);
        g.fillRect(100, 200, 100, 30);
        g.fillRect(100, 250, 100, 30);

        g.setColor(Color.BLACK);
        g.drawString("1.Play Game", 111, 120);
        g.drawString("2.Leaderboard", 105, 170);
        g.drawString("3.How to Play", 107, 220);
        g.drawString("4.Exit", 131, 270);

    }


    public static void main(String[] args) {
    }
}

