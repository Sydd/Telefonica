package utn.telefonica.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Costumer;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer,Integer> {

}
