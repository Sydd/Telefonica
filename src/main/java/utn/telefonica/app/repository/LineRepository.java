package utn.telefonica.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.telefonica.app.model.Line;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line,Integer> {

    List<Line> findByLineNumber(String lineNumber);

}
