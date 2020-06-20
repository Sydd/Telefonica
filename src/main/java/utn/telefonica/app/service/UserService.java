package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.User;
import utn.telefonica.app.model.enums.UserType;
import utn.telefonica.app.repository.UserRepository;
import utn.telefonica.app.utils.PhoneUtils;

import javax.persistence.NonUniqueResultException;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //Costumer loggin added. Desde el loginController se pide
    public User login(String username, String password) throws UserNotexistException, ValidationException {
        if (username == null || password == null) {
            throw new ValidationException("username and password must have a value!");
        }
        User customer = userRepository.findByUsernameAndPassword(username, password);
        return Optional.ofNullable(customer).orElseThrow(() -> new UserNotexistException());
    }


    //ONLY FOR TESTING.

    public ResponseEntity addCustomer(List<User> userList) {
        try {


            for (User user : userList) {

                user.setCreatedAt(Calendar.getInstance().getTime());

                userRepository.save(user);

                System.out.println("New user: " + user.getUsername());

            }

            return ResponseEntity.ok(HttpStatus.CREATED); //todo devolver customer nuevo creado en el header.

        } catch (Exception E) {

            System.out.println("Tried to created a user with an used username.");

            return new ResponseEntity("User already exist", HttpStatus.CONFLICT);
        }
    }

    public User getCostumerById(Integer i) throws UserNotexistException {
        return userRepository.findById(i).orElseThrow(() -> new UserNotexistException());
    }


    public ResponseEntity getAllCostumers(String firstName) {
        if (isNull(firstName)) {
            return ResponseEntity.ok(userRepository.findAll());
        }

        return ResponseEntity.ok(userRepository.findByFirstName(firstName));
    }

    public ResponseEntity addUser(User user) {

        ResponseEntity response;

        try {

            if (isNull(user.getUsername()) ||
                    isNull(user.getDni()) ||
                    isNull(user.getFirstName()) ||
                    isNull(user.getLastName()) ||
                    isNull(user.getPassword())) {
                throw new FieldIsNullException();
            }

            user.setCreatedAt(Calendar.getInstance().getTime());

            if (isNull(user.getUserType())) {
                user.setUserType(UserType.CUSTOMER);
            }
            user = userRepository.save(user);

            return ResponseEntity.created(PhoneUtils.getLocation(user)).build();

        } catch (NonUniqueResultException E) {

            return new ResponseEntity("User already exist", HttpStatus.CONFLICT);

        } catch (DataIntegrityViolationException E) {

            return new ResponseEntity("ERROR SQL " + E.getMostSpecificCause(), HttpStatus.CONFLICT); //TODO AVERIGUAR COMO

        } catch (FieldIsNullException E) {

            return new ResponseEntity("YOU CAN NOT HAVE NULL CAMPS.", HttpStatus.BAD_REQUEST);
        } catch (Exception E) {

            return new ResponseEntity(E.getMessage() + E.getClass().toString(), HttpStatus.CONFLICT);
        }
    }


}

