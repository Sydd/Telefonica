package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.City;
import utn.telefonica.app.Projections.CityRate;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByCityName(String cityName);

    @Query(value = "select c.id_city as id, c.city_name as name, c.cost_per_second as cost, c.price_per_second as price\n" +
            "from cities as c\n" +
            "where c.city_name like %?1% ",nativeQuery = true)
    List<CityRate> findCityRateByName(String name);

    @Query(value = "select c.id_city as id, c.city_name as name, c.cost_per_second as cost, c.price_per_second as price from cities as c ",nativeQuery = true)
    List<CityRate> findAllCityRate();
}
