package utn.telefonica.app.controller;

import com.mysql.cj.util.TestUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.User;
import utn.telefonica.app.projections.UserProjection;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.Date;


@RestController
@RequestMapping("/")
public class CallController {
    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }


    @PostMapping("antenna/call/")
    @ApiOperation(value="Add Call")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Added call"),
            @ApiResponse(code = 400, message = "An error was unexpectedly generated during the scan")})
    public ResponseEntity addCall(@RequestBody CallDto call)
    {
        try {
            return new ResponseEntity(callService.addCall(call), HttpStatus.CREATED);
        } catch (ParseException P) {
            return new ResponseEntity("Invalid Date",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/call/top")
    @ApiOperation(value="Get the number most called by your user")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "The most called is returned"),
            @ApiResponse(code = 403, message = "Problem with your user session")})
    public ResponseEntity getMostCalledNumber(@RequestHeader("Authorization") String token){
        try {

            int id = PhoneUtils.getUserByToken(token).getLoggedUser().getId();

            return ResponseEntity.ok(callService.getMostCalledNumber(id));
        } catch (InvalidSessionException E){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }



    @GetMapping("api/call/")
    @ApiOperation(value="Get calls by date")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Call list is returned"),
            @ApiResponse(code = 403, message = "Problem with your user session"),
            @ApiResponse(code = 400, message = "An error was unexpectedly generated during the scan")})
    public ResponseEntity getCallsByDate(@RequestHeader("Authorization") String token,
                                         @RequestParam(required = true) String from,
                                         @RequestParam(required = true) String to){
        try {

            User user = PhoneUtils.getUserByToken(token).getLoggedUser();


            String completeName = user.getFirstName() + " " + user.getLastName();

            return  ResponseEntity.ok(callService.getTotalCallsById(user.getId(), from, to,completeName));

        } catch (InvalidSessionException E){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
            }  catch (ParseException P) {
                return new ResponseEntity("Invalid Date",HttpStatus.BAD_REQUEST);
            }
    }

    @GetMapping("backoffice/call/{idUser}")
    @ApiOperation(value="Get calls per user")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Call list is returned"),
            @ApiResponse(code = 403, message = "Problem with your wrong user id")})
    public ResponseEntity getCallsPerUser(@PathVariable Integer idUser){
        try {

            return ResponseEntity.ok(callService.getCallsPerUser(idUser));
        } catch (UserNotexistException E){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
