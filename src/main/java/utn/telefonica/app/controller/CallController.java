package utn.telefonica.app.controller;

import com.mysql.cj.util.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.exceptions.InvalidSessionException;
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
    public ResponseEntity addCall(@RequestBody CallDto call)
    {
        try {
            return new ResponseEntity(callService.addCall(call), HttpStatus.CREATED);
        } catch (ParseException P) {
            return new ResponseEntity("Invalid Date",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/call/top")
    public ResponseEntity getMostCalledNumber(@RequestHeader("Authorization") String token){
        try {

            int id = PhoneUtils.getUserByToken(token).getLoggedUser().getId();

            return ResponseEntity.ok(callService.getMostCalledNumber(id));
        } catch (InvalidSessionException E){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("api/call/")
    public ResponseEntity getCallsByDate(@RequestHeader("Authorization") String token,
                                         @RequestParam(required = false) String from,
                                         @RequestParam(required = false) String to){
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

}
