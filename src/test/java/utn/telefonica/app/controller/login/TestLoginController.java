package utn.telefonica.app.controller.login;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.dto.LoginRequestDto;
import utn.telefonica.app.exceptions.InvalidLoginException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.model.User;
import utn.telefonica.app.projections.CallsPerUser;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.service.UserService;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.testutils.TestUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestLoginController {


    LoginController loginController;
    UserService userService;
    SessionManager sessionManager;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        sessionManager = mock(SessionManager.class);
        loginController = new LoginController(userService, sessionManager);
    }

    @Test
    public void testLoginOk() throws UserNotexistException, ValidationException, InvalidLoginException {
        User u = TestUtils.getTestingCustomer();
        when(userService.login("user", "password")).thenReturn(u);
        when(sessionManager.createSession(u)).thenReturn("tokenTest");
        ResponseEntity response = loginController.login(new LoginRequestDto("user", "password"));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getHeaders().get("Authorization").get(0), "tokenTest");
    }


    @Test(expected = InvalidLoginException.class)
    public void testLoginBadUser() throws UserNotexistException, ValidationException, InvalidLoginException {
        User u = TestUtils.getTestingCustomer();
        when(userService.login("user", "password")).thenThrow(new UserNotexistException());
        when(sessionManager.createSession(u)).thenReturn("tokenTest");
        ResponseEntity response = loginController.login(new LoginRequestDto("user", "password"));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getHeaders().get("Authorization").get(0), "tokenTest");
    }


    @Test
    public void testLogout(){
        assertEquals(loginController.logout("token").getStatusCode(),HttpStatus.OK);
    }
}
/*
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) throws InvalidLoginException, ValidationException {
        ResponseEntity response;
        try {
            User c = costumerService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            String token = sessionManager.createSession(c);
            response = ResponseEntity.ok().headers(createHeaders(token)).build();
        } catch (UserNotexistException e) {
            throw new InvalidLoginException(e);
        }
        return response;
    }/*

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }
}*/
