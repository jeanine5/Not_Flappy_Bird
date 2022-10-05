package Login;


import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class LoginReader {

    /**
     * Writes a String to file "userdata.txt".
     * @param someString a String
     */
    public void writeTo(String someString) {
        try {
            FileWriter writer = new FileWriter("userdata.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(someString);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds line to an ArrayList if it is not empty, or equals to "".
     * Returns the ArrayList.
     * @return ArrayList<String>
     */
    public ArrayList<String> readFrom() {
        ArrayList<String> identityList= new ArrayList<>();
        try {
            FileReader reader = new FileReader("userdata.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while ((line != null)) {
                if (!line.equals("")) {
                    identityList.add(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return identityList;
    }

}
