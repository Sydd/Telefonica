package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.projections.CallTotals;

import java.util.Date;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {

    public List<Call> findByphoneLine(Integer id);

    /* @Query(value = "SELECT C.customer.firstName as name, C as cost  FROM Call C where C.customer.id = :id and C.callDate BETWEEN :from and :to" )

     List<CallTotals> getTotalCallsByDate(@Param("id") Integer id, @Param("from") Date from, @Param("to") Date to); //todo hacer revisando la implementacion de controller/service
*/
    /* QUERY
    Select cu.firstName as name, ca.total_price as cost
    from  customers as cu
    join lines as l
    on cu.id_customer = l.id_customer
    join calls as ca
    on l.id_line =  caTo.id_line_to
    where cu.id = :id and ca.callDate between :from and :to;
     */


}
