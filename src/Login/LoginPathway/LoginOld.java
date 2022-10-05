package Login.LoginPathway;

import Login.AccountSystem;
import Login.LoginInfo.CheckInfo;
import Login.LoginInfo.LoginInfo;
import Login.LoginLines.*;
import Login.UserAccount;
import Menu.Menu;
import java.time.LocalDateTime;

public class LoginOld implements LoginPathway {
    private LoginLinesOld lines;


    public LoginOld() {
        this.lines = LoginLinesOld.USERNAME;
    }

    @Override
    public void processInfo(AccountSystem system, LoginInfo info) {
        UserAccount user = system.logIn(info.getUsername(), info.getPassword());
        user.loginHistory(LocalDateTime.now());
        launchMenu(user.getUsername(), user.getStatus());
    }

    @Override
    public boolean canProcess(AccountSystem mainSystem, LoginInfo mainInfo) {
        return mainSystem.checkPassword(mainInfo.getUsername(), mainInfo.getPassword());
    }

    @Override
    public String outputUI() {
        return lines.printUI();
    }

    @Override
    public void nextLine() {
        lines = lines.next();
    }

    @Override
    public boolean checkFinish() {
        return lines == LoginLinesOld.PASSWORD;
    }

    /**
     * Launches menu
     * @param user a Username in the AccountSystem
     * @param admin Not implemented feature
     */
    public void launchMenu(String user, boolean admin) {
        Menu mainMenu = new Menu();
        mainMenu.mainMenu(user, admin);
    }

}
