package utn.telefonica.app.controller;

import org.junit.Before;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.projections.CustomerPriceLastCall;
import utn.telefonica.app.repository.CallRepository;
import utn.telefonica.app.repository.CustomerRepository;
import utn.telefonica.app.service.CallService;

import utn.telefonica.app.service.CustomerService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class CustomerControllerTest {


    CustomerController customerController;

    CustomerService customerService;

    CallService callService;

    @Before
   public void SetUp() {
        customerService = mock(CustomerService.class);
        callService = mock(CallService.class);

        customerController = new CustomerController(customerService, callService);
    }

    @Test
   public void testgetPriceLastCallByIdok() throws UserNotexistException {
        CustomerPriceLastCall customer = TestUtils.getDummyCustomerPrice();
        when(customerService.getPriceLastCall(1)).thenReturn(ResponseEntity.ok(customer));
        ResponseEntity<CustomerPriceLastCall> response = customerController.getPriceLastCallById(1);
        assertEquals (response.getBody().getDni(),customer.getDni());


    }
}
