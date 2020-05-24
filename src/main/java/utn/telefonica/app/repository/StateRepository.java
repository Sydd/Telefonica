package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.Projections.StateCant;
import utn.telefonica.app.model.State;
import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>
{

    List<State> findByStateName (String stateName);

    @Query(value = "Select s.state_name, count(c.id) as cant from cities " +
            "join city on c.id = s.id"+
            "group by p.id", nativeQuery = true)
    List<StateCant> getPersonCant();
}



