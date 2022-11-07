package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.RequestPeriod;
import biz.intelix.focuX.followup.service.RequestPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/requestPeriod")
@CrossOrigin("*")

public class RequestPeriodController {

    private RequestPeriodService requestPeriodService;

    @Autowired
    public RequestPeriodController(RequestPeriodService requestPeriodService) {
        this.requestPeriodService = requestPeriodService;
    }

    @GetMapping("/all")
    public List<RequestPeriod> getAllRequestPeriod() {
        return requestPeriodService.getAllRequestPeriod();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestPeriod> findRequestPeriodById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return requestPeriodService.findRequestPeriodById(id);
    }

    @PostMapping("/save")
    public RequestPeriod createRequestPeriod(@RequestBody RequestPeriod requestPeriod)
            throws ParseException {
        return requestPeriodService.saveRequestPeriod(requestPeriod);
    }

    @PutMapping("/requestPeriod/{id}")
    public RequestPeriod updateRequestPeriod(@RequestBody RequestPeriod newRequestPeriod, @PathVariable Integer id)
            throws ParseException {
        return requestPeriodService.updateRequestPeriod(newRequestPeriod, id);
    }

    @PutMapping("/status/{id}")
    public RequestPeriod updateRequestPeriodStatus(@RequestBody RequestPeriod requestPeriod, @PathVariable Integer id)
            throws ParseException {
        return requestPeriodService.updateRequestPeriodStatus(id, requestPeriod);
    }

}
