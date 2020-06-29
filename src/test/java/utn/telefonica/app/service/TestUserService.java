package utn.telefonica.app.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.model.User;
import utn.telefonica.app.model.enums.UserType;
import utn.telefonica.app.projections.UserProjection;
import utn.telefonica.app.repository.UserRepository;
import utn.telefonica.app.service.UserService;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.utils.PhoneUtils;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;



public class TestUserService {


    UserService userService;

    UserRepository userRepository;


    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);

        userService = new UserService(userRepository);


    }

    @Test
    public void testAddUser() throws FieldIsNullException {
        User user = TestUtils.getTestingCustomer();
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.addUser(user);
        assertEquals(savedUser.getId(), savedUser.getId());
    }

    @Test
    public void testAddUserNoType() throws FieldIsNullException {
        User user = TestUtils.getTestingCustomer();
        user.setUserType(null);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.addUser(user);
        assertEquals(savedUser.getId(), savedUser.getId());
        assertEquals(savedUser.getUserType(), UserType.CUSTOMER);
    }

    @Test(expected = FieldIsNullException.class)
    public void testAddUserException() throws FieldIsNullException {
        User user = TestUtils.getTestingCustomer();
        user.setUsername(null);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.addUser(user);
    }

    @Test
    public void testUpdateUser() throws UserNotexistException {
        User user = TestUtils.getTestingCustomer();

        User toUpdate = new User(1, "Pedro", "niero", "asd", "asd", Calendar.getInstance().getTime(), TestUtils.getTestingCity(), null,null,UserType.CUSTOMER, "111");


        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(toUpdate));

        when(userRepository.save(user)).thenReturn(user);

        User userAux = userService.updateUser(user);

        assertEquals(userAux.getId(), user.getId());
        assertEquals(userAux.getLastName(), toUpdate.getLastName());
        assertEquals(userAux.getFirstName(), toUpdate.getFirstName());
        assertEquals(userAux.getUsername(), toUpdate.getUsername());
        assertEquals(userAux.getDni(), toUpdate.getDni());

    }

    @Test
    public void testLoginOk() throws UserNotexistException, ValidationException {
        User u = TestUtils.getTestingCustomer();

        when(userRepository.findByUsernameAndPassword("username", "pass")).thenReturn(u);

        User aux = userService.login("username", "pass");
        assertEquals(aux.getId(), u.getId());
    }

    @Test(expected = UserNotexistException.class)
    public void testLoginNotExist() throws UserNotexistException, ValidationException {
        User u = TestUtils.getTestingCustomer();
        when(userRepository.findByUsernameAndPassword("username", "pass")).thenReturn(null);

        User aux = userService.login("username", "pass");

        assertEquals(aux.getId(), u.getId());
    }

    @Test(expected = ValidationException.class)
    public void testLoginValidation() throws UserNotexistException, ValidationException {
        User u = TestUtils.getTestingCustomer();

        when(userRepository.findByUsernameAndPassword(null, "asd")).thenReturn(u);

        User aux = userService.login(null, "pass");

        assertEquals(aux.getId(), u.getId());
    }


    @Test
    public void testGetCostumerById() throws UserNotexistException {

        UserProjection u = TestUtils.getTestingUserProjection();

        when(userRepository.findByUserId(1)).thenReturn(u);

        UserProjection aux = userService.getCostumerById(1);

        assertEquals(aux.getId(), u.getId());
    }


    @Test
    public void testGetAllCostumersNoNameNoDni(){

        List<UserProjection> userList = TestUtils.getListUserProjection();

        when(userRepository.findByFirstNameStartsWith("")).thenReturn(userList);

        List<UserProjection> toTest = userService.getAllCostumers(null,null);

        assertEquals(toTest.get(0).getId(), userList.get(0).getId());
    }


    @Test
    public void testGetAllCostumersWithName(){

        List<UserProjection> userList = TestUtils.getListUserProjection();

        when(userRepository.findByFirstNameStartsWith("randi")).thenReturn(userList);

        List<UserProjection>  toTest = userService.getAllCostumers("randi",null);

        assertEquals(toTest.get(0).getId(), userList.get(0).getId());
    }

    @Test
    public void testGetAllCostumersWithDni(){

        List<UserProjection> userList = TestUtils.getListUserProjection();

        when(userRepository.findByDni("randi")).thenReturn(userList);

        List<UserProjection>  toTest = userService.getAllCostumers(null,"randi");

        assertEquals(toTest.get(0).getId(), userList.get(0).getId());
    }

    @Test
    public void testGetAllCostumersWithNameAndDni(){

        List<UserProjection> userList = TestUtils.getListUserProjection();

        when(userRepository.findByFirstNameStartsWithAndDniStartsWith("randi","222")).thenReturn(userList);

        List<UserProjection>  toTest = userService.getAllCostumers("randi","222");

        assertEquals(toTest.get(0).getId(), userList.get(0).getId());
    }

    @Test
    public void testDeleteUserOk() throws UserNotexistException{
        int idUser = 1;

        UserProjection u = TestUtils.getTestingUserProjection();

        when(userRepository.findByUserId(idUser)).thenReturn(u);

        assertEquals(userService.deleteUser(idUser),"User " + idUser + " deleted.");
    }

    @Test(expected = UserNotexistException.class)
    public void testDeleteUserNotFound() throws UserNotexistException{
        int idUser = 2;

        when(userRepository.findByUserId(idUser)).thenReturn(null);
        assertEquals(userService.deleteUser(idUser),"User " + idUser + " deleted.");
    }
}
