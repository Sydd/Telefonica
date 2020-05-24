package utn.telefonica.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.Service.BillService;
import utn.telefonica.app.model.Bill;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Integer id)
    {
        return billService.getBillById(id);
    }

    @PostMapping("/")
    public void AddBill(@RequestBody Bill bill)
    {
        billService.addBill(bill);
    }
}


