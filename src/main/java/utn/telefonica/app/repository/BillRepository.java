package utn.telefonica.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {


}


