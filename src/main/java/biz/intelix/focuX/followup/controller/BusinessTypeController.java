package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.BusinessType;
import biz.intelix.focuX.followup.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/businesstype")
@CrossOrigin("*")
public class BusinessTypeController {

    private BusinessTypeService businessTypeService;

    @Autowired
    public BusinessTypeController(BusinessTypeService businessTypeService) {
        this.businessTypeService = businessTypeService;
    }

    @GetMapping("/all")
    public List<BusinessType> getAllBusinessType() {
        return businessTypeService.getAllBusinessTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessType> findBusinessTypeByIdFile(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return businessTypeService.findBusinessTypeById(id);
    }

    @PostMapping("/save")
    public BusinessType createBusinessType(@RequestBody BusinessType businessType)
            throws ParseException {
        return businessTypeService.saveBusinessType(businessType);
    }

    @PutMapping("/businesstype/{id}")
    public BusinessType updateBusinessType(@RequestBody BusinessType newBusinessType, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return businessTypeService.updateBusinessType(newBusinessType, id);
    }

    @PutMapping("/status/{id}")
    public BusinessType updateBusinessTypeStatus(@RequestBody BusinessType businessType, @PathVariable(value = "id") Integer id)
            throws ParseException {
        return businessTypeService.updateBusinessTypeStatus(id, businessType);
    }
}
