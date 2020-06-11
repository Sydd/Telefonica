package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.dto.CallDto;
import utn.telefonica.app.model.City;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.model.Call;

import java.util.List;

@RestController
@RequestMapping("/antenna/call")
public class CallController {
    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }


    @PostMapping("/")
    public void addCall(@RequestBody Call call)
    {
        callService.addCall(call);
    }


}
