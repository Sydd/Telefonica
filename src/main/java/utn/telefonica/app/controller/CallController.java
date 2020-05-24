package utn.telefonica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.telefonica.app.service.CallService;
import utn.telefonica.app.model.Call;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/{id}")
    public Call getCallById(@PathVariable Integer id)
    {
        return callService.getCallById(id);
    }

    @PostMapping("/")
    public void AddCall(@RequestBody Call call)
    {
        callService.addCall(call);
    }
}
