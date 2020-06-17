package utn.telefonica.app.session;

import utn.telefonica.app.model.User;
import utn.telefonica.app.model.enums.UserType;

import java.util.Date;

public class Session {

    String      token;
    User        loggedUser;
    Date        lastAction;

    public Session(String token, User loggedUser, Date lastAction) {
        this.token = token;
        this.loggedUser = loggedUser;
        this.lastAction = lastAction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isEmployer(){ return loggedUser.userType == UserType.EMPLOYER;}

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Date getLastAction() {
        return lastAction;
    }

    public void setLastAction(Date lastAction) {
        this.lastAction = lastAction;
    }
}
