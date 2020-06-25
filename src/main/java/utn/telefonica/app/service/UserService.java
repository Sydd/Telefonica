package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.User;
import utn.telefonica.app.model.enums.UserType;
import utn.telefonica.app.projections.UserProjection;
import utn.telefonica.app.repository.UserRepository;

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


    public UserProjection getCostumerById(Integer i) throws UserNotexistException {
        return Optional.ofNullable(userRepository.findByUserId(i)).orElseThrow( ()-> new UserNotexistException());
    }


    public List<UserProjection> getAllCostumers(String firstName, String dni) {

        if(!isNull(firstName) && !isNull(dni) ) {
            return userRepository.findByFirstNameStartsWithAndDniStartsWith(firstName,dni);
        }

        if (!isNull(firstName)){
            return userRepository.findByFirstNameStartsWith(firstName);
        }
        if (!isNull(dni)) {
            return userRepository.findByDni(dni);
        }
        return userRepository.findByFirstNameStartsWith("");
    }

    public User updateUser(User user) throws UserNotexistException{

            User userAux = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotexistException());

            userAux.setCity(user.getCity());

            userAux.setLastName(user.getLastName());

            userAux.setFirstName(user.getFirstName());

            userAux.setDni(user.getDni());

            userAux.setLastName(user.getLastName());

            return userRepository.save(userAux);
    }

    public User addUser(User user) throws FieldIsNullException{

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

            return userRepository.save(user);
    }


}

