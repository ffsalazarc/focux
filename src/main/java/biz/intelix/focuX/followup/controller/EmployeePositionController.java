package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.EmployeePosition;
import biz.intelix.focuX.followup.service.EmployeePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/employeePosition")
@CrossOrigin("*")
public class EmployeePositionController {

    private EmployeePositionService employeePositionService;

    @Autowired
    public EmployeePositionController(EmployeePositionService employeePositionService) {
        this.employeePositionService = employeePositionService;
    }

    @GetMapping("/all")
    public List<EmployeePosition> getAllEmployeePositions() {
        return employeePositionService.getAllEmployeePositions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeePosition> findEmployeePositionById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return employeePositionService.findEmployeePositionById(id);
    }

    @PostMapping("/save")
    public EmployeePosition createEmployeePosition(@RequestBody EmployeePosition employeePosition)
            throws ParseException {
        return employeePositionService.saveEmployeePosition(employeePosition);
    }

    @PutMapping("/employeePosition/{id}")
    public EmployeePosition updateEmployeePosition(@RequestBody EmployeePosition newEmployeePosition, @PathVariable Integer id)
            throws ParseException {
        return employeePositionService.updateEmployeePosition(newEmployeePosition, id);
    }

    @PutMapping("/status/{id}")
    public EmployeePosition updateEmployeePositionStatus(@RequestBody EmployeePosition employeePosition, @PathVariable Integer id)
            throws ParseException {
        return employeePositionService.updateEmployeePositionStatus(id, employeePosition);
    }
}
