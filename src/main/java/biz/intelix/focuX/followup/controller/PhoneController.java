package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Phone;
import biz.intelix.focuX.followup.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/phones")
@CrossOrigin("*")
public class PhoneController {

    private PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/all")
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phone> findPhoneById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return phoneService.findPhoneById(id);
    }

    @PostMapping("/save")
    public Phone createPhone(@RequestBody Phone phone)
            throws ParseException {
        return phoneService.savePhone(phone);
    }

    @PutMapping("/department/{id}")
    public Phone updatePhone(@RequestBody Phone newPhone, @PathVariable(value = "id") Integer id) throws ParseException {
        return phoneService.updatePhone(newPhone, id);
    }

    @PutMapping("/status/{id}")
    public Phone updatePhoneStatus(@RequestBody Phone phone, @PathVariable(value = "id") Integer id) throws ParseException {
        return phoneService.updatePhoneStatus(id, phone);
    }
}
