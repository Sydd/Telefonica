package utn.telefonica.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.dto.UserWithCalls;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.projections.CallsPerUser;
import utn.telefonica.app.projections.CustomerCallsCant;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallControllerTest {
    CallService callService;
    CallController callController;

    @Before
    public void setUp() {
        callService = mock(CallService.class);

        callController = new CallController(callService);

        PhoneUtils.getSessionManager();

    }


    @Test
    public void testGetCallsByDateOk() throws ParseException {


        String token = PhoneUtils.getSessionManager().createSession(TestUtils.getTestingCustomer());

        String completeName = TestUtils.getTestingCustomer().getFirstName() + " " + TestUtils.getTestingCustomer().getLastName();

        UserWithCalls userWithCalls = new UserWithCalls(completeName, null);

        when(callService.getTotalCallsById(1, "10-10-2020", "10-10-2030", completeName)).thenReturn(userWithCalls);

        ResponseEntity<UserWithCalls> responseEntity = callController.getCallsByDate(token, "10-10-2020", "10-10-2030");

        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

        assertEquals(responseEntity.getBody().getCompleteName(),completeName);
    }


    @Test
    public void testGetCallsByDateParseException() throws ParseException {


        String token = PhoneUtils.getSessionManager().createSession(TestUtils.getTestingCustomer());

        String completeName = TestUtils.getTestingCustomer().getFirstName() + " " + TestUtils.getTestingCustomer().getLastName();

        UserWithCalls userWithCalls = new UserWithCalls(completeName, null);

        when(callService.getTotalCallsById(1, "10-10-2020", "10-10-2030", completeName)).thenThrow(new ParseException("a",2));

        ResponseEntity responseEntity = callController.getCallsByDate(token, "10-10-2020", "10-10-2030");

        assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);

        assertEquals(responseEntity.getBody(),"Invalid Date");
    }

    @Test
    public void testGetCallsByDateForbiden() throws ParseException {


        String token = PhoneUtils.getSessionManager().createSession(TestUtils.getTestingCustomer());

        String completeName = TestUtils.getTestingCustomer().getFirstName() + " " + TestUtils.getTestingCustomer().getLastName();

        UserWithCalls userWithCalls = new UserWithCalls(completeName, null);

        when(callService.getTotalCallsById(1, "10-10-2020", "10-10-2030", completeName)).thenThrow(new ParseException("a",2));

        ResponseEntity responseEntity = callController.getCallsByDate("asdasdasd", "10-10-2020", "10-10-2030");

        assertEquals(responseEntity.getStatusCode(),HttpStatus.FORBIDDEN);

    }


    @Test
    public void testAddCallOk() throws ParseException {

        CallDto call = TestUtils.getCallDto();

     //   when(callService.addCall(call)).thenReturn("Call saved");

        ResponseEntity response = callController.addCall(call);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testAddCallParseExc() throws ParseException {
        CallDto call = TestUtils.getCallDto();

        when(callService.addCall(call)).thenThrow(new ParseException("Invalid Date", 0));

        ResponseEntity response = callController.addCall(call);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

        assertEquals(response.getBody().toString(), "Invalid Date");
    }

    @Test
    public void testGetCallsPerUserOk() throws UserNotexistException {

        List<CallsPerUser> aux = new ArrayList<>();
        aux.add(TestUtils.getDummCallPerUser());
        when(callService.getCallsPerUser(1)).thenReturn(aux);
        ResponseEntity response = callController.getCallsPerUser(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetCallsPerUserNotExistsExc() throws UserNotexistException {

        when(callService.getCallsPerUser(1)).thenThrow(new UserNotexistException());
        ResponseEntity response = callController.getCallsPerUser(1);

        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }


    @Test
    public void testGetMostCalledNumberOk() throws InvalidSessionException{

        String token = PhoneUtils.getSessionManager().createSession(TestUtils.getTestingCustomer());

        List<CustomerCallsCant> customerCallsCantList = new ArrayList<>();

        customerCallsCantList.add(TestUtils.getDummyCustomerCallCant());

        when(callService.getMostCalledNumber(1)).thenReturn(customerCallsCantList);

        ResponseEntity<List<CustomerCallsCant>> responseEntity = callController.getMostCalledNumber(token);

        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

        assertEquals(responseEntity.getBody().get(0).getNumber(),"666");

    }


    @Test
    public void testGetMostCalledNumberInvalidSession() throws InvalidSessionException{

        String token = PhoneUtils.getSessionManager().createSession(TestUtils.getTestingCustomer());

        List<CustomerCallsCant> customerCallsCantList = new ArrayList<>();

        customerCallsCantList.add(TestUtils.getDummyCustomerCallCant());

        when(callService.getMostCalledNumber(1)).thenReturn(customerCallsCantList);

        ResponseEntity<List<CustomerCallsCant>> responseEntity = callController.getMostCalledNumber("asdasd");

        assertEquals(responseEntity.getStatusCode(),HttpStatus.FORBIDDEN);


    }

}
