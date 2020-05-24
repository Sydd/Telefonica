package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.repository.RateRepository;
import utn.telefonica.app.model.Rate;

@Service
public class RateService {

    private final RateRepository rateRepository;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public void addRate(Rate rate)
    {
        rateRepository.save(rate);
    }
    public Rate getRateById(Integer i)
    {
        return rateRepository.findById(i).get();
    }
}
