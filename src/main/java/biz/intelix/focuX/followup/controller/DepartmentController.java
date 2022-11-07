package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Department;
import biz.intelix.focuX.followup.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/departments")
@CrossOrigin("*")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return departmentService.findDepartmentById(id);
    }

    @PostMapping("/save")
    public Department createDepartment(@RequestBody Department commercialArea)
            throws ParseException {
        return departmentService.saveDepartment(commercialArea);
    }

    @PutMapping("/department/{id}")
    public Department updateDepartment(@RequestBody Department newDepartment, @PathVariable Integer id)
            throws ParseException {
        return departmentService.updateDepartment(newDepartment, id);
    }

    @PutMapping("/status/{id}")
    public Department updateDepartmentStatus(@RequestBody Department commercialArea, @PathVariable Integer id)
            throws ParseException {
        return departmentService.updateDepartmentStatus(id, commercialArea);
    }
}
