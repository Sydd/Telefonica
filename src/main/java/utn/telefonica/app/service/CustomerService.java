package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.dto.NameLastnameDto;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.projections.CustomerCant;
import utn.telefonica.app.Projections.CustomerExamen;
import utn.telefonica.app.repository.CustomerRepository;

import javax.swing.text.html.Option;
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


    public ResponseEntity addCostumer(Customer customer) {
        try {

            customer.setCreatedAt(Calendar.getInstance().getTime());
            customerRepository.save(customer);

            System.out.println("New user: " + customer.getUsername());


            return ResponseEntity.ok("Customer " + customer.getFirstName() + " saved.");

        } catch (Exception E) {

            System.out.println("Tried to created a customer with an used username.");

            return new ResponseEntity("User already exist", HttpStatus.CONFLICT);
        }
    }


    public List<CustomerCant> getCustomerCant() {
        List<CustomerCant> aux = null;
        aux = customerRepository.getCustomerCant();
        return aux;
    }

    //PARCIAL ->
    public List<CustomerExamen> getCustomerExamen() throws UserNotexistException {
        List<CustomerExamen> aux = null;
        aux = customerRepository.getCustomerExamen();
        Optional.ofNullable(aux).orElseThrow(() -> new UserNotexistException());
        return aux;
    }

    // FIN PARCIAL

    public Customer getCostumerById(Integer i) {
        return customerRepository.findById(i).get(); // todo poner or else trhow.
    }

    public List<Customer> getAllCostumers(String firstName) {
        if (isNull(firstName)) {
            return customerRepository.findAll();
        }

        return customerRepository.findByFirstName(firstName);
    }

    public NameLastnameDto getNameLastname(Integer idCustomer) throws UserNotexistException{
        Customer c = customerRepository.findById(idCustomer).orElseThrow(() -> new UserNotexistException());
        NameLastnameDto aux = new NameLastnameDto(c.getFirstName(), c.getLastName());
        return aux;
    }
}
