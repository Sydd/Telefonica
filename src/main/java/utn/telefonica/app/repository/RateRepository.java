package utn.telefonica.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate,Integer> {
}
