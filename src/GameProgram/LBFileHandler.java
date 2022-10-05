package GameProgram;

import java.io.*;
import java.util.Map;

public class LBFileHandler {

    private final String LBFileName = "LeaderBoard.txt";

    /**
     * Reads the leaderboard data from a file and saves it to an instance of a Leaderboard class.
     * @param lb instance of the Leaderboard class
     */
    public void readLBFile(Leaderboard lb) {

        try {
            FileReader reader = new FileReader(LBFileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            String[] l;

            while ((line = bufferedReader.readLine()) != null){
                l = line.split(",");
                lb.addNewScore(l[0], Integer. parseInt(l[1]));
            }
            bufferedReader.close();
            reader.close();

        } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     * Saves leaderboard data from an instance of the Leaderboard class to a file.
     * @param lb instance of the Leaderboard class
     */
    public void saveLBFile (Leaderboard lb) {

        try {
            FileWriter writer = new FileWriter(LBFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            Map<String, Integer> scoreMap = lb.getScoreMap();

            for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}