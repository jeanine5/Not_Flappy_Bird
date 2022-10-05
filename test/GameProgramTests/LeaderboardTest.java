package GameProgramTests;

import GameProgram.Leaderboard;
import org.junit.Test;

import GameProgram.Leaderboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeaderboardTest {


    @Test(timeout = 300)
    public void testAddNewScore() {
        Leaderboard scoreBoard = Leaderboard.getInstance();
        scoreBoard.addNewScore("Mary", 10);
        scoreBoard.addNewScore("Judy", 12);
        assertTrue(scoreBoard.getScoreMap().containsKey("Mary"));
        assert(scoreBoard.getScoreMap().get("Judy") == 12);
    }

    @Test(timeout = 300)
    public void testUpdateExistingScore() {
        Leaderboard scoreBoard = Leaderboard.getInstance();
        scoreBoard.addNewScore("Mary", 10);
        scoreBoard.updateExistingScore("Mary", 16);
        assert(scoreBoard.getScoreMap().get("Mary") == 16);
    }

    @Test
    public void testToString() {
        Leaderboard scoreBoard = Leaderboard.getInstance();
        scoreBoard.addNewScore("Mary", 10);
        scoreBoard.addNewScore("Judy", 12);
        scoreBoard.addNewScore("John", 28);
        scoreBoard.addNewScore("July", 19);
        System.out.println(scoreBoard);
    }

    @Test
    public void testGetScoreMap() {
        Leaderboard scoreBoard = Leaderboard.getInstance();
        scoreBoard.addNewScore("John", 28);
        scoreBoard.addNewScore("July", 19);
        assertEquals(6, scoreBoard.getScoreMap().size());
    }

}

