package utn.telefonica.app.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Costumer;

import java.util.List;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,Integer> {

    //List<Costumer> findByName(String name);

    List<Costumer> findByFirstName(String firstName);
}
