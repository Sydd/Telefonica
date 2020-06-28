package utn.telefonica.app.service;
import org.junit.Before;
import org.junit.Test;
import utn.telefonica.app.controller.CallController;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.dto.UserWithCalls;
import utn.telefonica.app.exceptions.BillNotFoundException;
import utn.telefonica.app.exceptions.InvalidCityException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.projections.BillsByCustomer;
import utn.telefonica.app.projections.CustomerCallsCant;
import utn.telefonica.app.repository.BillRepository;
import utn.telefonica.app.repository.CallRepository;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestCallService {
    CallService callService;

    CallRepository callRepository;

    @Before
    public void setUp(){
        callRepository = mock(CallRepository.class);
        callService = new CallService(callRepository);
    }

    @Test
    public void testUserWithCalls() throws ParseException{

        Date fromDate = PhoneUtils.dateConverter("10-10-2019");

        Date toDate = PhoneUtils.dateConverter("10-10-2020");

        List<utn.telefonica.app.projections.CallTotals> callTotals = new ArrayList<>();

        callTotals.add(TestUtils.getDummyCallsTotal());

        when(callRepository.getTotalCallsByDate(1, fromDate, toDate)).thenReturn(callTotals);

        UserWithCalls toTest = callService.getTotalCallsById(1,"10-10-2019","10-10-2020","Guitarra");

        assertEquals(toTest.getCompleteName(),"Guitarra");

        assertEquals(toTest.getCallsWithDate().get(0).getDate(),"Dumb dumb");

    }



    @Test
    public void testAddCall() throws  ParseException{
        Date aux = PhoneUtils.dateConverter("10-10-10");
        CallDto callDto = new CallDto("123","3","10","10-10-10");
        when(callRepository.addCall("123","3",Integer.getInteger(callDto.getCallDuration()),aux)).thenReturn(TestUtils.getTestingCall());
        assertEquals(callService.addCall(callDto).getId(),1);
    }

    @Test
    public void testGetMostCalledNumber() throws InvalidSessionException {

        List<CustomerCallsCant> callTotals = new ArrayList<>();

        callTotals.add(TestUtils.getDummyCustomerCallCant());

        when(callRepository.getTopCalls(1)).thenReturn(callTotals);

        assertEquals(callService.getMostCalledNumber(1).get(0).getCant(),TestUtils.getDummyCustomerCallCant().getCant());
    }


    @Test
    public void testGetCallsPerUser() throws UserNotexistException {

        List<utn.telefonica.app.projections.CallsPerUser> callsPerUsers = new ArrayList<>();

        utn.telefonica.app.projections.CallsPerUser aux = TestUtils.getDummCallPerUser();

        callsPerUsers.add(aux);

        when(callRepository.getCallsPerUser(1)).thenReturn(callsPerUsers);

        assertEquals(callService.getCallsPerUser(1).get(0),aux);
    }




}
