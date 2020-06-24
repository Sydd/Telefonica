package utn.telefonica.app.controller;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import utn.telefonica.app.projections.CustomerPriceLastCall;

public class TestUtils {

    public static CustomerPriceLastCall getDummyCustomerPrice(){

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        CustomerPriceLastCall dummy = factory.createProjection(CustomerPriceLastCall.class);

        dummy.setPrice("200");
        dummy.setDni("123");
        return dummy;
    }
}
