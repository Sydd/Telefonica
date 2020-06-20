package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.model.Call;

import java.util.List;

@RestController
@RequestMapping("/")
public class CallController {
    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }


    //ONLY FOR TESTING.

    @PostMapping("/testing/")
    public void addCalls(@RequestBody List<Call> calls)
    {
        callService.addCalls(calls);
    }

    @PostMapping("/antenna/call")
    public ResponseEntity addCall(@RequestBody CallDto call)
    {
        return callService.addCall(call);
    }


}
