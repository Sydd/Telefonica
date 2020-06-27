package utn.telefonica.app.service;

import com.sun.tools.example.debug.tty.LineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.InvalidPhoneLineException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.repository.LineRepository;
import utn.telefonica.app.model.PhoneLine;

import javax.sound.sampled.Line;
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

    public PhoneLine updatePhonelines(PhoneLine phoneLine) throws InvalidPhoneLineException{

        PhoneLine phonelineAux = lineRepository.findById(phoneLine.getId()).orElseThrow(()-> new InvalidPhoneLineException());

        phonelineAux.setState(phoneLine.isState());

        lineRepository.save(phonelineAux);

        return phonelineAux;
    }

    public String deleteLine(int idLine) throws LineNotFoundException {
        try {
            lineRepository.deleteById(idLine);
            return "Line " + idLine + " deleted.";
        } catch ( EmptyResultDataAccessException E) {
            throw new LineNotFoundException();
        }
    }

}


