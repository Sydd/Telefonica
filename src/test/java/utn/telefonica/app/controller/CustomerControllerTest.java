package utn.telefonica.app.controller;

import org.junit.Before;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utn.telefonica.app.dto.UserWithCalls;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.projections.CallTotals;
import utn.telefonica.app.projections.UserProjection;
import utn.telefonica.app.service.CallService;

import utn.telefonica.app.service.UserService;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class CustomerControllerTest {


    UserService costumerService;

    CallService callService;

    UserController userController;


    @Before
    public void setUp() {
        costumerService = mock(UserService.class);

        callService = mock(CallService.class);

        userController = new UserController(costumerService,callService);
    }

    @Test
    public void testgetCustomerByIdNo() throws UserNotexistException {


        when(costumerService.getCostumerById(1)).thenReturn(TestUtils.getTestingUserProjection());

        ResponseEntity<UserProjection> response = userController.getCustomerById(1,null,null);

        assertEquals(TestUtils.getTestingUserProjection().getId(), response.getBody().getId());

    }
/*
    @Test
    public void testGetCostumerWithDate() throws UserNotexistException, ParseException {


        Date fromDate = PhoneUtils.dateConverter("10-10-2019");

        Date toDate = PhoneUtils.dateConverter("10-10-2020");

        List<CallTotals> totalsList = new ArrayList<>();

        totalsList.add(TestUtils.getDummyCallsTotal());

        UserWithCalls dto = new UserWithCalls("DUMB", totalsList);

        when(costumerService.getCostumerById(1)).thenReturn(TestUtils.getTestingUserProjection());

        when(callService.getTotalCallsById(1, fromDate, toDate, "Dumb Dumb")).thenReturn(dto);

        ResponseEntity<UserWithCalls> response = userController.getCustomerById(1,"10-10-2019","10-10-2020");

        /assertEquals(dto.getCompleteName(), response.getBody().getCompleteName());

    }/*/



    @Test
    public void testGetCustomerByIdNotFound() throws UserNotexistException {

        when(costumerService.getCostumerById(2)).thenThrow( new UserNotexistException());

        ResponseEntity<UserProjection> response = userController.getCustomerById(2,null,null);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

 }





}

