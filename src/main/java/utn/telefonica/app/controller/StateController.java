package utn.telefonica.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.Projections.StateCant;
import utn.telefonica.app.Service.StateService;
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

