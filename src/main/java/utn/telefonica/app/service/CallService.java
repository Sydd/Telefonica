package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.telefonica.app.repository.CallRepository;
import utn.telefonica.app.model.Call;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CallService {
    private final CallRepository callRepository;

    @Autowired
    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public void addCalls(List<Call> calls)
    {

        //RANDOMIZO FECHAS PARA QUE TENGAN AL AZAR
        Calendar calendar = Calendar.getInstance();

        for(Call call:calls) {

            calendar.add(Calendar.MONTH, -3);

            call.setCallDate(calendar.getTime());
        }
        callRepository.saveAll(calls);
    }
    public Call getCallById(Integer i)
    {
        return callRepository.findById(i).get();
    }
}

