package utn.telefonica.app.controller;

import org.junit.Before;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.User;
import utn.telefonica.app.service.CallService;

import utn.telefonica.app.service.UserService;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import javax.persistence.NonUniqueResultException;
import java.util.Date;

import static java.util.Objects.isNull;
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


        when(costumerService.getCostumerById(1)).thenReturn(TestUtils.getTestingCustomer());

        ResponseEntity response = userController.getCustomerById(1,null,null);

       // assertEquals(ResponseEntity.ok(TestUtils.getTestingCustomer()).getStatusCode(),response.getStatusCode());
     //   assertEquals(ResponseEntity.ok(TestUtils.getTestingCustomer()).getBody(),response.getBody());
     //   verify(costumerService,times(1)).getCostumerById(1);
    }


    //@Test(expected = UserNotexistException.class)
    //public void testGetCustomerByIdNotFound() throws UserNotexistException {


   // }





}

