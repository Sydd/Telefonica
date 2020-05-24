package utn.telefonica.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.Repository.LineRepository;
import utn.telefonica.app.model.Line;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class LineService {
    private final LineRepository lineRepository;

    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public void addLine(Line line)
    {
        lineRepository.save(line);
    }

    public Line getLineById(Integer Id)
    {
        return lineRepository.findById(Id).get();
    }

    public List<Line> getAllLines(String lineNumber) {
        if(isNull(lineNumber))
        {
            return lineRepository.findAll();
        }
        return lineRepository.findByLineNumber(lineNumber);
    }
}


