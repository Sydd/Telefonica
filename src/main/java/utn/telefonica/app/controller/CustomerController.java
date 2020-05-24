package utn.telefonica.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.Service.CostumerService;
import utn.telefonica.app.model.Customer;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CostumerService costumerService;

    @Autowired
    public CustomerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping("/{id_customer}")
    public Customer getCustomerById(@PathVariable Integer id_costumer) {
        return costumerService.getCostumerById(id_costumer);
    }

    @PostMapping("/")
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        return costumerService.addCostumer(customer);
    }

    @GetMapping("/")
    public List<Customer> getAll(@RequestParam(required = false) String firstname){
        return costumerService.getAllCostumers(firstname);
    }
}
