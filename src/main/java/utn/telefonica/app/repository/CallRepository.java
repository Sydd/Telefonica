package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.projections.CallsPerUser;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.projections.CallTotals;
import utn.telefonica.app.projections.CustomerCallsCant;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {


    @Query(value = "Select ca.date_call as Date,ca.total_price as price,lo.line_number as OriginPhoneline,\n" +
            "ld.line_number as destinyNumber,co.city_name as originCity, cd.city_name as destinyCity, ca.call_duration as duration\n" +
            "    from  users as cu\n" +
            "    join phonelines as lo\n" +
            "    on cu.id_user = lo.user_id_user\n" +
            "    join calls as ca\n" +
            "    on lo.id_line =  ca.phone_line_id_line\n" +
            "    join phonelines as ld\n" +
            "    on ld.id_line = ca.phone_line_destiny_id_line\n" +
            "    join cities  as co\n" +
            "    on co.id_city = ca.city_from_id_city\n" +
            "    join cities as cd\n" +
            "    on cd.id_city = ca.city_to_id_city \n" +
            "    where cu.id_user = ?1 and ca.date_call between ?2 and ?3", nativeQuery = true)
    List<CallTotals> getTotalCallsByDate(Integer id, Date from, Date to);

    @Modifying
    @Query(value = "insert into calls(phone_line_id_line, phone_line_destiny_id_line, call_duration, date_call) values (?1,?2,?3,?4);    ", nativeQuery = true)
    @Transactional
    int addCall(String originNumber, String destinyNumber, String duration, Date date);


    @Query(value = "select  phoneDestiny.line_number as number, count(c.phone_line_destiny_id_line) as Cant\n" +
            "from calls as c\n" +
            "join phonelines as ph\n" +
            "on c.phone_line_id_line = ph.id_line\n" +
            "join phonelines as phoneDestiny\n" +
            "on phoneDestiny.id_line = c.phone_line_destiny_id_line\n" +
            "where ph.user_id_user = ?1\n" +
            "group by number \n" +
            "order by cant desc\n" +
            "limit 10", nativeQuery = true)
    List<CustomerCallsCant> getTopCalls(int idUser);


    @Query(value = "select c.date_call as DateCall,c.call_duration as Duration,c.total_price as TotalPrice\n" +
            "from calls as c\n" +
            "join phonelines as ph \n" +
            "on c.phone_line_id_line = ph.id_line\n" +
            "join users as cu\n" +
            "on ph.user_id_user = cu.id_user \n" +
            "where cu.id_user = ?1", nativeQuery = true)
    List<CallsPerUser> getCallsPerUser(int idUser);

}
