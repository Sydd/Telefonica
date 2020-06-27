package utn.telefonica.app.controller;

import com.sun.tools.example.debug.tty.LineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.InvalidPhoneLineException;
import utn.telefonica.app.exceptions.UserNotexistException;
import utn.telefonica.app.service.LineService;
import utn.telefonica.app.model.PhoneLine;

import java.util.List;

@RestController
@RequestMapping("/line")
public class LineController{

    private final LineService lineService;

    @Autowired
    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @GetMapping("/{id_line}")
    public PhoneLine getLineById(@PathVariable Integer id_line)
    {
        return lineService.getLineById(id_line);
    }

    @PostMapping("/")
    public void addLine(@RequestBody PhoneLine line)
    {
        lineService.addLine(line);
    }

    @GetMapping("/")
    public List<PhoneLine> getAll (@RequestParam(required = false) String lineNumber)
    {
        return lineService.getAllLines(lineNumber);
    }

    @PutMapping("/")
    public ResponseEntity updatePhoneLine(@RequestBody PhoneLine phoneLine) {
        try {
            return ResponseEntity.ok(lineService.updatePhonelines(phoneLine));
        } catch (InvalidPhoneLineException P)
        {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id_line}")
    public ResponseEntity deleteLineById(@PathVariable Integer id_Line){
        try {

            lineService.deleteLine(id_Line);
            return ResponseEntity.noContent().build();

        } catch (LineNotFoundException E) {

            return ResponseEntity.notFound().build();

        }
    }


}
