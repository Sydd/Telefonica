package utn.telefonica.app.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    //List<Costumer> findByName(String name);

    List<Customer> findByFirstName(String firstName);

    @Query("SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password")
    Customer findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
}
