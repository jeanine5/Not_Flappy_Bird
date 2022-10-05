import GameProgram.Leaderboard;
import Login.*;
import Menu.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountSystem system = new AccountSystem();
        Menu mainMenu = new Menu();
        LoginCommander command = new LoginCommander(system);
        LoginStart s = new LoginStart();
        LoginReader reader = new LoginReader();
        s.startUp(reader.readFrom(), system);
        Scanner in = new Scanner(System.in);
        System.out.println("NEW or OLD?");
        String userInput = in.nextLine();
        command.start(userInput);
        while (command.getState()) {
            System.out.println(command.output());
            userInput = in.nextLine();
            command.inputStore(userInput);
            command.next();
        }

        in.close();
    }
}