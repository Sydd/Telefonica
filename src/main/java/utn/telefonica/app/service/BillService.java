package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.projections.BillsByCustomer;
import utn.telefonica.app.repository.BillRepository;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionManager;
import utn.telefonica.app.utils.PhoneUtils;

import java.text.ParseException;
import java.util.*;

@Service
public class BillService {
    private final BillRepository billRepository;

    private final SessionManager sessionManager;

    @Autowired
    public BillService(BillRepository billRepository, SessionManager sessionManager) {
        this.billRepository = billRepository;
        this.sessionManager = sessionManager;
    }


    public Bill getBillById(Integer i) {
        return billRepository.findById(i).get();
    }


    public List<BillsByCustomer> getBillsByDate(String token, String from, String to) throws ParseException , InvalidSessionException {


        Session actualSession = Optional.ofNullable(sessionManager.getSession(token)).orElseThrow(() -> new InvalidSessionException());

        Date fromDate = PhoneUtils.dateConverter(from);

        Date toDate = PhoneUtils.dateConverter(to);

        return billRepository.getBillsByDate(actualSession.getLoggedUser().getId(), fromDate, toDate);

    }

}

