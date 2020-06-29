package utn.telefonica.app.controller;

import org.apache.coyote.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.projections.CallsPerUser;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.testutils.TestUtils;

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
    public void setUp()
    {
        callService = mock(CallService.class);
        callController = new CallController(callService);
    }

    @Test
    public void testAddCallOk() throws ParseException {
        CallDto call = TestUtils.getCallDto();

        when(callService.addCall(call)).thenReturn(TestUtils.getTestingCall());

        ResponseEntity response = callController.addCall(call);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testAddCallParseExc() throws ParseException{
        CallDto call = TestUtils.getCallDto();

       when(callService.addCall(call)).thenThrow(new ParseException("Invalid Date", 0));

        ResponseEntity response = callController.addCall(call);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().toString(),"Invalid Date");
    }

    @Test
    public void testGetCallsPerUserOk() throws UserNotexistException {

        List<CallsPerUser> aux = new ArrayList<>();
        aux.add(TestUtils.getDummCallPerUser());
        when(callService.getCallsPerUser(1)).thenReturn(aux);
        ResponseEntity response = callController.getCallsPerUser(1);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }
  
}
