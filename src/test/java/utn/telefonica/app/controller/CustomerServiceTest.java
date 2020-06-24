package utn.telefonica.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.EntityResponse;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.projections.CustomerPriceLastCall;
import utn.telefonica.app.repository.CustomerRepository;
import utn.telefonica.app.service.CustomerService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import static java.util.Objects.isNull;

public class CustomerServiceTest {



    CustomerService customerService;

    CustomerRepository customerRepository;

    @Before
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testGetPriceLastCall() throws UserNotexistException{
        when(customerRepository.getPriceLastCall(1)).thenReturn(TestUtils.getDummyCustomerPrice());

        ResponseEntity<CustomerPriceLastCall> responseTest = customerService.getPriceLastCall(1);

        assertEquals(responseTest.getBody().getDni(),TestUtils.getDummyCustomerPrice().getDni());


    }

    @Test(expected = UserNotexistException.class)
    public void testGetPriceLastCallExeption() throws UserNotexistException{
        when(customerRepository.getPriceLastCall(1)).thenReturn(TestUtils.getDummyCustomerPrice());

        ResponseEntity<CustomerPriceLastCall> responseTest = customerService.getPriceLastCall(3);

        assertEquals(responseTest.getBody().getDni(),TestUtils.getDummyCustomerPrice().getDni());


    }

}
