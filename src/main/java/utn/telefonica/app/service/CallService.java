package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.projections.CallsPerUser;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.dto.UserWithCalls;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.projections.CallTotals;
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


    @Autowired
    public CallService(CallRepository callRepository) {

        this.callRepository = callRepository;

    }


    public UserWithCalls getTotalCallsById(Integer id_customer, String from, String to, String completeName) throws ParseException {

        Date fromDate = PhoneUtils.dateConverter(from);

        Date toDate = PhoneUtils.dateConverter(to);

        return new UserWithCalls(completeName, callRepository.getTotalCallsByDate(id_customer, fromDate, toDate));
    }


    //METODO EXPUESTO PARA LA ANTENNA.
    public int addCall(CallDto call) throws ParseException {

         return callRepository.addCall(
                call.getOriginNumber(),
                call.getDestinyNumber(),
                 call.getCallDuration(),
                PhoneUtils.dateConverter(call.getCallDate()));

      //  return "Call saved";
    }


    public List<CustomerCallsCant> getMostCalledNumber(int idUser) throws InvalidSessionException {
            return callRepository.getTopCalls(idUser);

    }

    public List<CallsPerUser> getCallsPerUser(int idUser) throws UserNotexistException {
        return callRepository.getCallsPerUser(idUser);
    }
}

