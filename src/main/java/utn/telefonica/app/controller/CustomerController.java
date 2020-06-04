package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.service.CustomerService;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.service.LineService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService costumerService;
    private final CallService callService;

    @Autowired
    public CustomerController(CustomerService costumerService, CallService callService) {
        this.costumerService = costumerService;
        this.callService = callService;
    }




    @GetMapping("/{id_customer}")
    public ResponseEntity getCustomerById(@PathVariable Integer id_customer, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        ResponseEntity response;
        try {

            if (isNull(from) || isNull(to)) {

                response = ResponseEntity.ok(costumerService.getCostumerById(id_customer));

            } else {

                Date fromDate = Converter(from);

                Date toDate = Converter(to);

                response = ResponseEntity.ok(callService.getTotalCallsById(id_customer, fromDate, toDate));

            }
        } catch ( Exception E) {

            response = new ResponseEntity(E.getMessage(), HttpStatus.CONFLICT);

        } catch (UserNotexistException E){

            response = new ResponseEntity("User not exist", HttpStatus.CONFLICT);
        }

        return response;
    }




    Date Converter(String toConvert) throws Exception {
        Date aux = new SimpleDateFormat("dd-MM-yyyy").parse(toConvert);
        return aux;
    }

    @GetMapping("/cantcall")
    public ResponseEntity getCustomerCantCall(){
        return costumerService.getCustomerCantCall();
    }



    @PostMapping("/")
    public ResponseEntity addCustomer(@RequestBody List<Customer> customer) {
        return costumerService.addCustomer(customer); //todo devolver en el header el  uri del cliente creado.
    }

    @GetMapping("/")
    public List<Customer> getAll(@RequestParam(required = false) String firstname) {
        return costumerService.getAllCostumers(firstname);
    }
}
