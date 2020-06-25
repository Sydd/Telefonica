package utn.telefonica.app.controller;

import com.mysql.cj.util.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;


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

    @GetMapping("api/call/")
    public ResponseEntity getMostCalledNumber(@RequestHeader("Authorization") String token){
        try {

            int id = PhoneUtils.getUserByToken(token).getLoggedUser().getId();

            return ResponseEntity.ok(callService.getMostCalledNumber(id));
        } catch (InvalidSessionException E){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

}
