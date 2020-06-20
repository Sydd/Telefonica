package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.projections.BillsByCustomer;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {


    @Query(value = "select b.created_at as CreatedAt,b.is_payed as IsPayed, b.total_price as TotalPrice, b.total_calls as TotalCalls\n" +
            "from bills as b\n" +
            "join phonelines as ph on ph.id_line = b.phone_line_id_line \n" +
            "join users as u " +
            "where u.id_user = ?1 and b.created_at between ?2 and ?3\n" +
            "order by b.created_at desc ",nativeQuery =     true)
    List<BillsByCustomer> getBillsByDate(int id, Date fromDate, Date toDate);
}


