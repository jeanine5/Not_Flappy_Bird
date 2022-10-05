package UnfinishedMenuGUI;

import Login.AdminManager;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class AdminMenu {

    String username;

    private static final Logger logger = (Logger) LogManager.getLogger(AdminMenu.class);

    /**
     * Admin menu options are displayed to console.
     */
    public void displayOptions(){
        logger.info("Displaying Admin menu options");
        System.out.println("P - PROMOTE ADMIN USER");
        System.out.println("B - TEMPORARILY BAN USER");
        System.out.println("U - UNBAN ACCOUNT");
        System.out.println("D - DELETE USER");
        System.out.println("C - CREATE NEW ADMIN");
        logger.info("Admin menu options displayed");
    }

    /**
     * Once the user makes their choice, the scanner goes to the next line.
     * @return String returns the input
     */
    public String makeChoice(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Used to determine a timeframe to ban accounts.
     * @return LocalDateTime
     */
    public LocalDateTime getBanLength(){
        logger.info("Determining ban length");
        System.out.println("Enter Year, Month, Day, Hour, Minute");
        AdminMenu menu = new AdminMenu();
        int year = Integer.parseInt(menu.makeChoice());
        int month = Integer.parseInt(menu.makeChoice());
        int day = Integer.parseInt(menu.makeChoice());
        int hour = Integer.parseInt(menu.makeChoice());
        int minute = Integer.parseInt(menu.makeChoice());
        logger.info("length of ban determined");
        return LocalDateTime.of(year, month, day, hour, minute);

    }

    /**
     * Performs actions for Admin account in backend based on user input.
     * @param choice user input choice
     * @param username username of the user
     */

    public void adminActions (String choice, String username){
        AdminManager manager = new AdminManager();
        logger.info("New instance of admin manager created");
        switch (choice) {
            case "P":
                manager.promoteAdminUser(username);
                logger.info(username + " promoted to admin user");
                break;
            case "B": {
                AdminMenu menu = new AdminMenu();
                manager.temporaryBan(username, menu.getBanLength());
                logger.info(username + " has been temporarily banned");
                break;
            }
            case "U": {
                AdminMenu menu = new AdminMenu();
                manager.unbanAccount(username, menu.getBanLength());
                logger.info(username + " has been unbanned");
                break;
            }
            case "D":
                manager.deleteUser(username);
                logger.info(username + " has been deleted");
                break;
            default: {
                AdminMenu menu = new AdminMenu();
                logger.info("New instance of Admin menu has been created");
                System.out.println("Enter Password");
                String password = menu.makeChoice();
                manager.createNewAdmin(username, password);
                logger.info("New Admin user has been created.");
                break;
            }
        }

    }


}

