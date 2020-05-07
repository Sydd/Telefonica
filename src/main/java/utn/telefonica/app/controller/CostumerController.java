package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.model.Costumer;
import utn.telefonica.app.service.CostumerService;

import java.util.List;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    private final CostumerService costumerService;

    @Autowired
    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping("/{id_costumer}")
    public Costumer getCostumerById(@PathVariable Integer id_costumer) {
        return costumerService.getCostumerById(id_costumer);
    }

    @PostMapping("/")
    public void AddCostumer(@RequestBody Costumer costumer) {
        costumerService.addCostumer(costumer);
    }

    @GetMapping("/")
    public List<Costumer> getAll(@RequestParam(required = false) String firstname){
        return costumerService.getAllCostumers(firstname);
    }
}
