package utn.telefonica.app.testutils;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import utn.telefonica.app.model.City;
import utn.telefonica.app.model.State;
import utn.telefonica.app.model.User;
import utn.telefonica.app.model.enums.UserType;
import utn.telefonica.app.projections.BillsByCustomer;
import utn.telefonica.app.projections.UserProjection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public static List<UserProjection> getListUserProjection(){

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

        UserProjection userProjection = factory.createProjection(UserProjection.class);

        userProjection.setFirstName("Dumb 2");

        userProjection.setLastName("Dumb 2");

        userProjection.setUsername("dumbuser2");

        userProjection.setCity(getTestingCity());

        userProjection.setDni("00000");

        userProjection.setId(1);


        userProjection.setUserType(UserType.CUSTOMER);

        List<UserProjection> userProjectionList = new ArrayList<>();

        userProjectionList.add(userProjection);

        userProjectionList.add(getTestingUserProjection());

        return userProjectionList;

    }

    public static UserProjection getTestingUserProjection(){

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

        UserProjection userProjection = factory.createProjection(UserProjection.class);

        userProjection.setFirstName("Dumb");

        userProjection.setLastName("Dumb");

        userProjection.setId(0);

        userProjection.setUsername("dumbuser");

        userProjection.setCity(getTestingCity());

        userProjection.setDni("344221");

        userProjection.setUserType(UserType.CUSTOMER);

        return userProjection;
    }

    public static BillsByCustomer getDummyBillBy(){

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

        BillsByCustomer dummBillBy = factory.createProjection(BillsByCustomer.class);

        dummBillBy.setCreatedAt("nunca");

        dummBillBy.setIsPayed(false);

        dummBillBy.setTotalCalls(10);

        dummBillBy.setTotalPrice("200");

        return dummBillBy;

    }



}
