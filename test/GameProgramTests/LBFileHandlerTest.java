package GameProgramTests;

import GameProgram.LBFileHandler;
import GameProgram.Leaderboard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LBFileHandlerTest {

    @Test
    public void testReadLBFile() {
        Leaderboard lb = Leaderboard.getInstance();
        LBFileHandler fileHandler = new LBFileHandler();
        lb.addNewScore("John", 28);
        lb.addNewScore("July", 19);
        fileHandler.saveLBFile(lb);
        Leaderboard lb2 = Leaderboard.getInstance();
        fileHandler.readLBFile(lb2);
        assertEquals(6, lb2.getScoreMap().size());
    }

    @Test
    public void testSaveLBFile() {
        Leaderboard lb = Leaderboard.getInstance();
        LBFileHandler fileHandler = new LBFileHandler();
        fileHandler.readLBFile(lb);
        lb.addNewScore("Jason", 30);
        fileHandler.saveLBFile(lb);
        Leaderboard lb2 = Leaderboard.getInstance();
        fileHandler.readLBFile(lb2);
        assertEquals(6, lb2.getScoreMap().size());
    }

}
