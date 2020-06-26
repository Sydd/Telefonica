package utn.telefonica.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.User;
import utn.telefonica.app.projections.UserProjection;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    List<UserProjection> findByFirstNameStartsWith(String firstName);

    List<UserProjection> findByFirstNameStartsWithAndDniStartsWith(String firstName, String dni);

    List<UserProjection> findByDni(String dni);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = password(:password)")
    User findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);

    @Query(value = "SELECT u FROM User u where u.id = :id")
    UserProjection findByUserId(Integer id);


   // @Query(value = "Select c.first_name name, count(ca.id_call) cant from customers c join calls ca where c.id_customer = ca.customer_id_customer group by (c.id_customer)",nativeQuery = true)
    //List<CustomerCallsCant> getCallCant();


    //pARCIALLLLLLLLLLLLLLLL
  //  @Query(value = "Select c.first_name name,c.dni dni, ca.total_price price from customers c join calls ca where c.id_customer = ca.customer_id_customer and c.id_customer = ?1 order by ca.date_call desc limit 1", nativeQuery = true)
  //  CustomerPriceLastCall getPriceLastCall(@Param("id") Integer id);
    //parciaaaaaaaaaaaalllllllllllll

}
