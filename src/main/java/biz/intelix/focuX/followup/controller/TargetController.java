package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Target;
import biz.intelix.focuX.followup.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/target")
@CrossOrigin("*")
public class TargetController {

    private TargetService  targetService;
    @Autowired
    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping("/all")
    public List<Target> getAllTarget() {
        return targetService.getAllTarget();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Target> findTargetById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return targetService.findTargetById(id);
    }

    @PostMapping("/save")
    public Target createTarget(@RequestBody Target target)
            throws ParseException {
        return targetService.saveTarget(target);
    }

    @PutMapping("/target/{id}")
    public Target updateTarget(@RequestBody Target newTarget, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return targetService.updateTarget(newTarget, id);
    }

    @PutMapping("/status/{id}")
    public Target updateTargetStatus(@RequestBody Target target, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return targetService.updateTargetStatus(id, target);
    }
}