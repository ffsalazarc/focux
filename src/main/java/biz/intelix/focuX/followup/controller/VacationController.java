package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Vacation;
import biz.intelix.focuX.followup.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/vacations")
@CrossOrigin("*")
public class VacationController {

    private VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/all")
    public List<Vacation> getAllVacation() {
        return vacationService.getAllVacations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacation> findVacationByIdCollaborator(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return vacationService.findVacationByIdCollaborator(id);
    }

    @PostMapping("/save")
    public Vacation createVacation(@RequestBody Vacation vacation)
            throws ParseException {
        return vacationService.saveVacation(vacation);
    }

    @PutMapping("/vacation/{id}")
    public Vacation updateVacation(@RequestBody Vacation newVacation, @PathVariable Integer id)
            throws ParseException {
        return vacationService.updateVacation(newVacation, id);
    }

    @PutMapping("/status/{id}")
    public Vacation updateVacationStatus(@RequestBody Vacation vacation, @PathVariable Integer id)
            throws ParseException {
        return vacationService.updateVacationStatus(id, vacation);
    }
}
