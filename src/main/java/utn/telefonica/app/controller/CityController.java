package utn.telefonica.app.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value="Get all cities")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Cities list is returned"),
            @ApiResponse(code = 404, message = "Problem with city search")})
    public ResponseEntity<List<CityRate>> getAll(@RequestParam(required = false) String cityName) {
        try {

            return new ResponseEntity<List<CityRate>>(cityService.getAllCities(cityName), HttpStatus.OK);

        } catch (InvalidCityException C) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/")
    @ApiOperation(value="Update city rate")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "The city was updated"),
            @ApiResponse(code = 404, message = "Problem with city search"),
            @ApiResponse(code = 400, message = "The fields are incomplete or incorrect")})
    public ResponseEntity updateCityRate(@RequestBody(required = true) City newRate) {
        try {

            return ResponseEntity.ok(cityService.updateCity(newRate));

        } catch (InvalidCityException C) {
            return ResponseEntity.notFound().build();
        } catch (FieldIsNullException F) {
            return new ResponseEntity("YOU MUST ESPECIFY THE ID,COST AND PRICE ", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id_city}")
    @ApiOperation(value="Get city by id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "city is returned"),
            @ApiResponse(code = 404, message = "Problem in finding the city")})
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

