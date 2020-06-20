package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.InvalidLoginException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.User;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.service.UserService;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.utils.PhoneUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final CallService callService;


    @Autowired
    public UserController(UserService costumerService, CallService callService) {
        this.userService = costumerService;

        this.callService = callService;
    }




    @GetMapping("/{id_customer}")
    public ResponseEntity getCustomerById(@PathVariable Integer id_customer,
                                          @RequestParam(required = false) String from,
                                          @RequestParam(required = false) String to) {

        ResponseEntity response;

        try {

            if (isNull(from) || isNull(to)) {

                response = ResponseEntity.ok(userService.getCostumerById(id_customer));

            } else {

                Date fromDate = PhoneUtils.dateConverter(from);

                Date toDate = PhoneUtils.dateConverter(to);

                response = ResponseEntity.ok(callService.getTotalCallsById(id_customer, fromDate, toDate));

            }

        } catch (Exception E) {

            response = new ResponseEntity(E.getMessage(), HttpStatus.CONFLICT);

        } catch (UserNotexistException E) {

            response = new ResponseEntity("User not exist", HttpStatus.CONFLICT);
        }

        return response;
    }


    //ONLY FOR TESTING
    @PostMapping("/testing")
    public ResponseEntity getCustomerById(@RequestBody List<User> userList) {
        return userService.addCustomer(userList);
    }

    //Real
    @PostMapping("/backoffice/customer/")
    public ResponseEntity addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


}
