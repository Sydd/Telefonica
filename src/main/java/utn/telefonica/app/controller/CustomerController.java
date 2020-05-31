package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.service.CustomerService;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.service.LineService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
        private final CustomerService costumerService;

    @Autowired
    public CustomerController(CustomerService costumerService, LineService lineService) {
        this.costumerService = costumerService;
    }

    //?requestparam

    //RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate
    @GetMapping("/{id_customer}")
    public ResponseEntity getCustomerById(@PathVariable Integer id_customer) {
        ResponseEntity response;
        try {

            //if (isNull(fromDate) || isNull(toDate) ){
                response = ResponseEntity.ok( costumerService.getTotalCallsById(id_customer));
            //    response = ResponseEntity.ok( costumerService.getCostumerById(id_costumer));
           // }
        } catch (UserNotexistException e){
            response = new ResponseEntity("User not found.", HttpStatus.CONFLICT);
        }
        return response;
    }




    @PostMapping("/")
    public ResponseEntity addCustomer(@RequestBody List<Customer> customer) {
        return costumerService.addCustomer(customer); //todo devolver en el header el  uri del cliente creado.
    }

    @GetMapping("/")
    public List<Customer> getAll(@RequestParam(required = false) String firstname){
        return costumerService.getAllCostumers(firstname);
    }
}
