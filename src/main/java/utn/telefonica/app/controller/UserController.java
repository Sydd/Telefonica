package utn.telefonica.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.InvalidLoginException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.User;
import utn.telefonica.app.projections.UserProjection;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.service.UserService;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.utils.PhoneUtils;

import javax.persistence.NonUniqueResultException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/backoffice/")
public class UserController {

    private final UserService userService;

    private final CallService callService;


    @Autowired
    public UserController(UserService costumerService, CallService callService) {

        this.userService = costumerService;

        this.callService = callService;
    }

    @GetMapping("customer/")
    @ApiOperation(value="Get all costumers")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Customers were returned")})
    public ResponseEntity getAllCostumer(@RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String dni) {
        return ResponseEntity.ok(userService.getAllCostumers(firstName,dni));
    }


    @GetMapping("customer/{id_customer}")
    @ApiOperation(value="Get Costumer by id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Costumer by id is returned"),
            @ApiResponse(code = 400, message = "A problem has occurred with the date"),
            @ApiResponse(code = 404, message = "User search problem")})
    public ResponseEntity getCustomerById(@PathVariable Integer id_customer,
                                          @RequestParam(required = false) String from,
                                          @RequestParam(required = false) String to) {

        ResponseEntity response;

        try {


            if (isNull(from) || isNull(to)) {

                response = ResponseEntity.ok(userService.getCostumerById(id_customer));

            } else {

                UserProjection aux = userService.getCostumerById(id_customer);

                String completeName = aux.getFirstName() + " " + aux.getLastName();

                response = ResponseEntity.ok(callService.getTotalCallsById(id_customer, from, to,completeName));

            }

        } catch (ParseException P) {

            response =  new ResponseEntity("Invalid Date",HttpStatus.BAD_REQUEST);

        } catch (UserNotexistException E) {

            response = new ResponseEntity("User not exist", HttpStatus.NOT_FOUND);

        }
        return response;
    }




    @PostMapping("customer/")
    @ApiOperation(value="Creates a new user.")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "User created."),
            @ApiResponse(code = 409, message = "You tried to create an user with a duplicated username"),
            @ApiResponse(code = 400, message = "You tried to create an user  with null fields or bad json."  )})
    public ResponseEntity addUser(@RequestBody User user) {
        try {

            return ResponseEntity.created(PhoneUtils.getLocation(userService.addUser(user))).build();

        } catch (NonUniqueResultException E) {

            return new ResponseEntity("User already exist", HttpStatus.CONFLICT);

        } catch (DataIntegrityViolationException E) {

            return new ResponseEntity("ERROR SQL " + E.getMostSpecificCause(), HttpStatus.CONFLICT);

        } catch (FieldIsNullException E) {

            return new ResponseEntity("YOU CAN NOT HAVE NULL CAMPS.", HttpStatus.BAD_REQUEST);

        }
    }


    @PutMapping("customer/")
    @ApiOperation(value="Update user")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "The user was updated"),
            @ApiResponse(code = 404, message = "Problem with user search")})
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(user));
        } catch (UserNotexistException E) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("customer/{id_user}")
    @ApiOperation(value="Delete user by id")
    @ApiResponses( value = {
            @ApiResponse(code = 204, message = "User is deleted"),
            @ApiResponse(code = 404, message = "Trouble finding your User")})
    public ResponseEntity deleteUserById(@PathVariable Integer id_user){
        try {

            userService.deleteUser(id_user);
            return ResponseEntity.noContent().build();//? "User " + id_user + " deleted." : "User not found.");

            } catch (UserNotexistException E) {

            return ResponseEntity.notFound().build();

        }
    }



}
