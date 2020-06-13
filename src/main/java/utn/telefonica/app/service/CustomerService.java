package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.projections.CustomerCalls;
import utn.telefonica.app.projections.CustomerPriceLastCall;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CustomerService {

    private final utn.telefonica.app.repository.CustomerRepository customerRepository;

    @Autowired
    public CustomerService(utn.telefonica.app.repository.CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //Costumer loggin added. Desde el loginController se pide
    public Customer login(String username, String password) throws UserNotexistException, ValidationException {
        if (username == null || password == null) {
            throw new ValidationException("username and password must have a value!");
        }
        Customer customer = customerRepository.findByUsernameAndPassword(username, password);
        return Optional.ofNullable(customer).orElseThrow(() -> new UserNotexistException());
    }


    public ResponseEntity addCustomer(List<Customer> customerList) {
        try {

            for (Customer customer:customerList) {

                customer.setCreatedAt(Calendar.getInstance().getTime());

                customerRepository.save(customer);

                System.out.println("New user: " + customer.getUsername());

            }
            return ResponseEntity.ok("Customer saved.");                 //todo devolver customer nuevo creado en el header.

        } catch (Exception E) {

            System.out.println("Tried to created a customer with an used username.");

            return new ResponseEntity("User already exist", HttpStatus.CONFLICT);
        }
    }

    public Customer getCostumerById(Integer i) throws UserNotexistException {
        return customerRepository.findById(i).orElseThrow(() -> new UserNotexistException());
    }

  /*  public CustomerCalls getTotalCallsById(Integer i,String from,String to) throws UserNotexistException {
        return (CustomerCalls) customerRepository.getTotalCalls(i,from,to);
    }*/


    public List<Customer> getAllCostumers(String firstName) {
        if (isNull(firstName)) {
            return customerRepository.findAll();
        }

        return customerRepository.findByFirstName(firstName);
    }

   /* public ResponseEntity getCustomerCantCall() {
        return ResponseEntity.ok(customerRepository.getCallCant());
    }*/



}

