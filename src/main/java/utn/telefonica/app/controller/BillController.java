package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.BillNotFoundException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.service.BillService;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BillController {

    private final BillService billService;


    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;

    }

    @GetMapping("bill/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Integer id) {

        try {

            return new ResponseEntity<Bill>(billService.getBillById(id), HttpStatus.OK);

        } catch (BillNotFoundException E) {

            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("api/bill")
    public ResponseEntity getBillsByDate(@RequestHeader("Authorization") String token,
                                         @RequestParam(required = true) String from,
                                         @RequestParam(required = true) String to) {
        try {
            return ResponseEntity.ok(billService.getBillsByDate(token, from, to));
        } catch (InvalidSessionException E) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } catch (ParseException P) {
            return new ResponseEntity("Invalid date", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("backoffice/bill/{idUser}")
    public ResponseEntity getBillsByIdUser(@PathVariable int idUser) {
        try {
            return ResponseEntity.ok(billService.getBillsByUser(idUser));
        } catch (UserNotexistException E) {
            return ResponseEntity.notFound().build();
        }

    }
}

