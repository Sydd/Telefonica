package utn.telefonica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.PhoneLine;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<PhoneLine,Integer> {

    List<PhoneLine> findByLineNumber(String lineNumber);

}
