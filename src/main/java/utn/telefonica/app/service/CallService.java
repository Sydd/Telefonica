package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.exceptions.ValidationException;
import utn.telefonica.app.projections.CallTotals;
import utn.telefonica.app.projections.CustomerCallsCant;
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
    private final SessionManager sessionManager;


    @Autowired
    public CallService(CallRepository callRepository, SessionManager sessionManager) {
        this.callRepository = callRepository;
        this.sessionManager = sessionManager;
    }


    public List<CallTotals> getTotalCallsById(Integer id_customer, Date fromDate, Date toDate) {
        return callRepository.getTotalCallsByDate(id_customer, fromDate, toDate);
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


    public List<CustomerCallsCant> getMostCalledNumber(String token) throws InvalidSessionException {

            Session actualSession = Optional.
                    ofNullable(sessionManager.getSession(token)).
                    orElseThrow(() -> new InvalidSessionException());

            return callRepository.getTopCalls(actualSession.getLoggedUser().getId());


    }
}

