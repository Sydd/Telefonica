package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.service.RateService;
import utn.telefonica.app.model.Rate;

@RestController
@RequestMapping("/rate")
public class RateController {
    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/{id}")
    public Rate getRateById(@PathVariable Integer id)
    {
        return rateService.getRateById(id);
    }
    @PostMapping("/")
    public void addRate(@RequestBody Rate rate)
    {
        rateService.addRate(rate);
    }


}

