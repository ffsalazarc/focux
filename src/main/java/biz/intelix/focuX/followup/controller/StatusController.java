package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Status;
import biz.intelix.focuX.followup.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/statuses")
@CrossOrigin("*")
public class StatusController {

    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/all")
    public List<Status> getAllStatus() {
        return statusService.getAllStatus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> findStatusById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return statusService.findStatusById(id);
    }

    @PostMapping("/save")
    public Status createStatus(@RequestBody Status status)
            throws ParseException {
        return statusService.saveStatus(status);
    }

    @PutMapping("/statuses/{id}")
    public Status updateStatus(@RequestBody Status newStatus, @PathVariable Integer id)
            throws ParseException {
        return statusService.updateStatus(newStatus, id);
    }

    @PutMapping("/status/{id}")
    public Status updateStatusStatus(@RequestBody Status status, @PathVariable Integer id)
            throws ParseException {
        return statusService.updateStatusStatus(id, status);
    }
}
