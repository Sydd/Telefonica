package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.projections.CustomerCant;
import utn.telefonica.app.service.CustomerService;
import utn.telefonica.app.model.Customer;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

   /* @GetMapping("/{idCustomer}")
    public Customer getCustomerById(@PathVariable Integer idCustomer) {
        return customerService.getCostumerById(idCustomer);
    }*/

    @PostMapping("/")
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        return customerService.addCostumer(customer);
    }

    @GetMapping("/")
    public List<Customer> getAll(@RequestParam(required = false) String firstname){
        return customerService.getAllCostumers(firstname);
    }

    @GetMapping("/Cant")
    public List<CustomerCant> getCustomerCant()
    {
        return customerService.getCustomerCant();
    }

    //DtO

    @GetMapping("/{idCustomer}")
    public ResponseEntity getCustomerNameLastname(@PathVariable Integer idCustomer) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok( customerService.getNameLastname(idCustomer));
        } catch (UserNotexistException e) {
            response = ResponseEntity.badRequest().build();
            System.out.println("No existe el usuario");
        }
        return response;
    }
}
