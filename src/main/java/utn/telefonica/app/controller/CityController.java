package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.model.City;
import utn.telefonica.app.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/")
    public List<City> getAll(@RequestParam(required = false) String cityName){
        return cityService.getAllCities(cityName);
    }

}

