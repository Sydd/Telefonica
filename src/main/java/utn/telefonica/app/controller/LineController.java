package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.exceptions.InvalidPhoneLineException;
import utn.telefonica.app.exceptions.LineNotFoundException;
import utn.telefonica.app.service.LineService;
import utn.telefonica.app.model.PhoneLine;
import utn.telefonica.app.utils.PhoneUtils;

import javax.persistence.NonUniqueResultException;
import java.util.List;

@RestController
@RequestMapping("/")
public class LineController{

    private final LineService lineService;

    @Autowired
    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @GetMapping("backoffice/phonelines/{id_line}")
    public ResponseEntity<PhoneLine> getLineById(@PathVariable Integer id_line)
    {
        try {

            return ResponseEntity.ok(lineService.getLineById(id_line));

        } catch (LineNotFoundException E){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("backoffice/phonelines/")
    public ResponseEntity<PhoneLine> addLine(@RequestBody PhoneLine line)
    {
        try {
            return ResponseEntity.created(PhoneUtils.getLocation(lineService.addLine(line))).build();
        } catch (NonUniqueResultException E) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("backoffice/phonelines/")
    public List<PhoneLine> getAll (@RequestParam(required = false) String lineNumber)
    {
        return lineService.getAllLines(lineNumber);
    }

    @PutMapping("backoffice/phonelines/")
    public ResponseEntity updatePhoneLine(@RequestBody PhoneLine phoneLine) {
        try {
            return ResponseEntity.ok(lineService.updatePhonelines(phoneLine));
        } catch (InvalidPhoneLineException P) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("backoffice/phonelines/{id_line}")
    public ResponseEntity deleteLineById(@PathVariable Integer id_Line){
        try {

            lineService.deleteLine(id_Line);

            return ResponseEntity.noContent().build();

        } catch (LineNotFoundException E) {

            return ResponseEntity.notFound().build();

        }
    }


}
