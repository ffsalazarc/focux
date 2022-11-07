package biz.intelix.focuX.followup.controller;


import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.TypeRequest;
import biz.intelix.focuX.followup.service.TypeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/typerequests")
@CrossOrigin("*")
public class TypeRequestController {

    private TypeRequestService typeRequestService;

    @Autowired
    public TypeRequestController(TypeRequestService typeRequestService) {
        this.typeRequestService = typeRequestService;
    }

    @GetMapping("/all")
    public List<TypeRequest> getAllTypeRequests() {
        return typeRequestService.getAllTypeRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeRequest> findTypeRequestById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return typeRequestService.findTypeRequestById(id);
    }

    @PostMapping("/save")
    public TypeRequest createTypeRequest(@RequestBody TypeRequest typeRequest)
            throws ParseException {
        return typeRequestService.saveTypeRequest(typeRequest);
    }

    @PutMapping("/typerequest/{id}")
    public TypeRequest updateTypeRequest(@RequestBody TypeRequest newTypeRequest, @PathVariable Integer id)
            throws ParseException {
        return typeRequestService.updateTypeRequest(newTypeRequest, id);
    }

    @PutMapping("/status/{id}")
    public TypeRequest updateTypeRequestStatus(@RequestBody TypeRequest typeRequest, @PathVariable Integer id)
            throws ParseException {
        return typeRequestService.updateTypeRequestStatus(id, typeRequest);
    }
}
