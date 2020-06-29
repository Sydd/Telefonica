package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.projections.CityRate;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.InvalidCityException;
import utn.telefonica.app.model.City;
import utn.telefonica.app.service.CityService;

import java.util.List;

@RestController
@RequestMapping("backoffice/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/")
    public ResponseEntity<List<CityRate>> getAll(@RequestParam(required = false) String cityName) {
        try {

            return new ResponseEntity<List<CityRate>>(cityService.getAllCities(cityName), HttpStatus.OK);

        } catch (InvalidCityException C) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/")
    public ResponseEntity updateCityRate(@RequestBody(required = true) City newRate) {
        try {

            return ResponseEntity.ok(cityService.updateCity(newRate));

        } catch (InvalidCityException C) {
            return ResponseEntity.notFound().build();
        } catch (FieldIsNullException F) {
            return new ResponseEntity("YOU MUST ESPECIFY THE ID,COST AND PRICE ", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity getCityById(@PathVariable Integer id_city)
    {
        ResponseEntity response;

        try
        {
           response = ResponseEntity.ok(cityService.getCityById(id_city));
        }catch (InvalidCityException I)
        {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return response;
    }




}

