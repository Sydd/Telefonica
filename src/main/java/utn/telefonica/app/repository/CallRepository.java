package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Call;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {

    public List<Call> findByphoneLine(Integer id);
}
