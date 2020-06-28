package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.projections.CallsPerUser;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.projections.CallTotals;
import utn.telefonica.app.projections.CustomerCallsCant;

import java.util.Date;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {


    @Query(value = "Select ca.date_call as Date, ca.total_price as cost, l.line_number as phoneline\n" +
            "    from  users as cu\n" +
            "    join phonelines as l\n" +
            "    on cu.id_user = l.user_id_user\n" +
            "    join calls as ca\n" +
            "    on l.id_line =  ca.phone_line_id_line\n" +
            "    where cu.id_user = ?1 and ca.date_call between ?2 and ?3", nativeQuery = true)
    List<CallTotals> getTotalCallsByDate(Integer id, Date from, Date to);

    @Query(value= "insert into calls(phone_line_id_line, phone_line_destiny_id_line, call_duration, date_call) values (?1,?2,?3,?4) ", nativeQuery = true)
    Call addCall(String originNumber, String destinyNumber, Integer duration, Date date);


    @Query(value = "select  phoneDestiny.line_number as number, count(c.phone_line_destiny_id_line) as Cant\n" +
            "from calls as c\n" +
            "join phonelines as ph\n" +
            "on c.phone_line_id_line = ph.id_line\n" +
            "join phonelines as phoneDestiny\n" +
            "on phoneDestiny.id_line = c.phone_line_destiny_id_line\n" +
            "where ph.user_id_user = ?1\n" +
            "group by number \n" +
            "order by cant desc\n" +
            "limit 10",nativeQuery = true)
    List<CustomerCallsCant> getTopCalls(int idUser);


    @Query(value = "select c.date_call as DateCall,c.call_duration as Duration,c.total_price as TotalPrice\n" +
            "from calls as c\n" +
            "join phonelines as ph \n" +
            "on c.phone_line_id_line = ph.id_line\n" +
            "join customers as cu\n" +
            "on ph.customer_id_customer = cu.id_customer\n" +
            "where c.customer_id_customer = ?1\n", nativeQuery = true)
    List<CallsPerUser> getCallsPerUser(int idUser);





}
