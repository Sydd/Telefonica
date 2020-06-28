package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.InvalidCityException;
import utn.telefonica.app.model.City;
import utn.telefonica.app.projections.CityRate;
import utn.telefonica.app.repository.CityRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository){ this.cityRepository = cityRepository ;}


    public City getCityById(Integer i) throws InvalidCityException{
          return cityRepository.findById(i).orElseThrow(()->new InvalidCityException());

    }

    public List<CityRate> getAllCities(String cityname) throws InvalidCityException {
        List<CityRate>  cities;

        if(isNull(cityname)) {
            cities = cityRepository.findAllCityRate();
        } else {
            cities = cityRepository.findCityRateByName(cityname);
        }

        if(cities.size() < 1) throw new InvalidCityException();

        return cities;
    }

    public String updateCity(City newRate) throws InvalidCityException, FieldIsNullException {



        if (isNull(newRate.getId()) || newRate.getCostPerSecond() <= 0  || newRate.getPricePerSecond() <= 0 )throw new FieldIsNullException();

        City city = cityRepository.findById(newRate.getId()).orElseThrow(()-> new InvalidCityException());

        city.setCostPerSecond(newRate.getCostPerSecond());

        city.setPricePerSecond(newRate.getPricePerSecond());

        cityRepository.save(city);

        return "New rate saved.";

    }
}
