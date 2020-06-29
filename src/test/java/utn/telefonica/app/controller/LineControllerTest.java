package utn.telefonica.app.controller;

import org.apache.coyote.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utn.telefonica.app.exceptions.InvalidPhoneLineException;
import utn.telefonica.app.exceptions.LineNotFoundException;
import utn.telefonica.app.model.PhoneLine;
import utn.telefonica.app.service.LineService;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import javax.persistence.NonUniqueResultException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LineControllerTest {

    LineService lineService;
    LineController lineController;

    @Before
    public void setUp()
    {
        lineService = mock(LineService.class);
        lineController = new LineController(lineService);
    }

    @Test
    public void testGetLineByIdOk() throws LineNotFoundException{

        when(lineService.getLineById(1)).thenReturn(TestUtils.getPhoneLineTest());
        ResponseEntity response = lineController.getLineById(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetLineByIdLineNotFound() throws LineNotFoundException{
        when(lineService.getLineById(1)).thenThrow(new LineNotFoundException());
        ResponseEntity response = lineController.getLineById(1);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

   /* @Test //todo este tiene get location :c
    public void testAddLineOk() throws NonUniqueResultException{
        PhoneLine aux = new PhoneLine();
        aux.setId(1);
        when(lineService.addLine(TestUtils.getTestingPhoneline())).thenReturn(TestUtils.getPhoneLineTest());
        ResponseEntity response = lineController.addLine(aux);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }*/

   @Test
    public void getAllOk() {
       List<PhoneLine> aux = new ArrayList<>();
       aux.add(TestUtils.getTestingPhoneline());
       when(lineService.getAllLines("223")).thenReturn(aux);

       assertEquals(lineController.getAll("223").get(0).getId(),aux.get(0).getId());

   }

   @Test
    public void testUpdatePhoneLinesOk() throws InvalidPhoneLineException{

       when(lineService.updatePhonelines(TestUtils.getTestingPhoneline())).thenReturn(TestUtils.getPhoneLineTest());
       ResponseEntity response = lineController.updatePhoneLine(TestUtils.getTestingPhoneline());

       assertEquals(response.getStatusCode(),HttpStatus.OK);
   }

   @Test
    public void testUpdatePhoneLinesInvalidPhonelineExc() throws InvalidPhoneLineException{

       when(lineService.updatePhonelines(TestUtils.getTestingPhoneline())).thenThrow(new InvalidPhoneLineException());
       ResponseEntity response = lineController.updatePhoneLine(TestUtils.getTestingPhoneline());

       assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
   }

   @Test
    public void testDeleteLineByIdOk() throws LineNotFoundException{
       when(lineService.deleteLine(1)).thenReturn("error");
       ResponseEntity response = lineController.deleteLineById(1);

       assertEquals(response.getStatusCode(),HttpStatus.NO_CONTENT);
   }

   @Test
    public void testDeleteLineByIdLineNotFoundExc() throws LineNotFoundException{
       when(lineService.deleteLine(1)).thenThrow(new LineNotFoundException());
       ResponseEntity response = lineController.deleteLineById(1);

       assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
   }
}
