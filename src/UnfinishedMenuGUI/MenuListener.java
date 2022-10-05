package UnfinishedMenuGUI;

import Menu.MenuCommander;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuListener implements KeyListener {
    Menu.MenuCommander menu;

    public MenuListener(Menu.MenuCommander menu) {
        this.menu = menu;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //menu.inputCheck(Character.toString(e.getKeyChar()));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
