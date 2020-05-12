package utn.telefonica.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.telefonica.app.model.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByCityName(String cityName);
}
