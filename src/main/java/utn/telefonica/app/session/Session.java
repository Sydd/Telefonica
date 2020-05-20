package utn.telefonica.app.session;

import utn.telefonica.app.model.Customer;

import java.util.Date;

public class Session {

    String      token;
    Customer loggedUser;
    Date        lastAction;

    public Session(String token, Customer loggedUser, Date lastAction) {
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

    public Customer getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Customer loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Date getLastAction() {
        return lastAction;
    }

    public void setLastAction(Date lastAction) {
        this.lastAction = lastAction;
    }
}
