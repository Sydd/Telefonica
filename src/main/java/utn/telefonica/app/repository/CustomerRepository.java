package utn.telefonica.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.projections.CustomerCant;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    //List<Costumer> findByName(String name);

    List<Customer> findByFirstName(String firstName);

    @Query("SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password")
    Customer findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);

    /*
    Select c.first_name  NameFirst, c.last_name  NameLast, count(ca.id_call)  CantCalls" +
                   "from customers  c join calls  ca"+
                   "where c.id_customer = ca.customer_id_customer  group by c.id_customer
     */

    @Query(value = "Select c.first_name NameFirst,c.last_name NameLast, count(ca.id_call) CantCalls from customers c " +
            "join calls ca where c.id_customer = ca.customer_id_customer " +
            "group by c.id_customer",nativeQuery = true)
    List<CustomerCant> getCustomerCant();
}
