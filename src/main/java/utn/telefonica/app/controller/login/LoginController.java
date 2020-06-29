package utn.telefonica.app.controller.login;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @ApiOperation(value="Login")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "You are logged"),
            @ApiResponse(code = 400, message = "Username and password must have a value"),
            @ApiResponse(code = 401, message = "Username or Password incorrect")})
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) throws InvalidLoginException, ValidationException {
        ResponseEntity response;
        try {
            User c = costumerService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            String token = sessionManager.createSession(c);
            response = ResponseEntity.ok().headers(createHeaders(token)).build();
        } catch (UserNotexistException e) {
            response = new ResponseEntity("User or Password incorrect", HttpStatus.UNAUTHORIZED);
        } catch (ValidationException e) {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @PostMapping("/logout")
    @ApiOperation(value="Logout")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "You are logged out")})
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

