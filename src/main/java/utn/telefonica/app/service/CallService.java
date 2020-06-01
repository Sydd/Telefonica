package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.model.Customer;
import utn.telefonica.app.repository.CallRepository;
import utn.telefonica.app.model.Call;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CallService {
    private final CallRepository callRepository;

    @Autowired
    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public void addCall(Call call)
    {
        callRepository.save(call);
    }

    public Call getCallById(Integer i)
    {
        return callRepository.findById(i).get();
    }

    public List<Call> getAllCities() {
            return callRepository.findAll();
        }

}

