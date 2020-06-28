package utn.telefonica.app.controller;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import utn.telefonica.app.exceptions.BillNotFoundException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.projections.BillsByCustomer;
import utn.telefonica.app.service.BillService;
import utn.telefonica.app.testutils.TestUtils;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestBillController {

    BillController billController;

    BillService billService;

    @Before
    public void setUp() {
        billService = mock(BillService.class);
        billController = new BillController(billService);
    }

    @Test
    public void testGetBillById() throws BillNotFoundException {
        Bill aux = new Bill();
        aux.setId(20);
        when(billService.getBillById(20)).thenReturn(aux);
        assertEquals(billController.getBillById(20).getBody().getId(), 20);
        assertEquals(billController.getBillById(20).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetBillByIdNull() throws BillNotFoundException {
        Bill aux = new Bill();
        aux.setId(20);
        when(billService.getBillById(20)).thenThrow(new BillNotFoundException());
        assertEquals(billController.getBillById(20).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetBillsByDate() throws InvalidSessionException, ParseException {
        List<BillsByCustomer> listCustomer = new ArrayList<>();
        listCustomer.add(TestUtils.getDummyBillBy());
        when(billService.getBillsByDate("token", "1", "0")).thenReturn(listCustomer);
        assertEquals(billController.getBillsByDate("token", "1", "to").getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetBillsByDateInvalidSession() throws InvalidSessionException, ParseException {
        List<BillsByCustomer> listCustomer = new ArrayList<>();
        listCustomer.add(TestUtils.getDummyBillBy());
        when(billService.getBillsByDate("token", "1", "0")).thenThrow(new InvalidSessionException());
        assertEquals(billController.getBillsByDate("token", "1", "0").getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void testGetBillsByDateInvalidParse() throws InvalidSessionException, ParseException {
        List<BillsByCustomer> listCustomer = new ArrayList<>();
        listCustomer.add(TestUtils.getDummyBillBy());
        when(billService.getBillsByDate("token", "1", "0")).thenThrow(new ParseException("error", 2));
        assertEquals(billController.getBillsByDate("token", "1", "0").getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getBillsByIdUserok()throws UserNotexistException {
        List<BillsByCustomer> billsByCustomers = new ArrayList<>();

        billsByCustomers.add(TestUtils.getDummyBillBy());

        when(billService.getBillsByUser(10)).thenReturn(billsByCustomers);

        assertEquals(billController.getBillsByIdUser(10).getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void getBillsByIdUserNotFound()throws UserNotexistException {
        List<BillsByCustomer> billsByCustomers = new ArrayList<>();

        billsByCustomers.add(TestUtils.getDummyBillBy());

        when(billService.getBillsByUser(10)).thenThrow(new UserNotexistException());

        assertEquals(billController.getBillsByIdUser(10).getStatusCode(),HttpStatus.NOT_FOUND);

    }

}
