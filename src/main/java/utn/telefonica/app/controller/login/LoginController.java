package utn.telefonica.app.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.service.UserService;
import utn.telefonica.app.dto.LoginRequestDto;
import utn.telefonica.app.exceptions.*;
import utn.telefonica.app.model.User;
import utn.telefonica.app.session.SessionManager;

@RestController
@RequestMapping("/")
public class LoginController {

    UserService costumerService;
    SessionManager sessionManager;

    @Autowired
    public LoginController(UserService costumerService, SessionManager sessionManager) {
        this.costumerService = costumerService;
        this.sessionManager = sessionManager;
    }


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
    }

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
}

