package The_GUI;

import GameProgram.Obstacle;
import GameProgram.Reward;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameBoardObserver implements PropertyChangeListener {

    private GUI userInter;

    /**
     * Creates a new The_GUI.GameBoardObserver object.
     */
    public GameBoardObserver() {
    }

    /**
     * Saves an instance of GUI to an instance variable
     * @param newGUI GUI used for displaying the current game
     */
    public void saveGUI(GUI newGUI) {
        this.userInter = newGUI;
    }


    /**
     * Gets called when one of the observed properties has changed. Calls other methods to reflect the change on the GUI.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        switch (name) {
            case "move":
                userInter.moveAllLeft();
                break;
            case "reward":
                if (evt.getNewValue().equals("null")) {
                    removeFirstReward(evt.getOldValue());
                } else if (evt.getNewValue().equals("collected")) {
                    removeReward(evt.getOldValue());
                } else {
                    addReward(evt.getNewValue());
                }
                break;
            case "obstacle":
                if (evt.getNewValue().equals("null")) {
                    userInter.removeObstacle();
                } else {
                    addObstacle(evt.getNewValue());
                }
                break;
        }
    }

    public boolean removeReward(Object reward) {
        Reward r = (Reward) reward;
        if (!r.isBadReward()) {
            int xPos = r.getXCoordinate();
            int yPos = r.getYCoordinate();
            return userInter.removeGoldenApple(xPos, yPos);
        }
        return false;
    }

    public void removeFirstReward(Object r) {
        Reward reward = (Reward) r;
        if (reward.isBadReward()) {
            userInter.removeFirstPoisonApple();
        } else {
            userInter.removeFirstGoldenApple();
        }
    }

    public void addReward(Object r) {
        Reward reward = (Reward) r;
        if (reward.isBadReward()) {
            userInter.addPoisonApple(reward.getXCoordinate(), reward.getYCoordinate(),
                    reward.getWidth(), reward.getHeight());
        } else {
            userInter.addGoldenApple(reward.getXCoordinate(), reward.getYCoordinate(),
                    reward.getWidth(), reward.getHeight());
        }
    }

    public void addObstacle(Object obstacle) {
        Obstacle o = (Obstacle) obstacle;
        userInter.addObstacle(o.getLocation(), o.getTopObstacleHeight(), o.getBottomObstacleHeight(),
                o.getWidth());
    }
}
