package GameProgram;

public class LeaderboardDisplay {

    private final Leaderboard lb;

    private final LBFileHandler fileHandler = new LBFileHandler();

    /**
     * Constructs a new LeaderboardDisplay object.
     */
    public LeaderboardDisplay() {
        this.lb = Leaderboard.getInstance();
        fileHandler.readLBFile(lb);
    }

    /**
     * Updates the leaderboard with a username and its corresponding score.
     * @param score final score of the last game
     * @param username username of the user that played the game
     */
    public void updateLeaderboard(int score, String username) {
        if (!lb.updateExistingScore(username, score)){
            lb.addNewScore(username, score);
        } else {
            lb.updateExistingScore(username, score);
        }
        saveLeaderboard();
    }

    /**
     * Saves current leaderboard to a file.
     */
    public void saveLeaderboard() {
        fileHandler.saveLBFile(lb);
    }

    /**
     * Returns current leaderboard.
     * @return current leaderboard
     */
    public Leaderboard getLeaderboard() {
        return lb;
    }
}
