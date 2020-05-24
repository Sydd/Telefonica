package utn.telefonica.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Call;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {
}
