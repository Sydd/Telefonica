package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.BillNotFoundException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.model.User;
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


    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }


    public Bill getBillById(Integer i) throws BillNotFoundException {
        return billRepository.findById(i).orElseThrow(()-> new BillNotFoundException());
    }


    public List<BillsByCustomer> getBillsByDate(String token, String from, String to) throws ParseException , InvalidSessionException {

        User user = PhoneUtils.getUserByToken(token).getLoggedUser();

        System.out.println(user);

        Date fromDate = PhoneUtils.dateConverter(from);

        Date toDate = PhoneUtils.dateConverter(to);


        return billRepository.getBillsByDate(user.getId(), fromDate, toDate);

    }

    public List<BillsByCustomer> getBillsByUser(int idCustomer) throws UserNotexistException {
        try {
            return billRepository.getBillsByUserId(idCustomer);
        } catch (EmptyResultDataAccessException e) {
            throw  new UserNotexistException();
        }
    }

}

