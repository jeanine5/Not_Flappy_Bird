package Menu;

import GameProgram.LeaderboardDisplay;
import GameProgram.PlayGame;
import UnfinishedMenuGUI.MenuObserver;

public class MenuCommander {
    private final PlayGame play;

    private final LeaderboardDisplay display;

    /**
     * Constructs a new instance of MenuCommander object.
     * @param mainPlay instance of the class which runs the game
     * @param display instance of the class which displays the leaderboard
     */
    public MenuCommander(PlayGame mainPlay, LeaderboardDisplay display) {
        this.play = mainPlay;
        this.display = display;
    }

    /**
     * Converts a String containing the number of the menu option to an integer.
     * @param userInput String containing the number of the menu option
     * @return int representing the menu option
     */
    public int inputCheck(String userInput) {
        int output;
        switch (userInput) {
            case "1":
                output = 1;
                break;
            case "2":
                output = 2;
                break;
            case "3":
                output = 3;
                break;
            default:
                output = 4;
                break;
        }
        return output;
    }

    /**
     * Executes respective menu options depending on the user input.
     * @param option number of the menu option the user picked
     */
    public void next(int option) {
        if (option == 1) {
            play.startGame();
            play.runGame();
        } else if (option == 2) {
            System.out.println(display.getLeaderboard());
        } else if (option == 3) {
            System.out.println("Use UP and DOWN arrow keys to move the bird and avoid obstacles. Collect golden apples " +
                    "to increase your score. Avoid poison apples or the game will end!");
        }
        else  {play.stopRunning();}
    }

}
