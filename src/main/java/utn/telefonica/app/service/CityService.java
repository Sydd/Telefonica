package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.model.City;
import utn.telefonica.app.repository.CityRepository;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository){ this.cityRepository = cityRepository ;}

    public void addCity(City city) {
        cityRepository.save(city);
    }

    public City getCityById(Integer i){
        return  cityRepository.findById(i).get();
    }

    public List<City> getAllCities(String cityname) {
        if(isNull(cityname)) {
            return cityRepository.findAll();
        }

        return  cityRepository.findByCityName(cityname);
    }
}
