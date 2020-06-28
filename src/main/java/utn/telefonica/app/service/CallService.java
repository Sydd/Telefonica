package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.Projections.CallsPerUser;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.dto.UserWithCalls;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.Projections.CallTotals;
import utn.telefonica.app.projections.CustomerCallsCant;
import utn.telefonica.app.projections.UserProjection;
import utn.telefonica.app.repository.CallRepository;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.*;

@Service
public class CallService {
    private final CallRepository callRepository;

    private final UserService userService;


    @Autowired
    public CallService(CallRepository callRepository, UserService userService) {

        this.callRepository = callRepository;

        this.userService = userService;
    }


    public UserWithCalls getTotalCallsById(Integer id_customer, String from, String to,String completeName) throws ParseException{

        Date fromDate = PhoneUtils.dateConverter(from);

        Date toDate = PhoneUtils.dateConverter(to);

        return new UserWithCalls(completeName,callRepository.getTotalCallsByDate(id_customer, fromDate, toDate));
    }


    //METODO EXPUESTO PARA LA ANTENNA.
    public Call addCall(CallDto call) throws ParseException {

        return callRepository.addCall(
                call.getOriginNumber(),
                call.getDestinyNumber(),
                Integer.getInteger(call.getCallDuration()),
                PhoneUtils.dateConverter(call.getCallDate()));


    }

    public Call getCallById(Integer i) throws UserNotexistException{
        return callRepository.findById(i).orElseThrow(()-> new UserNotexistException());
    }


    public List<CustomerCallsCant> getMostCalledNumber(int idUser) throws InvalidSessionException {

            return callRepository.getTopCalls(idUser);

    }

    public List<CallsPerUser> getCallsPerUser(int idUser) throws UserNotexistException {
        return callRepository.getCallsPerUser(idUser);
    }
}

