package utn.telefonica.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.model.Costumer;
import utn.telefonica.app.Repository.CostumerRepository;

import java.util.List;

import static java.util.Objects.isNull;

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

    public List<Costumer> getAllCostumers(String firstName) {
        if(isNull(firstName)) {
            return costumerRepository.findAll();
        }

      return  costumerRepository.findByFirstName(firstName);
    }

}
