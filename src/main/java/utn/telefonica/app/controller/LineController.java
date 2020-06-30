package utn.telefonica.app.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value="Get phoneline by id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Phoneline is returned"),
            @ApiResponse(code = 404, message = "Trouble finding your phoneline")})
    public ResponseEntity<PhoneLine> getLineById(@PathVariable Integer id_line)
    {
        try {

            return ResponseEntity.ok(lineService.getLineById(id_line));

        } catch (LineNotFoundException E){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("backoffice/phonelines/")
    @ApiOperation(value="Add new phoneline")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Phoneline created"),
            @ApiResponse(code = 400, message = "Phoneline is already exists")})
    public ResponseEntity<PhoneLine> addLine(@RequestBody PhoneLine line)
    {
        try {
            return ResponseEntity.created(PhoneUtils.getLocation(lineService.addLine(line))).build();
        } catch (NonUniqueResultException E) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("backoffice/phonelines/")
    @ApiOperation(value="Get all lines")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "List of returned phone lines")})
    public List<PhoneLine> getAll (@RequestParam(required = false) String lineNumber)
    {
        return lineService.getAllLines(lineNumber);
    }

    @PutMapping("backoffice/phonelines/")
    @ApiOperation(value="Update phoneline")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "phone line is updated"),
            @ApiResponse(code = 400, message = "The requested line number does not exist")})
    public ResponseEntity updatePhoneLine(@RequestBody PhoneLine phoneLine) {
        try {
            return ResponseEntity.ok(lineService.updatePhonelines(phoneLine));
        } catch (InvalidPhoneLineException P) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("backoffice/phonelines/{id_Line}")
    @ApiOperation(value="Delete phoneline by id")
    @ApiResponses( value = {
            @ApiResponse(code = 204, message = "Phoneline is deleted"),
            @ApiResponse(code = 404, message = "Trouble finding your phoneline")})
    public ResponseEntity deleteLineById(@PathVariable Integer id_Line){
        try {

            lineService.deleteLine(id_Line);

            return ResponseEntity.noContent().build();

        } catch (LineNotFoundException E) {

            return ResponseEntity.notFound().build();

        }
    }


}
