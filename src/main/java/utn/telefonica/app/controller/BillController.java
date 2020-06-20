package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.service.BillService;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.utils.PhoneUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BillController {

    private final BillService billService;

    private final SessionManager sessionManager;



    @Autowired
    public BillController(BillService billService, SessionManager sessionManager) {
        this.billService = billService;
        this.sessionManager = sessionManager;

    }

    @GetMapping("bill/{id}")
    public Bill getBillById(@PathVariable Integer id)
    {

        return billService.getBillById(id);
    }

    @PostMapping("bill/")
    public void AddBill(@RequestBody List<Bill> billList)
    {
        billService.addBill(billList);
    }

    @GetMapping("api/bill")
    public ResponseEntity getBillsByDate(@RequestHeader("Authorization") String token,
                                         @RequestParam(required = true) String from,
                                         @RequestParam(required = true) String to) {
        ResponseEntity response;

        try {

            Session actualSession = Optional.ofNullable(sessionManager.getSession(token)).orElseThrow(() -> new InvalidSessionException());

            Date fromDate = PhoneUtils.dateConverter(from);

            Date toDate = PhoneUtils.dateConverter(to);

            response = ResponseEntity.ok(billService.getBillsByDate(actualSession.getLoggedUser().getId(), fromDate, toDate));

        } catch (Exception E) {

            response = new ResponseEntity(E.getMessage(), HttpStatus.CONFLICT);

        } catch (InvalidSessionException E) {
            response = new ResponseEntity("Invalid Session", HttpStatus.FORBIDDEN);
        }

        return response;
    }
}


