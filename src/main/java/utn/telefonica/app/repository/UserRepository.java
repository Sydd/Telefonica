package utn.telefonica.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //List<Costumer> findByName(String name);

    List<User> findByFirstName(String firstName);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = password(:password)")
    User findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
    


   // @Query(value = "Select c.first_name name, count(ca.id_call) cant from customers c join calls ca where c.id_customer = ca.customer_id_customer group by (c.id_customer)",nativeQuery = true)
    //List<CustomerCallsCant> getCallCant();


    //pARCIALLLLLLLLLLLLLLLL
  //  @Query(value = "Select c.first_name name,c.dni dni, ca.total_price price from customers c join calls ca where c.id_customer = ca.customer_id_customer and c.id_customer = ?1 order by ca.date_call desc limit 1", nativeQuery = true)
  //  CustomerPriceLastCall getPriceLastCall(@Param("id") Integer id);
    //parciaaaaaaaaaaaalllllllllllll

}
