package utn.telefonica.app.service;

import org.junit.Before;
import org.junit.Test;
import utn.telefonica.app.exceptions.BillNotFoundException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
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
    SessionManager sessionManager;

    @Before
    public void setUp(){
        billRepository = mock(BillRepository.class);

        billService = new BillService(billRepository);

        sessionManager = mock(SessionManager.class);
    }


    @Test
    public void testGetBillByIdok() throws BillNotFoundException{
        Bill toTest = new Bill();
        toTest.setId(20);
        when(billRepository.findById(20)).thenReturn(Optional.ofNullable(toTest));
        Bill aux = billService.getBillById(20);
        assertEquals(aux.getId(),toTest.getId());
    }

    @Test(expected = BillNotFoundException.class)
    public void testGetBillByIdNull() throws  BillNotFoundException{
        Bill toTest = new Bill();
        toTest.setId(20);
        when(billRepository.findById(10)).thenReturn(Optional.ofNullable(null));
        Bill aux = billService.getBillById(10);
        assertEquals(aux.getId(),toTest.getId());
    }

    @Test
    public void testGetBillsByUser() throws UserNotexistException {
        List<BillsByCustomer> byCustomerList = new ArrayList<>();
        byCustomerList.add(TestUtils.getDummyBillBy());
        when(billRepository.getBillsByUserId(10)).thenReturn(byCustomerList);
        List<BillsByCustomer> lisToTest = billService.getBillsByUser(10);
        assertEquals(lisToTest.get(0).getTotalPrice(),byCustomerList.get(0).getTotalPrice());
    }

  /*  @Test
    public void testGetBillsByDateOk()throws ParseException, InvalidSessionException{
      /*  List<BillsByCustomer> byCustomerList = new ArrayList<>();

        byCustomerList.add(TestUtils.getDummyBillBy());

        String token = "token";

        Date fromDate = PhoneUtils.dateConverter("10-10-2019");

        Date toDate = PhoneUtils.dateConverter("10-10-2020");

        when(billRepository.getBillsByDate(1, fromDate, toDate)).thenReturn(byCustomerList);

        List<BillsByCustomer> toTest = billService.getBillsByDate(token,"10-10-2019","10-10-2020");

        assertEquals(toTest.get(0).getTotalPrice(),byCustomerList.get(0).getTotalPrice());*/



    /*
    @Test(expected = ParseException.class)
    public void testGetBillsByDateException() throws ParseException, InvalidSessionException{

        List<BillsByCustomer> byCustomerList = new ArrayList<>();

        Session session = new Session("asd",TestUtils.getTestingCustomer(), Calendar.getInstance().getTime());

        byCustomerList.add(TestUtils.getDummyBillBy());


        List<BillsByCustomer> toTest = billService.getBillsByDate("asd","asdsad","asdasd0");

    }*/
}

