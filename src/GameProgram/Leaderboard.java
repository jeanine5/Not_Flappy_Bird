package GameProgram;

import java.util.*;
import java.util.stream.Collectors;

public class Leaderboard{
    private Map<String, Integer> scoreMap;


    private static Leaderboard lb;

    /**
     * Constructs a new GameProgram.Leaderboard object.
     */
    private Leaderboard(){
        scoreMap = new HashMap<>();
    }

    /**
      Returns a string representation of a sorted leaderboard.
      @return String representation of the leaderboard.
     */
    public String toString() {
        scoreMap = scoreMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        StringBuilder string = new StringBuilder();
        for (String key : scoreMap.keySet()) {
            string.append(key).append(": ").append(scoreMap.get(key)).append("\r\n");
        }
        return string.toString();
    }

    /**
     * Adds a new user and their highest score to the leaderboard.
     * @param username username of the user
     * @param score user's highest score
     */
    public void addNewScore(String username, int score){
        scoreMap.put(username, score);
    }

    /**
     * Updates the score of the user that is already on the leaderboard to a new highest score.
     * @param username username of the user
     * @param score new highest score of the user
     * @return boolean
     */
    public boolean updateExistingScore(String username, int score){
        if (!scoreMap.containsKey(username)){
            return false;
        } else if ((scoreMap.get(username) >= score)) {
            return true;
        } else {
            scoreMap.replace(username, scoreMap.get(username), score);
            return true;
        }
    }

    /**
     * Returns a Map that matches each username to their highest score.
     * @return Map that matches each username to their highest score.
     */
    public static Leaderboard getInstance() {
        if (lb == null){
            lb = new Leaderboard();
        }
        return lb;
    }

    /**
     * Returns a map that matches users to their highest scores.
     * @return a map that matches users to their highest score
     */
    public Map<String, Integer> getScoreMap() { return this.scoreMap; }
}
