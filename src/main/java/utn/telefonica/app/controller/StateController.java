package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.projections.StateCant;
import utn.telefonica.app.service.StateService;
import utn.telefonica.app.model.State;

import java.util.List;
@RestController
@RequestMapping("/state")
public class StateController {
    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/{id}")
    public State getStateById(@PathVariable Integer id)
    {
        return stateService.getStateById(id);
    }

    @PostMapping("/")
    public void addState(@RequestBody State state)
    {
        stateService.addState(state);
    }

    @GetMapping("/")
    public List<State> getAll(@RequestParam(required = false) String stateName){
        return stateService.getAllStates(stateName);
    }

    @GetMapping("/projection")
    public List<StateCant> getStateCant()
    {
        return stateService.getPersonCant();
    }
}

