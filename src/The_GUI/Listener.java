package The_GUI;

import GameProgram.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {
     GameManager manager;

    public Listener(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Manages up and down keystrokes by the user to move the player object
     * @param e given key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_UP){
            manager.moveUp();
        }
        if (c == KeyEvent.VK_DOWN){
            manager.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
