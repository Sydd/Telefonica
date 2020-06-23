package utn.telefonica.app.testutils;

import utn.telefonica.app.model.City;
import utn.telefonica.app.model.State;
import utn.telefonica.app.model.User;
import utn.telefonica.app.model.enums.UserType;

import java.util.Calendar;

public class TestUtils {

    public static User getTestingCustomer() {
        return new User(1,"Juan","Carlos","username","password", Calendar.getInstance().getTime(),getTestingCity(), null,null,UserType.CUSTOMER,"223344");
    }


    public static City getTestingCity(){
        return new City(1,"Mar del Pollo",223,2,3,null,null,null,getTestingState());
    }

    public static State getTestingState(){
        return new State(1,"Buenos Baires",null);
    }
}
