package utn.telefonica.app.service;

import org.junit.Before;
import org.junit.Test;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.projections.BillsByCustomer;
import utn.telefonica.app.repository.BillRepository;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestBillService {

    BillService billService;
    BillRepository billRepository;


    @Before
    public void setUp(){
        billRepository = mock(BillRepository.class);

        billService = new BillService(billRepository);
    }

    @Test
    public void testGetBillsByDateOk()throws ParseException, InvalidSessionException{
        List<BillsByCustomer> byCustomerList = new ArrayList<>();

        Session session = new Session("asd",TestUtils.getTestingCustomer(), Calendar.getInstance().getTime());

        Date fromDate = PhoneUtils.dateConverter("10-10-2019");

        Date toDate = PhoneUtils.dateConverter("10-10-2020");

        byCustomerList.add(TestUtils.getDummyBillBy());


        when(billRepository.getBillsByDate(session.getLoggedUser().getId(),fromDate,toDate)).thenReturn(byCustomerList);

        List<BillsByCustomer> toTest = billService.getBillsByDate("asd","10-10-2019","10-10-2020");

        assertEquals(toTest.get(0).getTotalPrice(),byCustomerList.get(0).getTotalPrice());

    }

    @Test(expected = ParseException.class)
    public void testGetBillsByDateException() throws ParseException, InvalidSessionException{

        List<BillsByCustomer> byCustomerList = new ArrayList<>();

        Session session = new Session("asd",TestUtils.getTestingCustomer(), Calendar.getInstance().getTime());

        byCustomerList.add(TestUtils.getDummyBillBy());


        List<BillsByCustomer> toTest = billService.getBillsByDate("asd","asdsad","asdasd0");


    }
}

