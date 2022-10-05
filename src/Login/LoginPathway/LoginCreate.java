package Login.LoginPathway;

import Login.AccountSystem;
import Login.LoginInfo.*;
import Login.LoginLines.*;

public class LoginCreate implements LoginPathway {
    private LoginLinesCreate lines;

    public LoginCreate() {
        this.lines = LoginLinesCreate.CREATE_USERNAME;
    }

    @Override
    public void processInfo(AccountSystem mainSystem, LoginInfo mainInfo) {
        mainSystem.createUser(mainInfo.getUsername(), mainInfo.getPassword(), mainInfo.getAdmin());
        mainInfo.writeToFile(mainInfo.getIdentity());
    }

    @Override
    public boolean canProcess(AccountSystem mainSystem, LoginInfo mainInfo) {
        CheckInfo checker = new CheckInfo(mainSystem);
        return checker.check(mainInfo.getUsername(), mainInfo.getPassword());
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
        return lines == LoginLinesCreate.CREATE_ADMIN;
    }
}
