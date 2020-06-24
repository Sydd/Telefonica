package utn.telefonica.app.testutils;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import utn.telefonica.app.Projections.CustomerExamen;
import utn.telefonica.app.model.City;
import utn.telefonica.app.model.State;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestUtils {

    public static CustomerExamen getDummyCustomerBy(){

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

        CustomerExamen customerExam = factory.createProjection(CustomerExamen.class);

        customerExam.setNameFirst("lala");

        customerExam.setNameLast("jaja");

        customerExam.setCityName("mdp");

        customerExam.setLastCallDuration(2);

        return customerExam;

    }

}
