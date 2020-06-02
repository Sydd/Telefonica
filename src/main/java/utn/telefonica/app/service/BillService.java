package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.repository.BillRepository;
import utn.telefonica.app.model.Bill;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void addBill(Bill bill)
    {
        billRepository.save(bill);
    }

    public Bill getBillById(Integer i){
        return billRepository.findById(i).get();
    }

}

