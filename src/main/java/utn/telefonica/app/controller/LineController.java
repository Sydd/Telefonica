package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.service.LineService;
import utn.telefonica.app.model.Line;

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
    public Line getLineById(@PathVariable Integer id_line)
    {
        return lineService.getLineById(id_line);
    }

    @PostMapping("/")
    public void addLine(@RequestBody Line line)
    {
        lineService.addLine(line);
    }

    @GetMapping("/")
    public List<Line> getAll (@RequestParam(required = false) String lineNumber)
    {
        return lineService.getAllLines(lineNumber);
    }


}
