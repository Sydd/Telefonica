package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.model.Costumer;
import utn.telefonica.app.repository.CostumerRepository;

import java.util.List;

@Service
public class CostumerService {

    private final CostumerRepository costumerRepository;

    @Autowired
    public CostumerService (CostumerRepository costumerRepository){
        this.costumerRepository = costumerRepository;
    }

    public void addCostumer(Costumer costumer) {
        costumerRepository.save(costumer);
    }

    public Costumer getCostumerById(Integer i){
       return  costumerRepository.findById(i).get();
    }

    public List<Costumer> getAllCostumers() {
        return costumerRepository.findAll();
    }
}
