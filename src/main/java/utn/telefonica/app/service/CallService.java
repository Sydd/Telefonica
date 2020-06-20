package utn.telefonica.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.projections.BillsByCustomer;
import utn.telefonica.app.projections.CallTotals;
import utn.telefonica.app.projections.CustomerCalls;
import utn.telefonica.app.repository.CallRepository;
import utn.telefonica.app.model.Call;
import utn.telefonica.app.utils.PhoneUtils;

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
        List<CallTotals>  aux =  callRepository.getTotalCallsByDate(id_customer,fromDate,toDate);
        return aux;
    }


    //METODO EXPUESTO PARA LA ANTENNA.
    public ResponseEntity addCall(CallDto call){
        try {

            return ResponseEntity.ok(
                    callRepository.addCall(
                            call.getOriginNumber(),
                            call.getDestinyNumber(),
                            Integer.getInteger(call.getCallDuration()),
                            PhoneUtils.dateConverter(call.getCallDate())));

        } catch ( Exception e) { //todo cambiar tipo de exception.
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    //ESTE METODO SOLO SE USA PARA GENERAR LLAMADAS RANDOM PARA TESTEAR.
    //NO ES USADO POR EL SISTEMA Y NO VA A QUEDAR EXPUESTO.
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

    public void addCalls(Call call) {
        callRepository.save(call);
    }


}

