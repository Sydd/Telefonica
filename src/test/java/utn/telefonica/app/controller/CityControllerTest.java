package utn.telefonica.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utn.telefonica.app.exceptions.FieldIsNullException;
import utn.telefonica.app.exceptions.InvalidCityException;
import utn.telefonica.app.exceptions.InvalidSessionException;
import utn.telefonica.app.model.City;
import utn.telefonica.app.service.CityService;
import utn.telefonica.app.testutils.TestUtils;
import utn.telefonica.app.projections.CityRate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityControllerTest {

    CityService cityService;
    CityController cityController;

    @Before
    public void setUp(){
        cityService = mock(CityService.class);

        cityController = new CityController(cityService);
    }

    @Test
    public void testGetAllOk() throws InvalidCityException {
        
        when(cityService.getAllCities(TestUtils.getTestingCity().getCityName())).thenReturn(TestUtils.getListCityProjections());

        ResponseEntity< List <CityRate>> response = cityController.getAll(TestUtils.getTestingCity().getCityName());
        
        assertEquals(response.getBody().get(0).getId(),TestUtils.getListCityProjections().get(0).getId());
    }

    @Test
    public void testGetAllNotFound() throws InvalidCityException {

        when(cityService.getAllCities(TestUtils.getTestingCity().getCityName())).thenThrow(new InvalidCityException());

        ResponseEntity< List <CityRate>> response = cityController.getAll(TestUtils.getTestingCity().getCityName());

        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateCityRateOk() throws InvalidCityException, FieldIsNullException {
        City aux = TestUtils.getTestingCity();

        when(cityService.updateCity(aux)).thenReturn("sep");

        ResponseEntity<City> response = cityController.updateCityRate(aux);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void testUpdateCityRateException() throws InvalidCityException, FieldIsNullException {
        City aux = TestUtils.getTestingCity();

        when(cityService.updateCity(aux)).thenThrow(new InvalidCityException());

        ResponseEntity<City> response = cityController.updateCityRate(aux);

        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateCityRateFieldNullException() throws InvalidCityException, FieldIsNullException {
        City aux = TestUtils.getTestingCity();

        when(cityService.updateCity(aux)).thenThrow(new FieldIsNullException());

        ResponseEntity<City> response = cityController.updateCityRate(aux);

        assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetCityById()  throws InvalidCityException {

        when(cityService.getCityById(1)).thenReturn(TestUtils.getTestingCity());

        ResponseEntity<City> responseEntity = cityController.getCityById(1);

        assertEquals(responseEntity.getBody().getId(),1);
    }


    @Test
    public void testGetCityByNotFound()  throws InvalidCityException {
        when(cityService.getCityById(1)).thenThrow(new InvalidCityException());

        ResponseEntity<City> responseEntity = cityController.getCityById(1);

        assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }
}
