package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.repository.LineRepository;
import utn.telefonica.app.model.PhoneLine;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class LineService {
    private final LineRepository lineRepository;

    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public void addLine(PhoneLine line)
    {
        lineRepository.save(line);
    }

    public PhoneLine getLineById(Integer Id)
    {
        return lineRepository.findById(Id).get();
    }

    public List<PhoneLine> getAllLines(String lineNumber) {
        if(isNull(lineNumber))
        {
            return lineRepository.findAll();
        }
        return lineRepository.findByLineNumber(lineNumber);
    }
}


