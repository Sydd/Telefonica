package utn.telefonica.app.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utn.telefonica.app.projections.CityRate;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.InvalidCityException;
import utn.telefonica.app.model.City;
import utn.telefonica.app.repository.CityRepository;
import utn.telefonica.app.testutils.TestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCityService {
    CityService cityService;

    CityRepository cityRepository;

    @Before
    public void setUp(){
        cityRepository = mock(CityRepository.class);

        cityService = new CityService(cityRepository);
    }

    @Test
    public void testGetCityByIdOk() throws InvalidCityException {
        City city = TestUtils.getTestingCity();

       when(cityRepository.findById(1)).thenReturn(Optional.ofNullable(city));

        City aux = cityService.getCityById(city.getId());

     assertEquals(aux.getId(),city.getId());

    }

    @Test(expected = InvalidCityException.class)
    public void testGetCityByIdInvalid () throws InvalidCityException{

        City city = TestUtils.getTestingCity();
        when(cityRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        City aux = cityService.getCityById(city.getId());

        assertEquals(aux.getId(),city.getId());
    }

    @Test
    public void testGetAllCitiesWithoutName() throws InvalidCityException {
        List<CityRate> cityRates = TestUtils.getListCityProjections();

        when(cityRepository.findAllCityRate()).thenReturn(cityRates);

        List<CityRate> aux = cityService.getAllCities(null);

        assertEquals(aux.get(0).getId(), cityRates.get(0).getId());
    }

    @Test
    public void testGetAllCitiesWithName() throws InvalidCityException {
        List<CityRate> cityRates = TestUtils.getListCityProjections();

        when(cityRepository.findCityRateByName("Mar del Pollo")).thenReturn(cityRates);

        List<CityRate> aux = cityService.getAllCities("Mar del Pollo");

        assertEquals(aux.get(0).getId(), cityRates.get(0).getId());
    }

    @Test(expected = InvalidCityException.class)
    public void testGetAllCitiesEmpty() throws InvalidCityException{
        List<CityRate> cityRates = new ArrayList<>();
        when(cityRepository.findCityRateByName("Mar del Pollo")).thenReturn(cityRates);
        cityService.getAllCities("Mar del Pollo");

    }

    @Test
    public void testUpdateCityFindOk() throws InvalidCityException, FieldIsNullException{
        City city = TestUtils.getTestingCity();
        when(cityRepository.findById(city.getId())).thenReturn(Optional.ofNullable(city));
        String aux = cityService.updateCity(city);

        assertEquals(aux,"New rate saved.");
    }

    @Test(expected = InvalidCityException.class)
    public void testUpdateCityInvalidCity() throws InvalidCityException, FieldIsNullException{
        City city = TestUtils.getTestingCity();
        when(cityRepository.findById(city.getId())).thenReturn(Optional.ofNullable(null));
        String aux = cityService.updateCity(city);
    }

    @Test(expected = FieldIsNullException.class)
    public void testUpdateCityFieldNull() throws InvalidCityException,FieldIsNullException{
        City city = TestUtils.getTestingCity();
        city.setCostPerSecond(-1);
        when(cityRepository.findById(city.getId())).thenReturn(Optional.ofNullable(city));
        String aux = cityService.updateCity(city);
    }

}
