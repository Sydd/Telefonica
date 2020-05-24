package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.projections.StateCant;
import utn.telefonica.app.repository.StateRepository;
import utn.telefonica.app.model.State;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class StateService {

    private final StateRepository stateRepository;


    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public void addState(State state)
    {
        stateRepository.save(state);
    }

    public State getStateById(Integer i)
    {
        return stateRepository.findById(i).get();
    }

    public List<State> getAllStates(String stateName)
    {
        if(isNull(stateName))
        {
            return stateRepository.findAll();
        }
        return  stateRepository.findByStateName(stateName);

    }

    public List<StateCant> getPersonCant()
    {
        return stateRepository.getPersonCant();
    }
}

