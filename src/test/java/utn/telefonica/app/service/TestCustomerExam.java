package utn.telefonica.app.service;

import org.junit.Before;
import org.junit.Test;

import utn.telefonica.app.Projections.CustomerExamen;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.repository.BillRepository;
import utn.telefonica.app.repository.CustomerRepository;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.testutils.TestUtils;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestBillService {

    CustomerService customerService;
    CustomerRepository customerRepository;


    @Before
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);

        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testGetBillsByDateOk()throws UserNotexistException {
        List<CustomerExamen> bycustomerExam = new ArrayList<>();

        bycustomerExam.add(TestUtils.getDummyCustomerBy());

        when(customerRepository.getCustomerExamen()).thenReturn(bycustomerExam);

        List<CustomerExamen> toTest = customerService.getCustomerExamen();

        assertEquals(toTest.get(0).getNameFirst(),bycustomerExam.get(0).getNameFirst());

    }

    /*
    public List<CustomerExamen> getCustomerExamen() throws UserNotexistException {
        List<CustomerExamen> aux = null;
        aux = customerRepository.getCustomerExamen();
        Optional.ofNullable(aux).orElseThrow(() -> new UserNotexistException());
        return aux;
    }
     */

    @Test(expected = ParseException.class)
    public void testGetBillsByDateException() throws ParseException, InvalidSessionException{

        List<BillsByCustomer> byCustomerList = new ArrayList<>();

        Session session = new Session("asd",TestUtils.getTestingCustomer(), Calendar.getInstance().getTime());

        byCustomerList.add(TestUtils.getDummyBillBy());

        when(sessionManager.getSession("asd")).thenReturn(session);

        List<BillsByCustomer> toTest = billService.getBillsByDate("asd","asdsad","asdasd0");


    }
}

