package biz.intelix.focuX.followup.controller;


import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.TypeStatus;
import biz.intelix.focuX.followup.service.TypeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/typestatuses")
@CrossOrigin("*")
public class TypeStatusController {

    private TypeStatusService typeStatusService;

    @Autowired
    public TypeStatusController(TypeStatusService typeStatusService) {
        this.typeStatusService = typeStatusService;
    }

    @GetMapping("/all")
    public List<TypeStatus> getAllTypeStatuses() {
        return typeStatusService.getAllTypeStatuses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeStatus> findTypeStatusById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return typeStatusService.findTypeStatusById(id);
    }

    @PostMapping("/save")
    public TypeStatus createTypeStatus(@RequestBody TypeStatus typeStatus)
            throws ParseException {
        return typeStatusService.saveTypeStatus(typeStatus);
    }

    @PutMapping("/typestatus/{id}")
    public TypeStatus updateTypeStatus(@RequestBody TypeStatus newTypeStatus, @PathVariable Integer id)
            throws ParseException {
        return typeStatusService.updateTypeStatus(newTypeStatus, id);
    }

    @PutMapping("/status/{id}")
    public TypeStatus updateTypeStatusStatus(@RequestBody TypeStatus typeStatus, @PathVariable Integer id)
            throws ParseException {
        return typeStatusService.updateTypeStatusStatus(id, typeStatus);
    }
}
