package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.RequestPause;
import biz.intelix.focuX.followup.service.RequestPauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/requestpause")
@CrossOrigin("*")
public class RequestPauseController {

    private RequestPauseService requestPauseService;

    @Autowired
    public RequestPauseController(RequestPauseService requestPauseService) {
        this.requestPauseService = requestPauseService;
    }

    @GetMapping("/all")
    public List<RequestPause> getAllRequestPause() {
        return requestPauseService.getAllRequestPauses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestPause> findRequestPauseByIdFile(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return requestPauseService.findRequestPauseById(id);
    }

    @PostMapping("/save")
    public RequestPause createRequestPause(@RequestBody RequestPause businessType)
            throws ParseException {
        return requestPauseService.saveRequestPause(businessType);
    }

    @PutMapping("/requestpause/{id}")
    public RequestPause updateRequestPause(@RequestBody RequestPause newRequestPause, @PathVariable(value = "id") Long id)
            throws ParseException {
        return requestPauseService.updateRequestPause(newRequestPause, id);
    }
}
