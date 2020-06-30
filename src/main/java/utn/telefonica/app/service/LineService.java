package utn.telefonica.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import utn.telefonica.app.exceptions.InvalidPhoneLineException;
import utn.telefonica.app.exceptions.LineNotFoundException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.repository.LineRepository;
import utn.telefonica.app.model.PhoneLine;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class LineService {
    private final LineRepository lineRepository;

    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public PhoneLine addLine(PhoneLine line)
    {
        return lineRepository.save(line);
    }

    public PhoneLine getLineById(Integer Id) throws LineNotFoundException
    {
        return lineRepository.findById(Id).orElseThrow(()-> new LineNotFoundException());
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

            Optional.ofNullable(lineRepository.findById(idLine)).orElseThrow(()-> new EmptyResultDataAccessException("asd",1));
            lineRepository.deleteById(idLine);
            return "Line " + idLine + " deleted.";
        } catch ( EmptyResultDataAccessException E) {
            throw new LineNotFoundException();
        }
    }

}


