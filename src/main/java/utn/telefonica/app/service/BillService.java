package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.projections.BillsByCustomer;
import utn.telefonica.app.repository.BillRepository;
import utn.telefonica.app.model.Bill;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void addBill(List<Bill> bills)
    {

        Random rn = new Random();

        for(Bill bill:bills) {

            int answer = rn.nextInt(10) - 10; // randomizo un numero entre 0 y -10

            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.MONTH, answer);

            bill.setTotalPrice(answer);

            bill.setCreatedAt(calendar.getTime());

            billRepository.save(bill);

        }

    }

    public Bill getBillById(Integer i){
        return billRepository.findById(i).get();
    }


    public List<BillsByCustomer> getBillsByDate(int id, Date fromDate, Date toDate) {
        return billRepository.getBillsByDate(id,fromDate,toDate);
    }

}

