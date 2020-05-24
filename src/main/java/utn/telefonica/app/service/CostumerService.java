package utn.telefonica.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.Customer;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CostumerService {

    private final utn.telefonica.app.Repository.CustomerRepository customerRepository;

    @Autowired
    public CostumerService (utn.telefonica.app.Repository.CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    //Costumer loggin added. Desde el loginController se pide
    public Customer login(String username, String password) throws UserNotexistException, ValidationException{
        if(username == null || password == null){
            throw new ValidationException("username and password must have a value!");
        }
        Customer customer = customerRepository.findByUsernameAndPassword(username,password);
        return Optional.ofNullable(customer).orElseThrow(()-> new UserNotexistException());
    }


    public ResponseEntity addCostumer(Customer customer) {
        try {

            customer.setCreatedAt(Calendar.getInstance().getTime());
            customerRepository.save(customer);

            System.out.println("New user: " + customer.getUsername());


            return ResponseEntity.ok("Customer " + customer.getFirstName() + " saved.");

        } catch (Exception E) {

            System.out.println("Tried to created a customer with an used username.");

            return new ResponseEntity("User already exist",HttpStatus.CONFLICT);
        }
    }

    public Customer getCostumerById(Integer i){
       return  customerRepository.findById(i).get(); // todo poner or else trhow.
    }

    public List<Customer> getAllCostumers(String firstName) {
        if(isNull(firstName)) {
            return customerRepository.findAll();
        }

      return  customerRepository.findByFirstName(firstName);
    }

}
