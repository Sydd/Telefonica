package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Service;
import utn.telefonica.app.projections.CallTotals;
import utn.telefonica.app.projections.CustomerCalls;
import utn.telefonica.app.repository.CallRepository;
import utn.telefonica.app.model.Call;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CallService {
    private final CallRepository callRepository;

    @Autowired
    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public List<CallTotals> getTotalCallsById(Integer id_customer, Date fromDate, Date toDate) {
        System.out.println(id_customer);
        List<CallTotals>   aux =  callRepository.getTotalCallsByDate(id_customer,fromDate,toDate);
        return aux;

    }

    public void addCalls(List<Call> calls)
    {

        //RANDOMIZO FECHAS PARA QUE TENGAN AL AZAR

        Random rn = new Random();


        for(Call call:calls) {

            int answer = rn.nextInt(10) - 10; // randomizo un numero entre 0 y -10

            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.MONTH, answer);

            call.setTotalPrice(answer);

            call.setCallDate(calendar.getTime());

        }
        callRepository.saveAll(calls);
    }
    public Call getCallById(Integer i)
    {
        return callRepository.findById(i).get();
    }


}

