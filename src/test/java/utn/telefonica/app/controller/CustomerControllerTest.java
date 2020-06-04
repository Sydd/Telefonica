package utn.telefonica.app.controller;

import org.junit.Before;

import org.junit.Test;
import utn.telefonica.app.service.CallService;

import utn.telefonica.app.service.CustomerService;

import static org.mockito.Mockito.*;


public class CustomerControllerTest {


    CustomerService costumerService;

    CallService callService;

    @Before
    public void setUp() {
        costumerService = mock(CustomerService.class);

        callService = mock(CallService.class);
    }

    @Test
    public void testgetCustomerByIdOk(){

    }

}

